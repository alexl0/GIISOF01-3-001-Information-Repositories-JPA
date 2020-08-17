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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.Dates;
import alb.util.reflection.ReflectionUtil;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class AttendancePassedTests.
 */
public class AttendancePassedTests {

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
	 * The constructor sets passed to true and attendance if >= 85 %.
	 */
	@Test
	public void testAttendedAndPassed() {
		boolean PASSED = true;
		int ENOUGH_TO_PASS = 85;
		
		Enrollment e = new Enrollment(mechanic, course, ENOUGH_TO_PASS, PASSED );
		
		assertTrue( e.isPassed() );
		assertEquals( ENOUGH_TO_PASS, e.getAttendance() );
	}

	/**
	 * The constructor sets passed to true and attendance if >= 85 %.
	 */
	@Test
	public void testAttendedNotPassed() {
		boolean NOT_PASSED = false;
		int ENOUGH_TO_PASS = 85;
		
		Enrollment e = new Enrollment(mechanic, course, 
				ENOUGH_TO_PASS, 
				NOT_PASSED );
		
		assertTrue( e.isPassed() == false );
		assertEquals( ENOUGH_TO_PASS, e.getAttendance() );
	}

	/**
	 * The constructor throws IllegalArgumentException if attendance is < 85 %
	 * and the course is mark as passed.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorDoesNoAllowPassedWithLessThan85Attendance() {
		boolean PASSED = true;
		int NOT_ENOUGH_TO_PASS = 84;
		
		new Enrollment(mechanic, course, NOT_ENOUGH_TO_PASS, PASSED);
	}

	/**
	 * Enrollment has no use case for update. That is, it is an unmodifiable 
	 * entity. Therefore, there are no setters on this class.
	 */
	@Test
	public void testNoSetters() {
		try {
			ReflectionUtil.getMethodOfClass(
					Enrollment.class, 
					"setPassed", 
					boolean.class
				);
			fail("There should't be this setter on this class");
		} catch (IllegalStateException e) { 
			// expected result
		}
		
		try {
			ReflectionUtil.getMethodOfClass(
					Enrollment.class, 
					"setAttendance", 
					int.class
				);
			fail("There should't be this setter on this class");
		} catch (IllegalStateException e) { 
			// expected result
		}
	}

}
