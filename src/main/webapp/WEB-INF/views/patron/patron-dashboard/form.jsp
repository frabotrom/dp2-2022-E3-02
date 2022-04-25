<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${ readonly }">
	<h3><acme:message code="patron.patronage.form.label.titleTotal"/></h3>
	<acme:input-integer code="patron.dashboard.form.label.totalProposedPatronages" path="totalProposedPatronages"/>
	<acme:input-integer code="patron.dashboard.form.label.totalAcceptedPatronages" path="totalAcceptedPatronages"/>	
	<acme:input-integer code="patron.dashboard.form.label.totalDeniedPatronages" path="totalDeniedPatronages"/>
	
	<%-- Proposed--%>
	<hr>
    <br>
    <h3><acme:message code="patron.patronage.form.label.titleProposed"/></h3>
	<acme:input-textarea code="patron.dashboard.form.label.averageBudgetProposed" path="averageBudgetProposed"/>
	<acme:input-textarea code="patron.dashboard.form.label.deviationBudgetProposed" path="deviationBudgetProposed"/>
	<acme:input-textarea code="patron.dashboard.form.label.minimunBudgetProposed" path="minimunBudgetProposed"/>
	<acme:input-textarea code="patron.dashboard.form.label.maximunBudgetProposed" path="maximunBudgetProposed"/>
	
	<%-- Accepted--%>
	<hr>
    <br>
    <h3><acme:message code="patron.patronage.form.label.titleAccepted"/></h3>
	<acme:input-textarea code="patron.dashboard.form.label.averageBudgetAccepted" path="averageBudgetAccepted"/>
	<acme:input-textarea code="patron.dashboard.form.label.deviationBudgetAccepted" path="deviationBudgetAccepted"/>
	<acme:input-textarea code="patron.dashboard.form.label.minimunBudgetAccepted" path="minimunBudgetAccepted"/>
	<acme:input-textarea code="patron.dashboard.form.label.maximunBudgetAccepted" path="maximunBudgetAccepted"/>
	
	<%-- Denied--%>
	<hr>
    <br>
    <h3><acme:message code="patron.patronage.form.label.titleDenied"/></h3>
	<acme:input-textarea code="patron.dashboard.form.label.averageBudgetDenied" path="averageBudgetDenied"/>
	<acme:input-textarea code="patron.dashboard.form.label.deviationBudgetDenied" path="deviationBudgetDenied"/>
	<acme:input-textarea code="patron.dashboard.form.label.minimunBudgetDenied" path="minimunBudgetDenied"/>
	<acme:input-textarea code="patron.dashboard.form.label.maximunBudgetDenied" path="maximunBudgetDenied"/>				
</acme:form>