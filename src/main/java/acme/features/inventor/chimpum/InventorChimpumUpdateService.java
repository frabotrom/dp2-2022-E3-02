package acme.features.inventor.chimpum;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Chimpum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorChimpumRepository repository;

	// AbstractUpdateService<Administrator, SystemConfiguration> interface ----------------


	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount()>=0, "budget", "inventor.chimpum.form.error.budget");
		}
		
		if (!errors.hasErrors("finalDate")) {			
			errors.state(request, entity.period()>=7, "finalDate", "inventor.chimpum.form.error.period");
			errors.state(request, entity.getFinalDate().after(entity.getInitialDate()), "finalDate", "inventor.chimpum.form.error.finalDate");
		}
		
		if (!errors.hasErrors("initialDate")) {
			
			Calendar calendar;
			Date deadline;

			calendar = Calendar.getInstance();
			calendar.setTime(entity.getInitialDate());
			calendar.add(Calendar.MONTH, -1);
			deadline = calendar.getTime();
			
			errors.state(request, deadline.after(entity.getCreationMoment()), "initialDate", "inventor.chimpum.form.error.creationMoment");
		}
		
	}
	
	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "budget", "initialDate", "finalDate", "optionalLink");
		
		//Aqui no se modifica el creationMoment
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
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
	

}
