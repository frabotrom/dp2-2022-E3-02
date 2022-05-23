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

<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code"/>
	<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff" path="legalStuff"/>	
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="inventor.patronage.form.label.creationDate" path="creationDate"/>
	<acme:input-moment code="inventor.patronage.form.label.initialDate" path="initialDate"/>
	<acme:input-moment code="inventor.patronage.form.label.finalDate" path="finalDate"/>	
	<acme:input-url code="inventor.patronage.form.label.optionalLink" path="optionalLink"/>	
	<acme:input-integer code="inventor.patronage.form.label.period" path="period"/>
	
	<hr>
    <br>
	<acme:button  code="inventor.patronage.list.button.createReport" action="/inventor/patronagereport/create?patronageId=${patronageId}"/>
	
	<%-- Patron Profile--%>
	<hr>
    <br>
    <h3><acme:message code="inventor.patronage.form.label.title"/></h3>
	<acme:input-textbox code="inventor.patronage.form.label.patronName" path="patronName"/>
	<acme:input-email code="inventor.patronage.form.label.patronEmail" path="patronEmail"/>
	<acme:input-textbox code="inventor.patronage.form.label.patronCompany" path="patronCompany"/>
</acme:form>

