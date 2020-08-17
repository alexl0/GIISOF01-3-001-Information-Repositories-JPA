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
package uo.ri.cws.application.service.training.crud.command;

import java.util.Optional;
import java.util.Set;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.DedecationRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;

/**
 * The Class DeleteCourse.
 */
public class DeleteCourse implements Command<Void> {

	/** The id. */
	private String id;
	
	/** The cr. */
	private CourseRepository cr=Factory.repository.forCourse();
	
	/** The dr. */
	private DedecationRepository dr=Factory.repository.forDedications();

	/**
	 * Instantiates a new delete course.
	 *
	 * @param id the id
	 */
	public DeleteCourse(String id) {
		this.id=id;
	}

	/**
	 * Execute.
	 *
	 * @return the void
	 * @throws BusinessException the business exception
	 */
	@Override
	public Void execute() throws BusinessException {
		Optional<Course> oc=cr.findById(id);

		//Comprobaciones de lógica
		//El curso existe
		BusinessCheck.isTrue(oc.isPresent(),"This course does not exists.");
		Course c=oc.get();
		//El curso no tiene mecánicos registrados
		BusinessCheck.isTrue(c.getEnrollments().isEmpty(),"This course has enrollments.");

		//Se puede borrar
		//Primero borrar las dedicaciones
		Set<Dedication>dedicaciones=c.getDedications();
		dedicaciones.forEach(d->dr.remove(d));
		c.clearDedications();

		//Y luego borrar el curso. Se hace en ese orden porque sino daría error porque hay foreign keys
		//en dedications que apuntan a course, por lo tanto hay que borrar primero siempre las filas de dedications
		cr.remove(c);

		return null;
	}

}
