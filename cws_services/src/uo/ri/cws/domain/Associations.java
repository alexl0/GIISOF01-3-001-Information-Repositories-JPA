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

import java.util.Set;

/**
 * The Class Associations.
 */
public class Associations {

	/**
	 * The Class Own.
	 */
	public static class Own {

		/**
		 * Link.
		 *
		 * @param client the client
		 * @param vehicle the vehicle
		 */
		//Se encargará de hacer el vínculo a la vez
		public static void link(Client client, Vehicle vehicle) {
			vehicle._setClient(client);

			client._getVehicles().add(vehicle);
		}
		
		/**
		 * Unlink.
		 *
		 * @param client the client
		 * @param vehicle the vehicle
		 */
		public static void unlink(Client client, Vehicle vehicle) {
			client._getVehicles().remove(vehicle);

			vehicle._setClient(null);
		}
	}

	/**
	 * The Class Classify.
	 */
	public static class Classify {

		/**
		 * Link.
		 *
		 * @param vehicleType the vehicle type
		 * @param vehicle the vehicle
		 */
		public static void link(VehicleType vehicleType, Vehicle vehicle) {
			vehicle._setVehicleType(vehicleType);

			vehicleType._getVehicles().add(vehicle);			
		}

		/**
		 * Unlink.
		 *
		 * @param vehicleType the vehicle type
		 * @param vehicle the vehicle
		 */
		public static void unlink(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().remove(vehicle);

			vehicle._setVehicleType(null);

		}


	}

	/**
	 * The Class Pay.
	 */
	public static class Pay {

		/**
		 * Link.
		 *
		 * @param paymentMean the payment mean
		 * @param client the client
		 */
		public static void link(PaymentMean paymentMean, Client client) {
			paymentMean._setClient(client);

			client._getPaymentMeans().add(paymentMean);
		}
		
		/**
		 * Unlink.
		 *
		 * @param client the client
		 * @param paymentMean the payment mean
		 */
		public static void unlink(Client client, PaymentMean paymentMean) {
			client._getPaymentMeans().remove(paymentMean);

			paymentMean._setClient(null);
		}

	}

	/**
	 * The Class Order.
	 */
	public static class Order {

		/**
		 * Link.
		 *
		 * @param vehicle the vehicle
		 * @param workOrder the work order
		 */
		public static void link(Vehicle vehicle, WorkOrder workOrder) {
			//El orden importa, porque parte de la identidad de workorder es vehicle
			workOrder._setVehicle(vehicle);

			vehicle._getWorkOrders().add(workOrder);			
		}
		
		/**
		 * Unlink.
		 *
		 * @param vehicle the vehicle
		 * @param workOrder the work order
		 */
		public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().remove(workOrder);	

