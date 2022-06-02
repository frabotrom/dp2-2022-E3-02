<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		
		<acme:menu-option code="master.menu.anonymous">
      		<acme:menu-suboption code="master.menu.any.user-account.list" action="/any/user-account/list"/>
      		<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.any.item.list-components" action="/any/item/list-components"/>
      		<acme:menu-suboption code="master.menu.any.item.Tool.list" action="/any/item/list-tools"/>
      		<acme:menu-suboption code="master.menu.any.toolkit.list" action="/any/toolkit/list-toolkits"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.any.chirp.list" action="/any/chirp/list"/>

		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/admin-dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.system-configuration" action="/administrator/system-configuration/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.announcement.create" action="/administrator/announcement/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.inventor" access="hasRole('Inventor')">
			<acme:menu-suboption code="master.menu.inventor.patronage" action="/inventor/patronage/list"/>
			<acme:menu-suboption code="master.menu.inventor.patronagereport" action="/inventor/patronagereport/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.inventor.components" action="/inventor/item/list-components"/>
			<acme:menu-suboption code="master.menu.inventor.tools" action="/inventor/item/list-tools"/>	
			<acme:menu-suboption code="master.menu.inventor.toolkit" action="/inventor/toolkit/list-toolkits"/>	
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.inventor.chimpum" action="/inventor/chimpum/list"/>
		</acme:menu-option>	
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
			<acme:menu-suboption code="master.menu.patron.list-patronage" action="/patron/patronage/list"/>
			<acme:menu-suboption code="master.menu.patron.list-patronage-reports" action="/patron/patronagereport/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.patron.dashboard" action="/patron/patron-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.autheticated.announcement.list" action="/authenticated/announcement/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.autheticated.system-configuration" action="/authenticated/system-configuration/show"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
			
	</acme:menu-right>
</acme:menu-bar>

