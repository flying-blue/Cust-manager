<%@page pageEncoding="utf-8" import="entity.*,java.util.*,dao.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul id="menu">
	<!-- 此处适合写绝对路径，原因如下：
		1.假设当前浏览器打开了资费查询页面，用户点击了主页要跳转过去，此时对于浏览器而言，
		  当前路径是findCost.do目标路径是toIndex.do
		2.对于浏览器而言，当前路径目标路径都是*.do 
		3.在写某连接时，目标路径时可以确定的，但是当前页面是无法确定的，可能是任何页面。
		4.此时只能写绝对路径-->
	<li><a href="/custManager/toIndex.do" class="index_off"></a></li>
	
	<c:forEach items="${allModules }" var="module">
		<c:if test="${module.moduleId==1 }">
			<c:choose> 
				<c:when test="${currentModule==1 }">
					<li><a href="/custManager/findRoleList.do?custId=${custId}" class="role_on"></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/custManager/findRoleList.do?custId=${custId}&search_type=1" class="role_off"></a></li>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${module.moduleId==2 }">
			<c:choose>
				<c:when test="${currentModule==2 }">
					<li><a href="/custManager/toAdmin.do" class="admin_on"></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/custManager/toAdmin.do" class="admin_off"></a></li>
				</c:otherwise>
			</c:choose>	
		</c:if>
	</c:forEach>
	
	<!-- <li><a href="/custManager/findCost.do" class="fee_off"></a></li> -->
	<!-- <li><a href="" class="account_off"></a></li>
	<li><a href="" class="service_off"></a></li>
	<li><a href="" class="bill_off"></a></li>
	<li><a href="" class="report_off"></a></li> -->
	<li><a href="/custManager/toUser.do" class="information_off"></a></li>
	<li><a href="/custManager/toUpdatePwd.do?adminCode=${adminCode }" class="password_off"></a></li>
</ul>
