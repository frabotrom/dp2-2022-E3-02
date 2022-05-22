<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.chirp.list.label.title" path="title"/>
	<acme:input-textbox code="any.chirp.list.label.author" path="author"/>
	<acme:input-textarea code="any.chirp.list.label.body" path="body"/>
	<acme:input-email code="any.chirp.list.label.email" path="email"/>
	
	<acme:input-checkbox code="any.chirp.form.label.confirmation" path="confirmation"/>
	<acme:submit code="any.chirp.list.label.button.create" action="/any/chirp/create"/>
</acme:form>