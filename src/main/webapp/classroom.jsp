

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

        $(function () {
            $(".easyui-combobox").combobox({
                url:"/findAllTeas.do",
                editable:false,
                valueField:'id',
                textField:'teaname'
            })
            $("#timecmb").combobox({
                url:"/getAweekTime",
                editable:false,
                valueField:'id',
                textField:'time'
            })
            $.ajax({
                type:"post",
                url:"/findAllRooms",
                success:function (data) {
                    $.each(data,function (i,dom) {
                        $("#dlg"+dom.id).dialog("open");
                    })
                }
            })
        })

        function func() {
            if(isNaN($("#timecmb").combobox('getValue'))){
                $.messager.alert('Warning','请选择时间');
            }else{
               alert($(".easyui-combobox").combobox('getValue'));
            }

        }




    </script>
</head>
<body>

<div class="demo-info" style="margin-bottom:10px">








</div>


<div style="padding:5px;border:1px solid #ddd">
   <strong>最近一周人员机房分配：
       <div style="margin-top: 10px"></div>
       <a href="#" class="easyui-linkbutton" data-options="plain:true">日期:</a>
       <select id="timecmb" class="easyui-combobox" name="teaname" style="width: 200px">
       <option value="请选择"></option>
   </select>

       <%--<a href="#" class="easyui-linkbutton" data-options="plain:true">教师姓名:</a>
     <select id="teacmb" class="easyui-combobox" name="teaname" style="width: 200px">
          <option value="请选择"></option>
      </select>--%>
           <a id="btn" href="#" class="easyui-linkbutton" data-options="plain:true,display:true" onclick="func()" >设置</a>

   </strong>
</div>

    <div id="one">
        <c:forEach items="${list}" var="item">
            <c:if test="${item.id%2!=0}" >
            <div id="dlg${item.id}" class="easyui-dialog" title="${item.name}" style="width:230px;height:200px;padding:10px;border: 2px;margin-left: 5px"
                 data-options="
                left: 120+${item.id}*120,
                top:200,
				iconCls: 'icon-save',
				closable: false,
				autoOpen: false,
				closed:true,
                draggable: false,
                toolbar: []
			" >
                上午: <select id="${item.id}" class="easyui-combobox" name="teaname" style="width: 200px">
                <option value="请选择"></option>
            </select>
                <hr/>
                下午: <select id="${item.id}" class="easyui-combobox" name="teaname" style="width: 200px">
                <option value="请选择"></option>
            </select>
            </div>
           </c:if>

            <c:if test="${item.id%2==0}">
                <div id="dlg${item.id}"  class="easyui-dialog" title="${item.name}" style="width:230px;height:200px;padding:10px;border: 2px"
                     data-options="
                 left: 120+(${item.id}-1)*120,
                  top:200+${item.id}+200   ,
				iconCls: 'icon-save',
				closable: false,
				autoOpen: false,
				closed:true,
				toolbar: [
				],
				draggable: false,
			" >
                    上午:<select id="${item.id}" class="easyui-combobox" name="teaname" style="width: 200px">
                    <option value="请选择"></option>
                </select>
                    <hr/>
                    下午:<select id="${item.id}" class="easyui-combobox" name="teaname" style="width: 200px">
                    <option value="请选择"></option>
                </select>
                </div>
            </c:if>
        </c:forEach>

    </div>

<div id="dlgpwd"  class="easyui-dialog" title="修改" style="width:600px;height:200px;padding:10px;border: 2px"
     data-options="
				iconCls: 'icon-save',
				closed:true,
				toolbar: [{
					text:'保存',
					iconCls:'icon-add',
					handler:function(){
						alert('add')
					}
				}],

			" >



    <form id="userForms" com.action="/select.do" method="post">
             <table border="1">
                 <tr>
                     <td>时间</td>
                     <td>星期一</td>
                     <td>星期二</td>
                     <td>星期三</td>
                     <td>星期四</td>
                     <td>星期五</td>
                     <td>星期六</td>
                     <td>星期天</td>

                 </tr>
                 <tr>
                     <td>上午</td>
                     <td><input name="mon" id="mon" style="width:65px;" class="easyui-validatebox" required="true" readonly/></td>
                     <td><input name="tues" id="tues" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="wed" id="wed"  style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="thur" id="thur" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="fri" id="fri" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="sat" id="sat" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="sun" id="sun" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                 </tr>
                 <tr>

                     <td>下午</td>
                     <td><input name="monn" id="monn" style="width:65px;" class="easyui-validatebox" required="true" readonly/></td>
                     <td><input name="tuesn" id="tuesn" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="wedn" id="wedn"  style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="thurn" id="thurn" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="frin" id="frin" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="satn" id="satn" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                     <td><input name="sunn" id="sunn" style="width:65px;" class="easyui-validatebox" required="true"/></td>
                 </tr>
             </table>
    </form>
</div>


</body>
</html>
