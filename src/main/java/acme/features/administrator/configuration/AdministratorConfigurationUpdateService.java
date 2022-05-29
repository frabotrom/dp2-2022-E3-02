package acme.features.administrator.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationRepository repository;

	// AbstractUpdateService<Administrator, SystemConfiguration> interface ----------------


	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if (!errors.hasErrors("systemCurrency")) {
			
			SystemConfiguration existing;
			existing = this.repository.getSystemConfiguration();
			final String acceptedCurrencies = existing.getAcceptedCurrencies().replace(" ","");
			final List<String> acceptedCurrenciesList = Arrays.asList(acceptedCurrencies.split(","));
			
			final String newAcceptedCurrencies = entity.getAcceptedCurrencies().replace(" ","");
			final List<String> newAcceptedCurrenciesList = Arrays.asList(newAcceptedCurrencies.split(","));
			
			errors.state(request, acceptedCurrenciesList.contains(entity.getSystemCurrency()) || newAcceptedCurrenciesList.contains(entity.getSystemCurrency()),
				"systemCurrency", "administrator.configuration.form.error.system-currency");
		}
		
		if (!errors.hasErrors("acceptedCurrencies")) {
			
			final String acceptedCurrencies = entity.getAcceptedCurrencies().replace(" ","");
			final List<String> acceptedCurrenciesList = Arrays.asList(acceptedCurrencies.split(","));
			Boolean currenciesResult = true;
			for (final String s: acceptedCurrenciesList) {
				if(!Pattern.matches("^[A-Z]{3}$", s)) {
					currenciesResult = false;
				}
			}
			
			errors.state(request, currenciesResult,"acceptedCurrencies", "administrator.configuration.form.error.accepted-currencies");
		}
	}
	
	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "strongSpamTerms", "weakSpamTerms", "acceptedCurrencies","systemCurrency","weakThreshold", "strongThreshold");
	}


	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "strongSpamTerms", "weakSpamTerms", "acceptedCurrencies","systemCurrency","weakThreshold", "strongThreshold");
	}
	
	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;

		SystemConfiguration result;

		result = this.repository.getSystemConfiguration();

		return result;
	}
	
	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
	
	

}
