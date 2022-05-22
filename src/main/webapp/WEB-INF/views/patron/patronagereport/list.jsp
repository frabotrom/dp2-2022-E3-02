<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage-report.form.label.sequenceNumber" path="sequenceNumber"/>
	<acme:list-column code="patron.patronage-report.form.label.creationMoment" path="creationMoment"/>
	<acme:list-column code="patron.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:list-column code="patron.patronage-report.form.label.patronage.code" path="patronage.code"/>
	
</acme:list>