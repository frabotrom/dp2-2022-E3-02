package acme.features.authenticated.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.SystemConfiguration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedConfigurationController extends AbstractController<Authenticated, SystemConfiguration> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedConfigurationShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
}