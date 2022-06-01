
package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.Patronagereport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;

@Service
public class PatronPatronageDeleteService implements AbstractDeleteService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		boolean result;
		int patronageId;
		Patronage patronage;
		Patron patron;

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

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "legalStuff", "budget", "initialDate", "finalDate", "optionalLink");

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

	}

	@Override
	public void delete(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		Collection<Patronagereport> reports;
		
		reports = this.repository.findReportsByPatronageId(entity.getId());
		
		this.repository.deleteAll(reports);	
		this.repository.delete(entity);

	}

}
