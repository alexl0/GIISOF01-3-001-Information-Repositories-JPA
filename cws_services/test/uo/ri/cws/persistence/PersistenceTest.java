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
package uo.ri.cws.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.cws.domain.Address;
import uo.ri.cws.domain.Associations;
import uo.ri.cws.domain.Cash;
import uo.ri.cws.domain.Charge;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.CreditCard;
import uo.ri.cws.domain.Intervention;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.PaymentMean;
import uo.ri.cws.domain.SparePart;
import uo.ri.cws.domain.Substitution;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.Voucher;
import uo.ri.cws.domain.WorkOrder;


/**
 * The Class PersistenceTest.
 */
public class PersistenceTest {

	/** The factory. */
	private EntityManagerFactory factory;
	
	/** The client. */
	private Client client;
	
	/** The sustitution. */
	private Substitution sustitution;
	
	/** The charge. */
	private Charge charge;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory("carworkshop");
		List<Object> graph = createGraph();
		persistGraph(graph);
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		removeGraph();
		factory.close();
	}

	/**
	 * Test client.
	 */
	@Test
	public void testClient() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		Client cl = mapper.merge( client );
		
		assertNotNull( cl.getId() );
		assertEquals( cl.getSurname(), "apellidos");
		assertEquals( cl.getName(), "nombre");
		assertEquals( cl.getDni(), "dni");
		
		trx.commit();
		mapper.close();	
	}

	/**
	 * Test vehicle.
	 */
	@Test
	public void testVehicle() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		Client cl = mapper.merge(client);
		Set<Vehicle> vehiculos = cl.getVehicles();
		Vehicle v = vehiculos.iterator().next();
		
		assertTrue( vehiculos.size() == 1 );
		assertSame( v.getClient(), cl);
		assertNotNull( v.getId());
		assertEquals( v.getMake(), "seat" );
		assertEquals( v.getModel(), "ibiza" );
		assertEquals( v.getPlateNumber(), "1234 GJI" );
		
		trx.commit();
		mapper.close();
	}

	/**
	 * Test sustitution.
	 */
	@Test
	public void testSustitution() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		Substitution s = mapper.merge( sustitution );
		SparePart r = s.getSparePart();
		Intervention i = s.getIntervention();
		
		assertTrue( r.getSustituciones().contains(s) ); 
		assertTrue( i.getSustitutions().contains(s) );

		trx.commit();
		mapper.close();		
	}
		
	/**
	 * Test over intervention asociation.
	 */
	@Test
	public void testOverInterventionAsociation() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Substitution s = mapper.merge( sustitution );
		Intervention i = s.getIntervention();
		Mechanic m = i.getMechanic();
		WorkOrder a = i.getWorkOrder();
		
		assertTrue( m.getInterventions().contains(i) ); 
		assertTrue( a.getInterventions().contains(i) ); 
		
		trx.commit();
		mapper.close();		
	}
	
	/**
	 * Test over order association.
	 */
	@Test
	public void testOverOrderAssociation() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Substitution s = mapper.merge( sustitution );
		WorkOrder a = s.getIntervention().getWorkOrder();
		Vehicle v = a.getVehicle();
		
		assertTrue( v.getWorkOrders().contains(a) ); 
		
		trx.commit();
		mapper.close();		
	}

	/**
	 * Test over own association.
	 */
	@Test
	public void testOverOwnAssociation() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Substitution s = mapper.merge( sustitution );
		Vehicle v = s.getIntervention().getWorkOrder().getVehicle();
		Client c = v.getClient();
		
		assertTrue( c.getVehicles().contains(v) ); 
		
		trx.commit();
		mapper.close();		
	}

	/**
	 * Test over classify association.
	 */
	@Test
	public void testOverClassifyAssociation() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Substitution s = mapper.merge( sustitution );
		Vehicle v = s.getIntervention().getWorkOrder().getVehicle();
		VehicleType tv = v.getVehicleType();
		
		assertTrue( tv.getVehicles().contains(v) ); 
		
		trx.commit();
		mapper.close();		
	}

	/**
	 * Test over charge association.
	 */
	@Test
	public void testOverChargeAssociation() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Charge c = mapper.merge( charge );
		Invoice f = c.getInvoice();
		PaymentMean mp = c.getPaymentMean();
		
		assertTrue( mp.getCharges().contains(c) );
		assertTrue( f.getCharges().contains(c) );
		
		trx.commit();
		mapper.close();		
	}

	/**
	 * Test over invocice association.
	 */
	@Test
	public void testOverInvociceAssociation() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Substitution s = mapper.merge( sustitution );
		WorkOrder a = s.getIntervention().getWorkOrder();
		Invoice f = a.getInvoice();
		
		assertTrue( f.getWorkOrders().contains(a) );
		
		trx.commit();
		mapper.close();		
	}

	/**
	 * Test over pay association.
	 */
	@Test
	public void testOverPayAssociation() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Substitution s = mapper.merge( sustitution );
		Client c = s.getIntervention().getWorkOrder().getVehicle().getClient();
		Set<PaymentMean> medios = c.getPaymentMeans();

		for(PaymentMean mp: medios) {
			assertSame( mp.getClient(), c );
		}
		
		trx.commit();
		mapper.close();		
	}

	/**
	 * Creates the graph.
	 *
	 * @return the list
	 */
	protected List<Object> createGraph() {

		client = new Client("dni", "nombre", "apellidos");
		Address address = new Address("street", "city", "zipcode");
		client.setAddress(address);
		Vehicle vehicle = new Vehicle("1234 GJI", "seat", "ibiza");
		Associations.Own.link(client, vehicle);
		
		VehicleType vehicleType = new VehicleType("coche", 50.0);
		Associations.Classify.link(vehicleType, vehicle);
		
		WorkOrder workOrder = new WorkOrder(vehicle, "falla la junta la trocla");
		Mechanic mechanic = new Mechanic("dni-mecanico", "nombre", "apellidos");
		workOrder.assignTo(mechanic);
	
		Intervention intervention = new Intervention(mechanic, workOrder, 60);
		workOrder.markAsFinished();
		
		SparePart sparePart = new SparePart("R1001", "junta la trocla", 100.0);
		sustitution = new Substitution(sparePart, intervention, 2);
		
		Voucher voucher = new Voucher("B-100", 100.0);
		voucher.setDescripcion( "Voucher just for testing" );
		Associations.Pay.link(voucher, client);
		
		CreditCard card = new CreditCard( 
					"1234567", 
					"visa", 
					Dates.inYearsTime( 1 ) 
				);
		Associations.Pay.link(card, client);
		
		Cash cash = new Cash( client );
		
		Invoice invoice = new Invoice( 1L );
		invoice.setDate( Dates.today() );
		invoice.addWorkOrder(workOrder);

		charge = new Charge(invoice, card, invoice.getAmount());
		
		List<Object> res = new LinkedList<Object>();
		
		res.add(vehicleType);
		res.add(sparePart);
		res.add(mechanic);
		res.add(client);
		res.add(voucher);
		res.add(card);
		res.add(cash);
		res.add(vehicle);
		res.add(invoice);
		res.add(workOrder);
		res.add(intervention);
		res.add(sustitution);
		res.add(charge);
		
		return res;
	}
	
	/**
	 * Persist graph.
	 *
	 * @param graph the graph
	 */
	private void persistGraph(List<Object> graph) {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		for(Object o: graph) {
			mapper.persist(o);
		}

		trx.commit();
		mapper.close();
	}
	
	/**
	 * Removes the graph.
	 */
	private void removeGraph() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		
		List<Object> merged = mergeGraph(mapper);
		
		for(Object o: merged) {
			mapper.remove(o);
		}

		trx.commit();
		mapper.close();
	}

	/**
	 * Merge graph.
	 *
	 * @param mapper the mapper
	 * @return the list
	 */
	private List<Object> mergeGraph(EntityManager mapper) {
		List<Object> res = new LinkedList<Object>();
		
		res.add( mapper.merge(charge) );

		Substitution s  = mapper.merge( sustitution );
		res.add( s );
		res.add( s.getSparePart() );
		res.add( s.getIntervention() );
		res.add( s.getIntervention().getMechanic() );
		res.add( s.getIntervention().getWorkOrder() );
		res.add( s.getIntervention().getWorkOrder().getVehicle() );
		res.add( s.getIntervention().getWorkOrder().getVehicle().getVehicleType() );
		res.add( s.getIntervention().getWorkOrder().getInvoice() );
		
		Client cl = mapper.merge(client);
		for(PaymentMean mp: cl.getPaymentMeans()) {
			res.add( mp );
		}
		res.add( cl );
		
		return res;
	}

}
