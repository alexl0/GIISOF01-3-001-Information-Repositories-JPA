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
package uo.ri.cws.application.service;

import uo.ri.cws.application.ServiceFactory;
import uo.ri.cws.application.service.client.ClientCrudService;
import uo.ri.cws.application.service.client.ClientHistoryService;
import uo.ri.cws.application.service.invoice.CreateInvoiceService;
import uo.ri.cws.application.service.invoice.SettleInvoiceService;
import uo.ri.cws.application.service.invoice.create.CreateInvoiceServiceImpl;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.mechanic.crud.MechanicCrudServiceImpl;
import uo.ri.cws.application.service.sparepart.SparePartCrudService;
import uo.ri.cws.application.service.training.CertificateService;
import uo.ri.cws.application.service.training.CourseAttendanceService;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.training.CourseReportService;
import uo.ri.cws.application.service.training.attendance.CourseAttendanceServiceImpl;
import uo.ri.cws.application.service.training.certificate.CertificateServiceImpl;
import uo.ri.cws.application.service.training.crud.CourseCrudServiceImpl;
import uo.ri.cws.application.service.training.report.CourseReportServiceImpl;
import uo.ri.cws.application.service.vehicle.VehicleCrudService;
import uo.ri.cws.application.service.vehicle.crud.VehicleCrudServiceImpl;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;
import uo.ri.cws.application.service.workorder.CloseWorkOrderService;
import uo.ri.cws.application.service.workorder.ViewAssignedWorkOrdersService;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.crud.WorkOrderCrudServiceImpl;

/**
 * A factory for creating Business objects.
 */
public class BusinessFactory implements ServiceFactory {

	/**
	 * For mechanic crud service.
	 *
	 * @return the mechanic crud service
	 */
	@Override
	public MechanicCrudService forMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}

	/**
	 * For create invoice service.
	 *
	 * @return the creates the invoice service
	 */
	@Override
	public CreateInvoiceService forCreateInvoiceService() {
		return new CreateInvoiceServiceImpl();
	}

	/**
	 * For vehicle crud service.
	 *
	 * @return the vehicle crud service
	 */
	@Override
	public VehicleCrudService forVehicleCrudService() {
		return new VehicleCrudServiceImpl();
	}

	/**
	 * For closing breakdown.
	 *
	 * @return the close work order service
	 */
	@Override
	public CloseWorkOrderService forClosingBreakdown() {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * For vehicle type crud service.
	 *
	 * @return the vehicle type crud service
	 */
	@Override
	public VehicleTypeCrudService forVehicleTypeCrudService() {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * For spare part crud service.
	 *
	 * @return the spare part crud service
	 */
	@Override
	public SparePartCrudService forSparePartCrudService() {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * For settle invoice service.
	 *
	 * @return the settle invoice service
	 */
	@Override
	public SettleInvoiceService forSettleInvoiceService() {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * For client crud service.
	 *
	 * @return the client crud service
	 */
	@Override
	public ClientCrudService forClientCrudService() {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * For client history service.
	 *
	 * @return the client history service
	 */
	@Override
	public ClientHistoryService forClientHistoryService() {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * For work order service.
	 *
	 * @return the work order crud service
	 */
	@Override
	public WorkOrderCrudService forWorkOrderService() {
		return new WorkOrderCrudServiceImpl();
	}

	/**
	 * For view assigned work orders service.
	 *
	 * @return the view assigned work orders service
	 */
	@Override
	public ViewAssignedWorkOrdersService forViewAssignedWorkOrdersService() {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * For assign work order service.
	 *
	 * @return the assign work order service
	 */
	@Override
	public AssignWorkOrderService forAssignWorkOrderService() {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * ################################### Ampliación ###################################.
	 *
	 * @return the course crud service
	 */

	@Override
	public CourseCrudService forCourseCrudService() {
		return new CourseCrudServiceImpl();
	}

	/**
	 * For course attendance service.
	 *
	 * @return the course attendance service
	 */
	@Override
	public CourseAttendanceService forCourseAttendanceService() {
		return new CourseAttendanceServiceImpl();
		//throw new RuntimeException("No implementado porque no está dentro de mis casos de uso (UO%3==0)");
	}

	/**
	 * For course report service.
	 *
	 * @return the course report service
	 */
	@Override
	public CourseReportService forCourseReportService() {
		return new CourseReportServiceImpl();
	}

	/**
	 * For certificate service.
	 *
	 * @return the certificate service
	 */
	@Override
	public CertificateService forCertificateService() {
		return new CertificateServiceImpl();
	}

}
