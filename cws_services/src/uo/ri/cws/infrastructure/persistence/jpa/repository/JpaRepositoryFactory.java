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
package uo.ri.cws.infrastructure.persistence.jpa.repository;

import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.ClientRepository;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.DedecationRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.repository.InterventionRepository;
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.PaymentMeanRepository;
import uo.ri.cws.application.repository.RepositoryFactory;
import uo.ri.cws.application.repository.SparePartRepository;
import uo.ri.cws.application.repository.SubstitutionRepository;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;

/**
 * A factory for creating JpaRepository objects.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

	/**
	 * For mechanic.
	 *
	 * @return the mechanic repository
	 */
	@Override
	public MechanicRepository forMechanic() {
		return new MechanicJpaRepository();
	}

	/**
	 * For work order.
	 *
	 * @return the work order repository
	 */
	@Override
	public WorkOrderRepository forWorkOrder() {
		return new WorkOrderJpaRepository();
	}

	/**
	 * For payment mean.
	 *
	 * @return the payment mean repository
	 */
	@Override
	public PaymentMeanRepository forPaymentMean() {
		return new PaymentMeanJpaRepository();
	}

	/**
	 * For invoice.
	 *
	 * @return the invoice repository
	 */
	@Override
	public InvoiceRepository forInvoice() {
		return new InvoiceJpaRepository();
	}

	/**
	 * For client.
	 *
	 * @return the client repository
	 */
	@Override
	public ClientRepository forClient() {
		return new ClientJpaRepository();
	}

	/**
	 * For spare part.
	 *
	 * @return the spare part repository
	 */
	@Override
	public SparePartRepository forSparePart() {
		return new SparePartJpaRepository();
	}

	/**
	 * For intervention.
	 *
	 * @return the intervention repository
	 */
	@Override
	public InterventionRepository forIntervention() {
		return new InterventionJpaRepository();
	}

	/**
	 * For vehicle.
	 *
	 * @return the vehicle repository
	 */
	@Override
	public VehicleRepository forVehicle() {
		return new VehicleJpaRepository();
	}

	/**
	 * For vehicle type.
	 *
	 * @return the vehicle type repository
	 */
	@Override
	public VehicleTypeRepository forVehicleType() {
		return new VehicleTypeJpaRepository();
	}

	/**
	 * For course.
	 *
	 * @return the course repository
	 */
	@Override
	public CourseRepository forCourse() {
		return new CourseJpaRepository();
	}
	
	/**
	 * For certificate.
	 *
	 * @return the certificate repository
	 */
	@Override
	public CertificateRepository forCertificate() {
		return new CertificateJpaRepository();
	}
	
	/**
	 * For enrollment.
	 *
	 * @return the enrollment repository
	 */
	@Override
	public EnrollmentRepository forEnrollment() {
		return new EnrollmentJpaRepository();
	}

	/**
	 * For dedications.
	 *
	 * @return the dedecation repository
	 */
	@Override
	public DedecationRepository forDedications() {
		return new DedicationJpaRepository();
	}

	@Override
	public SubstitutionRepository forSubstitution() {
		return new SubstitutionJpaRepository();
	}

}
