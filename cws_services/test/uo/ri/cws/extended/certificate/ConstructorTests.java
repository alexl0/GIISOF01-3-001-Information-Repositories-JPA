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
package uo.ri.cws.extended.certificate;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class ConstructorTests.
 */
public class ConstructorTests {

	/** The vehicle type. */
	private VehicleType vehicleType;
	
	/** The mechanic. */
	private Mechanic mechanic;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("123");
		vehicleType = new VehicleType("car");
	}


	/**
	 * Constructor takes the date of the system.
	 */
	@Test
	public void testBasicConstructor() {
		long before = new Date().getTime();
		Certificate c = new Certificate(mechanic, vehicleType);
		long after = new Date().getTime();

		long timeStamp = c.getDate().getTime();

		assertTrue( before <= timeStamp && timeStamp <= after );
	}

	/**
	 * Constructor links properly.
	 */
	@Test
	public void testConstructorLinks() {
		Certificate c = new Certificate(mechanic, vehicleType);

		assertTrue( c.getMechanic().equals( mechanic ) );
		assertTrue( mechanic.getCertificates().contains( c ) );
	}


	/**
	 * Date is copied on return.
	 */
	@Test
	public void testDateCopiedOnReturn() {
		Certificate c = new Certificate(mechanic, vehicleType);

		c.getDate().setTime( 0L );

		assertTrue( c.getDate().getTime() != 0  );
	}
}
