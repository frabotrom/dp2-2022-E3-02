<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.item.list.label.code" path="code" width="20%"/>
	<acme:list-column code="inventor.item.list.label.name" path="name" width="40%"/>
	<acme:list-column code="inventor.item.list.label.retailPrice" path="retailPrice" width="20%"/>
	<acme:list-column code="inventor.item.list.label.visible" path="visible" width="20%"/>
</acme:list>

<jstl:choose>
<jstl:when test="${acme:anyOf(command,'list-components')}">
<acme:button code="inventor.item.list.button.create.component" action="/inventor/item/create-component"/>
</jstl:when>
<jstl:otherwise>
<acme:button code="inventor.item.list.button.create.tool" action="/inventor/item/create-tool"/>
</jstl:otherwise>
</jstl:choose>