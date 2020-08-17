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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class ClearDedicationsTests.
 */
public class ClearDedicationsTests {

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
		
		//Map<VehicleType, Integer> percentages = Map.of(car, 25, truck, 75);		
		Map<VehicleType, Integer> percentages = new HashMap<>();
		percentages.put(car, 25);
		percentages.put(truck, 75);
		
		course.addDedications(percentages);
	}

	/**
	 * clearDedications() unlinks dedications.
	 */
	@Test
	public void testClearDedicationsUnlinks() {
		Set<Dedication> dedications = course.getDedications(); // must be a copy
		
		course.clearDedications();
		
		assertTrue( course.getDedications().isEmpty() );
		assertTrue( car.getDedications().isEmpty() );
		assertTrue( truck.getDedications().isEmpty() );
		for(Dedication d: dedications) {
			assertTrue( d.getVehicleType() == null );
			assertTrue( d.getCourse() == null );
		}
	}

	/**
	 * clearDedications must be call before addDedications if there already are 
	 * previous dedications.
	 */
	@Test
	public void testClearDedicationsBeforeAddDedications() {
		//Map<VehicleType, Integer> percentages = Map.of(car, 100);
		Map<VehicleType, Integer> percentages = new HashMap<>();
		percentages.put(car, 100);
		
		course.clearDedications();
		course.addDedications(percentages);

		assertTrue( course.getDedications().size() == 1 );
	}

}
