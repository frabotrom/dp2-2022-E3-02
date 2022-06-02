/*
 * EmployerDutyDeleteService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor, Chimpum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorChimpumRepository repository;

	// AbstractDeleteService<Employer, Duty> interface -------------------------


	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		return true;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		Chimpum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findChimpumById(id);

		return result;
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "budget", "initialDate", "finalDate", "optionalLink");
		
		//Aqui no se modifica el creationMoment
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "budget", "creationMoment", "initialDate", "finalDate", "optionalLink");
		model.setAttribute("period", entity.period());
		
		final Item item;
		int chimpumId;

		chimpumId = entity.getId();
		item = this.repository.findItemByChimpumId(chimpumId);
		
		model.setAttribute("artefactName", item.getName());
		model.setAttribute("artefactCode", item.getCode());
		model.setAttribute("artefactTechnology", item.getTechnology());
		model.setAttribute("artefactDescription", item.getDescription());
		model.setAttribute("artefactRetailPrice", item.getRetailPrice());
		model.setAttribute("artefactLink", item.getLink());
		model.setAttribute("artefactType", item.getType());
		model.setAttribute("artefactVisible", item.isVisible());
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		
		Item item;
		int chimpumId;
		
		chimpumId = request.getModel().getInteger("id");		
		item = this.repository.findItemByChimpumId(chimpumId);
		item.setChimpum(null);

		this.repository.delete(entity);
	}

}
