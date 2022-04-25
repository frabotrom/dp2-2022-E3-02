<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.user-account.list.label.username" path="username" width="40%"/>
	<acme:list-column code="any.user-account.list.label.role" path="role" width="60%"/>
</acme:list>