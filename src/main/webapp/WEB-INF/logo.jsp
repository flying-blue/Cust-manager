<%@page pageEncoding="utf-8"%>

<img src="images/logo_new.png" alt="logo" class="left" />
<!-- EL默认从4个对象中取值：
	pageContext,request,session,application
	也可以从cookie中取值，方式为:
		cookie.key.value -->
<%--
<span>${cookie.adminCode.value }</span>
--%>
<span>${adminCode }</span>
<a href="/custManager/logout.do">[退出]</a>
