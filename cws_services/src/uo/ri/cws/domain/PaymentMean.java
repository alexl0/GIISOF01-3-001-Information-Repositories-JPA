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

import java.util.HashSet;
import java.util.Set;

/**
 * The Class PaymentMean.
 */
public abstract class PaymentMean extends BaseEntity{
	
	/** The accumulated. */
	private double accumulated = 0.0;

	/** The client. */
	private Client client;
	
	/** The charges. */
	private Set<Charge> charges=new HashSet<>();

	//No se pone constructor por defecto porque es una clase abstracta

	/**
	 * Pay.
	 *
	 * @param importe the importe
	 */
	public void pay(double importe) {
		this.accumulated += importe;
	}

	/**
	 * Gets the accumulated.
	 *
	 * @return the accumulated
	 */
	public double getAccumulated() {
		return accumulated;
	}

	/**
	 * Gets the client.
	 *
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}
	
	/**
	 * Sets the client.
	 *
	 * @param client the client
	 */
	void _setClient(Client client) {
		this.client=client;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
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
		PaymentMean other = (PaymentMean) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
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
		return "PaymentMean [accumulated=" + accumulated + ", client=" + client + "]";
	}

}
