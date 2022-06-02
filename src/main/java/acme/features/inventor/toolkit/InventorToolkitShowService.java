package acme.features.inventor.toolkit;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Amount;
import acme.entities.SystemConfiguration;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor,Toolkit> {


	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int id;
		Toolkit toolkit;

		id = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(id);
		result = toolkit != null && toolkit.getInventor().getId() == request.getPrincipal().getActiveRoleId();

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
		
		Integer id;
		id = request.getModel().getInteger("id");
		
		final Collection<Amount> amounts = this.repository.findAmountsByToolkitId(id);
		final SystemConfiguration sysConfig = this.repository.getSystemConfiguration();
		final Double price = toolkit.totalPrice(amounts, sysConfig).getAmount();
		
		model.setAttribute("totalPrice", price);
		
		request.unbind(toolkit, model, "code","title","description","asemblyNotes","info","draftMode");
	}
	
}
