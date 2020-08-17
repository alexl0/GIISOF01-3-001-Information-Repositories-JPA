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
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

/**
 * The Class DeleteMechanic.
 */
public class DeleteMechanic implements Command<Void>{

	/** The mechanic id. */
	private String mechanicId;
	
	/** The mr. */
	private MechanicRepository mr=Factory.repository.forMechanic();

	/**
	 * Instantiates a new delete mechanic.
	 *
	 * @param idMecanico the id mecanico
	 */
	public DeleteMechanic(String idMecanico) {
		this.mechanicId = idMecanico;
	}

	/**
	 * Execute.
	 *
	 * @return the void
	 * @throws BusinessException the business exception
	 */
	public Void execute() throws BusinessException {
				
		Optional<Mechanic> om=mr.findById(mechanicId);
		
		//Comprobar si tiene intervenciones
		BusinessCheck.isTrue(om.isPresent(),"This mechanic does not exist");
		Mechanic m=om.get();//Nos hemos asegurado de que existe
		BusinessCheck.isTrue(m.getInterventions().isEmpty(),"This mechanic has interventions");
		mr.remove(m);

		return null;
	}

}
