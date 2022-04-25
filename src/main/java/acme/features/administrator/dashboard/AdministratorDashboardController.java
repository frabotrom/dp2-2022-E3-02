package acme.features.administrator.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.dashboards.AdminDashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorDashboardController extends AbstractController<Administrator, AdminDashboard>{
	
	//Internal state
	
	@Autowired
	protected AdministratorDashboardShowService2 showService;
	
	//Constructor
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}

}
