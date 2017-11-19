<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
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
        $('#dlg').dialog('close');
        $('#deleRole').dialog('close');
        var datagrid ;  //定义全局变量
        datagrid = $("#dataGrid").datagrid({
            width:"80%",
            title:"管理员人员管理",
            border:false,
            locale:"zh_CN",
            iconCls:'icon-save',
            striped:true,
            fitColumns:true,
            idField:"bid",
            sortOrder:"desc",
            collapsible:"false",
            url:"/doFoundAdmins",
            singleSelect:false,//是否单选
            pagination:true,//分页控件
            pageList: [5,10],//可以设置每页记录条数的列表
            columns: [[
                { field: 'userid', title: 'ID',width:10, algin: 'center' },
                { field: 'username', title: '姓名', width:20,align: 'center' },
                { field: 'usertype', title: '管理员部门',width:20, align: 'center' },
                { field: 'usertel', title: '管理员电话',width:20, align: 'center' },
                { field: 'useremail', title: '管理员邮箱',width:20, align: 'center' },
                { field: 'rolename', title: '角色名称', width:20,align: 'center' },
            ]],
            toolbar:[{
                id: 'btnAdd',
                text: "添加",
                iconCls: 'icon-add',
                handler: function () {
                    var row = $('#dataGrid').datagrid('getSelected');
                    if(row==null){
                        $.messager.confirm('提示','请选择一个管理员!');
                        return;
                    }
                    if (row){
                        $.ajax({
                            type:"post",
                            url:"/douserInfoByUserId?userid="+row.userid,
                            success:function (data) {
                                $('#userid').val(data.userid);
                            }
                        })
                        $('#addadmin').combobox({
                            url:'/findAllRoles',
                            editable:false,
                            valueField:'id',
                            textField:'rolename'
                            /*required:true,
                             multiple:true,
                             disabled:true,*/
                        });
                    }
                    $('#dlg').dialog('open');
                }
            },{
                id:'btnSearch',
                iconCls:'icon-search',
                text:'修改',
                handler:function () {
                    $.messager.confirm('提示','功能暂未开通!');
                }
            },{
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    var row = $('#dataGrid').datagrid('getSelected');
                    if(row==null){
                        $.messager.confirm('提示','请选择一个管理员!');
                        return;
                    }
                    if (row){
                        alert("ID : "+row.userid);
                        $.ajax({
                            type:"post",
                            url:"/douserInfoByUserId?userid="+row.userid,
                            success:function (data) {
                                $('#deleuserid').val(data.userid);
                            }
                        })
                        $('#deleadmin').combobox({
                            url:'/findAllRolesByUid?userid='+row.userid,
                            editable:false,
                            valueField:'id',
                            textField:'rolename'
                        })
                    }
                    $('#deleRole').dialog('open');
                }
            }],
        });
        //下拉框不可编辑 并且填充角色下拉框


    });

    //添加角色
    function commitAddRole() {
        $.ajax({
            type:"post",
            url:"/checkUserRoleIsExist",
            data:{"userid":$("#userid").val(),"roleid":$("#addadmin").combobox("getValue")},
            success:function (data) {
                alert(data);
                if(data=="f"){
                    $.messager.confirm("提示","该角色已经存在！");
                }else{
                    $.ajax({
                        type:"post",
                        url:"/addRoleForUser",
                        data:{"userid":$("#userid").val(),"roleid":$("#addadmin").combobox("getValue")},
                        success:function (data) {
                            if(data=='y'){
                                window.location.href="/addAdmin.jsp";
                            }
                        }
                    })
                }
            }
        })
    }
    //删除角色
    function commitDeleRole() {
        $.ajax({
            type:"post",
            url:"/deleRoleForUser",
            data:{"userid":$("#deleuserid").val(),"roleid":$("#deleadmin").combobox("getValue")},
            success:function (data) {
                if(data=='y'){
                    window.location.href="/addAdmin.jsp";
                }
            }
        })
    }

</script>
</head>
<body>
<div style="position: absolute;width: 100%;height: 100%;z-index: -1;left: 0;top: 0">
            <table  id="dataGrid" style="width:100%"></table>
           <%--添加角色弹框--%>
            <div id="dlg" class="easyui-dialog" title="角色分配" data-options="iconCls:'icon-save'" style="width:350px;height:180px;padding:10px" >
                <label><h3 style="margin-left: 130px">角色管理</h3></label>
                <form id="ff" com.action="" method="post">
                    <input id="userid" type="text" name="userid" style="display: none"/>
                    <table cellpadding="5">
                        <tr>
                            <td>添加角色:</td>
                            <td><select id="addadmin" class="easyui-combobox" name="rolename" style="width: 200px">
                                <option value="请选择"></option>
                            </select></td>
                        </tr>
                        <tr>
                            <td><input type="button" value="添加" onclick="commitAddRole()"></td>
                        </tr>
                    </table>
                </form>
            </div>

              <%--删除角色dialog--%>
            <div id="deleRole" class="easyui-dialog" title="角色分配" data-options="iconCls:'icon-save'" style="width:350px;height:180px;padding:10px" >
                    <label><h3 style="margin-left: 130px">角色管理</h3></label>
                    <form id="dd" com.action="" method="post">
                            <input id="deleuserid" type="text" name="deleuserid" style="display: none"/>
                        <table cellpadding="5">
                            <tr>
                                <td>删除角色:</td>
                                <td><select id="deleadmin" class="easyui-combobox" name="rolename" style="width: 200px">
                                    <option value="请选择"></option>
                                </select></td>
                            </tr>
                            <tr>
                                <td><input type="button" value="删除" onclick="commitDeleRole()"></td>
                            </tr>
                        </table>

                    </form>
            </div>


</div>


</body>
</html>