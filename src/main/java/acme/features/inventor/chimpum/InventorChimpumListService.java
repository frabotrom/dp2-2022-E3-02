package acme.features.inventor.chimpum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorChimpumListService implements AbstractListService<Inventor, Chimpum> {

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorChimpumRepository repository;

	// AbstractListService<Inventor, Chimpum> interface ---------------------------

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Chimpum> findMany(final Request<Chimpum> request) {
		assert request != null;

		Collection<Chimpum> result;
		int inventorId;

		inventorId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findChimpumsByInventorId(inventorId);

		return result;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "budget", "creationMoment", "initialDate", "finalDate");
		
		final Item item;
		int chimpumId;

		chimpumId = entity.getId();
		item = this.repository.findItemByChimpumId(chimpumId);
		
		model.setAttribute("artefactName", item.getName());
	}

}