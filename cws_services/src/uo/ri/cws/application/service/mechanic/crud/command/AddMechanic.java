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
package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

/**
 * The Class AddMechanic.
 */
public class AddMechanic implements Command<MechanicDto>{

	/** The dto. */
	private MechanicDto dto;

	/** The mr. */
	private MechanicRepository mr=Factory.repository.forMechanic();

	/**
	 * Instantiates a new adds the mechanic.
	 *
	 * @param mecanico the mecanico
	 */
	public AddMechanic(MechanicDto mecanico) {
		this.dto = mecanico;
	}

	/**
	 * Execute.
	 *
	 * @return the mechanic dto
	 * @throws BusinessException the business exception
	 */
	public MechanicDto execute() throws BusinessException {

		if(dto.dni.trim().isEmpty() || dto.name.trim().isEmpty() || dto.surname.trim().isEmpty() || dto==null || dto.dni==null || dto.name==null || dto.surname==null)
			throw new BusinessException("Los campos no pueden estar vacíos.");

		Mechanic m=new Mechanic(dto.dni, dto.name, dto.surname);

		//em.persist(m);
		mr.add(m); //El repositorio hace persist

		dto.id=m.getId();

		return dto;
	}

}
