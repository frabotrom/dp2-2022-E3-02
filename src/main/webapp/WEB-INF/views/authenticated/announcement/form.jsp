<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-moment code="authenticated.announcement.form.label.creationDate" path="creationDate"/>
	<acme:input-textbox code="authenticated.announcement.form.label.critical" path="critical"/>
	<acme:input-textbox code="authenticated.announcement.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.announcement.form.label.body" path="body"/>
	<acme:input-url code="authenticated.announcement.form.label.moreInfo" path="moreInfo"/>
</acme:form>