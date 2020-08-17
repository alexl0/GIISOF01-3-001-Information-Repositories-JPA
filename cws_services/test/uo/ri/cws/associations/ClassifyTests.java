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
package uo.ri.cws.associations;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Associations;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;


/**
 * The Class ClassifyTests.
 */
public class ClassifyTests {
	
	/** The vehicle. */
	private Vehicle vehicle;
	
	/** The vehicle type. */
	private VehicleType vehicleType;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		vehicle = new Vehicle("1234 GJI", "seat", "ibiza");
		vehicleType = new VehicleType("coche", 50.0);
		Associations.Classify.link(vehicleType, vehicle);
	}
	
	/**
	 * Test link on classify.
	 */
	@Test
	public void testLinkOnClassify() {
		assertTrue( vehicleType.getVehicles().contains( vehicle ));
		assertTrue( vehicle.getVehicleType() == vehicleType );
	}

	/**
	 * Test unlink on classify.
	 */
	@Test
	public void testUnlinkOnClassify() {
		Associations.Classify.unlink(vehicleType, vehicle);

		assertTrue( ! vehicleType.getVehicles().contains( vehicle ));
		assertTrue( vehicle.getVehicleType() == null );
	}

	/**
	 * Test safe return.
	 */
	@Test
	public void testSafeReturn() {
		Set<Vehicle> vehiculos = vehicleType.getVehicles();
		vehiculos.remove( vehicle );

		assertTrue( vehiculos.size() == 0 );
		assertTrue( "It must be a copy of the collection", 
			vehicleType.getVehicles().size() == 1
		);
	}

}
