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
package uo.ri.cws.application.service.invoice.create;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.CreateInvoiceService;
import uo.ri.cws.application.service.invoice.InvoiceDto;
import uo.ri.cws.application.service.invoice.create.command.CreateInvoiceFor;
import uo.ri.cws.application.service.invoice.create.command.FindWorkOrdersByClientDni;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.command.CommandExecutor;

/**
 * The Class CreateInvoiceServiceImpl.
 */
public class CreateInvoiceServiceImpl implements CreateInvoiceService {

	/** The executor. */
	private CommandExecutor executor = Factory.executor.forExecutor();

	/**
	 * Creates the invoice for.
	 *
	 * @param woIds the wo ids
	 * @return the invoice dto
	 * @throws BusinessException the business exception
	 */
	@Override
	public InvoiceDto createInvoiceFor(List<String> woIds)
			throws BusinessException {

		return executor.execute( new CreateInvoiceFor( woIds) );
	}

	/**
	 * Find work orders by client dni.
	 *
	 * @param dni the dni
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<WorkOrderDto> findWorkOrdersByClientDni(String dni)
			throws BusinessException {
		return executor.execute(new FindWorkOrdersByClientDni(dni));
	}

}
