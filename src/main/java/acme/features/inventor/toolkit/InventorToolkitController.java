package acme.features.inventor.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolkitController extends AbstractController<Inventor,Toolkit> {

	@Autowired
	protected InventorToolkitListService toolkitListService;

	@Autowired
	protected InventorToolkitShowService toolkitService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.toolkitService);
		super.addCommand("list-toolkits","list", this.toolkitListService);

	}
	
}
