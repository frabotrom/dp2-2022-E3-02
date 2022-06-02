package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	// AbstractUpdateService<Inventor, Item> -------------------------------------

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

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link");		
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
		
		if(!errors.hasErrors("name")) {
			final boolean isNameSpam = entity.nameHasSpam(this.repository.getSystemConfiguration());
			errors.state(request, !isNameSpam, "name", "inventor.item.item.form.error.spam");
		}
		
		if(!errors.hasErrors("technology")) {
			final boolean isTechnologySpam = entity.technologyHasSpam(this.repository.getSystemConfiguration());
			errors.state(request, !isTechnologySpam, "technology", "inventor.item.item.form.error.spam");
		}
		
		if(!errors.hasErrors("description")) {
			final boolean isDescriptionSpam = entity.descriptionHasSpam(this.repository.getSystemConfiguration());
			errors.state(request, !isDescriptionSpam, "description", "inventor.item.item.form.error.spam");
		}
		
		if(!errors.hasErrors("code")) {
			Item existing;
			existing=this.repository.findComponentById(entity.getId());
			if(existing!=null) {
				errors.state(request,existing.getId()==entity.getId() , "code", "inventor.item.form.error.duplicated-code");
			}

		}
		
		if (!errors.hasErrors("retailPrice")) {
			
			final String[] currencies=this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			Boolean accepted=false;
			for(int i=0;i<currencies.length;i++) {
				if(entity.getRetailPrice().getCurrency().equals(currencies[i].trim())) {
					accepted=true;
				}
			}
			errors.state(request, entity.getRetailPrice().getAmount() > 0, "retailPrice", "inventor.item.form.error.negative-price");
			errors.state(request, accepted, "retailPrice", "inventor.item.form.error.non-accepted-currency");

		}

	}		
	

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}