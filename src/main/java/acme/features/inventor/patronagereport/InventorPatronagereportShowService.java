package acme.features.inventor.patronagereport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronagereport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronagereportShowService implements AbstractShowService<Inventor, Patronagereport>{

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorPatronagereportRepository repository;

	// AbstractShowService<Inventor, PatronageReport> interface ---------------------------

	@Override
	public boolean authorise(final Request<Patronagereport> request) {
		assert request != null;

		boolean result;
		int patronageReportId;
		Patronagereport patronageReport;

		patronageReportId = request.getModel().getInteger("id");
		patronageReport = this.repository.findPatronageReportById(patronageReportId);
		result = request.getPrincipal().getActiveRoleId() == patronageReport.getPatronage().getInventor().getId();

		return result;
	}

	@Override
	public Patronagereport findOne(final Request<Patronagereport> request) {
		assert request != null;

		final Patronagereport report;
		int reportId;

		reportId = request.getModel().getInteger("id");
		report = this.repository.findPatronageReportById(reportId);

		return report;
	}

	@Override
	public void unbind(final Request<Patronagereport> request, final Patronagereport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "memorandum", "link");
		model.setAttribute("sequenceNumber", entity.getSequenceNumber());
	}

}