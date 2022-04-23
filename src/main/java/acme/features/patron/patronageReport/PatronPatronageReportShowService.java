package acme.features.patron.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronagereport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, Patronagereport>{
	// Internal state ---------------------------------------------------------

				@Autowired
				protected PatronPatronageReportRepository repository;

			// AbstractListService<Inventor, Patronage>  interface -------------------------
				
				
				@Override
				public boolean authorise(final Request<Patronagereport> request) {
					assert request != null;
					
					boolean result;
					int id;
					Patronagereport patronagereport;

					id = request.getModel().getInteger("id");
					patronagereport = this.repository.findOnePatronageReportById(id);
					result = patronagereport != null && patronagereport.getPatronage().getId() == request.getPrincipal().getActiveRoleId();

					return result;
				}
				
				@Override
				public Patronagereport findOne(final Request<Patronagereport> request) {
					assert request != null;

					Patronagereport result;
					int id;

					id = request.getModel().getInteger("id");
					result = this.repository.findOnePatronageReportById(id);

					return result;
				}
				
				@Override
				public void unbind(final Request<Patronagereport> request, final Patronagereport entity, final Model model) {
					
					assert request != null;
					assert entity != null;
					assert model != null;
					
					request.unbind(entity, model, "creationMoment", "memorandum", "optionalLink");
					
					
					
				}
}
