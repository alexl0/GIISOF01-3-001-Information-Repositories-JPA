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
package uo.ri.cws.application.service.training.report;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.training.CourseReportService;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.service.training.report.command.FindCertificatedByVehicleType;
import uo.ri.cws.application.service.training.report.command.FindTrainigByMechanicId;
import uo.ri.cws.application.service.training.report.command.FindTrainingByVehicleTypeAndMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

/**
 * The Class CourseReportServiceImpl.
 */
public class CourseReportServiceImpl implements CourseReportService {

	/** The executor. */
	private CommandExecutor executor = Factory.executor.forExecutor();
	
	/**
	 * Find trainig by mechanic id.
	 *
	 * @param id the id
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(String id) throws BusinessException {
		return executor.execute(new FindTrainigByMechanicId(id));
	}

	/**
	 * Find training by vehicle type and mechanic.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		return executor.execute(new FindTrainingByVehicleTypeAndMechanic());
	}

	/**
	 * Find certificated by vehicle type.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		return executor.execute(new FindCertificatedByVehicleType());
	}

}
