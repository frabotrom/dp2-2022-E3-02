<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readOnly}"> 
	
	<acme:input-textbox code="inventor.amount.form.label.amount" path="total"/>
	
	<jstl:choose>		
	
		<jstl:when test="${acme:anyOf(command, 'show,update,delete')}">
		
			<acme:input-textbox code="inventor.item.form.label.name" path="item.name" readonly="true"/>			
			<acme:input-textbox code="inventor.item.form.label.code" path="item.code" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.technology" path="item.technology" readonly="true"/>
			<acme:input-textarea code="inventor.item.form.label.description" path="item.description" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.type" path="item.type" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.link" path="item.link" readonly="true"/>
			
			<acme:button test="${draftMode == true}" code="inventor.amount.form.button.update" action="/inventor/amount/update"/>
			<acme:button test="${draftMode == true}" code="inventor.amount.form.button.delete" action="/inventor/amount/delete"/>	
				
		</jstl:when>
	
		<jstl:when test="${command == 'create'}">	
			
			<acme:input-textbox code="inventor.amount.form.label.select.item" path="item.name"/>
			
			<acme:submit code="inventor.amount.form.button.create" action="/inventor/amount/create?id=${id}"/>
						
		</jstl:when>	
		
	</jstl:choose>
	
</acme:form>