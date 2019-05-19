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
         <script type="text/javascript" src="js/jquery-1.4.3.js"></script> 
        <script language="javascript" type="text/javascript">
            //保存成功的提示消息
            function showResult() {
       			showResultDiv(true);
       			window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag){
                    divResult.style.display = "block";
                }else{
                    divResult.style.display = "none";
                }
            }
          //切换资费类型
            function feeTypeChange(type) {
                var inputArray = document.getElementById("main").getElementsByTagName("input");
                var sex = 1;
                	if (type == 1) {
                		sex = 1;
                	} else if (type == 2) {
                		sex = 2;
                	}
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
            <div id="save_result_info" class="save_success"><span>${message }</span></div><!--保存失败，角色名称重复！-->
            <form action="addRole.do" method="post" class="main_form">
            <span  class="cust_history" >新增顾客信息 </span>
              <table class="itemList" >
            	<tr >
            		<td class="item">
            			<span>编号：</span><span class="required">*${custerror }</span>
            		</td>
            		<td class="itemInfo">
            			<input type="text" class="itemInfo" name="custId"  value="${param.custId}"  />
            		</td>
            		<td class="item">
            			<span>建档时间：</span>
            		</td>
            		<td>
            			 <input type="text" class="itemInfo" name="appendDate" readonly="readonly" value="${sysdate}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>姓名：</span><span class="required">*${error }</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="custName"  value="${param.custName}" />
            		</td>
            		<td class="item" style="background: #eae">
            			<span>性别：</span>
            		</td>
            		<td >
            			<div class="itemInfo" >
            			<input type="radio" style="height:13px;" name="custSex" checked="checked" id="men"  value="1"/>
                    	<span >男</span>
                    	<input type="radio" style="height:13px;" name="custSex"  id="women"  value="2"/>
                    	<span>女</span>
                    	</div>
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>年龄：</span>
            		</td>
            		<td >
            			<input type="text" class="itemInfo" name="custAge"  value="${param.custAge}" />
            		</td>
            		<td class="item">
            			<span>电话：</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="telephone"  value="${param.telephone}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>住址：</span>
            		</td>
            		<td >
            			<input type="text" class="itemInfo clearfix" name="custAddr"  value="${param.custAddr}" />
            		</td>
            		<td class="item">
            			<span>备注：</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="custContent"  value="${param.custContent}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>身体状况：</span>
            		</td>
            		<td colspan="3">
            			<%-- <input type="text" class="itemInfo_body" name="bodyInfo"  value="${role.bodyInfo}" /> --%>
            			<textarea class="itemInfo_body" name="bodyInfo" >${param.bodyInfo }</textarea>
            		</td>
            		<td></td><td></td>
            	</tr>
            	</table>
            	
                <div class="button_info_upcust clearfix">
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
