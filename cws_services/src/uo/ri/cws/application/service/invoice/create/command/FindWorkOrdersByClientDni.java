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
package uo.ri.cws.application.service.invoice.create.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.ClientRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

/**
 * Localiza las averías de un cliente que no están facturadas.
 *
 * @author UO258774
 */
public class FindWorkOrdersByClientDni implements Command<List<WorkOrderDto>> {

	/** The dni. */
	String dni;
	
	/** The wr. */
	private WorkOrderRepository wr=Factory.repository.forWorkOrder();
	
	/** The cr. */
	private ClientRepository cr=Factory.repository.forClient();
	
	/**
	 * Instantiates a new find work orders by client dni.
	 *
	 * @param dni the dni
	 */
	public FindWorkOrdersByClientDni(String dni) {
		this.dni=dni;
	}

	/**
	 * Execute.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	/**
	 * Comprueba que existe el cliente y devuelve una lista de sus averías que están sin facturar
	 */
	public List<WorkOrderDto> execute() throws BusinessException {
		
		//Comprobación de lógica de negocio (el cliente existe)
		BusinessCheck.isTrue(cr.findById(dni)!=null,"There is no client with dni: "+dni);
		
		return DtoAssembler.toWorkOrderDtoList(wr.findWorkOrdersByDni(dni));
	}

}
