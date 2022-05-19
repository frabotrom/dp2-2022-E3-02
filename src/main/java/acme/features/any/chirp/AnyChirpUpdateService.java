package acme.features.any.chirp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractUpdateService;

@Service
public class AnyChirpUpdateService implements AbstractUpdateService<Any, Chirp>{
	
	@Autowired
	protected AnyChirpRepository repository;
	
	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		boolean result = true;
		Chirp chirp;
		int chirpId;
				
		chirpId = request.getModel().getInteger("id");
		chirp = this.repository.findOneChirpById(chirpId);
		if(!chirp.getAuthor().equals("")) result =false;
		
		return result;
	}
	
	@Override
	public Chirp findOne(final Request<Chirp> request) {
		assert request != null;
		
		Chirp result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneChirpById(id);
		
		return result;
	}
	
	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "author", "body", "email");
	}
	
	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}
	
	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "author", "body", "email", "creationDate");
	}
	
	@Override
	public void update(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
