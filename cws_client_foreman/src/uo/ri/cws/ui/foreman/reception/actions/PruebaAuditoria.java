package uo.ri.cws.ui.foreman.reception.actions;

import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;

public class PruebaAuditoria implements Action{

	@Override
	public void execute() throws Exception {
		
		WorkOrderCrudService as = Factory.service.forWorkOrderService();
		as.pruebaAutoria();
		
		System.out.println("Se han borrado las workOrders con más de una semana de antiguedad y estado ASSIGNED");
		
	}

}
