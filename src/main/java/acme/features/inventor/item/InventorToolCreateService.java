package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolCreateService implements AbstractCreateService<Inventor, Item>{
	
	@Autowired
	protected InventorItemRepository repository;


	// AbstractCreateService<Inventor, Item> interface -------------------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
		final Item item = new Item();
		item.setInventor(this.repository.findInventorById(request.getPrincipal().getActiveRoleId()).orElse(null));

		return item;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setVisible(false);
		entity.setType(ItemType.TOOL);

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Item equalCode=this.repository.findItemByCode(entity.getCode()).orElse(null);

		if(!errors.hasErrors("code")) {
			errors.state(request, equalCode==null, "code", "inventor.item.form.error.duplicated-code");
		}

		if (!errors.hasErrors("retailPrice")) {

			final String[] currencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			Boolean accepted = false;
			for (int i = 0; i < currencies.length; i++) {
				if (entity.getRetailPrice().getCurrency().equals(currencies[i].trim())) {
					accepted = true;
				}
			}
			errors.state(request, entity.getRetailPrice().getAmount() > 0, "retailPrice", "inventor.item.form.error.negative-price");
			errors.state(request, accepted, "retailPrice", "inventor.item.form.error.non-accepted-currency");
		}

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description","retailPrice", "link", "visible");

	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
