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

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.domain.Course;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * The Class CourseJpaRepository.
 */
public class CourseJpaRepository extends BaseJpaRepository<Course> implements CourseRepository {

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the optional
	 */
	@Override
	/**
	 * Busca un curso por su código. Se utiliza para comprobar
	 * que no haya un curso con el mismo código al añadir un nuevo curso,
	 * ya que el código de dos cursos nunca puede ser igual.
	 * @param code
	 * @return curso cuyo código es code
	 */
	public Optional<Course>findByCode(String code){
		return Jpa.getManager()
				.createNamedQuery("Course.findByCode",Course.class)
				.setParameter(1, code)
				.getResultStream()
				.findFirst();
	}

	/**
	 * Dado un id_mechanic y un id_vehicletype, devuelve los cursos que esten aprobados
	 * 		con porcentaje asistencia >= 85 por ese mecanico que se dedican a ese tipo de vehiculo.
	 *
	 * @param idVehicleType the id vehicle type
	 * @param idMechanic the id mechanic
	 * @return the list
	 */
	@Override
	public List<Course>findCoursesPassedByVehicleType(String idVehicleType, String idMechanic){
		return Jpa.getManager().createNamedQuery("Course.findCoursesPassedByVehicleType",Course.class)
				.setParameter(1, idVehicleType)
				.setParameter(2, idMechanic)
				.getResultList();
	}

	/**
	 * Devuelve el tipo de vehículo, el nombre de mecánicos y las horas de formación a las que ha asistido.
	 *
	 * @return the list
	 */
	@Override
	public List<?> findTrainingByVehicleTypeAndMechanic() {
		return Jpa.getManager().createNamedQuery("Mechanic.getTrainingByVehicleTypeAndMechanic")
				.getResultList();
	}

	/**
	 * Find training by mechanic id.
	 *
	 * @param id the id
	 * @return the list
	 */
	@Override
	public List<?> findTrainingByMechanicId(String id) {
		return Jpa.getManager().createNamedQuery("Mechanic.getTrainingByMechanicId")
				.setParameter(1, id)
				.getResultList();
	}
	
}
