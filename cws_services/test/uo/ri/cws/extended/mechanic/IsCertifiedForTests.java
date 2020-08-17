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
package uo.ri.cws.extended.mechanic;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class IsCertifiedForTests.
 */
public class IsCertifiedForTests {

	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The car. */
	private VehicleType car;
	
	/** The truck. */
	private VehicleType truck;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		mechanic = new Mechanic("123");
		car = new VehicleType("car");
		truck = new VehicleType("truck");
		
		new Certificate(mechanic, car);		// auto link
	}

	/**
	 * It is certified for car, but not for truck not null.
	 */
	@Test
	public void testCertifiedForCarNotForTruck() {
		assertTrue( mechanic.isCertifiedFor( car ) );
		assertTrue( ! mechanic.isCertifiedFor( truck ) );
		
		assertTrue( ! mechanic.isCertifiedFor( null ) );
	}

}
