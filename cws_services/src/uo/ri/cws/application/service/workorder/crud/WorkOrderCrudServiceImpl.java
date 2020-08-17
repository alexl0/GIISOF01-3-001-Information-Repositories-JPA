package uo.ri.cws.application.service.workorder.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.service.workorder.crud.command.PruebaAutoria;
import uo.ri.cws.application.util.command.CommandExecutor;

public class WorkOrderCrudServiceImpl implements WorkOrderCrudService {

	private CommandExecutor executor = Factory.executor.forExecutor();
	
	@Override
	public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteWorkOrder(String id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<WorkOrderDto> findWorkOrderById(String woId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pruebaAutoria() throws BusinessException {
		executor.execute(new PruebaAutoria());
	}

}
