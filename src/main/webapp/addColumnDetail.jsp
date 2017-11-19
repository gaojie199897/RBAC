<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<%@taglib uri="http://www.springsecurity.org/jsp" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增文章</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="layer-v3.1.0/layer/mobile/layer.js"></script>
    <script type="text/javascript" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/ueditor/ueditor.all.js"></script>
    <link rel="stylesheet" href="/ueditor/themes/default/css/ueditor.css">
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
  <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    $(function () {
        var ue=UE.getEditor('context');
        $.ajax({
            url:"${pageContext.request.contextPath}/UpdateSelect",
            type:"post",
            data:{"id":$("#sys_id").val()},
            success:function (data) {
                ue.ready(function () {
                    ue.setContent(data.context);
                });
                $("#sys_id").val(data.id);
                $("#mycolumn").combobox("setValue",data.syscode);
                $("#newtitle").val(data.newtitle);
                $("#color").val(data.color);
                $("#abstracts").val(data.abstracts);

            }

        });
        //填充下拉框
        $('#mycolumn').combobox({
            url:'${pageContext.request.contextPath}/selectallChinaMath',
            editable:false,
            valueField:'syscode',
            textField:'columnname'
        });

    });

    function submitData() {
        $.ajax({
          url:"/addNews",
          type:"post",
          data:$("#fm").serialize(),
          success:function (data) {
              if(data){
                  layer.open({
                      title: '提示'
                      ,content: '添加成功！'
                  });
              }
          }
      });

    }
    function Cupload() {
        alert($("#fileImport").val());
        $("#pict").src("$('#fileImport').val()");
    }


</script>
</head>
<body style="margin: 1px">
<div class="easyui-layout" id="addnews" style="width:1400px;border: 1px red solid">
    <form id="fm" method="post" enctype="multipart/form-data">
        <input type="text" value="${param.sys_id}" id="sys_id" style="display: none">
        <table cellpadding="6px" align="center">
            <tr >
                基本信息<hr>
            </tr>
            <tr>
                <td>所属栏目：</td>
                <td>
                    <select class="easyui-combobox" name="syscode" style="width:200px;" id="syscode">
                        <option>请选择</option>
                    </select>
                    <a onclick="dianji()" class="easyui-linkbutton" plain="true" icon="icon-ok"></a>
                </td>
            </tr>
            <tr>
                <td>文章标题：</td>
                <td>
                    <input  id="newtitle" name="newtitle" class="easyui-textbox" required="true" style="width: 200px"/>
                </td>
                <td>权重：</td>
                <td><input type="text" name="quanzhong" style="width: 80px"></td>
            </tr>
            <tr>
                <td>标题颜色：</td>
                <td>
                    <input  id="color" name="color" class="easyui-textbox" required="true" style="width: 200px"/>
                </td>
            </tr>
            <tr>
                <td> 文章图片：111   </td>
                <td>
                    <!--file://C:/fakepath/2.jpg-->
                    <input  type="file"  name="file" style="width:260px;" />
                     <%--<img src="" name="file"  id="pict" alt="默认图片" width="50px" height="50px">--%>
                </td>
            </tr>
           <%-- <tr>
                <td> </td>
                <td>
                    <form id="importFileForm" method="post" enctype="multipart/form-data">
                         <input  type="file" class="easyui-filebox" id="fileImport" name="file" style="width:260px;" onchange="Cupload()">
                         <input  type="reset" value="清除" onclick="Creset()"/>
                    </form>

                </td>
            </tr>--%>
           <%-- <tr>
                <td>摘要：</td>
                <td><textarea id="container1" name="abstracts"
                              style="width: 800px;  margin: 0 auto;"></textarea></td>
            </tr>

            <tr>
                <td>内容：</td>
                <td><textarea id="container2" name="context"
                              style="width: 800px; height: 200px; margin: 0 auto;"></textarea></td>
            </tr>--%>
            <tr>
                <td>摘要：</td>
                <td><textarea id="abstracts" name="abstracts"
                              style="width: 800px;  margin: 0 auto;">
                </textarea></td>
            </tr>

            <tr>
                <td>内容：</td>
                <td><textarea id="context" name="context"
                              style="width: 800px; height: 200px; margin: 0 auto;">

                </textarea>
                    <script type="text/javascript">
                        UE.getEditor('context');
                    </script></td>
            </tr>
            <tr>
                <td colspan="2"></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a href="javascript:" class="easyui-linkbutton" iconCls="icon-submit">暂存草稿</a>&nbsp;
                    <a href="javascript:submitData()" class="easyui-linkbutton" iconCls="icon-submit">发布</a>
                    <a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset">关闭</a>
                </td>
            </tr>
        </table>
    </form>

</div>
</body>

<div id="shu" style="padding:200px;">
    <ul id="tt"></ul>
</div>

</html>