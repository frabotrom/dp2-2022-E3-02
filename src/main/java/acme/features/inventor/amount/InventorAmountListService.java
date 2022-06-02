package acme.features.inventor.amount;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Amount;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorAmountListService implements AbstractListService<Inventor, Amount>{
	
	// Internal state --------------------------------------------------------

	@Autowired
	protected InventorAmountRepository repository;

	// AbstractListService<Inventor, Artifact> interface ---------------------
	
	@Override
	public boolean authorise(final Request<Amount> request) {
		assert request != null;
		
		final Integer toolkitId = request.getModel().getInteger("id");
		final Toolkit toolkit = this.repository.findToolkitById(toolkitId);
		final Integer activeId = request.getPrincipal().getActiveRoleId();
		
		return (toolkit.getInventor().getId()==activeId);	
		
	}

	@Override
	public Collection<Amount> findMany(final Request<Amount> request) {
		
		final Integer toolkitId = request.getModel().getInteger("id");
		return this.repository.findAmountsByToolkitId(toolkitId);
		
	}

	@Override
	public void unbind(final Request<Amount> request, final Collection<Amount> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;
		
		int toolkitId;
		Toolkit toolkit;
		final boolean showCreate;
		final boolean visibleItems;
		
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(toolkitId);
		showCreate  = (request.isPrincipal(toolkit.getInventor()) && toolkit.isDraftMode());
		visibleItems = !this.repository.findVisibleItems().isEmpty();
		
		model.setAttribute("id", toolkitId);
		model.setAttribute("showCreate", showCreate);
		model.setAttribute("visibleItems", visibleItems);
		
	}

	@Override
	public void unbind(final Request<Amount> request, final Amount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String artifactName = entity.getItem().getName();
		
		request.unbind(entity, model, "total", "item");
		model.setAttribute("item.name", artifactName);
		
		final Double totalPrice = entity.getItem().getRetailPrice().getAmount()*entity.getTotal();		
		model.setAttribute("total-price", totalPrice);
		
		
	}

}