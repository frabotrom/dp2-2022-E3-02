package acme.features.inventor.chimpum;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum>{

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorChimpumRepository repository;

	// AbstractShowService<Inventor, PatronageReport> interface ---------------------------

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean result;
		final Item item;
		int chimpumId;

		chimpumId = request.getModel().getInteger("id");
		item = this.repository.findItemByChimpumId(chimpumId);
		result = request.getPrincipal().getActiveRoleId() == item.getInventor().getId();

		return result;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		final Chimpum chimpum;
		int chimpumId;

		chimpumId = request.getModel().getInteger("id");
		chimpum = this.repository.findChimpumById(chimpumId);

		return chimpum;
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "budget", "creationMoment", "initialDate", "finalDate", "optionalLink");
		model.setAttribute("period", entity.period());
		
		final Item item;
		int chimpumId;

		chimpumId = entity.getId();
		item = this.repository.findItemByChimpumId(chimpumId);
		
		model.setAttribute("artefactName", item.getName());
		model.setAttribute("artefactCode", item.getCode());
		model.setAttribute("artefactTechnology", item.getTechnology());
		model.setAttribute("artefactDescription", item.getDescription());
		model.setAttribute("artefactRetailPrice", item.getRetailPrice());
		model.setAttribute("artefactLink", item.getLink());
		model.setAttribute("artefactType", item.getType());
		model.setAttribute("artefactVisible", item.isVisible());
	}

}