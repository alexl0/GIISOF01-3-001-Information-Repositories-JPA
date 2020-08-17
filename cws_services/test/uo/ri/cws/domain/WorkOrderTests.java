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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Invoice.InvoiceStatus;

/**
 * Para entender mejor estos test repasa el diagrama de estados de una
 * avería en la documentación del problema de referencia.
 */
public class WorkOrderTests {

	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The work order. */
	private WorkOrder workOrder;
	
	/** The intervention. */
	private Intervention intervention;
	
	/** The spare part. */
	private SparePart sparePart;
	
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
		vehicle = new Vehicle("1234 GJI", "ibiza", "seat");
		Associations.Own.link(client, vehicle);

		vehicleType = new VehicleType("coche", 50.0 /* €/hour */);
		Associations.Classify.link(vehicleType, vehicle);

		workOrder = new WorkOrder(vehicle, "falla la junta la trocla");
		mechanic = new Mechanic("dni-mecanico", "nombre", "apellidos");
		workOrder.assignTo( mechanic );

		intervention = new Intervention(mechanic, workOrder, 60);

		sparePart = new SparePart("R1001", "junta la trocla", 100.0 /* € */);
		new Substitution(sparePart, intervention, 2);

		workOrder.markAsFinished(); // changes status & computes price
	}

	/**
	 * The amount of the work order is 250.0 €
	 */
	@Test
	public void testBasicWorkOrderAmount() {
		assertTrue( workOrder.getAmount() == 250.0 );
	}

	/**
	 * With two interventions the amount is computed correctly.
	 */
	@Test
	public void testComputeAmountForTwoInterventions() {
		workOrder.reopen();  // changes from FINISHED to OPEN again
		Mechanic another = new Mechanic("1", "a", "n");
		workOrder.assignTo( another );
		new Intervention(another, workOrder, 30);

		workOrder.markAsFinished();

		assertTrue( workOrder.getAmount() == 275.0 );
	}

	/**
	 * Removing one intervention the amount is correctly recomputed
	 * The (re)computation is done when the work order passes to FINISHED.
	 */
	@Test
	public void testRcomputeAmountWhenRemoveingIntervention() {
		workOrder.reopen();
		Mechanic another = new Mechanic("1", "a", "n");
		workOrder.assignTo( another );
		new Intervention(another, workOrder, 30);

		Associations.Intervene.unlink( intervention );
		workOrder.markAsFinished(); // <-- recomputes here

		assertTrue( workOrder.getAmount() == 25.0 );
	}

	/**
	 * An invoice cannot be added to a non FINISHED work order.
	 *
	 * @throws IllegalStateException the illegal state exception
	 */
	@Test( expected = IllegalStateException.class )
	public void testNotFinishedWorkOrderException() {
		workOrder.reopen();
		List<WorkOrder> workOrders = new ArrayList<>();
		workOrders.add( workOrder );
		new Invoice( 0L,  workOrders ); // <-- must throw IllegalStateExcepcion
	}

	/**
	 * A just created invoice is in NOT_YET_PAID status.
	 */
	@Test
	public void testNotYetPaidForNewInvoice() {
		List<WorkOrder> averias = new ArrayList<>();
		averias.add( workOrder );
		Invoice invoice = new Invoice( 0L, averias );

		assertTrue( invoice.getStatus() ==  InvoiceStatus.NOT_YET_PAID );
	}

	/**
	 * A work order cannot be marked as INVOICED if without assigned invoice.
	 *
	 * @throws IllegalStateException the illegal state exception
	 */
	@Test(expected = IllegalStateException.class)
	public void testCannotBeMarkedAsInvoiced() {
		workOrder.markAsInvoiced();  // must throw exception "not assigned"
	}

	/**
	 * The returned date of an invoice must be a copy of the internal one.
	 */
	@Test
	public void testGetDateReturnsCopy() {
		Date one = workOrder.getDate();
		Date other = workOrder.getDate();

		assertTrue( one != other );
		assertTrue( one.equals( other ) );
	}

}
