package acme.features.inventor.patronagereport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.PatronageStatus;
import acme.entities.Patronagereport;
import acme.features.inventor.patronage.InventorPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronagereportCreateService implements AbstractCreateService<Inventor, Patronagereport> {

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorPatronageRepository patronageRepository;
	@Autowired
	protected InventorPatronagereportRepository patronagereportRepository;

	// AbstractListService<Inventor, PatronageReport> interface ---------------------------

	@Override
	public boolean authorise(final Request<Patronagereport> request) {
		assert request != null;

		boolean result;
		int patronageId;
		Patronage patronage;

		patronageId = request.getModel().getInteger("patronageId");
		patronage = this.patronageRepository.findOnePatronageById(patronageId);
		result = request.getPrincipal().getActiveRoleId() == patronage.getInventor().getId();
		result = result && patronage.getStatus().equals(PatronageStatus.ACCEPTED);

		return result;
	}

	@Override
	public Patronagereport instantiate(final Request<Patronagereport> request) {
		assert request != null;

		Patronagereport result;
		int patronageId;
		Patronage patronage;
		Date date;
		String numPatronageReports;		
		
		patronageId = request.getModel().getInteger("patronageId");
		patronage = this.patronageRepository.findOnePatronageById(patronageId);
		date = new Date();
		numPatronageReports = Integer.toString(this.patronagereportRepository.findPatronageReportByPatronageId(patronageId).size()+1);
		
		result = new Patronagereport();
		result.setPatronage(patronage);
		result.setCreationMoment(date);
		result.setSerialNumber(Long.valueOf("000"+ numPatronageReports));

		return result;
	}
	
	@Override
	public void bind(final Request<Patronagereport> request, final Patronagereport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,"memorandum","optionalLink");
		
	}
	
	@Override
	public void validate(final Request<Patronagereport> request, final Patronagereport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Boolean isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "inventor.patronagereport.form.error.must-confirm");
	}
	
	@Override
	public void unbind(final Request<Patronagereport> request, final Patronagereport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "serialNumber","creationMoment","memorandum","optionalLink");
		model.setAttribute("patronageId", request.getModel().getAttribute("patronageId"));
		model.setAttribute("sequenceNumber", entity.getSequenceNumber());
		model.setAttribute("confirm", "false");
	}	

	@Override
	public void create(final Request<Patronagereport> request, final Patronagereport entity) {
		assert request != null;
		assert entity != null;

		this.patronagereportRepository.save(entity);
		
	}

}