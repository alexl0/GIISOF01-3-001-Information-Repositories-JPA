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
package uo.ri.cws.myTest;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.BusinessFactory;
import uo.ri.cws.application.service.training.CertificateService;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.infrastructure.persistence.jpa.executor.JpaExecutorFactory;
import uo.ri.cws.infrastructure.persistence.jpa.repository.JpaRepositoryFactory;

/**
 * The Class MyTest.
 */
public class MyTest {

	/**
	 * Se generan 34 certificados. Éste test sólo funciona con la base de datos según se descomprime.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test
	public void test34Certificates() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CertificateService cs = Factory.service.forCertificateService();
		int qty = cs.generateCertificates();
		assertTrue(qty==34 || qty==0); //La primera vez genera 34, y luego 0 porque ya no hay más que generar

	}

	/**
	 * Borrar curso que no existe.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = BusinessException.class)
	public void DeleteCourse1() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		cs.deleteCourse("curso_que_no_existe");
	}

	/**
	 * Borrar curso tiene mecanicos asignados.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = BusinessException.class)
	public void DeleteCourse2() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		cs.deleteCourse("166cb982-29ba-4b7b-8bf1-ca9cceb1030a");
	}

	/**
	 * Añadir curso cuyo código ya existe.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = BusinessException.class)
	public void RegisterNew1() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		
		CourseDto dto=new CourseDto();
		
		Map<String,Integer> percentages=new HashMap<String,Integer>();
		percentages.put("3b9c3e0e-9b4a-4a64-96ea-533900751edf", 100);
		dto.code="COURSE_5";
		dto.percentages=percentages;
		dto.description="descripcion";
		dto.endDate=Dates.tomorrow();
		dto.startDate=Dates.today();
		dto.hours=1;
		dto.name="nombre";
		
		cs.registerNew(dto);
	}

	/**
	 * Los tipos de vehículo que se le pasan no existen.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = RuntimeException.class)
	public void RegisterNew2() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		CourseDto dto=new CourseDto();
		Map<String,Integer> percentages=new HashMap<String,Integer>();
		percentages.put("idVehicleType_que_no_existe", 100); //Si los porcentajes suman 100 o no ya se controla en otros test

		dto.percentages=percentages;
		dto.code="curso_nuevo_00";
		dto.description="descripcion";
		dto.endDate=Dates.tomorrow();
		dto.startDate=Dates.today();
		dto.hours=1;
		dto.name="nombre";

		cs.registerNew(dto);
	}

	/**
	 * Los porcentajes no suman 100.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void RegisterNew3() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		CourseDto dto=new CourseDto();
		Map<String,Integer> percentages=new HashMap<String,Integer>();
		percentages.put("3b9c3e0e-9b4a-4a64-96ea-533900751edf", 101); //Si los porcentajes suman 100 o no ya se controla en otros test

		dto.code="curso_nuevo_01";
		dto.percentages=percentages;
		dto.description="descripcion";
		dto.endDate=Dates.tomorrow();
		dto.startDate=Dates.today();
		dto.hours=1;
		dto.name="nombre";

		cs.registerNew(dto);
	}

	/**
	 * Fecha de finalización antes que fecha de inicio.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void RegisterNew4() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		CourseDto dto=new CourseDto();
		Map<String,Integer> percentages=new HashMap<String,Integer>();
		percentages.put("3b9c3e0e-9b4a-4a64-96ea-533900751edf", 100); //Si los porcentajes suman 100 o no ya se controla en otros test

		dto.code="curso_nuevo_02";
		dto.percentages=percentages;
		dto.description="descripcion";
		dto.endDate=Dates.today();
		dto.startDate=Dates.tomorrow();
		dto.hours=1;
		dto.name="nombre";

		cs.registerNew(dto);
	}

	/**
	 * Modificar un curso que no existe.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = BusinessException.class)
	public void UpdateCourse1() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		CourseDto dto=new CourseDto();
		dto.id="id_que_no_existe";
		dto.code="codigo_del_curso_nuevo_00";
		dto.description="Curso nuevo";
		cs.updateCourse(dto);
	}

	/**
	 * Modificar un curso está siendo impartido.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = BusinessException.class)
	public void UpdateCourse2() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		CourseDto dto=new CourseDto();
		dto.id="166cb982-29ba-4b7b-8bf1-ca9cceb1030a";
		dto.code="codigo_del_curso_nuevo_00";
		dto.description="Curso nuevo";
		cs.updateCourse(dto);
	}

	/**
	 * Modificar un curso e intentar poner un código que ya existe.
	 *
	 * @throws BusinessException the business exception
	 */
	@Test(expected = BusinessException.class)
	public void UpdateCourse3() throws BusinessException {

		Factory.service = new BusinessFactory();
		Factory.repository = new JpaRepositoryFactory();
		Factory.executor = new JpaExecutorFactory();

		CourseCrudService cs = Factory.service.forCourseCrudService();
		CourseDto dto=new CourseDto();
		dto.id="166cb982-29ba-4b7b-8bf1-ca9cceb1030a";
		dto.code="COURSE_5";
		dto.description="Curso nuevo";
		cs.updateCourse(dto);
	}

}