			workOrder._setVehicle(null);
		}

	}

	/**
	 * The Class ToInvoice.
	 */
	public static class ToInvoice {
		
		/**
		 * Link.
		 *
		 * @param invoice the invoice
		 * @param workOrder the work order
		 */
		public static void link(Invoice invoice, WorkOrder workOrder) {
			workOrder._setInvoice(invoice);

			invoice._getWorkOrders().add(workOrder);
		}
		
		/**
		 * Unlink.
		 *
		 * @param invoice the invoice
		 * @param workOrder the work order
		 */
		public static void unlink(Invoice invoice, WorkOrder workOrder) {
			invoice._getWorkOrders().remove(workOrder);

			workOrder._setInvoice(null);
		}
	}

	/**
	 * The Class Charges.
	 */
	public static class Charges {

		/**
		 * Link.
		 *
		 * @param invoice the invoice
		 * @param charge the charge
		 * @param payMentMean the pay ment mean
		 */
		public static void link(Invoice invoice, Charge charge, PaymentMean payMentMean) {
			charge._setPaymentMean(payMentMean);
			charge._setInvoice(invoice);

			payMentMean._getCharges().add(charge);	
			invoice._getCharges().add(charge);		
		}
		
		/**
		 * Unlink.
		 *
		 * @param charge the charge
		 */
		public static void unlink(Charge charge) {
			charge.getInvoice()._getCharges().remove(charge);
			charge.getPaymentMean()._getCharges().remove(charge);

			charge._setInvoice(null);
			charge._setPaymentMean(null);
		}

	}

	/**
	 * The Class Assign.
	 */
	public static class Assign {

		/**
		 * Link.
		 *
		 * @param mechanic the mechanic
		 * @param workOrder the work order
		 */
		public static void link(Mechanic mechanic, WorkOrder workOrder) {
			workOrder._setMechanic(mechanic);

			mechanic._getAssigned().add(workOrder);
		}

		/**
		 * Unlink.
		 *
		 * @param mechanic the mechanic
		 * @param workOrder the work order
		 */
		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {
			mechanic._getAssigned().remove(workOrder);

			workOrder._setMechanic(null);
		}

	}

	/**
	 * The Class Intervene.
	 */
	public static class Intervene {

		/**
		 * Link.
		 *
		 * @param workOrder the work order
		 * @param intervention the intervention
		 * @param mechanic the mechanic
		 */
		public static void link(WorkOrder workOrder, Intervention intervention, Mechanic mechanic) {
			intervention._setMechanic(mechanic);
			intervention._setWorkOrder(workOrder);

			workOrder._getInterventions().add(intervention);
			mechanic._getInterventions().add(intervention);
		}
		
		/**
		 * Unlink.
		 *
		 * @param intervention the intervention
		 */
		public static void unlink(Intervention intervention) {
			Mechanic mechanic=intervention.getMechanic();
			WorkOrder workOrder=intervention.getWorkOrder();

			workOrder._getInterventions().remove(intervention);
			mechanic._getInterventions().remove(intervention);

			intervention._setMechanic(null);
			intervention._setWorkOrder(null);
		}

	}

	/**
	 * The Class Sustitute.
	 */
	public static class Sustitute {

		/**
		 * Link.
		 *
		 * @param sparePart the spare part
		 * @param substitution the substitution
		 * @param intervention the intervention
		 */
		public static void link(SparePart sparePart, Substitution substitution, Intervention intervention) {
			substitution._setSparePart(sparePart);
			substitution._setIntervention(intervention);

			sparePart._getSubstitutions().add(substitution);
			intervention._getSubstitutions().add(substitution);
		}
		
		/**
		 * Unlink.
		 *
		 * @param substitution the substitution
		 */
		public static void unlink(Substitution substitution) {
			substitution.getSparePart()._getSubstitutions().remove(substitution);
			substitution.getIntervention()._getSubstitutions().remove(substitution);

			substitution._setSparePart(null);
			substitution._setIntervention(null);
		}

	}

	//############################################################ Ampliación ############################################################

	/**
	 * The Class Enroll.
	 */
	public static class Enroll{
		
		/**
		 * Link.
		 *
		 * @param mechanic the mechanic
		 * @param enrollment the enrollment
		 * @param course the course
		 */
		public static void link(Mechanic mechanic, Enrollment enrollment, Course course) {
			enrollment._setMechanic(mechanic);
			enrollment._setCourse(course);

			mechanic._getEnrollments().add(enrollment);
			course._getEnrollments().add(enrollment);
		}
		
		/**
		 * Unlink.
		 *
		 * @param enrollment the enrollment
		 */
		public static void unlink(Enrollment enrollment) {
			Mechanic m=enrollment.getMechanic();
			Course c=enrollment.getCourse();

			m._getEnrollments().remove(enrollment);
			c._getEnrollments().remove(enrollment);

			enrollment._setCourse(null);
			enrollment._setMechanic(null);
		}
	}

	/**
	 * The Class Certify.
	 */
	public static class Certify{
		
		/**
		 * Link.
		 *
		 * @param m the m
		 * @param c the c
		 * @param v the v
		 */
		public static void link(Mechanic m, Certificate c, VehicleType v) {
			c._setMechanic(m);
			c._setVehicleType(v);

			m._getCertificates().add(c);
			v._getCertificates().add(c);
		}
		
		/**
		 * Unlink.
		 *
		 * @param certificate the certificate
		 */
		public static void unlink(Certificate certificate) {
			Mechanic m=certificate.getMechanic();
			VehicleType v=certificate.getVehicleType();

			m._getCertificates().remove(certificate);
			v._getCertificates().remove(certificate);

			certificate._setMechanic(null);
			certificate._setVehicleType(null);

		}
	}

	/**
	 * The Class Dedicate.
	 */
	public static class Dedicate{
		
		/**
		 * Link.
		 *
		 * @param vehicleType the vehicle type
		 * @param dedication the dedication
		 * @param course the course
		 */
		public static void link(VehicleType vehicleType, Dedication dedication, Course course) {
			dedication._setVehicleType(vehicleType);
			dedication._setCourse(course);

			course._getDedications().add(dedication);
			vehicleType._getDedications().add(dedication);
		}
		
		/**
		 * Unlink.
		 *
		 * @param d the d
		 */
		public static void unlink(Dedication d) {
			Course course=d.getCourse();
			VehicleType vehicleType=d.getVehicleType();

			course._getDedications().remove(d);
			vehicleType._getDedications().remove(d);

			d._setVehicleType(null);
			d._setCourse(null);
		}
		
		/**
		 * Para que no de concurrent exception al hacer clearDedications() en Course.
		 *
		 * @param dedications the dedications
		 */
		public static void unlink(Set<Dedication> dedications) {
			for(Dedication d:dedications)
				unlink(d);
		}
	}

}
