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
                window.setTimeout("showResultDiv(false);", 4000);
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
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="updateRole.do" method="post" class="main_form">
                
                <table class="itemList" >
            	<tr >
            		<td class="item">
            			<span>编号：</span>
            		</td>
            		<td class="itemInfo">
            			<input type="text" class="itemInfo" name="custMoId" readonly="readonly" value="${role.custId}" />
            		</td>
            		<td class="item">
            			<span>建档时间：</span>
            		</td>
            		<td>
            			 <input type="text" class="itemInfo" name="appendDate" readonly="readonly" value="${role.appendDate}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>姓名：</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="custName"  value="${role.name}" />
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
            			<span>年龄：</span>
            		</td>
            		<td >
            			<input type="text" class="itemInfo" name="custAge"  value="${role.age}" />
            		</td>
            		<td class="item">
            			<span>电话：</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="telephone"  value="${role.tel}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>住址：</span>
            		</td>
            		<td >
            			<input type="text" class="itemInfo" name="custAddr"  value="${role.addr}" />
            		</td>
            		<td class="item">
            			<span>备注：</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="custContent"  value="${role.content}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>身体状况：</span>
            		</td>
            		<td colspan="3">
            			<input type="text" class="itemInfo_body" name="bodyInfo"  value="${role.bodyInfo}" />
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
