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

import java.util.Optional;

import uo.ri.cws.application.repository.DedecationRepository;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * The Class DedicationJpaRepository.
 */
public class DedicationJpaRepository extends BaseJpaRepository<Dedication> implements DedecationRepository {

	/**
	 * Dado un id_course y un id_vehicletype te devuelve el objeto dedication.
	 *
	 * @param idCourse the id course
	 * @param idVehicleType the id vehicle type
	 * @return the optional
	 */
	@Override
	public Optional<Dedication> findDedicationByCourseAndType(String idCourse, String idVehicleType){
		return Jpa.getManager()
				.createNamedQuery("Dedication.findDedicationByCourseAndType",Dedication.class)
				.setParameter(1, idCourse)
				.setParameter(2, idVehicleType)
				.getResultStream()
				.findFirst();		
	}
	
}
