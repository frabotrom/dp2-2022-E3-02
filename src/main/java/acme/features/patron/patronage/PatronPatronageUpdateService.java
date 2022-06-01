
package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {

		assert request != null;
		boolean result;
		int patronageId;
		Patronage patronage;
		final Patron patron;

		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findPatronageById(patronageId);
		patron = patronage.getPatron();
		result = patronage.isPublished() == false && request.isPrincipal(patron);

		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "legalStuff", "budget", "initialDate", "finalDate", "optionalLink");
		entity.setInventor(this.repository.findInventorByUsername(request.getModel().getAttribute("inventor").toString()));

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		
		request.unbind(entity, model, "code", "legalStuff", "budget", "initialDate", "finalDate", "optionalLink", "published");
		model.setAttribute("inventors", this.repository.findAllInventors());
		model.setAttribute("patronageId", entity.getId());
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage patronage;
		int id;

		id = request.getModel().getInteger("id");
		patronage = this.repository.findPatronageById(id);

		return patronage;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Patronage exists;

			exists = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, exists == null, "code", "patronage.patronage.form.error.duplicated");
		}

		if (!errors.hasErrors("initialDate")) {
			Calendar calendar;

			calendar = new GregorianCalendar();
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);

			errors.state(request, entity.getInitialDate().after(calendar.getTime()), "initialDate", "patron.patronage.form.error.initialDate");
		}

		if (!errors.hasErrors("finalDate")) {
			Calendar calendar;

			calendar = new GregorianCalendar();
			calendar.setTime(entity.getInitialDate());
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);

			errors.state(request, entity.getFinalDate().after(calendar.getTime()), "finishTime", "patron.patronage.form.error.finalDate");
		}

		if (!errors.hasErrors("budget")) {
			final Set<String> acceptedCurrencies;
			final String[] acceptedCurrenciesSt = this.repository.findAcceptedCurrencies().split(";");
			acceptedCurrencies = new HashSet<String>();
			Collections.addAll(acceptedCurrencies, acceptedCurrenciesSt);

			errors.state(request, entity.getBudget().getAmount() > 0., "budget", "patronage.patronage.form.error.budget.negative");

			errors.state(request, acceptedCurrencies.contains(entity.getBudget().getCurrency()), "budget", "patronage.patronage.form.error.budget.invalid");
		}

	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
