/* 
 * MIT License
 * 
 * Copyright (c) 2019 Alejandro León Pereira
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package uo.ri.cws.application.service.workorder;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Foreman
 * It follows the ISP principle (@see SOLID principles from RC Martin).
 */
public interface WorkOrderCrudService {

	/**
	 * Registers a new work order out of the data received. Only this fields
	 * will be taken into account:
	 * 	- the vehicle id, and
	 * 	- the description of the work to be done
	 * The rest of fields will assigned by the service, thus any provided value
	 * will be ignored.
	 *
	 * @param dto the dto
	 * @return another dto with the provided values and service-assigned
	 * 		fields filled: id, date and status
	 * @throws BusinessException if:
	 * 	- there is another work order for the same vehicle at the same
	 * 		date and time (timestamp), or
	 *  - the vehicle does not exist
	 */
	WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException;

	/**
	 * Updates the description of the work order specified by the id and
	 * 	version fields. No other field is taken for the update. Only work orders
	 * 	with status of OPEN or ASSIGNED can be updated.
	 *
	 * @param dto the dto
	 * @throws BusinessException if:
	 * 	- there is no work order with that id, or
	 *  - there work order has not the specified version (optimistic lock), or
	 *  - the work order is not in the OPEN or ASSIGNED status
	 */
	void updateWorkOrder(WorkOrderDto dto) throws BusinessException;

	/**
	 * Removes the work order form the system if it still do not have
	 * 	interventions.
	 *
	 * @param id the id
	 * @throws BusinessException if:
	 * 	- the work order does not exist, or
	 *  - there already is some intervention registered.
	 */
	void deleteWorkOrder(String id) throws BusinessException;

	/**
	 * Find work order by id.
	 *
	 * @param woId the wo id
	 * @return the optional filled if the work order exists
	 * @throws BusinessException the business exception
	 */
	Optional<WorkOrderDto> findWorkOrderById(String woId)
			throws BusinessException;

	/**
	 * Returns a list of work orders registered for the specified vehicle id.
	 *
	 * @param id the id
	 * @return the list, will be empty if:
	 * 	- the vehicle does not exist, or
	 *  - it has no work orders registered.
	 * @throws BusinessException the business exception
	 */
	List<WorkOrderDto> findWorkOrdersByVehicleId(String id)
			throws BusinessException;

	/**
	 * Returns a list of work orders registered for the specified plate number.
	 *
	 * @param plate the plate
	 * @return the list, will be empty if:
	 * 	- the vehicle does not exist, or
	 *  - it has no work orders registered.
	 * @throws BusinessException the business exception
	 */
	List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate)
			throws BusinessException;

	void pruebaAutoria() throws BusinessException;

}
