package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

public class InventorToolkitsListService implements AbstractListService<Inventor, Toolkit>{

	// Internal state ---------------------------------------------------------

			@Autowired
			protected InventorToolkitsRepository repository;

		// AbstractListService<Inventor, Patronage>  interface -------------------------
			
			
			@Override
			public boolean authorise(final Request<Toolkit> request) {
				assert request != null;

				return true;
			}
			
			@Override
			public Collection<Toolkit> findMany(final Request<Toolkit> request) {
				assert request != null;

				Collection<Toolkit> result;
				final int inventorId;

				inventorId = request.getPrincipal().getActiveRoleId();
				result = this.repository.findManyToolkitsByInventorId(inventorId);

				return result;
			}
			
			@Override
			public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

				request.unbind(entity, model, "code","title","description","asemblyNotes");
			}
}
