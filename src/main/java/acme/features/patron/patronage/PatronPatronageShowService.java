package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		
		final int id = request.getModel().getInteger("id");
		final Patronage patronage = this.repository.findOnePatronageById(id);
		final int myId = request.getPrincipal().getActiveRoleId();
		
		result = (patronage.getPatron().getId() == myId || patronage.getInventor().getId() == myId);
		
		return result;
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
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate","initialDate","finalDate","optionalLink");
		model.setAttribute("period", entity.periodOfTime());
		model.setAttribute("inventorName", entity.getInventor().getUserAccount().getIdentity().getFullName());
		model.setAttribute("inventorEmail", entity.getInventor().getUserAccount().getIdentity().getEmail());
		model.setAttribute("inventorCompany", entity.getInventor().getCompany());

	}

}
