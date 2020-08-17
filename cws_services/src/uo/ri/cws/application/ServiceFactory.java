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
package uo.ri.cws.application;

import uo.ri.cws.application.service.client.ClientCrudService;
import uo.ri.cws.application.service.client.ClientHistoryService;
import uo.ri.cws.application.service.invoice.CreateInvoiceService;
import uo.ri.cws.application.service.invoice.SettleInvoiceService;
import uo.ri.cws.application.service.mechanic.MechanicCrudService;
import uo.ri.cws.application.service.sparepart.SparePartCrudService;
import uo.ri.cws.application.service.training.CertificateService;
import uo.ri.cws.application.service.training.CourseAttendanceService;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.training.CourseReportService;
import uo.ri.cws.application.service.vehicle.VehicleCrudService;
import uo.ri.cws.application.service.vehicletype.VehicleTypeCrudService;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;
import uo.ri.cws.application.service.workorder.CloseWorkOrderService;
import uo.ri.cws.application.service.workorder.ViewAssignedWorkOrdersService;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;

/**
 * A factory for creating Service objects.
 */
public interface ServiceFactory {

	/**
	 * For vehicle type crud service.
	 *
	 * @return the vehicle type crud service
	 */
	// Manager use cases
	VehicleTypeCrudService forVehicleTypeCrudService();
	
	/**
	 * For mechanic crud service.
	 *
	 * @return the mechanic crud service
	 */
	MechanicCrudService forMechanicCrudService();
	
	/**
	 * For spare part crud service.
	 *
	 * @return the spare part crud service
	 */
	SparePartCrudService forSparePartCrudService();

	/**
	 * For course crud service.
	 *
	 * @return the course crud service
	 */
	CourseCrudService forCourseCrudService();
	
	/**
	 * For course attendance service.
	 *
	 * @return the course attendance service
	 */
	CourseAttendanceService forCourseAttendanceService();
	
	/**
	 * For course report service.
	 *
	 * @return the course report service
	 */
	CourseReportService forCourseReportService();
	
	/**
	 * For certificate service.
	 *
	 * @return the certificate service
	 */
	CertificateService forCertificateService();

	/**
	 * For create invoice service.
	 *
	 * @return the creates the invoice service
	 */
	// Cash use cases
	CreateInvoiceService forCreateInvoiceService();
	
	/**
	 * For settle invoice service.
	 *
	 * @return the settle invoice service
	 */
	SettleInvoiceService forSettleInvoiceService();

	/**
	 * For work order service.
	 *
	 * @return the work order crud service
	 */
	// Foreman use cases
	WorkOrderCrudService forWorkOrderService();
	
	/**
	 * For assign work order service.
	 *
	 * @return the assign work order service
	 */
	AssignWorkOrderService forAssignWorkOrderService();
	
	/**
	 * For vehicle crud service.
	 *
	 * @return the vehicle crud service
	 */
	VehicleCrudService forVehicleCrudService();
	
	/**
	 * For client crud service.
	 *
	 * @return the client crud service
	 */
	ClientCrudService forClientCrudService();
	
	/**
	 * For client history service.
	 *
	 * @return the client history service
	 */
	ClientHistoryService forClientHistoryService();

	/**
	 * For closing breakdown.
	 *
	 * @return the close work order service
	 */
	// Mechanic use cases
	CloseWorkOrderService forClosingBreakdown();
	
	/**
	 * For view assigned work orders service.
	 *
	 * @return the view assigned work orders service
	 */
	ViewAssignedWorkOrdersService forViewAssignedWorkOrdersService();

}
