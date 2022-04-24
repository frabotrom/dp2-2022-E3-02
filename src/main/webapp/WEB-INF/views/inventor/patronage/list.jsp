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


<acme:list>
	<acme:list-column code="inventor.patronage.list.label.code" path="code" width="15%"/>
	<acme:list-column code="inventor.patronage.list.label.status" path="status" width="15%"/>
	<acme:list-column code="inventor.patronage.list.label.budget" path="budget" width="15%"/>
	<acme:list-column code="inventor.patronage.list.label.initialDate" path="initialDate" width="15%"/>
	<acme:list-column code="inventor.patronage.list.label.finalDate" path="finalDate" width="15%"/>
	<acme:list-column code="inventor.patronage.list.label.patron" path="patron" width="25%"/>
</acme:list>
