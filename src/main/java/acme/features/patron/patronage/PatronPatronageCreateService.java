
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
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;

	// AbstractCreateService<Employer, Duty> interface -------------------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		final Patronage result;
		Date initialD;
		Date finalD;

		initialD =DateUtils.addMonths( new Date(System.currentTimeMillis() + 300000),1);
		finalD= DateUtils.addMonths( initialD,1);
		finalD= DateUtils.addMinutes(finalD, 1);


		result = new Patronage();
		result.setLegalStuff("");
		result.setInitialDate(initialD);
		result.setFinalDate(finalD);
		result.setPatron(this.repository.findPatronById(request.getPrincipal().getActiveRoleId()).orElse(null));


		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);
		entity.setStatus(PatronageStatus.PROPOSED);
		entity.setInventor(this.repository.findInventorById(Integer.valueOf(request.getModel().getAttribute("inventorId").toString())).orElse(null));

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
			errors.state(request, existing == null, "code", "patron.patronage.form.error.duplicated");
		}
		
		if(!errors.hasErrors("initialDate")) {
			final Date minimumStartDate=DateUtils.addMonths(entity.getCreationDate(), 1);

			
			errors.state(request,entity.getInitialDate().after(minimumStartDate), "initialDate", "patron.patronage.form.error.too-close-start-date");
			
		}
		if(!errors.hasErrors("finalDate")) {
			final Date minimumFinishDate=DateUtils.addMonths(entity.getInitialDate(), 1);

			errors.state(request,entity.getFinalDate().after(minimumFinishDate), "finalDate", "patron.patronage.form.error.one-month");
			
		}
		
		
		if (!errors.hasErrors("budget")) {

			final Boolean acceptedCurrency= this.repository.findSystemConfiguration().getAcceptedCurrencies()
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

		request.unbind(entity, model, "code", "legalStuff", "budget", "initialDate", "finalDate","optionalLink");
		model.setAttribute("inventor", this.repository.findInventors());

	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
}