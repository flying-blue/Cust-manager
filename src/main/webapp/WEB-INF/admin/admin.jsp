<%@page pageEncoding="utf-8"  %>
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
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //重置密码
            function resetPwd() {
                alert("请至少选择一条数据！");
                //document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteAdmin(id) {
                var r = window.confirm("确定要删除此管理员吗？");
                if (r) {
					location.href="deleteAdmin.do?adminId="+id;
               	  	document.getElementById("operate_result_info").style.display = "block";
				}
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
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
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <div>
                        分店名称：				<select name="addNo" id="seleAdds" class="select_search readonly" >
                            <option value="">全部</option>
                            <c:forEach items="${addList }" var="add">
                            	<option value="${add.addNo }"  <c:if test="${add.addNo==adminInfo.addNo }">selected</c:if>>${add.addName }</option>
                            </c:forEach>
                        </select>
                        <!-- <select id="selModules" class="select_search">
                            <option>全部</option>
                            <option>角色管理</option>
                            <option>管理员管理</option>
                            <option>资费管理</option>
                            <option>账务账号</option>
                            <option>业务账号</option>
                            <option>账单管理</option>
                            <option>报表</option>
                        </select> -->
                    </div>
                    <div>角色：<input type="text" value="" class="text_search width200" /></div>
                    <div><input type="button" value="搜索" class="btn_search"/></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddAdmin.do';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">所在分店</th>
                            <th></th>
                        </tr>                      
                        <!-- <tr>
                            <td><input type="checkbox" /></td>
                            <td>1</td>
                            <td>贾强</td>
                            <td>admin</td>
                            <td>13077332357</td>
                            <td>jiaqiang123@qq.com.cn</td>
                            <td>2011-31-12</td>
                            <td>
                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">超级管理员...</a>
                                浮动的详细信息
                                <div class="detail_info">
                                    超级管理员，角色管理员，账单管理员，报表管理员，业务账号管理员，账务账号管理员，aa，bb
                                </div>
                            </td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='admin_modi.html';" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin();" />
                            </td>
                        </tr> -->
                       
                        <c:forEach items="${admins }" var="a">
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${a.adminId }</td>
                            <td>${a.name }</td>
                            <td>${a.adminCode }</td>
                            <td>${a.telephone }</td>
                            <td>${a.email }</td>
                            <td>
                            	<fmt:formatDate value="${a.enrolldate }" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                            <td>
                           		<c:if test="${a.addNo==null }">超级管理员</c:if>
                            	<c:forEach items="${addList }" var="add">
	                            	<c:if test="${add.addNo==a.addNo }">${add.addName }</c:if>
	                            </c:forEach>
                            </td>
                            <td class="td_modi">
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toModifyAdmin.do?adminCode=${a.adminCode }'"/>
                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(${a.adminId })"/>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                <c:if test="${page==1 }">
        	        <a href="#">上一页</a>
                </c:if>
                <c:if test="${page!=1 }">
                    <a href="toAdmin.do?page=${page-1 }">上一页</a>
                </c:if>
                <c:forEach begin="1" end="${total }" var="i">
                	<c:if test="${i==page }">
	                    <a href="toAdmin.do?page=${i }" class="current_page">${i }</a>
                	</c:if>
                	<c:if test="${i!=page }">
	                    <a href="toAdmin.do?page=${i }">${i }</a>
                	</c:if>
                </c:forEach>
                <c:if test="${page==total }">
                    <a href="#">下一页</a>
                </c:if>
                <c:if test="${page!=total }">
                    <a href="toAdmin.do?page=${page+1 }">下一页</a>
                </c:if>
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
