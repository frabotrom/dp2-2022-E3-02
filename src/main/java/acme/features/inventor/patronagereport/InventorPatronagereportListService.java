package acme.features.inventor.patronagereport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronagereport;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorPatronagereportListService implements AbstractListService<Inventor, Patronagereport> {

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository patronageRepository;
	@Autowired
	protected InventorPatronagereportRepository patronagereportRepository;

	// AbstractListService<Inventor, PatronageReport> interface ---------------------------

	@Override
	public boolean authorise(final Request<Patronagereport> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Patronagereport> findMany(final Request<Patronagereport> request) {
		assert request != null;

		Collection<Patronagereport> result;
		int inventorId;

		inventorId = request.getPrincipal().getActiveRoleId();
		result = this.patronagereportRepository.findPatronageReportsByInventorId(inventorId);

		return result;
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