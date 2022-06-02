package acme.features.inventor.amount;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;

import acme.entities.Amount;
import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorAmountCreateService  implements AbstractCreateService<Inventor,Amount>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorAmountRepository repository;
	
	// AbstractListService<Inventor, Item> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;
		
		final Integer toolkitId = request.getModel().getInteger("id");
		final Toolkit toolkit = this.repository.findToolkitById(toolkitId);
		final Integer activeId = request.getPrincipal().getActiveRoleId();
		final boolean publishedItems = !this.repository.findVisibleItems().isEmpty();
		
		return (toolkit.isDraftMode() && toolkit.getInventor().getId()==activeId && publishedItems);	
		
	}

	@Override
	public void bind(final Request<Amount> request, final Amount entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		String itemName;
		Item item;
		
		itemName = (String) request.getModel().getAttribute("item.name");
		item = this.repository.findItemByName(itemName);
		

		entity.setItem(item);
		request.bind(entity, errors, "total", "item.name");
		
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;		
		
		final List<Item> visibleItems;	
		visibleItems = this.repository.findVisibleItems();
		
		request.unbind(entity, model, "total", "item.name");
		model.setAttribute("id", request.getModel().getAttribute("id"));
		model.setAttribute("items", visibleItems);
		model.setAttribute("draftMode", entity.getToolkit().isDraftMode());
		
	}

	@Override
	public Amount instantiate(final Request<Amount> request) {
		assert request != null;

		Integer toolkitId;
		Toolkit toolkit;
		final Item item;
		
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(toolkitId);
		item = new Item();
		
		Amount result;
		result = new Amount();
		result.setToolkit(toolkit);
		result.setItem(item);

		return result;
		
	}

	@Override
	public void validate(final Request<Amount> request, final Amount entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(entity.getItem().getType() == ItemType.TOOL) {
			errors.state(request, entity.getTotal()<=1, "*", "inventor.amount.form.error.only-1-type-of-tool-allowed");
		}
		
		if(entity.getTotal()<=0) {
			errors.state(request, entity.getTotal()>0, "*", "inventor.amount.form.error.amount-must-be-positive");
		}
		
		if(!errors.hasErrors("item.name")) {
			final Collection<Amount> amounts = this.repository.findAmountsByToolkitId(entity.getToolkit().getId());
			final String itemName = entity.getItem().getName();
			final boolean repeatedItem = amounts.stream()
										.anyMatch(x -> Objects.equal(x.getItem().getName(), itemName));
			errors.state(request, !repeatedItem, "*", "inventor.amount.form.error.repeated-item");
		}
		
	}

	@Override
	public void create(final Request<Amount> request, final Amount entity) {
		assert request != null;
		assert entity != null;
		
		String itemName;
		Item item;
		
		itemName = (String) request.getModel().getAttribute("item.name");
		item = this.repository.findItemByName(itemName);

		entity.setItem(item);
		
		this.repository.save(entity);
	
	}

}
