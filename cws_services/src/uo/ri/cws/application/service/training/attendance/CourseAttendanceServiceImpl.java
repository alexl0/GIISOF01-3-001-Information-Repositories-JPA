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
package uo.ri.cws.application.service.training.attendance;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.training.CourseAttendanceService;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.service.training.attendance.command.FindAllActiveMechanics;
import uo.ri.cws.application.util.command.CommandExecutor;

/**
 * The Class CourseAttendanceServiceImpl.
 */
public class CourseAttendanceServiceImpl implements CourseAttendanceService {

	/** The executor. */
	private CommandExecutor executor = Factory.executor.forExecutor();

	/**
	 * Register new.
	 *
	 * @param dto the dto
	 * @return the enrollment dto
	 * @throws BusinessException the business exception
	 */
	@Override
	public EnrollmentDto registerNew(EnrollmentDto dto) throws BusinessException {
		throw new BusinessException("Not implemented.");
	}

	/**
	 * Delete attendace.
	 *
	 * @param id the id
	 * @throws BusinessException the business exception
	 */
	@Override
	public void deleteAttendace(String id) throws BusinessException {
		throw new BusinessException("Not implemented.");
	}

	/**
	 * Find attendance by course id.
	 *
	 * @param id the id
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<EnrollmentDto> findAttendanceByCourseId(String id) throws BusinessException {
		throw new BusinessException("Not implemented.");
	}

	/**
	 * Find all active courses.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<CourseDto> findAllActiveCourses() throws BusinessException {
		throw new BusinessException("Not implemented.");
	}

	/**
	 * Find all active mechanics.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<MechanicDto> findAllActiveMechanics() throws BusinessException {
		return executor.execute(new FindAllActiveMechanics());
	}

}
