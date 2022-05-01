package acme.features.any.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount>{
	
	@Autowired
	protected AnyUserAccountRepository repository;
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		
		final boolean result;
		UserAccount userAccount;
		int id;
		
		// Para comprobar que una cuenta de usuario está activa y a su vez no es ni de un usuario con los roles administrador ni anónimo
		id=request.getModel().getInteger("id");
		userAccount=this.repository.findOneUserAccountById(id);
		result = userAccount != null && userAccount.isEnabled() && !userAccount.getAuthorityString().contains("Administrator");
		
		return result;
	}

	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;
		UserAccount result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneUserAccountById(id);
		result.getRoles().forEach(r -> {});
		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("role", entity.getAuthorityString());
		model.setAttribute("name", entity.getIdentity().getName());
		model.setAttribute("surname", entity.getIdentity().getSurname());
		model.setAttribute("email", entity.getIdentity().getEmail());
		
		request.unbind(entity, model);
		
	}

}
