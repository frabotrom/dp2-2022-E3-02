<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.item.list.label.name" path="name"/>
	<acme:input-textbox code="inventor.item.list.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="inventor.item.list.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.list.label.description" path="description"/>
	<acme:input-url code="inventor.item.list.label.link" path="link"/>
	<acme:input-money code="inventor.item.list.label.retailPrice" path="retailPrice"/>
	<acme:input-select code="inventor.item.list.label.visible" path="visible" readonly="true">
		<acme:input-option code="Not Visible" value="FALSE" selected="${visible == false}"/>
		<acme:input-option code="Visible" value="TRUE" selected="${visible == true}"/>
	</acme:input-select>
	
	<jstl:choose>
        <jstl:when test="${acme:anyOf(command,'create-component')}">
            <acme:submit code="inventor.item.form.button.create" action="/inventor/item/create-component"/>
        </jstl:when>
        <jstl:when test="${acme:anyOf(command,'show,update-component,delete-component,publish-component') && visible == false}"> 
            <acme:submit code="inventor.item.form.button.update" action="/inventor/item/update-component"/>
            <acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete-component"/>
        	<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish-component"/>

        </jstl:when>        
    </jstl:choose>
	
</acme:form>
