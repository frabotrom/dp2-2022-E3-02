
package acme.features.patron.patronageReports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronagereport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, Patronagereport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageReportRepository repository;

	// AbstractShowService<Inventor, PatrongeReport> interface -------------------


	@Override
	public boolean authorise(final Request<Patronagereport> request) {
		assert request != null;

		boolean result;
		int patronageReportId;
		Patronagereport patronageReport;

		patronageReportId = request.getModel().getInteger("id");
		patronageReport = this.repository.findOnePatronageReportById(patronageReportId);
		result = patronageReport != null && patronageReport.getPatronage().getPatron().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Patronagereport findOne(final Request<Patronagereport> request) {
		assert request != null;

		Patronagereport result;
		int patronageReportId;

		patronageReportId = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageReportById(patronageReportId);

		return result;
	}

	@Override
	public void unbind(final Request<Patronagereport> request, final Patronagereport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "optionalLink");
		model.setAttribute("patronage.code", entity.getPatronage().getCode());
		model.setAttribute("patronage.status", entity.getPatronage().getStatus());
		model.setAttribute("patronage.budget", entity.getPatronage().getBudget());
		model.setAttribute("patronage.optionalLink", entity.getPatronage().getOptionalLink());
		model.setAttribute("patronage.legalStuff", entity.getPatronage().getLegalStuff());
		model.setAttribute("patronage.creationDate", entity.getPatronage().getCreationDate());
		model.setAttribute("patronage.initialDate", entity.getPatronage().getInitialDate());
		model.setAttribute("patronage.finalDate", entity.getPatronage().getFinalDate());

	}

}
