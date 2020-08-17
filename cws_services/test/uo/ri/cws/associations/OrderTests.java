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
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.WorkOrder;


/**
 * The Class OrderTests.
 */
public class OrderTests {
	
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
	}
	
	/**
	 * Test link on order.
	 */
	@Test
	public void testLinkOnOrder() {
		// The constructor of "WorkOrder" creates the link with "vehicle"
		// It calls Association.Averiar.link(...)
		assertTrue( vehicle.getWorkOrders().contains( workOrder ));
		assertTrue( workOrder.getVehicle() == vehicle );
	}

	/**
	 * Test unlink on order.
	 */
	@Test
	public void testUnlinkOnOrder() {
		Associations.Order.unlink(vehicle, workOrder);
		
		assertTrue( ! vehicle.getWorkOrders().contains( workOrder ));
		assertTrue( workOrder.getVehicle() == null );
	}

	/**
	 * Test unlink twice on order.
	 */
	@Test
	public void testUnlinkTwiceOnOrder() {
		Associations.Order.unlink(vehicle, workOrder);
		Associations.Order.unlink(vehicle, workOrder);
		
		assertTrue( ! vehicle.getWorkOrders().contains( workOrder ));
		assertTrue( workOrder.getVehicle() == null );
	}

	/**
	 * Test safe return.
	 */
	@Test
	public void testSafeReturn() {
		Set<WorkOrder> workOrders = vehicle.getWorkOrders();
		workOrders.remove( workOrder );

		assertTrue( workOrders.size() == 0 );
		assertTrue( "It must be a copy of the collection or a read-only version", 
			vehicle.getWorkOrders().size() == 1
		);
	}



}
