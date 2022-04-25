package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyToolController extends AbstractController<Any, Item>{

	@Autowired
	protected AnyToolListService	listAllService;

	@Autowired
	protected AnyToolShowService	showService;
	
	@Autowired
	protected AnyToolListByToolkit	listAllByToolkitService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listAllService);
		super.addCommand("list-items-toolkit",  "list", this.listAllByToolkitService);

	}
}