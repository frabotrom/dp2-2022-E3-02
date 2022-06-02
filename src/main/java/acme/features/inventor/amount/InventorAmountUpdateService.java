package acme.features.inventor.amount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Amount;
import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorAmountUpdateService implements AbstractUpdateService<Inventor,Amount> {
	
	// Internal state ------------------------------------------------------------

		@Autowired
		protected InventorAmountRepository repository;


		// AbstractListService<Inventor, Artifact> interface ---------------------
		

		@Override
		public boolean authorise(final Request<Amount> request) {
			assert request != null;
			
			final Integer amountId = request.getModel().getInteger("id");
			final Toolkit toolkit = this.repository.findToolkitByAmountId(amountId);
			final Integer activeId = request.getPrincipal().getActiveRoleId();
			
			return (toolkit.isDraftMode() && toolkit.getInventor().getId()==activeId);	
		}
		
		@Override
		public void bind(final Request<Amount> request, final Amount entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			request.bind(entity, errors, "total", "item.name");
			
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
			model.setAttribute("item.retailprice", item.getRetailPrice());
			model.setAttribute("item.description", item.getDescription());
			model.setAttribute("item.type", item.getType());
			model.setAttribute("item.link", item.getLink());
			model.setAttribute("draftMode", entity.getToolkit().isDraftMode());
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
		public void validate(final Request<Amount> request, final Amount entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			if(entity.getItem().getType() == ItemType.TOOL) {
				errors.state(request, entity.getTotal()<=1, "*", "inventor.amount.form.error.only-1-type-of-tool-allowed");
			}
			
			if(entity.getTotal()<=0) {
				errors.state(request, entity.getTotal()<=0, "*", "inventor.amount.form.error.amount-must-be-positive");
			}
			
		}

		@Override
		public void update(final Request<Amount> request, final Amount entity) {
			assert request != null;
			assert entity != null;
			
			this.repository.save(entity);
			
		}

}
