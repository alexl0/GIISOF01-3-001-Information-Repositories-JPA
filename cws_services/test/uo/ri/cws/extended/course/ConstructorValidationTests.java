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
package uo.ri.cws.extended.course;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class ConstructorValidationTests.
 */
public class ConstructorValidationTests {

	/** The code. */
	private String code = "code";
	
	/** The duration. */
	private int duration = 10;
	
	/** The name. */
	private String name = "name";
	
	/** The start date. */
	private Date startDate = Dates.addDays( Dates.today(), 15);
	
	/** The end date. */
	private Date endDate = Dates.addDays( startDate, 5);
	
	/** The description. */
	private String description = "description";

	/**
	 * code cannot be empty (nor null).
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCodeNotEmpty() {
		new Course("", name, description, startDate, endDate, duration);
	}

	/**
	 * name cannot be empty (nor null).
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNameNotEmpty() {
		new Course(code, "", description, startDate, endDate, duration);
	}

	/**
	 * description cannot be empty (nor null).
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDescriptionNotEmpty() {
		new Course(code, name, "", startDate, endDate, duration);
	}

	/**
	 * startDate cannot be null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testStartDateNotNull() {
		new Course(code, name, description, null, endDate, duration);
	}

	/**
	 * endDate cannot be null.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEndDateNotNull() {
		new Course(code, name, description, startDate, null, duration);
	}

	/**
	 * endDate must be after startDate.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEndDateAfterStartDate() {
		startDate = Dates.today();
		endDate = Dates.subDays( startDate, 1);

		new Course(code, name, description, startDate, endDate, duration);
	}

	/**
	 * startDate and endDate are copied.
	 */
	@Test
	public void testDatesCopied() {
		Course c = new Course(code, name, description, 
				startDate, endDate, 
				duration);

		c.getStartDate().setTime( 0 );
		c.getEndDate().setTime( 0 );

		assertTrue( c.getStartDate().getTime() != 0 );
		assertTrue( c.getEndDate().getTime() != 0 );
	}

	/**
	 * duration cannot be zero.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDurationNonZero() {
		new Course(code, name, description, startDate, endDate, 0);
	}

	/**
	 * duration cannot be negative.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDurationnonNegative() {
		new Course(code, name, description, startDate, endDate, -1);
	}

	/**
	 * percentages cannot be empty.
	 */
	@Test(expected=IllegalStateException.class)
	public void testCourseConstructorPercentages() {
		Map<VehicleType,Integer> percentages=new HashMap<VehicleType,Integer>();
		new Course(code, name, description, startDate, endDate, 1, percentages);
	}

}
