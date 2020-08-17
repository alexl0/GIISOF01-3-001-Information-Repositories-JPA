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
package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import alb.util.assertion.StateCheck;
import alb.util.date.Dates;
import alb.util.math.Round;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

/**
 * The Class Invoice.
 */
public class Invoice extends BaseEntity{
	
	/**
	 * The Enum InvoiceStatus.
	 */
	public enum InvoiceStatus { 
 /** The not yet paid. */
 NOT_YET_PAID, 
 /** The paid. */
 PAID }
	
	/** The number. */
	private Long number;
	
	/** The date. */
	private Date date;
	
	/** The amount. */
	private double amount;
	
	/** The vat. */
	private double vat;
	
	/** The status. */
	private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;

	/** The work orders. */
	private Set<WorkOrder> workOrders=new HashSet<>();
	
	/** The charges. */
	private Set<Charge>charges=new HashSet<>();

	/**
	 * Instantiates a new invoice.
	 */
	Invoice(){

	}
	
	/**
	 * Instantiates a new invoice.
	 *
	 * @param number the number
	 */
	public Invoice(Long number) {
		this(number, new Date());
	}
	
	/**
	 * Instantiates a new invoice.
	 *
	 * @param number the number
	 * @param date the date
	 */
	public Invoice(Long number, Date date) {
		// TODO check arguments (always), through IllegalArgumentException

		// store the number
		this.number=number;
		// store a copy of the date
		this.date=new Date(date.getTime());
	}
	
	/**
	 * Instantiates a new invoice.
	 *
	 * @param number the number
	 * @param workOrders the work orders
	 */
	public Invoice(Long number, List<WorkOrder> workOrders) {
		this(number);
		for(WorkOrder w:workOrders) {
			StateCheck.isTrue(WorkOrderStatus.FINISHED.equals(w.getStatus()),"Any of the work orders is not in FINISHED state");
			addWorkOrder(w);
		}
	}
	
	/**
	 * Instantiates a new invoice.
	 *
	 * @param number the number
	 * @param date the date
	 * @param workOrders the work orders
	 */
	public Invoice(Long number, Date date, List<WorkOrder> workOrders) {
		this(number,workOrders);
		this.date=date;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return new Date(this.date.getTime());
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date=new Date(date.getTime());
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		//Hay que calcular el importe antes de devolverlo, ya que si no, devuelve siempre 0. Por eso no pasaba el test.
		computeAmount();
		return amount;
	}
	
	/**
	 * Gets the vat.
	 *
	 * @return the vat
	 */
	public double getVat() {
		return vat;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public InvoiceStatus getStatus() {
		return status;
	}

	/**
	 * Gets the work orders.
	 *
	 * @return the sets the
	 */
	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}
	
	/**
	 * Gets the work orders.
	 *
	 * @return the work orders
	 */
	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<>(workOrders);
	}
	
	/**
	 * Gets the charges.
	 *
	 * @return the sets the
	 */
	Set<Charge> _getCharges() {
		return charges;
	}
	
	/**
	 * Gets the charges.
	 *
	 * @return the charges
	 */
	public Set<Charge> getCharges() {
		return new HashSet<>(charges);
	}

	/**
	 * Computed amount and vat (vat depends on the date).
	 */
	private void computeAmount() {
		if(date.before(Dates.fromDdMmYyyy(1, 7, 2012))){
			this.vat=18;
		}
		else
			this.vat=21;
		double amountWorkOrders=0;
		for(WorkOrder workOrder:workOrders) {
			amountWorkOrders+=workOrder.getAmount();
		}
		amountWorkOrders+=(amountWorkOrders*this.vat/100);
		amountWorkOrders=Round.twoCents(amountWorkOrders);
		this.amount=amountWorkOrders;
	}

	/**
	 * Adds (double links) the workOrder to the invoice and updates the amount and vat .
	 *
	 * @param workOrder the work order
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 * @see State diagrams on the problem statement document
	 */
	public void addWorkOrder(WorkOrder workOrder) {
		StateCheck.isTrue(this.status.equals(InvoiceStatus.NOT_YET_PAID),"The invoice status is not NOT_YET_PAID");
		StateCheck.isTrue(workOrder.getStatus().equals(WorkOrderStatus.FINISHED),"The invoice status is not NOT_YET_PAID");
		Associations.ToInvoice.link(this, workOrder);
		workOrder.markAsInvoiced();
		computeAmount();
		workOrders.add(workOrder);
	}

	/**
	 * Removes a work order from the invoice and recomputes amount and vat.
	 *
	 * @param workOrder the work order
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 * @see State diagrams on the problem statement document
	 */
	public void removeWorkOrder(WorkOrder workOrder) {
		StateCheck.isTrue(this.status.equals(InvoiceStatus.NOT_YET_PAID),"The invoice status is not NOT_YET_PAID");
		Associations.ToInvoice.unlink(this, workOrder);
		workOrder.markBackToFinished();
		computeAmount();
	}

	/**
	 * Marks the invoice as PAID, but.
	 *
	 * @throws IllegalStateException if
	 * 	- Is already settled 
	 *  - Or the amounts paid with charges to payment means do not cover 
	 *  	the total of the invoice
	 */
	public void settle() {
		StateCheck.isTrue(this.status.equals(InvoiceStatus.PAID),"The invoice is already settled");
		double quantity=0;
		for(Charge c:charges) {
			quantity+=c.getAmount();
		}
		StateCheck.isTrue(quantity>=this.getAmount(),"The amounts paid with charges to payment means do not cover the total of the invoice");
		this.status=InvoiceStatus.PAID;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Invoice [number=" + number + ", date=" + date + ", amount=" + amount + ", vat=" + vat + ", status="
				+ status + "]";
	}

}
