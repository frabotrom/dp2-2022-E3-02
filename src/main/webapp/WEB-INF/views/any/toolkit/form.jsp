<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.toolkit.list.label.code" path="code"/>
	<acme:input-textbox code="any.toolkit.list.label.title" path="title"/>
	<acme:input-textbox code="any.toolkit.list.label.description" path="description"/>
	<acme:input-textbox code="any.toolkit.list.label.assemblyNotes" path="asemblyNotes"/>
	<acme:input-url code="any.toolkit.list.label.info" path="info"/>
</acme:form>
