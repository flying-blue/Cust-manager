<%@page pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>客户管理</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
           <c:import url="../logo.jsp"></c:import>         
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <c:import url="../menu.jsp"></c:import>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="modifyAdmin.do" method="post" class="main_form">
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" value="${admin.name }" name="name"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long error_msg">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info"><input type="text" readonly="readonly" class="readonly" value="${code }"  name="adminCode"/></div>
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input type="text" value="${admin.telephone }"  name="telephone"/>
                        <span class="required">*</span>
                        <div class="validate_msg_long error_msg">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" value="${admin.email }" name="email" />
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">50长度以内，正确的 email 格式</div>
                    </div>
                    <div class="text_info clearfix"><span>分店分配：</span></div>
                    <div class="input_info_high">
                        <c:if test="${admin.addNo==null }">
	                        <select name="addNo" id="selAdds" class="input_info readonly" disabled="disabled">
	                            <c:forEach items="${addList }" var="add">
	                            	<option value="" >超级管理员</option>
	                            	<option value="${add.addNo }"  <c:if test="${add.addNo==admin.addNo }">selected</c:if>>${add.addName }</option>
	                            </c:forEach>
	                        </select>
	                        <span class="required">*这是超级管理员，请勿修改</span>
                        </c:if>
                        <c:if test="${admin.addNo!=null }">
	                        <select name="addNo" id="selAdds" class="select_search readonly" >
	                            <c:forEach items="${addList }" var="add">
	                            	<option value="${add.addNo }"  <c:if test="${add.addNo==admin.addNo }">selected</c:if>>${add.addName }</option>
	                            </c:forEach>
	                        </select>
                        <span class="required">*</span>
                        <div class="validate_msg_tiny error_msg">至少选择一个</div>
                        </c:if>
                    </div>
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save" onclick="showResult();" />
                        <input type="button" value="取消" class="btn_save" onclick="history.back()"/>
                    </div>
                </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[优秀的技师，舒适的环境，给您更好的体验]</p>
            <p>版权所有(C)美康堂养生服务有限公司 </p>
        </div>
    </body>
</html>
