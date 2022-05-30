package acme.features.administrator.announcement;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	// Internal state -------------------------------------------------------------

	@Autowired
	protected AdministratorAnnouncementRepository repository;

	// AbstractCreateService<Administrator, Announcement> interface ---------------


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,  "title", "body", "critical", "moreInfo");
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "critical", "moreInfo");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Announcement();
		result.setTitle("");
		result.setCreationDate(moment);
		result.setBody("");
		result.setCritical(false);
		result.setMoreInfo("");
		

		return result;
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("confirmation")) {
			boolean confirmation;
	
			confirmation = request.getModel().getBoolean("confirmation");
			errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		}
		
		if (!errors.hasErrors("title")) {
			Boolean containsSpam;
			
			containsSpam = entity.titleHasSpam(this.repository.getSystemConfiguration());			
			errors.state(request, !containsSpam, "title", "administrator.announcement.form.error.spam");
		}
		
		if (!errors.hasErrors("body")) {
			Boolean containsSpam;
			
			containsSpam = entity.bodyHasSpam(this.repository.getSystemConfiguration());			
			errors.state(request, !containsSpam, "body", "administrator.announcement.form.error.spam");
		}
		
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);
		this.repository.save(entity);
	}

}