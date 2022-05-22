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
	protected InventorComponentListService 	  listComponentService;
		
	@Autowired
	protected InventorComponentCreateService  createComponentService;
	
	
	//Tools
	@Autowired
	protected InventorToolListService	      listToolService;
	
	@Autowired 
	protected InventorToolCreateService       createToolService;
	
	
	//Item
	@Autowired
	protected InventorItemPublishService      publishItemService;
	
	@Autowired
	protected InventorItemShowService	      showItemService;
	
	@Autowired
	protected InventorItemUpdateService       updateItemService;
	
	@Autowired 
	protected InventorItemDeleteService       deleteItemService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list-components","list", this.listComponentService);
		super.addCommand("create-component","create", this.createComponentService);
		super.addCommand("list-tools","list", this.listToolService);
		super.addCommand("create-tool","create", this.createToolService);
		super.addCommand("show", this.showItemService);
		super.addCommand("delete", this.deleteItemService);
		super.addCommand("update", this.updateItemService);
		super.addCommand("publish","update",this.publishItemService);


	}
}
