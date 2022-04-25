<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form readonly="${readonly}">

<h1>
	<acme:message code="administrator.dashboard.form.title" />
</h1>

<h2>
	<acme:message
		code="administrator.dashboard.form.label.totalComponents" />
	<acme:print value="${totalComponents}" />
</h2>


<h3>
	<acme:message
		code="administrator.dashboard.form.label.averagePriceComponents" />
</h3>

<table class="table table-sm"
	id="id-averagePriceComponents">


	<jstl:forEach items="${ technology }" var="technology">

		<tr>
			<th scope="row"><acme:print value="${ technology }" /></th>
			<jstl:set
				value="${ averagePriceComponents.entrySet().stream().filter(e -> e.getKey().getFirst().equals(technology)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
						<td style="width: 10%"><acme:message
								code="${ entry.getKey().getSecond() }" />
						</td>
						<td><acme:print value="${ entry.getValue() }" /></td>
					</tr>
				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.deviationPriceComponents" />
</h3>

<table class="table table-sm"
	id="id-deviationPriceComponents">


	<jstl:forEach items="${ technology }" var="technology">

		<tr>
			<th scope="row"><acme:print value="${ technology }" /></th>
			<jstl:set
				value="${ deviationPriceComponents.entrySet().stream().filter(e -> e.getKey().getFirst().equals(technology)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
						<td style="width: 10%"><acme:message
								code="${ entry.getKey().getSecond() }" />
						</td>
						<td><acme:print value="${ entry.getValue() }" /></td>
					</tr>
				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>


<h3>
	<acme:message
		code="administrator.dashboard.form.label.minimumPriceComponents" />
</h3>

<table class="table table-sm"
	id="id-minimumPriceComponents">


	<jstl:forEach items="${ technology }" var="technology">

		<tr>
			<th scope="row"><acme:print value="${ technology }" /></th>
			<jstl:set
				value="${ minimumPriceComponents.entrySet().stream().filter(e -> e.getKey().getFirst().equals(technology)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
						<td style="width: 10%"><acme:message
								code="${ entry.getKey().getSecond() }" />
						</td>
						<td><acme:print value="${ entry.getValue() }" /></td>
					</tr>
				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.maximumPriceComponents" />
</h3>

<table class="table table-sm"
	id="id-maximumPriceComponents">


	<jstl:forEach items="${ technology }" var="technology">

		<tr>
			<th scope="row"><acme:print value="${ technology }" /></th>
			<jstl:set
				value="${ maximumPriceComponents.entrySet().stream().filter(e -> e.getKey().getFirst().equals(technology)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">
					<tr>
						<td style="width: 10%"><acme:message
								code="${ entry.getKey().getSecond() }" />
						</td>
						<td><acme:print value="${ entry.getValue() }" /></td>
					</tr>
				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message
		code="administrator.dashboard.form.label.totalTools" />
	<acme:print value="${totalTools}" />
</h2>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.averagePriceTools" />
</h3>

<table class="table table-sm"
	id="id-averagePriceTools">


	<jstl:forEach items="${ currency }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ averagePriceTools.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row"><acme:print value="${ entry.getValue() }" /></th>

				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.deviationPriceTools" />
</h3>

<table class="table table-sm"
	id="id-deviationPriceTools">


	<jstl:forEach items="${ currency }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ deviationPriceTools.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row"><acme:print value="${ entry.getValue() }" /></th>

				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.maximumPriceTools" />
</h3>

<table class="table table-sm"
	id="id-maximumPriceTools">


	<jstl:forEach items="${ currency }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ maximumPriceTools.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row"><acme:print value="${ entry.getValue() }" /></th>

				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.minimumPriceTools" />
</h3>

<table class="table table-sm"
	id="id-minimumPriceTools">


	<jstl:forEach items="${ currency }" var="currency">

		<tr>
			<th scope="row"><acme:print value="${ currency }" /></th>
			<jstl:set
				value="${ minimumPriceTools.entrySet().stream().filter(e -> e.getKey().equals(currency)).iterator() }"
				var="entrySet" />
			<jstl:if test="${ entrySet.hasNext() }">
				<jstl:forEach items="${ entrySet }" var="entry">

					<th scope="row"><acme:print value="${ entry.getValue() }" /></th>

				</jstl:forEach>
			</jstl:if>
		</tr>
	</jstl:forEach>
</table>
<h2>
	<acme:message
		code="administrator.dashboard.form.label.totalPatronages" />
</h2>

<table class="table table-sm" id="id-totalPatronages">

	<jstl:forEach items="${ totalPatronages.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ totalPatronages.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>


<h3>
	<acme:message
		code="administrator.dashboard.form.label.averagePatronagesBudget" />
</h3>
<table class="table table-sm" id="id-averagePatronagesBudget">

	<jstl:forEach items="${ averagePatronagesBudget.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ averagePatronagesBudget.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.deviationPatronagesBudget" />
</h3>
<table class="table table-sm" id="id-deviationPatronagesBudget">

	<jstl:forEach items="${ deviationPatronagesBudget.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ deviationPatronagesBudget.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.minimumPatronagesBudget" />
</h3>
<table class="table table-sm" id="id-minimumPatronagesBudget">

	<jstl:forEach items="${ minimumPatronagesBudget.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ minimumPatronagesBudget.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message
		code="administrator.dashboard.form.label.maximumPatronagesBudget" />
</h3>
<table class="table table-sm" id="id-maximumPatronagesBudget">

	<jstl:forEach items="${ maximumPatronagesBudget.keySet() }"
		var="key">
		<tr>
			<jstl:set value="${ maximumPatronagesBudgets.get(key) }"
				var="amount" />
			<jstl:if test="${ amount>0 }">
				<th scope="row" style="width: 10%"><acme:message
						code="${ key }" /></th>
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>

		</tr>
	</jstl:forEach>
</table>
</acme:form>