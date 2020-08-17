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
package uo.ri.cws.application.repository;

import java.util.List;
import uo.ri.cws.domain.WorkOrder;

/**
 * The Interface WorkOrderRepository.
 */
public interface WorkOrderRepository extends Repository<WorkOrder>{

	/**
	 * Find by ids.
	 *
	 * @param workOrderIds the work order ids
	 * @return lista con averias cuyo id aparece en idsAveria,
	 * 	o lista vacía si no hay ninguna
	 */
	List<WorkOrder> findByIds(List<String> workOrderIds);

	/**
	 * Retorna las work orders de un determinado cliente por su dni.
	 *
	 * @param dni the dni
	 * @return the list
	 */
	List<WorkOrder> findWorkOrdersByDni(String dni);
	
	List<WorkOrder> findAll();
}