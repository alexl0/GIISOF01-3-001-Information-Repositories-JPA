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
import uo.ri.cws.domain.Invoice.InvoiceStatus;

/**
 * The Class Charge.
 */
public class Charge extends BaseEntity{
	
	/** The invoice. */
	private Invoice invoice;
	
	/** The payment mean. */
	private PaymentMean paymentMean;
	
	/** The amount. */
	private double amount;

	/**
	 * Instantiates a new charge.
	 */
	Charge(){

	}
	
	/**
	 * Instantiates a new charge.
	 *
	 * @param invoice the invoice
	 * @param paymentMean the payment mean
	 * @param amount the amount
	 */
	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		//Si es un bono, comprobar que tenga suficiente para pagar
		if(paymentMean.getClass()==Voucher.class) {
			StateCheck.isTrue(amount<=((Voucher)paymentMean).getDisponible(),"Not enough money avaliable in the voucher");
			((Voucher)paymentMean).setAvaliable(((Voucher)paymentMean).getDisponible()-amount);
		}
		// store the amount
		this.amount = amount;
		// increment the paymentMean accumulated ( paymentMean.pay( -amount) )
		paymentMean.pay(amount);
		// link invoice, this and paymentMean
		Associations.Charges.link(invoice, this, paymentMean);
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
	 * Gets the payment mean.
	 *
	 * @return the payment mean
	 */
	public PaymentMean getPaymentMean() {
		return paymentMean;
	}
	
	/**
	 * Sets the payment mean.
	 *
	 * @param payMentMean the pay ment mean
	 */
	void _setPaymentMean(PaymentMean payMentMean) {
		this.paymentMean=payMentMean;
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Unlinks this charge and restores the value to the payment mean.
	 *
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		// assert the invoice is not in PAID status
		StateCheck.isTrue(!invoice.getStatus().equals(InvoiceStatus.PAID));
		// decrement the payment mean accumulated ( paymentMean.pay( -amount) )
		paymentMean.pay(-amount);
		// unlink invoice, this and paymentMean
		Associations.Charges.unlink(this);
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
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((paymentMean == null) ? 0 : paymentMean.hashCode());
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
		Charge other = (Charge) obj;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		if (paymentMean == null) {
			if (other.paymentMean != null)
				return false;
		} else if (!paymentMean.equals(other.paymentMean))
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
		return "Charge [invoice=" + invoice + ", paymentMean=" + paymentMean + ", amount=" + amount + "]";
	}

}
