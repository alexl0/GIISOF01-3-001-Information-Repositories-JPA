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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class GetEnrollmentsForTests.
 */
public class GetEnrollmentsForTests {

	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The car. */
	private VehicleType car;
	
	/** The truck. */
	private VehicleType truck;
	
	/** The only truck course. */
	private Course onlyTruckCourse;
	
	/** The car and truck course. */
	private Course carAndTruckCourse;
	
	/** The c 1 enrollment. */
	private Enrollment c1Enrollment;
	
	/** The c 2 enrollment. */
	private Enrollment c2Enrollment;

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
		
		onlyTruckCourse = new Course("C1", "truck", "truck description",
				Dates.fromDdMmYyyy(11, 11, 2019),
				Dates.fromDdMmYyyy(25, 11, 2019),
				100 /* hours */
			);
		Map<VehicleType, Integer> dedications = new HashMap<>();
		dedications.put( truck, 100 /* % */);
		onlyTruckCourse.addDedications(dedications);


		carAndTruckCourse = new Course("C2", "car&truck", "car&truck description",
				Dates.fromDdMmYyyy(11, 12, 2019),
				Dates.fromDdMmYyyy(25, 12, 2019),
				100 /* hours */
			);
		dedications.clear();
		dedications.put( car, 	25 /* % */);
		dedications.put( truck, 75 /* % */);
		carAndTruckCourse.addDedications(dedications);
		
		c1Enrollment = new Enrollment(mechanic, onlyTruckCourse, 100, true);
		c2Enrollment = new Enrollment(mechanic, carAndTruckCourse, 100, true);	
	}

	/**
	 * Returns the car course enrollment for "car".
	 */
	@Test
	public void testReturnsOneForCar() {
		Set<Enrollment> es = mechanic.getEnrollmentsFor( car );
		
		assertTrue( es.size() == 1 );
		assertTrue( es.contains( c2Enrollment ) );
	}

	/**
	 * Returns both enrollments for truck.
	 */
	@Test
	public void testReturnsTwoForTruck() {
		Set<Enrollment> es = mechanic.getEnrollmentsFor( truck );
		
		assertTrue( es.size() == 2 );
		assertTrue( es.contains( c1Enrollment ) );
		assertTrue( es.contains( c2Enrollment ) );
	}

	/**
	 * Returns empty set for other type.
	 */
	@Test
	public void testReturnEmptyForOther() {
		VehicleType other = new VehicleType("other");
		
		Set<Enrollment> es = mechanic.getEnrollmentsFor( other );
		
		assertTrue( es.isEmpty() );
	}

	/**
	 * Returns empty set for null type.
	 */
	@Test
	public void testReturnEmptyForNull() {
		Set<Enrollment> es = mechanic.getEnrollmentsFor( null );
		
		assertTrue( es.isEmpty() );
	}

}
