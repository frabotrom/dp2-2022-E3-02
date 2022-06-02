<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textarea code="inventor.chimpum.form.label.description" path="description"/>		
	<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/>
	
	<%-- Para que no se muestre a crearlo--%>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-moment code="inventor.chimpum.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	
	<acme:input-moment code="inventor.chimpum.form.label.initialDate" path="initialDate"/>
	<acme:input-moment code="inventor.chimpum.form.label.finalDate" path="finalDate"/>	
	<acme:input-url code="inventor.chimpum.form.label.optionalLink" path="optionalLink"/>
	
	<%-- Para que no se muestre a crearlo--%>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:input-integer code="inventor.chimpum.form.label.period" path="period" readonly="true"/>	
	</jstl:if>	
	
	
	<%-- Artefact Profile--%>
	<%-- Para que no se muestre a crearlo--%>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">	
		<hr>
	    <br>
	    <h3><acme:message code="inventor.chimpum.form.label.artefact.title"/></h3>
		<acme:input-textbox code="inventor.chimpum.form.label.artefactName" path="artefactName" readonly="true"/>
		<acme:input-textbox code="inventor.chimpum.form.label.artefactCode" path="artefactCode" readonly="true"/>
		<acme:input-textbox code="inventor.chimpum.form.label.artefactTechnology" path="artefactTechnology" readonly="true"/>
		<acme:input-textarea code="inventor.chimpum.form.label.artefactDescription" path="artefactDescription" readonly="true"/>
		<acme:input-money code="inventor.chimpum.form.label.artefactRetailPrice" path="artefactRetailPrice" readonly="true"/>
		<acme:input-url code="inventor.chimpum.form.label.artefactLink" path="artefactLink" readonly="true"/>
		<acme:input-textbox code="inventor.chimpum.form.label.artefactType" path="artefactType" readonly="true"/>
		<acme:input-select code="inventor.chimpum.form.label.artefactVisible" path="artefactVisible" readonly="true">
			<acme:input-option code="Not Visible" value="FALSE" selected="${artefactVisible == false}"/>
			<acme:input-option code="Visible" value="TRUE" selected="${artefactVisible == true}"/>
		</acme:input-select>	
	</jstl:if>
	
	
	<jstl:if test="${acme:anyOf(command, 'create')}">
		<acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create?itemId=${itemId}"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:submit code="inventor.chimpum.form.button.update" action="/inventor/chimpum/update"/>
		<acme:submit code="inventor.chimpum.form.button.delete" action="/inventor/chimpum/delete"/>
	</jstl:if>
	
	
	
</acme:form>

