package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage>  {
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected InventorPatronageRepository repository;

		// AbstractListService<Inventor, Patronage>  interface -------------------------
			
			
			@Override
			public boolean authorise(final Request<Patronage> request) {
				assert request != null;

				return true;
			}
			
			@Override
			public Patronage findOne(final Request<Patronage> request) {
				assert request != null;

				Patronage result;
				int id;

				id = request.getModel().getInteger("id");
				result = this.repository.findOnePatronageById(id);

				return result;
			}
			
			@Override
			public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

				request.unbind(entity, model, "status", "legalStuff", "code", "budget", "optionalLink", "creationDate", "initialDate", "finalDate");
				model.setAttribute("period", entity.periodOfTime());
				model.setAttribute("patronName", entity.getPatron().getUserAccount().getIdentity().getFullName());
				model.setAttribute("patronEmail", entity.getPatron().getUserAccount().getIdentity().getEmail());
			}

}
