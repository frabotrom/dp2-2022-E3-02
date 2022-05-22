<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="patron.patronage-report.form.label.sequenceNumber" path="sequenceNumber"/>
	<acme:input-moment code="patron.patronage-report.form.label.creationMoment" path="creationMoment"/>	
	<acme:input-textarea  code="patron.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="patron.patronage-report.form.label.optionalLink" path="optionalLink"/>

	<hr>
    <br>
    <h3><acme:message code="patron.patronage-report.form.label.title"/></h3>

	<acme:input-textbox code="patron.patronage-report.form.label.patronage.code" path="patronage.code"/>
	<acme:input-textbox code="patron.patronage-report.form.label.patronage.status" path="patronage.status"/>
	<acme:input-textbox code="patron.patronage-report.form.label.patronage.legalStuff" path="patronage.legalStuff"/>
	<acme:input-money code="patron.patronage-report.form.label.patronage.budget" path="patronage.budget"/>
	<acme:input-moment code="patron.patronage-report.form.label.patronage.creationDate" path="patronage.creationDate"/>
	<acme:input-moment code="patron.patronage-report.form.label.patronage.initialDate" path="patronage.initialDate"/>
	<acme:input-moment code="patron.patronage-report.form.label.patronage.finalDate" path="patronage.finalDate"/>
	<acme:input-url code="patron.patronage-report.form.label.patronage.optionalLink" path="patronage.optionalLink"/>

	
</acme:form>