package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Date;
import java.util.List;
import java.util.Set;

import alb.util.date.Dates;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.InterventionRepository;
import uo.ri.cws.application.repository.SubstitutionRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Intervention;
import uo.ri.cws.domain.Substitution;
import uo.ri.cws.domain.WorkOrder;

public class PruebaAutoria implements Command<Void> {

	private WorkOrderRepository wr=Factory.repository.forWorkOrder();
	private InterventionRepository ir=Factory.repository.forIntervention();
	
	//Se ha creado éste repositorio
	private SubstitutionRepository sr=Factory.repository.forSubstitution();

	@Override
	public Void execute() throws BusinessException {

		Date oneWeekAgo=Dates.subDays(Dates.today(), 7);

		List<WorkOrder>workOrders=wr.findAll();

		for (WorkOrder w:workOrders) {
			if(w.getDate().before(oneWeekAgo) && w.getStatus()==WorkOrder.WorkOrderStatus.ASSIGNED) {

				Set<Intervention>interventions=w.getInterventions();
				for(Intervention i:interventions) {
					Set<Substitution>substitutions=i.getSustitutions();
					for(Substitution s:substitutions) {
						sr.remove(s);
					}
					ir.remove(i);
				}

				wr.remove(w);
			}
		}

		return null;
	}

}
