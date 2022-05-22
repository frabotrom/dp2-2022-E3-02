<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronage-report.form.label.link" path="optionalLink"/>

	<jstl:when test="${command == 'show'}">	
		<acme:input-textbox code="inventor.patronage-report.form.label.sequenceNumber" path="sequenceNumber"/>
		<acme:input-moment code="inventor.patronage-report.form.label.creationMoment" path="creationMoment"/>
	</jstl:when>	
	
	<jstl:when test="${command == 'create'}">
		<acme:input-checkbox code="inventor.patronage-report.form.label.confirm" path="confirm"/>
		<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronagereport/create?patronageId=${patronageId}"/>
	</jstl:when>

</acme:form> 