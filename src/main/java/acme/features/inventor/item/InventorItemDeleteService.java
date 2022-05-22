package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorItemDeleteService implements AbstractDeleteService<Inventor, Item> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorItemRepository repository;

		// AbstractDeleteService<Inventor, Item> interface -------------------------

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		final int id = request.getModel().getInteger("id");
		final Item comp = this.repository.findItemById(id);
		final Inventor inventor = comp.getInventor();
		result = !comp.isVisible() && request.isPrincipal(inventor);

		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice","link");		
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice","link","visible");				
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		final int id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);
		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.delete(entity);
		
	}
	
	

}
