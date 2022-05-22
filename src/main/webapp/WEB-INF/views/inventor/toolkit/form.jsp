<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.list.label.code" path="code"/>
	<acme:input-textbox code="inventor.toolkit.list.label.title" path="title"/>
	<acme:input-textarea code="inventor.toolkit.list.label.description" path="description"/>
	<acme:input-textbox code="inventor.toolkit.list.label.assemblyNotes" path="asemblyNotes"/>
	<acme:input-url code="inventor.toolkit.list.label.info" path="info"/>
	<acme:input-url code="inventor.toolkit.list.label.draftMode" path="draftMode"/>
	<acme:button code="inventor.toolkit.form.button.items" action="/inventor/item/list-items-toolkit?id=${id}"/>
</acme:form>
