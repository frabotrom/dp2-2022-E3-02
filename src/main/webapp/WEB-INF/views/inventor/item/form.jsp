<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.item.list.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.list.label.code" path="code"/>
	<acme:input-textbox code="inventor.item.list.label.technology" path="technology"/>
	<acme:input-textbox code="inventor.item.list.label.description" path="description"/>
	<acme:input-url code="inventor.item.list.label.link" path="link" placeholder="-----"/>
	<acme:input-money code="inventor.item.list.label.retailPrice" path="retailPrice"/>
</acme:form>
