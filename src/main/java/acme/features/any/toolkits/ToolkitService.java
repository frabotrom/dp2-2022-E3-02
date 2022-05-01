package acme.features.any.toolkits;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class ToolkitService implements AbstractShowService<Any,Toolkit> {


	@Autowired
	protected ToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		final boolean result;
		Toolkit toolkit;
		int id;
		
		// Para comprobar que un toolkit no está en draftmode
		id=request.getModel().getInteger("id");
		toolkit=this.repository.findOneToolkitById(id);
		result = toolkit != null && !toolkit.isDraftMode();
		
		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		Toolkit result;
		int id;
		id=request.getModel().getInteger("id");
		result=this.repository.findOneToolkitById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit toolkit, final Model model) {
		assert toolkit != null;
		assert request != null;
		assert model != null;
		request.unbind(toolkit, model, "code","title","description","asemblyNotes","info");
	}
	
}
