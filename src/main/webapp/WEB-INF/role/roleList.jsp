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
        <script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script> 
        <script type="text/javascript" src="js/jquery-1.4.3.js"></script> 
        <script language="javascript" type="text/javascript">
            function deleteRole(id) {
                var r = window.confirm("确定要删除此角色吗？");
                if (r) {
					location.href="deleteRole.do?custDeId="+id;
				}
                document.getElementById("operate_result_info").style.display = "block";
            }
           	var search_type =1;
           	window.onload=function(){
           		var o = $("input[name='search_type']:checked").val();
           		search_type = o;
           	}
           	function changeType(type){
                if(type == 1){
                	search_type = 1;
                }
                if(type == 2){
                	search_type = 2;
                }
           }
            function search(){
            	var custId=document.getElementById("custId").value;
            	var startDate=document.getElementById("da1").value;	
            	var endDate=document.getElementById("da2").value;	
            	var  addNo=document.getElementById("selAdds").value;	
            	
            	console.log(addNo);
	
            	var startNum = parseInt(startDate.replace(/-/g,''),10); 	
            	var endNum = parseInt(endDate.replace(/-/g,''),10); 	
            	if(startNum>endNum){
            		alert("结束日期不能早于起始日期！");
            		return false; 	
            		} 	
            	window.location.href="findRoleList.do?custId="+custId+"&da1="+startDate+"&da2="+endDate+"&addNo="+addNo+"&search_type="+search_type;	
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
            <form action="" method="post">
            <!--查询-->
                <div class="search_add">
                	<c:set var="search_type" value="${search_type}" />
                    <div>
                        分店名称：			<c:if test="${adminInfo.addNo==null }">
                        <select name="addNo" id="selAdds" class="select_search readonly">
                            <option value="">全部</option>
                            <c:forEach items="${addList }" var="add">
                            	<option value="${add.addNo }"  <c:if test="${add.addNo==addNo }">selected</c:if>>${add.addName }</option>
                            </c:forEach>
                        </select>
                        </c:if>
                        <c:if test="${adminInfo.addNo!=null }">
                        <select name="addNo" id="selAdds" class="select_search readonly" disabled="disabled">
                            <option value="">全部</option>
                            <c:forEach items="${addList }" var="add">
                            	<option value="${add.addNo }"  <c:if test="${add.addNo==adminInfo.addNo }">selected</c:if>>${add.addName }</option>
                            </c:forEach>
                        </select>
                        </c:if>
                    </div>
                    <div>顾客编号：<input type="text" id="custId" value="${custId}" name="custId" class="text_search width100 clearfix" /></div>
                    <div>消费时间：
                    	<span style="font-size:14px;"> 
                    	<input class="Wdate width100" type="text" id="da1" name="da1" readonly="readonly"  value="${da1}"
                    	onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/> 
                    	- <input class="Wdate width100" id="da2" type="text" readonly="readonly"  value="${da2}"
                    	onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></span>		
                    </div>
                    
                    <div><input type="button" value="搜索" class="btn_search" onclick="search()"/></div>
	                <!--增加-->
	                <c:if test="${adminInfo.addNo==null }">
	                    <input type="button" value="新增顾客" class="btn_add" disabled="disabled" onclick="location.href='toAddRole.do?addNo=${addNo}';" />
	                 </c:if>
	                <c:if test="${adminInfo.addNo!=null }">
	                    <input type="button" value="新增顾客" class="btn_add"  onclick="location.href='toAddRole.do?addNo=${addNo}';" />
	                </c:if>
                </div>
                <div class="top_total" id="disptype">
                
                	<input  type="radio" style="height:13px;" name="search_type" ${search_type==1?'checked':'' } onclick="changeType(1)"  id="onPage"  value="1"/> 全部
                    <input  type="radio" style="height:13px;" name="search_type" ${search_type==2?'checked':'' } onclick="changeType(2)"  id="all"  value="2"/>
                    <span >分页</span>
              		<span>合计：</span><input type="text" id="tot" value="${curTotal }元" class="text_ouput width100" readonly="readonly" ></input>
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    		删除错误！该角色被使用，不能删除。
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">   
                    <table id="datalist">
                        <tr>                            
                            <th class="width70">顾客编号</th>
                            <th class="width50">姓名</th>
                            <th class="width50">性别</th>
                            <th class="width50">年龄</th>
                            <th>电话</th>
                            <th>建档时间</th>
                            <th class="width200">家庭住址</th>
                            <th class="width150">备注</th>
                            <th class="td_modi"></th>
                        </tr>                      
                       <!--  <tr>
                            <td>1</td>
                            <td>贾强</td>
                            <td>角色管理、管理员管理、资费管理、账务账号、业务账号、账单、报表</td>
                            <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='role_modi.html';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteRole();" />
                            </td>
                        </tr> -->
                        <!-- 如果没有查到数据 -->
                       	<c:if test="${roles.size()<=0 }">
	                        <tr>
	                        	<td colspan="9">
	                      		  未查询到数据!
	                        	</td>
	                        </tr>
                       	</c:if>
						<!-- 查询到数据时 -->
                        <c:forEach items="${roles }" var="r">
                        <tr>
                            <td>${r.custId }</td>
                            <td>${r.name }</td>
                            <td>
                            	<c:if test="${r.sex==1 }">男</c:if>
                            	<c:if test="${r.sex==2 }">女</c:if>
                            </td>
                            <td>${r.age }</td>
                            <td>${r.tel }</td>
                            <td>
                            	<fmt:formatDate value="${r.appendDate }" pattern="yyyy-MM-dd" /> 
                            </td>
                            <td >${r.addr }
                            </td>
                            <td>${r.content }</td>
                            <td>
                                <input type="button" value="详细" class="btn_modify" onclick="location.href='toCustDetail.do?custMoId=${r.custId }'"/>
                                <%-- <input type="button" value="删除" class="btn_delete" onclick="deleteRole(${r.roleId })" /> --%>
                            </td>
                        </tr>
                        </c:forEach>
                      
                    </table>
                </div> 
                <!--分页-->
                <c:if test="${search_type!=1 }">
                <div id="pages">
        	       <c:if test="${page==1 }">
        	        <a href="#">上一页</a>
                </c:if>
                <c:if test="${page!=1 }">
                    <a href="findRoleList.do?page=${page-1 }&custId=${custId}&da1=${da1}&da2=${da2}&addNo=${addNo}&search_type=${search_type}">上一页</a>
                </c:if>
                <c:forEach begin="1" end="${total }" var="i">
                	<c:if test="${i==page }">
	                    <a href="findRoleList.do?page=${i }&custId=${custId}&da1=${da1}&da2=${da2}&addNo=${addNo}&search_type=${search_type}" class="current_page">${i }</a>
                	</c:if>
                	<c:if test="${i!=page }">
	                    <a href="findRoleList.do?page=${i }&custId=${custId}&da1=${da1}&da2=${da2}&addNo=${addNo}&search_type=${search_type}">${i }</a>
                	</c:if>
                </c:forEach>
                <c:if test="${page==total }">
                    <a href="#">下一页</a>
                </c:if>
                <c:if test="${page!=total }">
                    <a href="findRoleList.do?page=${page+1 }&custId=${custId}&da1=${da1}&da2=${da2}&addNo=${addNo}&search_type=${search_type}">下一页</a>
                </c:if>
                </div>
                </c:if>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[优秀的技师，舒适的环境，给您更好的体验]</p>
            <p>版权所有(C)美康堂养生服务有限公司 </p>
        </div>
    </body>
</html>
