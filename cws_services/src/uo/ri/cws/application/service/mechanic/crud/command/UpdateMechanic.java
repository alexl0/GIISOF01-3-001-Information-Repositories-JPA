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

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

/**
 * The Class UpdateMechanic.
 */
public class UpdateMechanic implements Command<Void> { 

	/** The dto. */
	private MechanicDto dto;

	/** The mr. */
	private MechanicRepository mr=Factory.repository.forMechanic();

	/**
	 * Instantiates a new update mechanic.
	 *
	 * @param dto the dto
	 */
	public UpdateMechanic(MechanicDto dto) {
		this.dto = dto;
	}

	/**
	 * Execute.
	 *
	 * @return the void
	 * @throws BusinessException the business exception
	 */
	public Void execute() throws BusinessException {

		if(dto.id.trim().isEmpty() || dto.id==null || dto.dni.trim().isEmpty() || dto.name.trim().isEmpty() || dto.surname.trim().isEmpty() || dto==null || dto.dni==null || dto.name==null || dto.surname==null)
			throw new BusinessException("Los campos no pueden estar vacíos.");

		Optional<Mechanic>om=mr.findById(dto.id);
		BusinessCheck.exists(om,"There is no such mechanic");

		//Existe
		Mechanic m=om.get();
		BusinessCheck.hasVersion(m, dto.version);//Se comprueba si existe ya una versión

		m.setName(dto.name);
		m.setSurname(dto.surname);

		return null;
	}

}
