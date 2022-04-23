package acme.features.inventor.toolkits;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

public class InventorToolkitsController extends AbstractController<Inventor,Toolkit>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorToolkitsListService	listService;

		@Autowired
		protected InventorToolkitsShowService	showService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.listService);
			super.addCommand("show", this.showService);
		}
}
