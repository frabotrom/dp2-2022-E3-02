package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any,Item> {

	@Autowired
	protected AnyComponentListService listAllComponentsService;
	
	@Autowired
	protected AnyComponentShowService componentShowService;
	
	@Autowired
	protected AnyToolListService	listAllToolsService;

	@Autowired
	protected AnyToolShowService	toolShowService;
  
	@Autowired
	protected AnyToolListByToolkit	listAllByToolkitService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.toolShowService);
		super.addCommand("list-tools", "list", this.listAllToolsService);
		super.addCommand("list-items-toolkit",  "list", this.listAllByToolkitService);
		super.addCommand("show-component", this.componentShowService);
		super.addCommand("list-components","list", this.listAllComponentsService);
	}
}
