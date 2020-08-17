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
package uo.ri.cws.application.repository;

/**
 * A factory for creating Repository objects.
 */
public interface RepositoryFactory {

	/**
	 * For mechanic.
	 *
	 * @return the mechanic repository
	 */
	MechanicRepository forMechanic();
	
	/**
	 * For work order.
	 *
	 * @return the work order repository
	 */
	WorkOrderRepository forWorkOrder();
	
	/**
	 * For payment mean.
	 *
	 * @return the payment mean repository
	 */
	PaymentMeanRepository forPaymentMean();
	
	/**
	 * For invoice.
	 *
	 * @return the invoice repository
	 */
	InvoiceRepository forInvoice();
	
	/**
	 * For client.
	 *
	 * @return the client repository
	 */
	ClientRepository forClient();
	
	/**
	 * For spare part.
	 *
	 * @return the spare part repository
	 */
	SparePartRepository forSparePart();
	
	/**
	 * For intervention.
	 *
	 * @return the intervention repository
	 */
	InterventionRepository forIntervention();
	
	/**
	 * For vehicle.
	 *
	 * @return the vehicle repository
	 */
	VehicleRepository forVehicle();
	
	/**
	 * For vehicle type.
	 *
	 * @return the vehicle type repository
	 */
	VehicleTypeRepository forVehicleType();
	
	/**
	 * For course.
	 *
	 * @return the course repository
	 */
	CourseRepository forCourse();
	
	/**
	 * For certificate.
	 *
	 * @return the certificate repository
	 */
	CertificateRepository forCertificate();
	
	/**
	 * For enrollment.
	 *
	 * @return the enrollment repository
	 */
	EnrollmentRepository forEnrollment();
	
	/**
	 * For dedications.
	 *
	 * @return the dedecation repository
	 */
	DedecationRepository forDedications();
	
	SubstitutionRepository forSubstitution();

}
