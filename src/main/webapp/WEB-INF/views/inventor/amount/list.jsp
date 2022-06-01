<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.amount.list.label.item.name" path="item.name" width="50%"/>
	<acme:list-column code="inventor.amount.list.label.amount" path="total" width="30%"/>	
	<acme:list-column code="inventor.amount.list.label.total-price" path="total-price" width="20%"/>
</acme:list>

<acme:button  test="${showCreate && visibleItems}" code="inventor.amount.list.button.create" action="/inventor/amount/create?id=${id}"/>