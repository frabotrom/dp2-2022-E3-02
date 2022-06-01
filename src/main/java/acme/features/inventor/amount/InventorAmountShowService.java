package acme.features.inventor.amount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Amount;
import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorAmountShowService implements AbstractShowService<Inventor, Amount>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorAmountRepository repository;

	// AbstractListService<Inventor, Artifact> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;
		
		final Integer amountId = request.getModel().getInteger("id");
		final Toolkit toolkit = this.repository.findToolkitByAmountId(amountId);
		final Integer activeId = request.getPrincipal().getActiveRoleId();
		
		return (toolkit.getInventor().getId()==activeId);	
	}

	@Override
	public Amount findOne(final Request<Amount> request) {
		assert request != null;
	
		Integer id;
		Amount amount;
		id = request.getModel().getInteger("id");
		amount = this.repository.findAmountById(id);
		
		return amount;
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
				
		final Item item = this.repository.findItemByAmountId(entity.getId());

		request.unbind(entity, model, "total", "item.name");
		model.setAttribute("item.name", item.getName());
		model.setAttribute("item.code", item.getCode());
		model.setAttribute("item.technology", item.getTechnology());
		model.setAttribute("item.retailPrice", item.getRetailPrice());
		model.setAttribute("item.description", item.getDescription());
		model.setAttribute("item.type", item.getType());
		model.setAttribute("item.link", item.getLink());
		model.setAttribute("isDraftMode", entity.getToolkit().isDraftMode());
	}
}