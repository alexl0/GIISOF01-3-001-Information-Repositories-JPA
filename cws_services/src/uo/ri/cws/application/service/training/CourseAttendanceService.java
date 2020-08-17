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

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin).
 */
public interface CourseAttendanceService {

	/**
	 * Registers the attendance of a mechanic to a course.
	 *
	 * @param dto the dto
	 * @return the same dto but with the id field set to the generated UUID value
	 * @throws BusinessException if:
	 * 	- the mechanic id does not exit, or
	 * 	- the course id does not exist, or
	 *  - there already is another enrollment registered for the same mechanic
	 *  	and course, or
	 *  - the attendance is under 85% and the course is marked as passed, or
	 *  - the course is not yet finished, or  <- IGNORE THIS, complicates testing
	 *  - the value for percentage is not in the range 0..100
	 */
	EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException;

	/**
	 * Removes the attendance record specified by the id.
	 *
	 * @param id of the attendance record
	 * @throws BusinessException if the attendance record does not exist
	 */
	void deleteAttendace(String id) throws BusinessException;

	/**
	 * Lists all the attendance records for the specified course id.
	 *
	 * @param id the id
	 * @return the list of attendances or an empty list if the course
	 * 		does not exist or the course has no attendance registered yet
	 * @throws BusinessException the business exception
	 */
	List<EnrollmentDto> findAttendanceByCourseId(String id) throws BusinessException;

	/**
	 * Find all active courses.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	List<CourseDto> findAllActiveCourses() throws BusinessException;
	
	/**
	 * Find all active mechanics.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	List<MechanicDto> findAllActiveMechanics() throws BusinessException;
}
