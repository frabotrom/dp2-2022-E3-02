/*
 * AnonymousShoutController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item> {

	// Internal state ---------------------------------------------------------
	
	//Components
	@Autowired
	protected InventorComponentListService	 listComponentService;
		
	@Autowired
	protected InventorComponentCreateService  createComponentService;
	
	@Autowired
	protected InventorComponentDeleteService  deleteComponentService;

	@Autowired
	protected InventorComponentUpdateService  updateComponentService;
	
	@Autowired
	protected InventorComponentPublishService publishComponentService;
	
	//Tools
	@Autowired
	protected InventorToolListService	 listToolService;
	
	@Autowired
	protected InventorItemShowService	  showItemService;
	
	@Autowired 
	protected InventorToolCreateService createToolService;
	
	@Autowired 
	protected InventorToolDeleteService deleteToolService;
	
	@Autowired
	protected InventorToolPublishService publishToolService;
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-components","list", this.listComponentService);
		super.addCommand("create-component","create", this.createComponentService);
		super.addCommand("delete-component","delete", this.deleteComponentService);
		super.addCommand("update-component","update", this.updateComponentService);
		super.addCommand("publish-component","update",this.publishComponentService);
		super.addCommand("list-tools","list", this.listToolService);
		super.addCommand("delete-tool","delete", this.deleteToolService);
		super.addCommand("create-tool","create", this.createToolService);
		super.addCommand("publish-tool","update",this.publishToolService);
		super.addCommand("show", this.showItemService);


	}
}
