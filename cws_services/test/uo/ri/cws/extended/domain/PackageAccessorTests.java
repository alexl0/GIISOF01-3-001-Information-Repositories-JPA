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
package uo.ri.cws.extended.domain;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import uo.ri.cws.domain.Cash;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.CreditCard;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
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
 * The Class PackageAccessorTests.
 */
public class PackageAccessorTests {

	/**
	 * Test.
	 */
	@Test
	public void test() {
		Class<?>[] classes = {
			Course.class,
			Dedication.class,
			Mechanic.class,
			Enrollment.class,
			VehicleType.class,
			Certificate.class,
			Invoice.class,
			WorkOrder.class,
			Client.class,
			Vehicle.class,
			Intervention.class,
			Substitution.class,
			SparePart.class,
			PaymentMean.class,
			CreditCard.class,
			Voucher.class,
			Cash.class
		};
		
		for(Class<?> clazz : classes) {
			assertNotPublicAssociationAccessors( clazz );
		}
	}

	/**
	 * Assert not public association accessors.
	 *
	 * @param clazz the clazz
	 */
	private void assertNotPublicAssociationAccessors(Class<?> clazz) {
		Set<Method> methods = filter(clazz, "_set", "_get");
		for(Method m: methods) {
			int modifiers = m.getModifiers();
			assertTrue( "bad public modifer on " + clazz.getSimpleName(),
					! Modifier.isPublic(modifiers) 
				);
		}
	}

	/**
	 * Filter.
	 *
	 * @param clazz the clazz
	 * @param prefixes the prefixes
	 * @return the sets the
	 */
	private Set<Method> filter(Class<?> clazz, String... prefixes) {
		Set<Method> res = new HashSet<>();

		Method[] methods = clazz.getDeclaredMethods();
		for(Method m: methods) {
			for(String pf: prefixes) {
				if ( m.getName().startsWith( pf ) ) {
					res.add( m );
				}
			}
		}

		return res;
	}

}
