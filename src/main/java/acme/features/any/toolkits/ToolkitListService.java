package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class ToolkitListService implements AbstractListService<Any,Toolkit> {

	@Autowired
	protected ToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		Collection<Toolkit> result;
		result = this.repository.findAllToolkits();
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit toolkit, final Model model) {
		assert toolkit != null;
		assert request != null;
		assert model != null;
		request.unbind(toolkit,model,"code","title","info");
	}
	
}
