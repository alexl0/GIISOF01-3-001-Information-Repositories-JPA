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

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.cws.domain.Cash;
import uo.ri.cws.domain.Charge;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.CreditCard;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.Voucher;

/**
 * The Class ChargeTests.
 */
public class ChargeTests {

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * A charge to a card increases the accumulated.
	 */
	@Test
	public void testCargoTarjeta() {
		Date tomorrow = Dates.tomorrow();
		CreditCard t = new CreditCard("123", "visa", tomorrow);
		Invoice f = new Invoice( 123L );

		new Charge(f, t, 100.0);

		assertTrue(t.getAccumulated() == 100.0);
	}

	/**
	 * A charge to cash increases the accumulated.
	 */
	@Test
	public void testCargoMetalico() {
		Cash m = new Cash(new Client("123", "n", "a"));
		Invoice f = new Invoice( 123L );

		new Charge(f, m, 100.0);

		assertTrue(m.getAccumulated() == 100.0);
	}

	/**
	 * A charge to a voucher increases the accumulated and decreases the
	 * available.
	 */
	@Test
	public void testCargoBono() {
		Voucher b = new Voucher("123", 150.0, "For testing");
		Invoice f = new Invoice(123L);

		new Charge(f, b, 100.0);

		assertTrue(b.getAccumulated() == 100.0);
		assertTrue(b.getDisponible() == 50.0);
	}

	/**
	 * A credit card cannot be charged if its expiration date is over.
	 *
	 * @throws IllegalStateException the illegal state exception
	 */
	@Test(expected = IllegalStateException.class)
	public void tesChargeExpiredCard() {
		Date expired = Dates.yesterday();
		CreditCard t = new CreditCard("123", "TTT", expired);
		Invoice f = new Invoice(123L);

		new Charge(f, t, 100.0); // Card validity date expired exception
	}

	/**
	 * A voucher cannot be charged if it has no available money.
	 *
	 * @throws IllegalStateException the illegal state exception
	 */
	@Test(expected = IllegalStateException.class)
	public void testEmptyVoucherCannotBeCharged() {
		Voucher b = new Voucher("123", 150.0, "For testing");
		Invoice f = new Invoice(123L);

		new Charge(f, b, 151.0);  // Not enough money exception
	}

}
