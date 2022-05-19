<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.chirp.list.label.title" path="title"/>
	<acme:input-textbox code="any.chirp.list.label.author" path="author"/>
	<acme:input-textbox code="any.chirp.list.label.creationDate" path="creationDate"/>
	<acme:input-textarea code="any.chirp.list.label.body" path="body"/>
	<acme:input-email code="any.chirp.list.label.email" path="email"/>
</acme:form>

<jstl:choose>
	<jstl:when test="${command == 'create'}">
		<acme:input-checkbox code="any.chirp.form.label.accept" path="accept"/>
		<acme:submit code="any.chirp.list.label.button.create" action="/any/chirp/create"/>
	</jstl:when>
</jstl:choose>