package acme.features.inventor.patronagereport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Patronagereport;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronagereportController extends AbstractController<Inventor, Patronagereport>{

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorPatronagereportListService listService;

	@Autowired
	protected InventorPatronagereportShowService showService;
	
	@Autowired
	protected InventorPatronagereportCreateService createService;

	// Constructors ---------------------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}
}