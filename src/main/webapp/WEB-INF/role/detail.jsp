<%@page pageEncoding="utf-8" import="entity.*,java.util.*,dao.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
            <!--保存操作后的提示信息：成功或者失败-->
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="updateRole.do" method="post" class="main_form">
            	<div class="top_cust_record" id="disptype">
            		<span  class="cust_history" >顾客个人信息 </span>
            		<span class="all_con">
            		<input type="button" value="返回 " class="btn_add" onclick="history.back()" />
            		<input type="button" value="办卡消费 " class="btn_add" onclick="location.href='toAddConsum.do?custMoId=${role.custId }';" />
            		<input type="button" value="修改信息" class="btn_add" onclick="location.href='toUpdateRole.do?custMoId=${role.custId }';" />
            	</span>
            	</div>
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
            			<input type="text" class="itemInfo" name="custName" readonly="readonly" value="${role.name}" />
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
            			<input type="text" class="itemInfo" name="custSex" readonly="readonly" value="${role.age}" />
            		</td>
            		<td class="item">
            			<span>电话：</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="custMoId" readonly="readonly" value="${role.tel}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>住址：</span>
            		</td>
            		<td >
            			<input type="text" class="itemInfo" name="custSex" readonly="readonly" value="${role.addr}" />
            		</td>
            		<td class="item">
            			<span>备注：</span>
            		</td>
            		<td>
            			<input type="text" class="itemInfo" name="custMoId" readonly="readonly" value="${role.content}" />
            		</td>
            	</tr>
            	<tr >
            		<td class="item">
            			<span>身体状况：</span>
            		</td>
            		<td colspan="3">
            			<input type="text" class="itemInfo_body" name="custMoId" readonly="readonly" value="${role.bodyInfo}" />
            		</td>
            		<td></td><td></td>
            	</tr>
            	</table>
            	<div class="top_cust_record" id="disptype">
            		<span  class="cust_history" >顾客消费记录 </span>
            		<span class="all_con">总消费：<input  type="text" id="cutot" value="${custTotal }元" class="text_ouput width100" readonly="readonly" ></input>
            	</span>
            	</div>
            	
            	<table id="datalist" >
            		<tr>                            
                            <th>No</th>
                            <th class="width80">消费金额(元)</th>
                            <th>消费时间</th>
                            <th class="width150">消费项目</th>
                            <th class="width150">备注</th>
                            <th class="td_modi"></th>
                        </tr> 
                        <c:if test="${custList.size()<=0 }">
	                        <tr>
	                        	<td colspan="6">
	                      		  暂无消费记录!
	                        	</td>
	                        </tr>
                       	</c:if> 
                         <c:forEach items="${custList }" var="c">
                        <tr>
                          <td>${c.conNum }</td>
                            <td>${c.consum }</td>
                            <td>
                            	<fmt:formatDate value="${c.consumDate }" pattern="yyyy-MM-dd" /> 
                            </td>
                            <td>
                            	<c:if test="${c.consumKbn==null }">其他项目</c:if>
                            	<c:if test="${c.consumKbn==1 }">中医足部经络</c:if>
                            	<c:if test="${c.consumKbn==2 }">面部拨筋</c:if>
                            	<c:if test="${c.consumKbn==3 }">肩颈调理</c:if>
                            	<c:if test="${c.consumKbn==4 }">脾胃调理</c:if>
                            	<c:if test="${c.consumKbn==5 }">结肠排毒</c:if>
                            	<c:if test="${c.consumKbn==6 }">卵巢保养</c:if>
                            </td>
                            <td>${c.content }</td>
                            <td></td>
                        </tr>
                        </c:forEach>
            	</table>
            	
                <!-- <div class="text_info_cust clearfix"><span>设置项目：</span></div>
                <div class="input_info_high">
                    <div class="input_info_scroll">
                        <ul>
                            <li><input type="checkbox" checked="checked"/>中医足部经络</li>
                            <li><input type="checkbox" />面部拨筋</li>
                            <li><input type="checkbox" />肩颈调理</li>
                            <li><input type="checkbox" />脾胃调理</li>
                            <li><input type="checkbox" />结肠排毒</li>
                            <li><input type="checkbox" />卵巢保养</li>
                            <li><input type="checkbox" />肾保养</li>
                        </ul>
                    </div>
                    <span class="required">*</span>
                    <div class="validate_msg_tiny">至少选择一个权限</div>
                </div> -->
               <!--  <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="history.back()"/>
                </div> -->
            </form> 
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[优秀的技师，舒适的环境，给您更好的体验]</p>
            <p>版权所有(C)美康堂养生服务有限公司 </p>
        </div>
    </body>
</html>
