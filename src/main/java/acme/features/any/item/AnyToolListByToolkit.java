package acme.features.any.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolListByToolkit implements AbstractListService<Any, Item>{

	@Autowired
	protected AnyToolRepository repository;
	
// AbstractListService<Any, Invention> interface --------------
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		Collection<Item> result;
		final int toolkitId = request.getModel().getInteger("id");
		result = this.repository.findAllComponentsByToolkitId(toolkitId);
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