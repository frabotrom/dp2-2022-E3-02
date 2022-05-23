package acme.features.inventor.toolkit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Amount;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor,Toolkit>{	

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
		
		request.bind(entity, errors, "title", "code", "description", "assemblyNotes", "info");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		Integer id;
		id = request.getModel().getInteger("id");
		final Double price = this.repository.findToolkitPrice(id).orElse(0.0);
		final Money currentPrice = new Money();
		currentPrice.setCurrency("EUR");
		currentPrice.setAmount(price);
		model.setAttribute("price", currentPrice);
		
		
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "info", "draftMode");
		
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
		
		final List<Amount> quantities = this.repository.findAmountsByToolkitId(entity.getId());
		
		errors.state(request, !quantities.isEmpty(), "*", "inventor.toolkit.form.error.empty-quantities");
		
//		SpamDetector spamDetector;
//		String strongSpamTerms;
//		String weakSpamTerms;
//		int strongSpamThreshold;
//		int weakSpamThreshold;
//		
//		spamDetector = new SpamDetector();
//		strongSpamTerms = this.repository.findStrongSpamTerms();
//		weakSpamTerms = this.repository.findWeakSpamTerms();
//		strongSpamThreshold = this.repository.findStrongSpamTreshold();
//		weakSpamThreshold = this.repository.findWeakSpamTreshold();
//		
//		if(!errors.hasErrors("title")) {
//			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getTitle())
//				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getTitle()),
//				"title", "inventor.toolkit.form.error.spam");
//		}
//		
//		if(!errors.hasErrors("description")) {
//			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getDescription())
//				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getDescription()),
//				"description", "inventor.toolkit.form.error.spam");
//		}
//		
//		if(!errors.hasErrors("assemblyNotes")) {
//			errors.state(request, !spamDetector.containsSpam(weakSpamTerms.split(","), weakSpamThreshold, entity.getAssemblyNotes())
//				&& !spamDetector.containsSpam(strongSpamTerms.split(","), strongSpamThreshold, entity.getAssemblyNotes()),
//				"assemblyNotes", "inventor.toolkit.form.error.spam");
//		}
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		entity.setDraftMode(false);
		this.repository.save(entity);
		
	}
	
}
