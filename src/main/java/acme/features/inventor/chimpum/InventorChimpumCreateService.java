package acme.features.inventor.chimpum;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.entities.Item;
import acme.entities.ItemType;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum> {

	// Internal state -------------------------------------------------------------

	@Autowired
	protected InventorChimpumRepository repository;

	// AbstractCreateService<Administrator, Announcement> interface ---------------

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		
		assert request != null;
		
		// Mira que es tipo Tool y la relación (solo si es un relacion OneToOne o ManyToOne)
		
		boolean result;
		final Item item;
		
		int itemId;		
		itemId = request.getModel().getInteger("itemId");
		item = this.repository.findItemByItemId(itemId);
		
		result = item!= null && item.getType() == ItemType.TOOL && item.getChimpum() == null;
		
		return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "budget", "initialDate", "finalDate", "optionalLink");
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "budget", "initialDate", "finalDate", "optionalLink");
		
		int itemId;
		itemId = request.getModel().getInteger("itemId");
		model.setAttribute("itemId", itemId);
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {
		assert request != null;

		Chimpum result;

		result = new Chimpum();
		
		return result;
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
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		Item item;
		int itemId;
		itemId = request.getModel().getInteger("itemId");
		
		// Para asegurar que es tipo Tool
		item = this.repository.findToolByItemId(itemId);
		item.setChimpum(entity);

		this.repository.save(entity);
	}

}