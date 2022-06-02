package acme.features.inventor.amount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Amount;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorAmountDeleteService implements AbstractDeleteService<Inventor,Amount> {

	// Internal state ---------------------------------------------------------------

		@Autowired
		protected InventorAmountRepository repository;
		
		// AbstractListService<Inventor, Artifact> interface ------------------------

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
			
			request.bind(entity, errors, "total", "item");
			
		}

		@Override
		public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "total", "item");
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
			
		}

		@Override
		public void delete(final Request<Amount> request, final Amount entity) {
			assert request != null;
			assert entity != null;
			
			this.repository.delete(entity);
			
		}
	
}
