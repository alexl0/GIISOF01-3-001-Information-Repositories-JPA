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


/**
 * The Class OwnTests.
 */
public class OwnTests {
	
	/** The vehicle. */
	private Vehicle vehicle;
	
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
	}
	
	/**
	 * Test link on own.
	 */
	@Test
	public void testLinkOnOwn() {
		assertTrue( client.getVehicles().contains( vehicle ));
		assertTrue( vehicle.getClient() == client );
	}

	/**
	 * Test unlink on own.
	 */
	@Test
	public void testUnlinkOnOwn() {
		Associations.Own.unlink(client, vehicle);

		assertTrue( ! client.getVehicles().contains( vehicle ));
		assertTrue( vehicle.getClient() == null );
	}

	/**
	 * Test safe return.
	 */
	@Test
	public void testSafeReturn() {
		Set<Vehicle> vehicles = client.getVehicles();
		vehicles.remove( vehicle );

		assertTrue( vehicles.size() == 0 );
		assertTrue( "It must be a copy of the collection or a read-only version", 
			client.getVehicles().size() == 1
		);
	}


}
