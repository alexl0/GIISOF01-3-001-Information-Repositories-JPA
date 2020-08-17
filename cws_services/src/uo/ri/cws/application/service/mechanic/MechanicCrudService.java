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
package uo.ri.cws.application.service.mechanic;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin).
 */
public interface MechanicCrudService {

	/**
	 * Add a new mechanic to the system with the data specified in the dto.
	 * 		The id value will be ignored
	 * @param mecanico dto
	 * @return the dto with the id filed updated to the UUID generated
	 * @throws BusinessException if there already exist another 
	 * 		mechanic with the same dni
	 */
	MechanicDto addMechanic(MechanicDto mecanico) throws BusinessException;
	
	/**
	 * Delete mechanic.
	 *
	 * @param idMecanico the id mecanico
	 * @throws BusinessException if the mechanic does not exist
	 */
	void deleteMechanic(String idMecanico) throws BusinessException;
	
	/**
	 * Updates values for the mechanic specified by the id field, 
	 * 		just name and surname will be updated.
	 *
	 * @param mechanic dto, the id field, name and surname cannot be null
	 * @throws BusinessException if the mechanic does not exist
	 */
	void updateMechanic(MechanicDto mechanic) throws BusinessException;

	/**
	 * Find mechanic by id.
	 *
	 * @param id of the mechanic
	 * @return the dto for the mechanic or null if there is none with the id
	 * DOES NOT @throws BusinessException
	 * @throws BusinessException the business exception
	 */
	Optional<MechanicDto> findMechanicById(String id) throws BusinessException;
	
	/**
	 * Find all mechanics.
	 *
	 * @return the list of all mechanics registered in the system
	 * 	without regarding their contract status or if they have 
	 * 	contract or not. It might be an empty list if there is no mechanic
	 * 
	 * DOES NOT @throws BusinessException
	 * @throws BusinessException the business exception
	 */
	List<MechanicDto> findAllMechanics() throws BusinessException;
	
}
