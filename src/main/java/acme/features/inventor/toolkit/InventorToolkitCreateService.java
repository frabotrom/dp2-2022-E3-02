package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService  implements AbstractCreateService<Inventor,Toolkit>{	
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;
	
	// AbstractListService<Inventor, Artifact> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		final Integer activeId = request.getPrincipal().getActiveRoleId();		
		final Collection<Integer> existingInventors = this.repository.findAllInventorId();		
		return  existingInventors.stream().anyMatch(x -> Objects.equals(x, activeId));
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
		
		
				
		request.unbind(entity, model, "title", "code", "description", "asemblyNotes", "info","draftMode");
		
		
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;

		final Integer activeId = request.getPrincipal().getActiveRoleId();	
		Toolkit result;
		result = new Toolkit();
		result.setDraftMode(true);
		result.setInventor(this.repository.findInventorById(activeId));

		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final Collection<String> codes = this.repository.findAllToolkitCodes();
			final String toolkitCode = entity.getCode();
			final boolean repeatedCode = codes.stream()
										.anyMatch(x -> x.equals(toolkitCode));
			errors.state(request, !repeatedCode, "code", "inventor.toolkit.form.error.repeated-code");
		}
		
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
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	
	}
	
}