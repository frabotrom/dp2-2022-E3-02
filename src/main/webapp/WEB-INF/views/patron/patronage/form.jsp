<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="patron.patronage.form.label.proposed" value="PROPOSED" selected="${ status == 'PROPOSED' }"/>
		<acme:input-option code="patron.patronage.form.label.accepted" value="ACCEPTED" selected="${ status == 'ACCEPTED' }"/>
		<acme:input-option code="patron.patronage.form.label.denied" value="DENIED" selected="${ status == 'DENIED' }"/>
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.creationDate" path="creationDate"/>
	<acme:input-moment code="patron.patronage.form.label.initialDate" path="initialDate"/>
	<acme:input-moment code="patron.patronage.form.label.finalDate" path="finalDate"/>
	<acme:input-url code="patron.patronage.form.label.optionalLink" path="optionalLink"/>
	<acme:input-integer code="patron.patronage.form.label.period" path="period"/>
	
	<%-- Inventor Profile--%>
	<hr>
    <br>
    <h3><acme:message code="patron.patronage.form.label.title"/></h3>
    <acme:input-textbox code="patron.patronage.form.label.name" path="inventorName"/>
    <acme:input-email code="patron.patronage.form.label.email" path="inventorEmail"/>
    <acme:input-textbox code="patron.patronage.form.label.company" path="inventorCompany"/>	
    
    <jstl:choose>
    	<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && PatronageStatus == PROPOSED }">
    		<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
    		<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
    		<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
    	</jstl:when>
    	<jstl:when test="${command == 'create' }">
    		<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
    	</jstl:when>
    </jstl:choose>	
</acme:form>