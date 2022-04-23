package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronagereport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListService implements AbstractListService<Patron, Patronagereport>{
	// Internal state ---------------------------------------------------------

			@Autowired
			protected PatronPatronageReportRepository repository;

		// AbstractListService<Inventor, Patronage>  interface -------------------------
			
			
			@Override
			public boolean authorise(final Request<Patronagereport> request) {
				assert request != null;

				return true;
			}
			
			@Override
			public Collection<Patronagereport> findMany(final Request<Patronagereport> request) {
				assert request != null;

				Collection<Patronagereport> result;
				final int patronId;

				patronId = request.getPrincipal().getActiveRoleId();
				result = this.repository.findManyPatronageReportsByPatronId(patronId);

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
