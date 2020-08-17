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
package uo.ri.cws.extended.enrollment;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class GetAttendedHoursForTests.
 */
public class GetAttendedHoursForTests {

	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The car. */
	private VehicleType car;
	
	/** The truck. */
	private VehicleType truck;
	
	/** The course. */
	private Course course;

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
		
		course = new Course("course", "1o1", "description",
				Dates.fromDdMmYyyy(11, 11, 2019),
				Dates.fromDdMmYyyy(25, 11, 2019),
				100 /* hours */
			);
		
		Map<VehicleType, Integer> dedications = new HashMap<>();
		dedications.put( car, 	25 /* % */);
		dedications.put( truck, 75 /* % */);
		course.addDedications(dedications);
	}

	/**
	 * Returns right attended hours for car with 100 % attendance.
	 */
	@Test
	public void testRightForCar() {
		Enrollment e = new Enrollment(mechanic, course, 100, true);

		assertEquals( 25, e.getAttendedHoursFor( car ));
	}


	/**
	 * Returns right attended hours for truck with 100 % attendance.
	 */
	@Test
	public void testRightForTruck() {
		Enrollment e = new Enrollment(mechanic, course, 100, true);

		assertEquals( 75, e.getAttendedHoursFor( truck ));
	}


	/**
	 * Returns 0 attended hours for a non involved vehicle type with 100 % attendance.
	 */
	@Test
	public void testZeroForOtherType() {
		Enrollment e = new Enrollment(mechanic, course, 100, true);

		VehicleType other = new VehicleType("other");
		assertEquals( 0, e.getAttendedHoursFor( other ));
	}

	
	/**
	 * Returns right attended hours for car with 85 % attendance.
	 */
	@Test
	public void testRightForCarWith85Attendance() {
		Enrollment e = new Enrollment(mechanic, course, 85, true);

		int expected = (int)(25 * 85 / 100);
		assertEquals( expected, e.getAttendedHoursFor( car ));
	}

	/**
	 * Returns right attended hours for truck with 85 % attendance.
	 */
	@Test
	public void testRightForTruckWith85Attendance() {
		Enrollment e = new Enrollment(mechanic, course, 85, true);

		int expected = (int)(75 * 85 / 100);
		assertEquals( expected, e.getAttendedHoursFor( truck ));
	}

}
