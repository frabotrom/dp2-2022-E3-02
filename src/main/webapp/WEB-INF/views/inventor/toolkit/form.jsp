<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.list.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkit.list.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.list.label.description" path="description"/>
	<acme:input-textbox code="inventor.toolkit.list.label.assemblyNotes" path="asemblyNotes"/>
	<acme:input-url code="inventor.toolkit.list.label.info" path="info"/>

	<jstl:choose>
	
		<jstl:when test="${draftMode == true && acme:anyOf(command, 'show,update,delete,publish')}">
		<acme:input-money code="inventor.toolkit.form.label.price" path="price" placeholder="${price}" readonly="true"/>
		<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update"/>
		<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/toolkit/delete"/>
		<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/toolkit/publish"/>
		
	<acme:button code="inventor.toolkit.form.button.items" action="/inventor/item/list-items-toolkit?id=${id}"/>
		
		</jstl:when>
		<jstl:when test="${draftMode == false && command == 'show'}">
		<acme:input-money code="inventor.toolkit.form.label.price" path="price" placeholder="${price}" readonly="true"/>
		
	<acme:button code="inventor.toolkit.form.button.items" action="/inventor/item/list-items-toolkit?id=${id}"/>
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">		
		<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/toolkit/create"/>			
		</jstl:when>	
		
	</jstl:choose>



</acme:form>
