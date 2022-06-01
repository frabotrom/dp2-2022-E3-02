
package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

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

	@Autowired
	protected PatronPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		entity.setInventor(this.repository.findInventorByUsername(request.getModel().getAttribute("inventor").toString()));

		request.bind(entity, errors, "code", "legalStuff", "budget", "initialDate", "finalDate", "optionalLink");

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("inventorsList", this.repository.findAllInventors());

		request.unbind(entity, model, "code", "legalStuff", "budget", "initialDate", "finalDate", "optionalLink", "published");
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		final Patronage result;
		final Date moment;
		final Date startMoment;
		final Date endMoment;

		result = new Patronage();
		moment = new Date(System.currentTimeMillis() - 1);
		startMoment = DateUtils.addMonths(moment, 1);
		endMoment = DateUtils.addMonths(startMoment, 1);
		result.setPublished(false);
		result.setStatus(PatronageStatus.PROPOSED);
		result.setPatron(this.repository.findPatronById(request.getPrincipal().getActiveRoleId()));
		result.setCode("");
		result.setLegalStuff("");
		result.setCreationDate(moment);
		result.setInitialDate(startMoment);
		result.setFinalDate(endMoment);
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Patronage exists;

			exists = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, exists == null, "code", "patronage.patronage.form.error.code.duplicated");
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

			errors.state(request, entity.getFinalDate().after(calendar.getTime()), "finalDate", "patron.patronage.form.error.finalDate");
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
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}