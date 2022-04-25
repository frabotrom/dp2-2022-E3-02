<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textarea code="administrator.configuration.form.label.strong-spam-terms" path="strongSpamTerms"/>
	<acme:input-textarea code="administrator.configuration.form.label.weak-spam-terms" path="weakSpamTerms"/>
	<acme:input-textbox code="administrator.configuration.form.label.system-currency" path="systemCurrency"/>
	<acme:input-textarea code="administrator.configuration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<acme:input-double code="administrator.configuration.form.label.weak-threshold" path="weakThreshold"/>
	<acme:input-double code="administrator.configuration.form.label.strong-threshold" path="strongThreshold"/>

</acme:form>