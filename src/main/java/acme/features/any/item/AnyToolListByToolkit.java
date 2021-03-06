package acme.features.any.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolListByToolkit implements AbstractListService<Any, Item>{

	@Autowired
	protected AnyItemRepository repository;
	
	// AbstractListService<Any, Item> interface --------------
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		final boolean result;
		Toolkit toolkit;
		int toolkitId;
		
		// Para comprobar que un toolkit no est? en draftmode
		toolkitId = request.getModel().getInteger("id");
		toolkit=this.repository.findOneToolkitByToolkitId(toolkitId);
		result = toolkit != null && !toolkit.isDraftMode();
		
		return result;
	}
	
	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		Collection<Item> result;
		final int toolkitId = request.getModel().getInteger("id");
		result = this.repository.findAllItemsByToolkitId(toolkitId);
		return result;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "name", "technology", "retailPrice");
	}
}