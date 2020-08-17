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
package uo.ri.cws.application.service.training.certificate.command;

import java.util.List;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.DedecationRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class GenerateCertificates.
 */
public class GenerateCertificates implements Command<Integer> {

	/** The cr. */
	private CertificateRepository cr=Factory.repository.forCertificate();
	
	/** The mr. */
	private MechanicRepository mr=Factory.repository.forMechanic();
	
	/** The vr. */
	private VehicleTypeRepository vr=Factory.repository.forVehicleType();
	
	/** The cor. */
	private CourseRepository cor=Factory.repository.forCourse();
	
	/** The er. */
	private EnrollmentRepository er=Factory.repository.forEnrollment();
	
	/** The dr. */
	private DedecationRepository dr=Factory.repository.forDedications();

	/**
	 * Execute.
	 *
	 * @return the integer
	 * @throws BusinessException the business exception
	 */
	@Override
	/**
	 * 
	 * Genera certificados de los mecánicos y los almacena en la tabla tcertificates.
	 * 
	 * Algoritmo
	 * 
	 * 		para cada id_mechanic {
	 * 			para cada tipo de vehículo (en los que no está certificado el mecánico) {
	 * 				nhoras=0
	 * 				para cada curso aprobado, con asistencia>85 que se dedica a ese tipo de vehículo
	 * 					nhoras += duracion * attendance * percentage
	 * 				if(nhoras>=minTrainingHours && noEstaCertificado)
	 * 					expedir(id_mechanic, vehicletype_id, curso.enddate)
	 * 			}
	 * 		}
	 */
	public Integer execute() throws BusinessException {
		List<Mechanic> mechanics = mr.findAll();
		List<VehicleType> types;
		List<Course>courses;
		Enrollment enrollment;
		Dedication dedication;
		
		int numGenerated=0;
		
		for(Mechanic m:mechanics) {
			types = vr.findNotCertificatedByMechanic(m.getId());
			for(VehicleType t:types) {
				double nHoras=0;
				courses=cor.findCoursesPassedByVehicleType(t.getId(), m.getId());
				for(Course c:courses) {
					
					enrollment=er.findEnrollmentByMechanic(m.getId(), c.getId()).get();
					int attendance=enrollment.getAttendance();
					
					dedication=dr.findDedicationByCourseAndType(c.getId(), t.getId()).get();
					int percentage=dedication.getPercentage();
					
					nHoras+=c.getHours()*(attendance/100.0)*(percentage/100.0);
				}
				if(nHoras>=t.getMinTrainingHours()) {
					Certificate cer=new Certificate(m,t);
					cr.add(cer);
					numGenerated++;
				}
			}
		}
		return numGenerated;
	}

}
