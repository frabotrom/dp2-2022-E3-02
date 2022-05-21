package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.features.any.userAccount.AnyUserAccountRepository;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected AnyUserAccountRepository userRepository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Patronage patronage;
		
		masterId = request.getModel().getInteger("masterId");
		patronage = this.repository.findOnePatronageById(masterId);
		result = patronage.getPatron().getId() == request.getPrincipal().getActiveRoleId();
		
		return result;
	}
	
	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		final Patronage result;
		final Patron patron;
		
		result.setPatron(request.getPrincipal().getActiveRoleId());
		result.setPatron(patron.setUserAccount(this.userRepository.findOneUserAccountById(request.getPrincipal().getAccountId()));
	}

}
