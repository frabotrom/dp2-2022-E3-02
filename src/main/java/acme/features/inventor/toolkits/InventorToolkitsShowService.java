package acme.features.inventor.toolkits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitsShowService implements AbstractShowService<Inventor, Toolkit>{
	// Internal state ---------------------------------------------------------

				@Autowired
				protected InventorToolkitsRepository repository;

			// AbstractListService<Inventor, Patronage>  interface -------------------------
				
				@Override
				public boolean authorise(final Request<Toolkit> request) {
					assert request != null;
					
					boolean result;
					int id;
					final Toolkit toolkit;

					id = request.getModel().getInteger("id");
					toolkit = this.repository.findOneToolkitById(id);
					result = toolkit != null && toolkit.getInventor().getId() == request.getPrincipal().getActiveRoleId();

					return result;
				}
				
				@Override
				public Toolkit findOne(final Request<Toolkit> request) {
					assert request != null;

					Toolkit result;
					int id;

					id = request.getModel().getInteger("id");
					result = this.repository.findOneToolkitById(id);

					return result;
				}
				
				@Override
				public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
					assert request != null;
					assert entity != null;
					assert model != null;

					request.unbind(entity, model, "code", "title", "description", "asemblyNotes", "info", "draftMode");
				}
}
