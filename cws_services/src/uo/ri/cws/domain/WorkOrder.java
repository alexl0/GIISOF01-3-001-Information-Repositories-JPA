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
import java.util.Set;

import alb.util.assertion.StateCheck;

/**
 * The Class WorkOrder.
 */
public class WorkOrder extends BaseEntity{
	
	/**
	 * The Enum WorkOrderStatus.
	 */
	public enum WorkOrderStatus {
		
		/** The open. */
		OPEN,
		
		/** The assigned. */
		ASSIGNED,
		
		/** The finished. */
		FINISHED,
		
		/** The invoiced. */
		INVOICED		
	}
	
	/** The date. */
	private Date date;
	
	/** The description. */
	private String description;
	
	/** The amount. */
	private double amount = 0.0;
	
	/** The status. */
	private WorkOrderStatus status = WorkOrderStatus.OPEN;

	/** The vehicle. */
	private Vehicle vehicle;
	
	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The invoice. */
	private Invoice invoice;
	
	/** The interventions. */
	private Set<Intervention> interventions=new HashSet<>();

	/**
	 * Instantiates a new work order.
	 */
	public WorkOrder() {
		
	}
	
	/**
	 * Instantiates a new work order.
	 *
	 * @param vehicle the vehicle
	 */
	public WorkOrder(Vehicle vehicle) {
		this.date=new Date();

		//this.vehicle = vehicle;
		Associations.Order.link(vehicle, this);
	}
	
	/**
	 * Instantiates a new work order.
	 *
	 * @param vehicle the vehicle
	 * @param description the description
	 */
	public WorkOrder(Vehicle vehicle, String description) {
		this(vehicle);
		this.description=description;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return new Date(date.getTime());//Para que pase el test
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		//Se calcula primero para que no devuelva 0
		computeAmount();
		return amount;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public WorkOrderStatus getStatus() {
		return status;
	}

	/**
	 * Gets the vehicle.
	 *
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	/**
	 * Sets the vehicle.
	 *
	 * @param vehicle the vehicle
	 */
	void _setVehicle(Vehicle vehicle) {
		this.vehicle=vehicle;
	}
	
	/**
	 * Gets the mechanic.
	 *
	 * @return the mechanic
	 */
	public Mechanic getMechanic() {
		return mechanic;
	}
	
	/**
	 * Sets the mechanic.
	 *
	 * @param mechanic the mechanic
	 */
	void _setMechanic(Mechanic mechanic) {
		this.mechanic=mechanic;
	}
	
	/**
	 * Gets the invoice.
	 *
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}
	
	/**
	 * Sets the invoice.
	 *
	 * @param invoice the invoice
	 */
	void _setInvoice(Invoice invoice) {
		this.invoice=invoice;
	}
	
	/**
	 * Gets the interventions.
	 *
	 * @return the sets the
	 */
	Set<Intervention> _getInterventions() {
		return interventions;
	}
	
	/**
	 * Gets the interventions.
	 *
	 * @return the interventions
	 */
	public Set<Intervention> getInterventions() {
		return new HashSet<Intervention>(interventions);
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
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
		WorkOrder other = (WorkOrder) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
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
		return "WorkOrder [date=" + date + ", description=" + description + ", amount=" + amount + ", status=" + status
				+ ", vehicle=" + vehicle + "]";
	}

	/**
	 * Changes it to INVOICED state given the right conditions
	 * This method is called from Invoice.addWorkOrder(...)
	 *
	 * @throws IllegalStateException if
	 * 	- The work order is not FINISHED, or
	 *  - The work order is not linked with the invoice
	 * @see State diagrams on the problem statement document
	 */
	public void markAsInvoiced() {
		StateCheck.isTrue(WorkOrderStatus.FINISHED.equals(status)&&this.getInvoice()!=null,"The work order is not in FINISHED state");//invoice no puede ser null pa que pase el test
		this.status=WorkOrderStatus.INVOICED;
	}

	/**
	 * Changes it to FINISHED state given the right conditions and 
	 * computes the amount.
	 *
	 * @throws IllegalStateException if
	 * 	- The work order is not in ASSIGNED state, or
	 *  - The work order is not linked with a mechanic
	 * @see State diagrams on the problem statement document
	 */
	public void markAsFinished() {
		StateCheck.isTrue(WorkOrderStatus.ASSIGNED.equals(status)&&this.getMechanic()!=null,"The work order is not in ASSIGNED state");
		Associations.Assign.link(mechanic,this);
		computeAmount();
		status=WorkOrderStatus.FINISHED;
	}
	
	/**
	 * Compute amount.
	 */
	private void computeAmount() {
		double amount=0.0;
		for(Intervention i : interventions) {
			amount+=i.getAmount();
		}
		this.amount=amount;
	}
	
	/**
	 * Changes it back to FINISHED state given the right conditions
	 * This method is called from Invoice.removeWorkOrder(...)
	 *
	 * @throws IllegalStateException if
	 * 	- The work order is not INVOICED, or
	 *  - The work order is still linked with the invoice
	 * @see State diagrams on the problem statement document
	 */
	public void markBackToFinished() {
		StateCheck.isTrue(WorkOrderStatus.INVOICED.equals(status)&&this.getInvoice()==null,"The work order is not in INVOICED state");
		this.status=WorkOrderStatus.FINISHED;
	}

	/**
	 * Links (assigns) the work order to a mechanic and then changes its status
	 * to ASSIGNED.
	 *
	 * @param mechanic the mechanic
	 * @throws IllegalStateException if
	 * 	- The work order is not in OPEN status, or
	 *  - The work order is already linked with another mechanic
	 * @see State diagrams on the problem statement document
	 */
	public void assignTo(Mechanic mechanic) {
		StateCheck.isTrue(WorkOrderStatus.OPEN.equals(status)&&this.getMechanic()==null,"The work order is not in OPEN state");
		Associations.Assign.link(mechanic, this);
		this.status=WorkOrderStatus.ASSIGNED;
	}

	/**
	 * Unlinks (deassigns) the work order and the mechanic and then changes 
	 * its status back to OPEN.
	 *
	 * @throws IllegalStateException if
	 * 	- The work order is not in ASSIGNED status
	 * @see State diagrams on the problem statement document
	 */
	public void desassign() {
		StateCheck.isTrue(WorkOrderStatus.ASSIGNED.equals(status)&&this.getMechanic()!=null,"The work order is not in ASSIGNED state");
		Associations.Assign.unlink(mechanic, this);
	}

	/**
	 * In order to assign a work order to another mechanic is first have to
	 * be moved back to OPEN state and unlinked from the previous mechanic.  
	 *
	 * @throws IllegalStateException if
	 * 	- The work order is not in FINISHED status
	 * @see State diagrams on the problem statement document
	 */
	public void reopen() {
		StateCheck.isTrue(WorkOrderStatus.FINISHED.equals(status),"The work order is not in FINISHED state");
		this.status=WorkOrderStatus.OPEN;
		Associations.Assign.unlink(mechanic, this);
	}

}
