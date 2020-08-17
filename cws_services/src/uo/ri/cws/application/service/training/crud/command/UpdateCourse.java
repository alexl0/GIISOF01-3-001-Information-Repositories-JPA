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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import alb.util.date.Dates;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.DedecationRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.VehicleType;

/**
 * The Class UpdateCourse.
 */
public class UpdateCourse implements Command<Void> {

	/** The dto. */
	private CourseDto dto;

	/** The cr. */
	private CourseRepository cr=Factory.repository.forCourse();

	/** The vr. */
	private VehicleTypeRepository vr=Factory.repository.forVehicleType();

	/** The dr. */
	private DedecationRepository dr=Factory.repository.forDedications();

	/**
	 * Instantiates a new update course.
	 *
	 * @param dto the dto
	 */
	public UpdateCourse(CourseDto dto) {
		this.dto=dto;
	}

	/**
	 * Execute.
	 *
	 * @return the void
	 * @throws BusinessException the business exception
	 */
	@Override
	public Void execute() throws BusinessException {

		//Comprobar datos que ha introducido el usuario
		validar(dto);

		/* ##################### Comprobaciones de lógica de negocio ##################### */

		Optional<Course> oc=cr.findById(dto.id);

		//Existe el curso
		BusinessCheck.exists(oc,"There is no such course");
		Course c=oc.get();

		//Se comprueba si el codigo que quiere actualizar el usuario ya es el codigo de algún curso
		Optional<Course>oc2=cr.findByCode(dto.code);
		BusinessCheck.isFalse(oc2.isPresent(),"Ya existe un curso con ese código.");

		//Se comprueba si ha sido o está siendo impartido
		BusinessCheck.isTrue(c.getStartDate().after(Dates.today()),"This course has been or is being taught now.");

		/*Se comprueba si la versión que tentemos en memoria es la misma que la que hay en la base de datos, porque si es
		 * distinta no se puede dejar actualizar */
		BusinessCheck.hasVersion(c, dto.version);

		//Se hace la actualización
		c.setCode(dto.code);
		c.setDescription(dto.description);
		c.setEndDate(dto.endDate);
		c.setHours(dto.hours);
		c.setName(dto.name);
		c.setStartDate(dto.startDate);

		//Actualizar las dedicaciones
		//Se borran las que ya tiene
		Set<Dedication>dedicaciones=c.getDedications();
		dedicaciones.forEach(d->dr.remove(d));
		c.clearDedications();

		/* Crear map de VehicleType y int, para poder añadir dedicaciones.
		 * Ya que Dedication pide como parámetro para el constructor un VehicleType, no su id */
		Map<VehicleType,Integer> percentages=new HashMap<VehicleType,Integer>();
		dto.percentages.forEach((s,i)->percentages.put(vr.findById(s).get(), i));

		//Añadir las nuevas dedicaciones
		c.addDedications(percentages);

		return null;
	}

	/**
	 * Comprueba que los dato introducidos por el usuario son válidos.
	 *
	 * @param dto the dto
	 * @throws BusinessException the business exception
	 */
	private void validar(CourseDto dto) throws BusinessException {

		//Nombre o descripción null o vacios
		if(dto.name==null||dto.description==null || dto.name.trim().isEmpty()||dto.description.trim().isEmpty())
			throw new BusinessException("name o description estan vacías o son nulas.");

		//Comprobar fechas
		if(dto.startDate==null|dto.endDate==null)
			throw new BusinessException("alguna de las fechas es null");
		if(dto.startDate.after(dto.endDate))
			throw new BusinessException("la fecha de inicio debe ser antes de la de fin");
		if(dto.hours<=0)
			throw new BusinessException("hours es <=0");

		//Comprobar si da tiempo a dar ese curso
		int days=Dates.diffDays(dto.endDate, dto.startDate)+1;//+1 porque contamos el dia que empieza y el que acaba el curso
		if(dto.hours>Math.abs(((days)*12)))
			throw new BusinessException("No da tiempo a dar ese curso, ni siquiera impartiendo clase 12 horas al día todos los días.");

		//Comprobar dedicaciones

		Map<String, Integer> percentages=dto.percentages;

		//Comprobación: dedications está vacío
		if(percentages.isEmpty())
			throw new BusinessException("Dedications is empty.");

		//Comprobación: la suma es exactamente 100
		List<Integer>suma2=new ArrayList<>();
		percentages.forEach((v,i)->suma2.add(i));
		int totalPrueba=0;
		for(Integer i:suma2) {
			totalPrueba+=i;
		}
		if(totalPrueba!=100)
			throw new BusinessException("La suma de los porcentajes debe ser exactamente del 100%"
					+ "\n\t\ty no se puede insertar dos veces el mismo tipo de vehiculo");
	}

}
