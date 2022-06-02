package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Amount;
import acme.entities.SystemConfiguration;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor,Toolkit>{	

	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		Integer toolkitId;
		toolkitId = request.getModel().getInteger("id");
		final Integer inventorId = request.getPrincipal().getActiveRoleId();		
		final Inventor toolkitInventor = this.repository.findInventorByToolkitId(toolkitId);		
		return inventorId.equals(toolkitInventor.getId());		
	}
	
	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "code", "description", "asemblyNotes", "info");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Integer id;
		id = request.getModel().getInteger("id");
		
		final Collection<Amount> amounts = this.repository.findAmountsByToolkitId(id);
		final SystemConfiguration sysConfig = this.repository.getSystemConfiguration();
		final Double price = entity.totalPrice(amounts, sysConfig).getAmount();
		
		model.setAttribute("totalPrice", price);
		
		request.unbind(entity, model, "title", "code", "description", "asemblyNotes", "info", "draftMode");
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Integer id;
		Toolkit toolkit;
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(id);
		
		return toolkit;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("title")) {
			final boolean isTitleSpam = entity.titleHasSpam(this.repository.getSystemConfiguration());
			errors.state(request, !isTitleSpam, "title", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("description")) {
			final boolean isDescriptionSpam = entity.descriptionHasSpam(this.repository.getSystemConfiguration());
			errors.state(request, !isDescriptionSpam, "description", "inventor.toolkit.form.error.spam");
		}
		
		if(!errors.hasErrors("asemblyNotes")) {
			final boolean isAsemblyNotesSpam = entity.asemblyNotesHasSpam(this.repository.getSystemConfiguration());
			errors.state(request, !isAsemblyNotesSpam, "asemblyNotes", "inventor.toolkit.form.error.spam");
		}
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		

		this.repository.save(entity);
		
	}
	
}