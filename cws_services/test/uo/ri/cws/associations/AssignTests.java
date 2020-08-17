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
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.WorkOrder;


/**
 * The Class AssignTests.
 */
public class AssignTests {
	
	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The work order. */
	private WorkOrder workOrder;
	
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
		Associations.Own.link(client, vehicle );

		vehicleType = new VehicleType("coche", 50.0);
		Associations.Classify.link(vehicleType, vehicle);
		
		workOrder = new WorkOrder(vehicle, "falla la junta la trocla");

		mechanic = new Mechanic("dni-mecanico", "nombre", "apellidos");
		Associations.Assign.link(mechanic, workOrder);
	}
	
	/**
	 * Test link on assign.
	 */
	@Test
	public void testLinkOnAssign() {
		assertTrue( mechanic.getAssigned().contains( workOrder ));
		assertTrue( workOrder.getMechanic() == mechanic );
	}

	/**
	 * Test unlink on assign.
	 */
	@Test
	public void testUnlinkOnAssign() {
		Associations.Assign.unlink(mechanic, workOrder );
		
		assertTrue( ! mechanic.getAssigned().contains( workOrder ));
		assertTrue( mechanic.getAssigned().size() == 0 );
		assertTrue( workOrder.getMechanic() == null );
	}

	/**
	 * Test safe return.
	 */
	@Test
	public void testSafeReturn() {
		Set<WorkOrder> asignadas = mechanic.getAssigned();
		asignadas.remove( workOrder );

		assertTrue( asignadas.size() == 0 );
		assertTrue( "It must be a copy of the collection", 
			mechanic.getAssigned().size() == 1
		);
	}

}
