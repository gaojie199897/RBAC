<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://www.springsecurity.org/jsp" prefix="se"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教学管理系统-添加管理员界面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    $(function () {
        $('#addprivlg').dialog('close');
        $('#delePrivDiv').dialog('close');
        var datagrid ;  //定义全局变量
        datagrid = $("#dataGrid").datagrid({
            width:"80%",
            title:"管理员人员管理",
            iconCls:'icon-save',
            striped:true,
            fitColumns:true,
            idField:"bid",
            sortOrder:"desc",
            collapsible:"false",
            url:"/findAllRoles",
            singleSelect:true,//是否单选
            pagination:true,//分页控件
            pageList: [5, 10,15,20],//可以设置每页记录条数的列表
            columns: [[
                { field: 'id', title: 'ID',width:12, algin: 'center' },
                { field: 'rolename', title: '角色名称',width:12, align: 'center' },
                { field: 'privname', title: '已拥有权限',width:12, align: 'center' },

            ]],
            toolbar:[{
                id: 'btnAdd',
                text: "添加",
                iconCls: 'icon-add',
                handler: function () {
                    var row = $('#dataGrid').datagrid('getSelected');
                    if(row==null){
                        $.messager.confirm('提示','请选择一个角色!');
                        return;
                    }
                    if (row){
                        $("#myroleid").val(row.id);
                        $("#tt").tree({
                            url: '${pageContext.request.contextPath}/getAllMenusJson',
                            animate:true,
                            checkbox:true,
                            cascadeCheck:false,
                            loadFilter :
                                function(data) {
                                    change(data);
                                    //图标的设定
                                    $.each(data,function (i,v){
                                        v.iconCls="icon-ok";
                                    });
                                    return data;
                                },
                            onLoadSuccess:function () {
                                $.ajax({
                                    type:"post",
                                    url:"/findAddPrivByRoleid",
                                    data:{"roleid":row.id},
                                    success:function (data) {
                                         $.each(data,function (i,dom) {
                                             var node = $('#tt').tree('find',dom.id);//找到id为”tt“这个树的节点id为”1“的对象
                                             $('#tt').tree('check', node.target);
                                         })
                                    }
                                })
                            }
                        });
                    }
                    $('#addprivlg').dialog('open');
                }
            },{
                id:'btnSearch',
                iconCls:'icon-search',
                text:'查询',
                handler:function () {
                    alert("查询");
                }
            },{
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    var row = $('#dataGrid').datagrid('getSelected');
                    if(row==null){
                        $.messager.confirm('提示','请选择一个角色!');
                        return;
                    }
                    if (row){
                        $("#delroleid").val(row.id);
                        $("#dd").tree({
                            url: '${pageContext.request.contextPath}/forcmbByPriv?id='+row.id,
                            animate:true,
                            checkbox:true,
                            cascadeCheck:false,
                            loadFilter :
                                function(data) {
                                    change(data);
                                    //图标的设定
                                    $.each(data,function (i,v){
                                        v.iconCls="icon-ok";
                                    });
                                    return data;
                                }
                        });
                    }
                    $('#delePrivDiv').dialog('open');
                }
            }],
        });
    });

    //使用递归 添加tree树
    function change(data){
        if(!data.length){
            data.text=data.name;
            if(data.children){
                change(data.children);
            }
        }else{
            $.each(data,function (i,v){
                change(v);
            });
        }
    }
    //保存权限
    function getChecked(){
        var nodes = $('#tt').tree('getChecked');
        var s = '';
        for(var i=0; i<nodes.length; i++){
            if (s != '') s += ',';
            s += nodes[i].id;
        }
        $.ajax({
            type:"post",
            url:"/doAddPrivForTree",
            data:{"roleid":$('#myroleid').val(),"privalegeId":s},
            success:function (data) {
                if(data=='y'){
                    $.messager.alert("提示","添加成功！");
                    window.location.href="/addRole.jsp";
                }
            }
        })
    }

    //删除权限
    function commitDelePriv() {
        var nodes = $('#dd').tree('getChecked');
        var s = '';
        for(var i=0; i<nodes.length; i++){
            if (s != '') s += ',';
            s += nodes[i].id;
        }
        $.ajax({
            type:"post",
            url:"/delePriv",
            data:{"roleId":$("#delroleid").val(),"privalegeId":s},
            success:function (data) {
                if(data=='y'){
                    window.location.href="/addRole.jsp";
                }
            }
        })

    }

</script>
</head>
<body>


<se:authorize URL="/addAdmin11.jsp">
    <a>xxxxxx</a>
</se:authorize>
<div style="position: absolute;width: 100%;height: 100%;z-index: -1;left: 0;top: 0">
            <table  id="dataGrid" style="width:100%"></table>

    <%--添加权限弹框--%>
    <div id="addprivlg" class="easyui-dialog" title="角色分配" data-options="iconCls:'icon-save'" style="width:350px;height:300px;padding:10px" >
        <label><h3 style="margin-left: 130px">权限分配</h3></label>
        <input type="text" id="myroleid" style="display: none">
        分配权限：<a href="#" class="easyui-linkbutton" onclick="getChecked()">保存权限</a>
        <ul id="tt"></ul>
    </div>


    <%--删除权限dialog--%>
    <div id="delePrivDiv" class="easyui-dialog" title="角色分配" data-options="iconCls:'icon-save'" style="width:350px;height:180px;padding:10px" >
        <label><h3 style="margin-left: 130px">权限分配</h3></label>
        <input type="text" id="delroleid" style="display: none">
        分配权限：<a href="#" class="easyui-linkbutton" onclick="commitDelePriv()">删除权限</a>
        <ul id="dd"></ul>
     <%--   <form id="dd" com.action="" method="post">
            <input id="deleRoleid" type="text" name="deleRoleid" style="display: none"/>
            <table cellpadding="5">
                <tr>
                    <td>删除权限:</td>
                    <td><select id="delePrivCmb" class="easyui-combobox" style="width: 200px">
                        <option value="请选择"></option>
                    </select></td>
                </tr>
                <tr>
                    <td><input type="button" value="删除" onclick="commitDelePriv()"></td>
                </tr>
            </table>

        </form>--%>
    </div>


</div>
</body>
</html>