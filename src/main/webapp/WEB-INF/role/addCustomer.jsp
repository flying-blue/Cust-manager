<%@page pageEncoding="utf-8" import="entity.*,java.util.*,dao.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>客户管理</title>
        <link type="text/css" rel="stylesheet" media="all"  href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all"  href="styles/global_color.css" />
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
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，角色名称重复！-->
            <form action="addConsum.do" method="post" class="main_form">
            
            <span  class="cust_history" >新增办卡消费 </span>
              <table class="itemList" >
            	<tr >
            		<td class="item">
            			<span>编号：</span>
            		</td>
            		<td class="itemInfo">
            			<input type="text" class="itemInfo" name="custId" readonly="readonly"  value="${role.custId}"  />
            		</td>
            		<td class="item">
            			<span>消费时间：</span>
            		</td>
            		<td>
            			 <input type="text" class="itemInfo" name="appendDate" readonly="readonly" value="${sysdate}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>姓名：</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="custName" readonly="readonly"  value="${role.name }" />
            		</td>
            		<td class="item" style="background: #eae">
            			<span>性别：</span>
            		</td>
            		<td >
            			<input type="text" class="itemInfo" name="custSex" readonly="readonly" value="${role.sex==1?'男':'女' }" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>消费金额：</span><span class="required">*${error }</span>
            		</td>
            		<td >
            			<input type="text" class="itemInfo" name="consum"  value="${param.consum}" />
            		</td>
            		<td class="item">
            			<span>消费项目：</span>
            		</td>
            		<td>
            			<%-- <input type="text" class="itemInfo" name="consumKbn"  value="${param.consumKbn}" /> --%>
            			<select name="consumKbn" id="selAdds" class="select_search readonly" >
                            <option value="">请选择</option>
                            <option value="1">中医足部经络</option>
                            <option value="2">面部拨筋</option>
                            <option value="3">肩颈调理</option>
                            <option value="4">脾胃调理</option>
                            <option value="5">结肠排毒</option>
                            <option value="6">卵巢保养</option>
                            <%-- <c:forEach items="${addList }" var="add">
                            	<option value="${add.addNo }"  <c:if test="${add.addNo==adminInfo.addNo }">selected</c:if>>${add.addName }</option>
                            </c:forEach> --%>
                        </select>
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>备注：</span>
            		</td>
            		<td colspan="3">
            			<textarea class="itemInfo_body" name="bodyInfo" >${param.bodyInfo }</textarea>
            		</td>
            		<td></td><td></td>
            	</tr>
            	</table>
            	
            
            
            	<%-- <div class="text_info clearfix"><span>编号：</span></div>
                <div class="input_info">
                    <input type="text" class="width150" name="custId" />
                    <span class="required">*${custerror }</span>
                    <!-- <div class="validate_msg_medium">不能为空，且为20长度的字母、数字和汉字的组合</div> -->
                </div>
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input type="text" class="width150" name="name" />
                    <span class="required">*${error }</span>
                    <!-- <div class="validate_msg_medium">不能为空，且为20长度的字母、数字和汉字的组合</div> -->
                </div>
                <div class="text_info clearfix"><span>性别：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="sex" checked="checked" id="monthly" onclick="feeTypeChange(1);" value="1"/>
                    <label for="monthly">男</label>
                    <input type="radio" name="sex"  id="package" onclick="feeTypeChange(2);" value="2"/>
                    <label for="package">女</label>
                </div>
                <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="telephone" value="${param.tele }" />
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>消费金额：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="consum" value="${param.consum }" />
                        <span class="required">*</span>
                        <!-- <div class="validate_msg_medium error_msg">50长度以内，正确的 email 格式</div> -->
                    </div>                    
                <div class="text_info clearfix"><span>消费项目：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
                            <li><input type="checkbox" checked="checked"/>中医足部经络</li>
                            <li><input type="checkbox" checked="checked"/>面部拨筋</li>
                            <li><input type="checkbox" />肩颈调理</li>
                            <li><input type="checkbox" />脾胃调理</li>
                            <li><input type="checkbox" />结肠排毒</li>
                            <li><input type="checkbox" />卵巢保养</li>
                            <li><input type="checkbox" />肾保养</li>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny">至少选择一个项目</div>
                </div>
                <div class="text_info clearfix"><span>备注：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70" name="descr" ></textarea>
                    <div class="validate_msg_short">100长度的字母、数字、汉字和下划线的组合</div>
                </div>  --%>
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
