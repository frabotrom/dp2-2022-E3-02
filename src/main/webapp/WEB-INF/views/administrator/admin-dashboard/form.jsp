<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${ readonly }">

	<%-- Global--%>
	<h3><acme:message code="admin.patronage.form.label.titleGlobal"/></h3>
	<acme:input-integer code="admin.dashboard.form.label.totalComponents" path="totalComponents"/>
	<acme:input-integer code="admin.dashboard.form.label.totalTools" path="totalTools"/>	
	<acme:input-textarea code="admin.dashboard.form.label.totalPatronages" path="totalPatronages"/>
	
	<%-- Components--%>
	<hr>
    <br>
	<h3><acme:message code="admin.patronage.form.label.titleComponents"/></h3>
	<acme:input-textarea code="admin.dashboard.form.label.averagePriceComponents" path="averagePriceComponents"/>
	<acme:input-textarea code="admin.dashboard.form.label.deviationPriceComponents" path="deviationPriceComponents"/>	
	<acme:input-textarea code="admin.dashboard.form.label.minimunPriceComponents" path="minimunPriceComponents"/>
	<acme:input-textarea code="admin.dashboard.form.label.maximunPriceComponents" path="maximunPriceComponents"/>
	
	<%-- Tools--%>
	<hr>
    <br>
	<h3><acme:message code="admin.patronage.form.label.titleTools"/></h3>
	<acme:input-textarea code="admin.dashboard.form.label.averagePriceTools" path="averagePriceTools"/>
	<acme:input-textarea code="admin.dashboard.form.label.deviationPriceTools" path="deviationPriceTools"/>	
	<acme:input-textarea code="admin.dashboard.form.label.minimunPriceTools" path="minimunPriceTools"/>
	<acme:input-textarea code="admin.dashboard.form.label.maximunPriceTools" path="maximunPriceTools"/>

	<%-- Total Patronages--%>
	<hr>
    <br>
	<h3><acme:message code="admin.patronage.form.label.titleTotal"/></h3>
	<acme:input-integer code="admin.dashboard.form.label.totalProposedPatronages" path="totalProposedPatronages"/>
	<acme:input-integer code="admin.dashboard.form.label.totalAcceptedPatronages" path="totalAcceptedPatronages"/>	
	<acme:input-integer code="admin.dashboard.form.label.totalDeniedPatronages" path="totalDeniedPatronages"/>
	
	<%-- Proposed--%>
	<hr>
    <br>
    <h3><acme:message code="admin.patronage.form.label.titleProposed"/></h3>
	<acme:input-textarea code="admin.dashboard.form.label.averageBudgetProposed" path="averageBudgetProposed"/>
	<acme:input-textarea code="admin.dashboard.form.label.deviationBudgetProposed" path="deviationBudgetProposed"/>
	<acme:input-textarea code="admin.dashboard.form.label.minimunBudgetProposed" path="minimunBudgetProposed"/>
	<acme:input-textarea code="admin.dashboard.form.label.maximunBudgetProposed" path="maximunBudgetProposed"/>
	
	<%-- Accepted--%>
	<hr>
    <br>
    <h3><acme:message code="admin.patronage.form.label.titleAccepted"/></h3>
	<acme:input-textarea code="admin.dashboard.form.label.averageBudgetAccepted" path="averageBudgetAccepted"/>
	<acme:input-textarea code="admin.dashboard.form.label.deviationBudgetAccepted" path="deviationBudgetAccepted"/>
	<acme:input-textarea code="admin.dashboard.form.label.minimunBudgetAccepted" path="minimunBudgetAccepted"/>
	<acme:input-textarea code="admin.dashboard.form.label.maximunBudgetAccepted" path="maximunBudgetAccepted"/>
	
	<%-- Denied--%>
	<hr>
    <br>
    <h3><acme:message code="admin.patronage.form.label.titleDenied"/></h3>
	<acme:input-textarea code="admin.dashboard.form.label.averageBudgetDenied" path="averageBudgetDenied"/>
	<acme:input-textarea code="admin.dashboard.form.label.deviationBudgetDenied" path="deviationBudgetDenied"/>
	<acme:input-textarea code="admin.dashboard.form.label.minimunBudgetDenied" path="minimunBudgetDenied"/>
	<acme:input-textarea code="admin.dashboard.form.label.maximunBudgetDenied" path="maximunBudgetDenied"/>				
</acme:form>