package acme.features.authenticated.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedConfigurationShowService implements AbstractShowService<Authenticated, SystemConfiguration> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedConfigurationRepository repository;

	// AbstractShowService<Authenticated, SystemConfiguration> interface ----------------
	

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;

		SystemConfiguration result;

		result = this.repository.getSystemConfiguration();

		return result;
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "acceptedCurrencies","systemCurrency");
	}

}
