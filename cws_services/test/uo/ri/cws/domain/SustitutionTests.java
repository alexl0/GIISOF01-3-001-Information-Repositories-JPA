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

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Associations;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.Intervention;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.SparePart;
import uo.ri.cws.domain.Substitution;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.WorkOrder;


/**
 * The Class SustitutionTests.
 */
public class SustitutionTests {

	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The work order. */
	private WorkOrder workOrder;
	
	/** The intervention. */
	private Intervention intervention;
	
	/** The vehicle. */
	private Vehicle vehicle;
	
	/** The vehicle type. */
	private VehicleType vehicleType;
	
	/** The client. */
	private Client client;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		client = new Client("dni-cliente", "nombre", "apellidos");
		vehicle = new Vehicle("1234 GJI", "seat", "ibiza");
		Associations.Own.link(client, vehicle);

		vehicleType = new VehicleType("coche", 50.0);
		Associations.Classify.link(vehicleType, vehicle);

		workOrder = new WorkOrder(vehicle, "falla la junta la trocla");
		mechanic = new Mechanic("dni-mecanico", "nombre", "apellidos");

		intervention = new Intervention(mechanic, workOrder, 0);
	}

	/**
	 * A substitution with 0 spare parts throws IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSustitutionThrowsExceptionIfZero() {
		SparePart r = new SparePart("R1001", "junta la trocla", 100.0);
		new Substitution(r, intervention, 0);
	}

	/**
	 * A substitution with negative spare parts throws IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSustitutionThrowsExceptionIfNegative() {
		SparePart r = new SparePart("R1001", "junta la trocla", 100.0);
		new Substitution(r, intervention, -1);
	}

	/**
	 * It computes the right amount of a substitution.
	 */
	@Test
	public void testSubstitutionAmount() {
		SparePart r = new SparePart("R1001", "junta la trocla", 100.0);
		Substitution s = new Substitution(r, intervention, 2);

		assertTrue( s.getImporte() == 200.0 );
	}

}
