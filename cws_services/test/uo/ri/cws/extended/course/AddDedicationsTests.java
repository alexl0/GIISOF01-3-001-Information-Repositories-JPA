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
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class AddDedicationsTests.
 */
public class AddDedicationsTests {

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
		car = new VehicleType("car");
		truck = new VehicleType("truck");
		course = new Course("C1");
	}

	/**
	 * When percentages sums 100% dedications are created and linked.
	 */
	@Test
	public void testDedicationsAmount100() {
		/*Map<VehicleType, Integer> percentages = Map.of(
				car,   25, 
				truck, 75
			);*/
		Map<VehicleType, Integer> percentages = new HashMap<>();
		percentages.put(car, 25);
		percentages.put(truck, 75);
		
		course.addDedications(percentages);
		
		assertTrue( course.getDedications().size() == 2 );
		Set<Dedication> dedications = course.getDedications();
		for(Dedication d: dedications) {
			assertTrue( d.getCourse().equals( course ) );
			assertTrue( course.getDedications().contains( d ));
		}
	}
	
	/**
	 * If percentages are less than 100% throws exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDedicationsLessThan100() {
		/*Map<VehicleType, Integer> percentages = Map.of(
				car,   25,
				truck, 25
			);*/
		Map<VehicleType, Integer> percentages = new HashMap<>();
		percentages.put(car, 25);
		percentages.put(truck, 25);
		
		course.addDedications(percentages);
	}

	/**
	 * If percentages are greater than 100% throws exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDedicationsGreaterThan100() {
		/*Map<VehicleType, Integer> percentages = Map.of(
				car,   25,
				truck, 85
			);*/
		Map<VehicleType, Integer> percentages = new HashMap<>();
		percentages.put(car, 25);
		percentages.put(truck, 85);
		
		course.addDedications(percentages);
	}

	/**
	 * Cannot add dedications if course already has dedications.
	 */
	@Test
	public void testAddMoreDedicationsThrowsException() {
		//Map<VehicleType, Integer> percentages = Map.of(car, 100);
		Map<VehicleType, Integer> percentages = new HashMap<>();
		percentages.put(car, 100);
		course.addDedications(percentages);
		
		//percentages = Map.of(car, 25, truck, 85);
		percentages = new HashMap<>();
		percentages.put(car, 25);
		percentages.put(truck, 85);
		
		try {
			course.addDedications(percentages);
			fail("An IllegalStateException must be thrown");
			
		} catch (IllegalStateException expected) {
			// expected exception, all is well
			
		} catch (Exception notExpected) {
			notExpected.printStackTrace();
			fail("An IllegalStateException must be thrown");
		}
	}
	
}
