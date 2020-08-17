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

import alb.util.assertion.Argument;

/**
 * The Class Substitution.
 */
public class Substitution extends BaseEntity{
	
	/** The spare part. */
	private SparePart sparePart;
	
	/** The intervention. */
	private Intervention intervention;
	
	/** The quantity. */
	private int quantity;

	/**
	 * Instantiates a new substitution.
	 */
	Substitution(){
		
	}
	
	/**
	 * Instantiates a new substitution.
	 *
	 * @param sparePart the spare part
	 * @param intervention the intervention
	 */
	public Substitution(SparePart sparePart, Intervention intervention) {
		Associations.Sustitute.link(sparePart, this, intervention);
	}
	
	/**
	 * Instantiates a new substitution.
	 *
	 * @param sparePart the spare part
	 * @param intervention the intervention
	 * @param quantity the quantity
	 */
	public Substitution(SparePart sparePart, Intervention intervention, int quantity) {
		this(sparePart, intervention);
		Argument.isTrue(quantity>0,"Quantity must be >0");
		this.quantity=quantity;
	}
	
	/**
	 * Gets the spare part.
	 *
	 * @return the spare part
	 */
	public SparePart getSparePart() {
		return sparePart;
	}
	
	/**
	 * Sets the spare part.
	 *
	 * @param sparePart the spare part
	 */
	void _setSparePart(SparePart sparePart) {
		this.sparePart=sparePart;
	}
	
	/**
	 * Gets the intervention.
	 *
	 * @return the intervention
	 */
	public Intervention getIntervention() {
		return intervention;
	}
	
	/**
	 * Sets the intervention.
	 *
	 * @param intervention the intervention
	 */
	void _setIntervention(Intervention intervention) {
		this.intervention=intervention;
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
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
		result = prime * result + ((intervention == null) ? 0 : intervention.hashCode());
		result = prime * result + ((sparePart == null) ? 0 : sparePart.hashCode());
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
		Substitution other = (Substitution) obj;
		if (intervention == null) {
			if (other.intervention != null)
				return false;
		} else if (!intervention.equals(other.intervention))
			return false;
		if (sparePart == null) {
			if (other.sparePart != null)
				return false;
		} else if (!sparePart.equals(other.sparePart))
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
		return "Substitution [sparePart=" + sparePart + ", intervention=" + intervention + ", quantity=" + quantity
				+ "]";
	}
	
	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public double getImporte() {
		return sparePart.getPrice()*quantity;
	}



}
