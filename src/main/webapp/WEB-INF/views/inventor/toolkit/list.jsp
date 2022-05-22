<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.toolkit.list.label.code" path="code" width="10%"/>
	<acme:list-column code="inventor.toolkit.list.label.title" path="title" width="30%"/>
	<acme:list-column code="inventor.toolkit.list.label.info" path="info" width="40%"/>
	<acme:list-column code="inventor.toolkit.list.label.draftMode" path="draftMode" width="20%"/>
</acme:list>
