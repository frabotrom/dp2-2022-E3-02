<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.user-account.form.label.name" path="name"/>
	<acme:input-textbox code="any.user-account.form.label.surname" path="surname"/>
	<acme:input-textbox code="any.user-account.form.label.email" path="email"/>
	<acme:input-textbox code="any.user-account.form.label.role" path="role"/>
</acme:form>
