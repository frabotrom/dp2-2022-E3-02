package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;
	

	// AbstractUpdateService<Patron, Patronage> -------------------------------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int patronageId;
		Patronage patronage;

		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = (patronage != null && !patronage.getStatus().equals(PatronageStatus.ACCEPTED) && request.isPrincipal(patronage.getPatron()));

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
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setInventor(this.repository.findInventorById(Integer.valueOf(request.getModel().getAttribute("inventorId").toString())));


		request.bind(entity, errors, "code", "legalStuff", "budget", "initialDate", "finalDate","optionalLink");
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Patronage existing;

			existing = this.repository.findOnePatronageByCode(entity.getCode());
			if(existing!=null) {
			errors.state(request, existing.getId()==entity.getId() , "code", "patron.patronage.form.error.duplicated");
			}
		}
		
		if(!errors.hasErrors("intialDate")) {
			final Date minimumStartDate=DateUtils.addMonths(entity.getCreationDate(), 1);

			
			errors.state(request, entity.getInitialDate().after(minimumStartDate), "intialDate", "patron.patronage.form.error.too-close-start-date");
			
		}
		if(!errors.hasErrors("finalDate")) {
			final Date minimumFinishDate=DateUtils.addMonths(entity.getInitialDate(), 1);

			errors.state(request, entity.getFinalDate().after(minimumFinishDate), "finalDate", "patron.patronage.form.error.one-month");
			
		}
		
		
		if (!errors.hasErrors("budget")) {
			final Boolean acceptedCurrency=this.repository.findSystemConfiguration().getAcceptedCurrencies()
				.matches("(.*)"+entity.getBudget().getCurrency()+"(.*)");
			
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-budget");
			errors.state(request, acceptedCurrency, "budget", "patron.patronage.form.error.non-accepted-currency");
		}
		

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "intialDate", "finalDate","optionalLink","status");
		model.setAttribute("inventorName", this.repository.findInventor().getIdentity().getFullName());
		model.setAttribute("inventorEmail", this.repository.findInventor().getIdentity().getEmail());
		model.setAttribute("inventorCompany", this.repository.findInventor().getCompany());
		model.setAttribute("inventorId", entity.getInventor().getId());
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	
}