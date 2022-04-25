package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolController extends AbstractController<Inventor, Item>{
	// Internal state ---------------------------------------------------------

			@Autowired
			protected InventorToolListService	listService;

			@Autowired
			protected InventorToolShowService	showService;

			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addCommand("list", this.listService);
				super.addCommand("show", this.showService);
			}
}
