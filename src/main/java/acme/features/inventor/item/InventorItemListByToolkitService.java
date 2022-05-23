package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemListByToolkitService implements AbstractListService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository repository;
	
// AbstractListService<Any, Invention> interface --------------
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		final boolean result;
		final int toolkitId;
		final Toolkit toolkit;
		
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(toolkitId);
		result = toolkit != null && request.getPrincipal().getActiveRoleId()==toolkit.getInventor().getId();

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

		request.unbind(entity, model, "code", "name", "retailPrice", "visible");
	}
}