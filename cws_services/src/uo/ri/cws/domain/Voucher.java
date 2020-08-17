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

import alb.util.assertion.StateCheck;

/**
 * The Class Voucher.
 */
public class Voucher extends PaymentMean {
	
	/** The code. */
	private String code;
	
	/** The available. */
	private double available;
	
	/** The description. */
	private String description;

	/**
	 * Instantiates a new voucher.
	 */
	Voucher(){

	}
	
	/**
	 * Instantiates a new voucher.
	 *
	 * @param code the code
	 */
	public Voucher(String code) {
		super();
		this.code = code;
	}
	
	/**
	 * Instantiates a new voucher.
	 *
	 * @param code the code
	 * @param avaliable the avaliable
	 * @param description the description
	 */
	public Voucher(String code, double avaliable, String description) {
		this(code);
		this.available=avaliable;
		this.description=description;
	}

	/**
	 * Instantiates a new voucher.
	 *
	 * @param code the code
	 * @param avaliable the avaliable
	 */
	public Voucher(String code, double avaliable) {
		this(code);
		this.setAvaliable(avaliable);
	}
	
	/**
	 * Augments the accumulated and decrements the available .
	 *
	 * @return the code
	 * @throws IllegalStateException if not enough available to pay
	 */
	/*	@Override
	public void pay(double amount) {
		//if(amount<=this.getAvailable())
		//this.available=this.getAvailable()-amount;
	}
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Gets the disponible.
	 *
	 * @return the disponible
	 */
	public double getDisponible() {
		return available;
	}
	
	/**
	 * Sets the avaliable.
	 *
	 * @param avaliable the new avaliable
	 */
	public void setAvaliable(double avaliable) {
		StateCheck.isTrue(avaliable>=0,"The parameter 'avalable' must be >=0");
		this.available=avaliable;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voucher other = (Voucher) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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
		return "Voucher [code=" + code + ", available=" + available + ", description=" + description + "]";
	}
	
	/**
	 * Sets the descripcion.
	 *
	 * @param description the new descripcion
	 */
	public void setDescripcion(String description) {
		this.description=description;

	}

}
