<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.configuration.form.label.system-currency" path="systemCurrency"/>
	<acme:input-textarea code="authenticated.configuration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<b><acme:message code="authenticated.configuration.form.service"/></b><acme:message code=" https://api.exchangerate.host/latest?base={0}&symbols={1}"/>
	<br>
</acme:form>