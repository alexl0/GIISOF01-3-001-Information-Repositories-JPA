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
package uo.ri.cws.application.service.training;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin).
 */
public interface CourseCrudService {
	
	/**
	 * Registers a new course in the system.
	 *
	 * @param dto the dto
	 * @return the same Dto with the id field assigned to the created UUID
	 * @throws BusinessException the business exception
	 */
	CourseDto registerNew(CourseDto dto) throws BusinessException;
	
	/**
	 * Updates the course specified by the id with the new data. 
	 * A course an only be modified if it has not yet started. If the start 
	 * date is wrong then remove the course and start again...
	 * The dedications of the course to the vehicle types are not modified 
	 * by this operation.
	 *   
	 *
	 * @param dto the dto
	 * @throws BusinessException if:
	 * 	- it does not exist the course with the specified id, or
	 *  - the current version of the course does not match the version of the dto, or
	 *  - the course has its start date in the past, or 
	 * 	- the new data does not pass the validation specified 
	 * 		in @see registerNew
	 */
	void updateCourse(CourseDto dto) throws BusinessException;

	/**
	 * A course can only be deleted if it still has no attendance registered.
	 * Delete a course also implies remove all its dedications in cascade.
	 * 
	 * Note: A course and its dedications form an aggregate.
	 *  
	 *
	 * @param id the id
	 * @throws BusinessException if:
	 * 	- there is no course with the specified id, or
	 * 	- the course already has enrollments registered.
	 */
	void deleteCourse(String id) throws BusinessException;
	
	/**
	 * Find all courses.
	 *
	 * @return a list of CourseDto. Each one represents a course.
	 * @throws BusinessException the business exception
	 * @see CourseDto class for details.
	 * 
	 * DOES NOT @throws BusinessException
	 */
	List<CourseDto> findAllCourses() throws BusinessException;

	/**
	 * Find all vehicle types.
	 *
	 * @return a list of VehicleTypeDto.
	 * @throws BusinessException the business exception
	 * @see VehicleTypeDto class for details.
	 * 
	 * DOES NOT @throws BusinessException
	 */
	List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException;

	/**
	 * Find course by id.
	 *
	 * @param cId the c id
	 * @return an Optional, what if there is no course with the specified id?
	 * DOES NOT @throws BusinessException
	 * @throws BusinessException the business exception
	 */
	Optional<CourseDto> findCourseById(String cId) throws BusinessException;
}
