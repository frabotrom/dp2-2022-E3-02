package acme.features.any.item;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyComponentListService implements AbstractListService<Any,Item> {

	@Autowired
	protected AnyItemRepository repository;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return true;
	}
	
	
	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		
		final Collection<Item> result;
		final Collection<String> authorities = new ArrayList<String>();
		for(final GrantedAuthority authority: request.getPrincipal().getAuthorities()) {
			authorities.add(authority.toString());
		}
		
		if(authorities.contains("AUTH_Inventor")||authorities.contains("AUTH_Patron")) {
			result = this.repository.findAllComponents();
		}
		
		else {
			// Para un usuario anónimo
			result = this.repository.findAllVisibleComponents();
		}
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert entity != null;
		assert request != null;
		assert model != null;
		request.unbind(entity,model,"name","code","retailPrice");
		
		if(entity.isVisible()) {
			model.setAttribute("visible", "Visible");
		}
		else {
			model.setAttribute("visible", "Not Visible");
		}
		
	}
}
