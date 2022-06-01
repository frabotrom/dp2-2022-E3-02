<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="patron.patronage.form.label.proposed" value="PROPOSED" selected="${ status == 'PROPOSED' }" />
		<acme:input-option code="patron.patronage.form.label.accepted" value="ACCEPTED" selected="${ status == 'ACCEPTED' }" />
		<acme:input-option code="patron.patronage.form.label.denied" value="DENIED" selected="${ status == 'DENIED' }" />
	</acme:input-select>

	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff" />
	<acme:input-money code="patron.patronage.form.label.budget" path="budget" />
	
	<jstl:choose>
		<jstl:when test="${published==true}">
			<acme:input-moment code="patron.patronage.form.label.creationDate" path="creationDate" />
		</jstl:when>
	</jstl:choose>

	<acme:input-moment code="patron.patronage.form.label.initialDate" path="initialDate" />
	<acme:input-moment code="patron.patronage.form.label.finalDate" path="finalDate" />
	<acme:input-url code="patron.patronage.form.label.link" path="optionalLink" />

	<jstl:choose>

		<jstl:when
			test="${acme:anyOf(command, 'create, update, delete, publish') && published==false}">
			<acme:input-select code="patron.patronage.form.label.select.inventor" path="inventors">
				<jstl:forEach items="${inventors}" var="optionInventor">
					<acme:input-option code="${optionInventor.userAccount.username}" value="${optionInventor.userAccount.username}"
										selected="${inventor.equals(optionInventor)}"/>
				</jstl:forEach>
			</acme:input-select>

		</jstl:when>

		<jstl:when test="${command=='show'}">
			<h2>
				<acme:message code="patron.patronage.message.inventor" />
			</h2>
			<acme:input-textbox code="patron.patronage.form.label.inventor.name" path="inventorName" />
			<acme:input-textbox code="patron.patronage.form.label.inventor.email" path="inventorEmail" />
			<acme:input-textbox code="patron.patronage.form.label.inventor.company" path="inventorCompany" />

		</jstl:when>

	</jstl:choose>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update" />
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete" />
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish" />
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create" />
		</jstl:when>
	</jstl:choose>

</acme:form>