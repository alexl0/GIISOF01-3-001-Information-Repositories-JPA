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

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;

/**
 * The Interface AssignWorkOrderService.
 */
public interface AssignWorkOrderService {

	/**
	 * Assigns the work order to mechanic so the he/she can see what
	 * 	work has to today/next. Only work orders with OPEN status can be assigned.
	 * 	Work orders changes to ASSIGNED status when assigned.
	 *
	 * @param woId the wo id
	 * @param mechanicId the mechanic id
	 * @throws BusinessException if:
	 * 	- the mechanic does not exist, or
	 *  - the work order does not exist, or
	 *  - the work order is not in OPEN status
	 */
	void assignWorkOrderToMechanic(String woId, String mechanicId)
			throws BusinessException;

	/**
	 * Returns a list of certificates (i.e, certified mechanics) for the
	 * 	vehicle type. Every certificate includes full mechanic data
	 * 	(@see MechanicDto).
	 *
	 * @param id of the vehicle type
	 * @return the list. It might be empty if no mechanic is certified for the
	 * 	specified vehicle type.
	 * @throws BusinessException the business exception
	 */
	List<CertificateDto> findCertificatesByVehicleTypeId(String id)
			throws BusinessException;

	/**
	 * Find unfinished work orders.
	 *
	 * @return a list of all work orders either in the OPEN or ASSIGN status
	 * @throws BusinessException the business exception
	 */
	List<WorkOrderDto> findUnfinishedWorkOrders() throws BusinessException;

}
