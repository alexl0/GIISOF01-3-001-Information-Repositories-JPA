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

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin).
 */
public interface CourseReportService {

	/**
	 * Returns a report summary of the training of the specified mechanic.
	 *
	 * @param id of the mechanic
	 * @return the list of lines, one for each vehicle type the mechanic
	 * 	have had some training
	 * @throws BusinessException the business exception
	 */
	List<TrainingForMechanicRow> findTrainigByMechanicId(String id)
			throws BusinessException;

	/**
	 * Returns a list of rows, one for each vehicle type and mechanic that had 
	 * attended to a course.
	 *
	 * @return the list, that might be empty if no mechanic has been trained
	 * 		for any vehicle type.
	 * @throws BusinessException the business exception
	 */
	List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic()
			throws BusinessException;

	/**
	 * Returns a list of rows, one for each mechanic that had been certified,
	 * sorted by vehicle type and mechanic.
	 *
	 * @return the list, that might be empty if no mechanic has been certified
	 * @throws BusinessException the business exception
	 */
	List<CertificateDto> findCertificatedByVehicleType()
			throws BusinessException;
}
