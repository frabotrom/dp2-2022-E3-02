<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.item.list.label.name" path="name"/>
	
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command,'update-component,show')}">
		<acme:input-textbox code="inventor.item.list.label.code" path="code" readonly="true"/>
	</jstl:when>
	<jstl:otherwise>
		<acme:input-textbox code="inventor.item.list.label.code" path="code"/>
	</jstl:otherwise>
	</jstl:choose>
	
	
	<acme:input-textbox code="inventor.item.list.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.item.list.label.description" path="description"/>
	<acme:input-url code="inventor.item.list.label.link" path="link"/>
	<acme:input-money code="inventor.item.list.label.retailPrice" path="retailPrice"/>
	<acme:input-money code="inventor.item.list.label.retailPriceModified" path="retailPriceModified" readonly="true"/>
	
	<jstl:choose>
	<jstl:when test="${acme:anyOf(command,'show')}">
	<acme:input-select code="inventor.item.list.label.visible" path="visible" readonly="true">
		<acme:input-option code="Not Visible" value="FALSE" selected="${visible == false}"/>
		<acme:input-option code="Visible" value="TRUE" selected="${visible == true}"/>
	</acme:input-select>
	</jstl:when>
	</jstl:choose>
	
	
	<jstl:choose>
        <jstl:when test="${acme:anyOf(command,'create-component')}">
            <acme:submit code="inventor.item.form.button.create" action="/inventor/item/create-component"/>
        </jstl:when>
         <jstl:when test="${acme:anyOf(command,'create-tool')}">
            <acme:submit code="inventor.item.form.button.create.tool" action="/inventor/item/create-tool"/>
        </jstl:when>
        <jstl:when test="${acme:anyOf(command,'show,update,delete,publish') && visible == false && type=='COMPONENT'}"> 
            <acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
            <acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete"/>
        	<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>
        </jstl:when>
        <jstl:when test="${acme:anyOf(command,'show,update,delete,publish') && visible == false && type=='TOOL'}"> 
            <acme:submit code="inventor.item.tool.form.button.update" action="/inventor/item/update"/>
            <acme:submit code="inventor.item.tool.form.button.delete" action="/inventor/item/delete"/>
        	<acme:submit code="inventor.item.tool.form.button.publish" action="/inventor/item/publish"/>
        </jstl:when>         
    </jstl:choose>
	
</acme:form>
