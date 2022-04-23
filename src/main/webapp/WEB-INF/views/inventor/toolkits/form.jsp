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
	<acme:input-textbox code="inventor.toolkits.list.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkits.list.label.title" path="title"/>
	<acme:input-textbox code="inventor.toolkits.list.label.description" path="description"/>
	<acme:input-textbox code="inventor.toolkits.list.label.asemblyNotes" path="asemblyNotes"/>
	<acme:input-textbox code="inventor.toolkits.list.label.info" path="info"/>
	<acme:input-money code="inventor.toolkits.list.label.totalPrice" path="totalPrice"/>
	
</acme:form>

