package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronagereport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportListService implements AbstractListService<Inventor, Patronagereport> {

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractListService<Inventor, PatronageReport> interface ---------------------------

	@Override
	public boolean authorise(final Request<Patronagereport> request) {
		assert request != null;

		boolean result;
		int patronageId;
		Patronagereport patronageReport;

		patronageId = request.getModel().getInteger("patronageId");
		patronageReport = this.repository.findPatronageReportByPatronageId(patronageId)
			.stream().findFirst().orElse(null);

		result = false;
		System.out.println(patronageReport);
		if(patronageReport != null)
			result = request.getPrincipal().getActiveRoleId() == patronageReport.getPatronage().getInventor().getId();
		System.out.println("Active role ID"+request.getPrincipal().getActiveRoleId());
		System.out.println("Inventor ID"+patronageReport.getPatronage().getInventor().getId());

		
		return result;
	}

	@Override
	public Collection<Patronagereport> findMany(final Request<Patronagereport> request) {
		assert request != null;

		Collection<Patronagereport> reports;
		int patronageId;

		patronageId = request.getModel().getInteger("patronageId");
		reports = this.repository.findPatronageReportByPatronageId(patronageId);

		return reports;
	}

	@Override
	public void unbind(final Request<Patronagereport> request, final Patronagereport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment");
		model.setAttribute("sequenceNumber", entity.getSequenceNumber());
	}

}