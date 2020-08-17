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

/**
 * The Class Intervention.
 */
public class Intervention extends BaseEntity{
	
	/** The work order. */
	private WorkOrder workOrder;
	
	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The date. */
	private Date date;
	
	/** The minutes. */
	private int minutes;

	/** The substitutions. */
	private Set<Substitution> substitutions=new HashSet<>();

	/**
	 * Instantiates a new intervention.
	 */
	public Intervention() {

	}
	
	/**
	 * Instantiates a new intervention.
	 *
	 * @param workOrder the work order
	 * @param mechanic the mechanic
	 */
	//La fecha no entra porque es la de hoy, no se le pasa
	public Intervention(WorkOrder workOrder, Mechanic mechanic) {
		this.date=new Date();
		Associations.Intervene.link(workOrder, this, mechanic);//El orden importa
	}
	
	/**
	 * Instantiates a new intervention.
	 *
	 * @param mechanic the mechanic
	 * @param workOrder2 the work order 2
	 * @param i the i
	 */
	public Intervention(Mechanic mechanic, WorkOrder workOrder2, int i) {
		this(workOrder2, mechanic);
		this.minutes=i;
	}

	/**
	 * Gets the work order.
	 *
	 * @return the work order
	 */
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	
	/**
	 * Sets the work order.
	 *
	 * @param workOrder the work order
	 */
	void _setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
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
		this.mechanic = mechanic;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Gets the minutes.
	 *
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Gets the substitutions.
	 *
	 * @return the sets the
	 */
	Set<Substitution> _getSubstitutions() {
		return substitutions;
	}
	
	/**
	 * Gets the sustitutions.
	 *
	 * @return the sustitutions
	 */
	public Set<Substitution> getSustitutions() {
		return new HashSet<>(substitutions);
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
		result = prime * result + ((mechanic == null) ? 0 : mechanic.hashCode());
		result = prime * result + ((workOrder == null) ? 0 : workOrder.hashCode());
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
		Intervention other = (Intervention) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
			return false;
		if (workOrder == null) {
			if (other.workOrder != null)
				return false;
		} else if (!workOrder.equals(other.workOrder))
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
		return "Intervention [workOrder=" + workOrder + ", mechanic=" + mechanic + ", date=" + date + ", minutes="
				+ minutes + "]";
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		double amount=0;

		//Spare parts price
		for(Substitution s : substitutions) {
			amount += s.getImporte();
		}

		//VehicleType price
		amount+=workOrder.getVehicle().getVehicleType().getPricePerHour()*minutes/60;

		return amount;
	}



}
