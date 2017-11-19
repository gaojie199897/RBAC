<%--
  Created by IntelliJ IDEA.
  User: Mr.Gao
  Date: 2017/11/12
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="layer-v3.1.0/layer/mobile/layer.js"></script>
<head>
    <title>Title</title>
</head>
<script type="text/javascript">
    $(function () {
        $("#tt").tree({
            url: '${pageContext.request.contextPath}/selectallChinaMath',
            animate:true,
            checkbox:true,
            state:closed,
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

            }
        });
    });
    //使用递归 添加tree树
    function change(data){
        if(!data.length){
            data.text=data.columnname;
            data.id=data.syscode;
            data.state="'closed'";
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
<body>
<ul id="tt"></ul>
</body>
</html>
