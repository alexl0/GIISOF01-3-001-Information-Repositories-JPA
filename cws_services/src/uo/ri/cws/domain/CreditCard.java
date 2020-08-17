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
import alb.util.assertion.StateCheck;
import alb.util.date.Dates;

/**
 * The Class CreditCard.
 */
public class CreditCard extends PaymentMean {/** The number. */
//Ya hereda de base entity, pq ya hereda de paymentmean
	private String number;
	
	/** The type. */
	private String type;
	
	/** The valid thru. */
	private Date validThru;

	/**
	 * Instantiates a new credit card.
	 */
	CreditCard(){

	}
	
	/**
	 * Instantiates a new credit card.
	 *
	 * @param number the number
	 */
	public CreditCard(String number) {
		super();
		this.number = number;
	}
	
	/**
	 * Instantiates a new credit card.
	 *
	 * @param number the number
	 * @param type the type
	 * @param validThru the valid thru
	 */
	public CreditCard(String number, String type, Date validThru) {
		this(number);
		this.type=type;
		StateCheck.isTrue(validThru.after(Dates.today()), "Expired credit card");
		this.validThru=validThru;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the valid thru.
	 *
	 * @return the valid thru
	 */
	public Date getValidThru() {
		return new Date(this.validThru.getTime());//Devolvemos una copia, no el objeto
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
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
		return "CreditCard [number=" + number + ", type=" + type + ", validThru=" + validThru + "]";
	}

}
