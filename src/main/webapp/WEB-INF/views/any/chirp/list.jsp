<%@ page language="java"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.chirp.list.label.title" path="title" width="25%"/>
	<acme:list-column code="any.chirp.list.label.author" path="author" width="15%"/>
	<acme:list-column code="any.chirp.list.label.creationDate" path="creationDate" width="15%"/>
	<acme:list-column code="any.chirp.list.label.body" path="body" width="30%"/>
	<acme:list-column code="any.chirp.list.label.email" path="email" width="15%"/>
</acme:list>

<acme:button code="any.chirp.list.label.button.create" action="/any/chirp/create"/>