<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="security" uri="http://www.springsecurity.org/jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/bootstrap.min.css-v=3.3.5.css">
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jQuery1.11.1.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jQuery1.11.1.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var selects;
        function openTab(text,url,iconCls) {
            if (parent.$("#tabs").tabs("exists", text)) {
                parent.$("#tabs").tabs("select", text);
            } else {
                var content = "<iframe frameborder=0 scrolling='scroll' style='width:100%;height:100%' src='" + url + "'></iframe>";
                parent.$("#tabs").tabs("add", {
                    title: text,
                    iconCls: iconCls,
                    closable: true,
                    content: content
                });
            }
        }
        $(function () {
            var checkNode=$("#tt").tree('getChecked');
            $("#tt").tree({
                url: '${pageContext.request.contextPath}/selectallChinaMath',
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
                onClick:function (checkNode) {
                    $("#topsearch").show();
                    selects=$("#tt").tree('getSelected');
                    showcontent();

                },
            });
            $("#selectmore").click(function () {
                $("#content").panel({title:selects.text});
                var id=$("#radios:checked").val();
                var name=$("#c_name").val();
                alert(name)
                $('#ths').datagrid({
                    url:'${pageContext.request.contextPath}/selectList?cm_syscode='+selects.id+"&id="+id+"&name="+name,
                    pagination:true,
                    pageList:[2,3,5,10],
                    columns:[[
                        {field:'newtitle',title:'标题',width:90},
                        {field:'chinamath',title:'栏目',width:223,formatter:function (val,rows,rowIndex) {
                            return val.columnname;
                        }},
                        {field:'clickcount',title:'点击数',width:100},
                        {field:'userInfo',title:'创建者',width:117,formatter:function (val,rows,rowIndex) {
                            return val.username;
                        }},
                        {field:'typesss',title:'状态',width:100},
                        {field:'date',title:'更新时间',width:100,align:'right'},
                    ]]

                });
            });
        });
        function showcontent(){

            $('#ths').datagrid({
                url:'${pageContext.request.contextPath}/selectList?syscode='+selects.id,
                pagination:true,
                pageList:[2,3,5,10],
                toolbar:[{
                id: 'btnAdd',
                text: "添加",
                iconCls: 'icon-add',
                handler: function () {
                    openTab("国内新增","/addColumnDetail.jsp")
                    //window.location.href="/addColumnDetail.jsp";
                }
            },{
                id:'btnSearch',
                iconCls:'icon-search',
                text:'修改',
                handler:function () {
                    var row = $('#ths').datagrid('getSelected');
                    if(row==null){
                        $.messager.confirm('提示','请选择一个条新闻!');
                        return;
                    }else{
                        openTab("修改新闻","/addColumnDetail.jsp?sys_id="+row.id);
                    }

                }
            },{
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    var row = $('#ths').datagrid('getSelected');
                    if(row==null){
                        $.messager.confirm('提示','请选择一个条新闻!');
                        return;
                    }

                    $.ajax({
                        type:"post",
                        url:"/deleNews",
                        data:{"id":row.id},
                        success:function (data) {
                            if(data=="y"){
                                $("#dataGrid").datagrid("load",{
                                    "cname":$("#c_name").val(),
                                    "state":$("input[name='state']:checked").val()
                                })
                            }
                        }
                    });

                }
            }],

                columns:[[
                        {field:'id',title:'ID',width:90},
                       {field:'newtitle',title:'标题',width:90},
                       {field:'chinamath',title:'栏目',width:223,formatter:function (val,rows,rowIndex) {
                           return val.columnname;
                       }},
                       {field:'clickcount',title:'点击数',width:100},
                       {field:'userInfo',title:'创建者',width:117,formatter:function (val,rows,rowIndex) {
                           return val.username;
                       }},
                       {field:'typesss',title:'状态',width:100},
                       {field:'date',title:'更新时间',width:100,align:'right'},
                       {field:'date',title:'操作',width:100,align:'right'},

                ]]
            });
        }

        //使用递归 添加tree树
        function change(data){
            if(!data.length){
                data.text=data.columnname;
                data.id=data.syscode;
                if(data.children){
                    change(data.children);
                }
            }else{
                $.each(data,function (i,v){
                    change(v);
                });
            }
        }
    </script>
</head>
<body style="margin: 1px">

<div class="easyui-layout" style="width:1333px;height:630px;">
        <div region="west" split="true"  style="width:200px;">
            <ul id="tt"></ul>
        </div>
    <div id="content" region="center" style="padding:60px;">
        <%--顶部搜索框--%>
        <div style="display:none; width: 100%;height: 50px;border: 1px #30ffea solid;background-color: #FEFEFE" id="topsearch">
            <form action="" method="post" style="margin-left: 50px">
                <div style="margin-top:10px">
                    <span style="font-size: 14px">标题：</span>
                    <input class="easyui-textbox" id="c_name"  name="columnname" style="width: 20%;height: 32px">
                    <a class="btn btn-primary btn-rounded" id="selectmore"  style="height: 32px">查询</a>
                    <a class="btn btn-default btn-rounded"  style="height: 32px " onclick="Reset()">重置</a>
                    <span style="font-size: 14px">状态：</span>
                    <c:forEach items="${combobox}" var="item">
                        <input id="radios" type="radio" name="state" value="${item.tid}"> <span style="font-size: 14px">${item.tname}：</span>
                    </c:forEach>
                </div>
            </form>
        </div>
        <table id="ths"></table>
    </div>
</div>
</body>
</html>