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
package uo.ri.cws.application.service.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.mechanic.crud.command.AddMechanic;
import uo.ri.cws.application.service.mechanic.crud.command.DeleteMechanic;
import uo.ri.cws.application.service.mechanic.crud.command.FindAllMechanics;
import uo.ri.cws.application.service.mechanic.crud.command.FindMechanicById;
import uo.ri.cws.application.service.mechanic.crud.command.UpdateMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

/**
 * The Class MechanicCrudServiceImpl.
 */
public class MechanicCrudServiceImpl implements MechanicCrudService {
	
	/** The executor. */
	private CommandExecutor executor = Factory.executor.forExecutor();
	

	/**
	 * Adds the mechanic.
	 *
	 * @param mecanico the mecanico
	 * @return the mechanic dto
	 * @throws BusinessException the business exception
	 */
	@Override
	public MechanicDto addMechanic(MechanicDto mecanico) throws BusinessException {
		return executor.execute(new AddMechanic( mecanico ));
	}

	/**
	 * Update mechanic.
	 *
	 * @param mecanico the mecanico
	 * @throws BusinessException the business exception
	 */
	@Override
	public void updateMechanic(MechanicDto mecanico) throws BusinessException {
		executor.execute(new UpdateMechanic( mecanico ));
	}

	/**
	 * Delete mechanic.
	 *
	 * @param idMecanico the id mecanico
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deleteMechanic(String idMecanico) throws BusinessException {
		executor.execute(new DeleteMechanic(idMecanico));
	}

	/**
	 * Find all mechanics.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		return executor.execute(new FindAllMechanics());
	}

	/**
	 * Find mechanic by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws BusinessException the business exception
	 */
	@Override
	public Optional<MechanicDto> findMechanicById(String id) throws BusinessException {
		return executor.execute(new FindMechanicById(id));
	}

}