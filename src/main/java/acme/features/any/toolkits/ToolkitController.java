package acme.features.any.toolkits;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class ToolkitController extends AbstractController<Any,Toolkit> {

	@Autowired
	protected ToolkitListService toolkitListService;

	@Autowired
	protected ToolkitService toolkitService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.toolkitService);
		super.addCommand("list-toolkits","list", this.toolkitListService);

	}
	
}
