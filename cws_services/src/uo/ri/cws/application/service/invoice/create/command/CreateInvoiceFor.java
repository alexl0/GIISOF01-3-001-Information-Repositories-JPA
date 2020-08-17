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
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoiceDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;
/**
 * Dada una serie de reparaciones, se comprueba que han terminado y que no estan facturadas
 * y se facturan.
 * @author UO258774
 *
 */
public class CreateInvoiceFor implements Command<InvoiceDto> {

	/** The work order ids. */
	private List<String> workOrderIds;
	
	/** The ir. */
	private InvoiceRepository ir=Factory.repository.forInvoice();
	
	/** The wr. */
	private WorkOrderRepository wr=Factory.repository.forWorkOrder();

	/**
	 * Constructor al que se le pasan los id's de las workOrders desde la clase CreateInvoiceServiceImpl.
	 *
	 * @param workOrderIds the work order ids
	 */
	public CreateInvoiceFor(List<String> workOrderIds) {
		this.workOrderIds = workOrderIds;
	}

	/**
	 * Execute.
	 *
	 * @return the invoice dto
	 * @throws BusinessException the business exception
	 */
	@Override
	/**
	 * Comando execute que hace las comprobaciones de negocio oportunas y añade la factura.
	 * De hacer persistentes los cambios (o hacer un rollback si es necesario) ya se encarga
	 * 
	 */
	public InvoiceDto execute() throws BusinessException {
		Long next=ir.getNextInvoiceNumber();
		List<WorkOrder>workOrders=wr.findByIds(workOrderIds);

		//Lógica de negocio
		BusinessCheck.isFalse(workOrders.isEmpty(),"There is no work orders.");
		for(WorkOrder w:workOrders) {
			BusinessCheck.isTrue(w.getStatus().equals(WorkOrderStatus.FINISHED),"The work order is not in FINISHED state.");
			BusinessCheck.isFalse(w.getStatus().equals(WorkOrderStatus.INVOICED),"The work order already in INVOICED state.");
		}

		Invoice i=new Invoice(next,workOrders);
		ir.add(i);

		return DtoAssembler.toDto(i);
	}

}
