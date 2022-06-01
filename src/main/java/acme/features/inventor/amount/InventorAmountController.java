package acme.features.inventor.amount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Amount;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorAmountController extends AbstractController<Inventor, Amount>{
	
	@Autowired
	protected InventorAmountListService listService;	
	
	@Autowired
	protected InventorAmountShowService showService;

	@Autowired
	protected InventorAmountCreateService createService;
	
//	@Autowired
//	protected InventorAmountDeleteService deleteService;
//	
//	@Autowired
//	protected InventorAmountUpdateService updateService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
//		super.addCommand("delete", this.deleteService);
//		super.addCommand("update", this.updateService);
	}
	
}