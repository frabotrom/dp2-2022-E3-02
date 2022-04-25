<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.item.list.label.name" path="name"/>
	<acme:input-textbox code="any.item.list.label.code" path="code"/>
	<acme:input-textbox code="any.item.list.label.technology" path="technology"/>
	<acme:input-textarea code="any.item.list.label.description" path="description"/>
	<acme:input-url code="any.item.list.label.link" path="link"/>
	<acme:input-money code="any.item.list.label.retailPrice" path="retailPrice"/>
	<acme:input-select code="any.item.list.label.visible" path="visible">
		<acme:input-option code="Visible" value="TRUE" selected="${visible == true}"/>
		<acme:input-option code="Not Visible" value="FALSE" selected="${visible == false}"/>
	</acme:input-select>
	
	
</acme:form>
