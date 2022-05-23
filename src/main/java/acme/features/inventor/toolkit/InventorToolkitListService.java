package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListService implements AbstractListService<Inventor,Toolkit> {

	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result;
		final int inventorId;
		
		inventorId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findToolkitsByInventorId(inventorId);
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit toolkit, final Model model) {
		assert toolkit != null;
		assert request != null;
		assert model != null;
		
		request.unbind(toolkit,model,"code","title","info","draftMode");
	}
	
}
