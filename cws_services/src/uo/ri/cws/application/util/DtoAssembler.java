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
package uo.ri.cws.application.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import uo.ri.cws.application.service.client.ClientDto;
import uo.ri.cws.application.service.invoice.CardDto;
import uo.ri.cws.application.service.invoice.CashDto;
import uo.ri.cws.application.service.invoice.InvoiceDto;
import uo.ri.cws.application.service.invoice.PaymentMeanDto;
import uo.ri.cws.application.service.invoice.VoucherDto;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.service.vehicle.VehicleDto;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.Client;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.Cash;
import uo.ri.cws.domain.CreditCard;
import uo.ri.cws.domain.PaymentMean;
import uo.ri.cws.domain.Voucher;
import uo.ri.cws.domain.WorkOrder;

/**
 * The Class DtoAssembler.
 */
public class DtoAssembler {

	/**
	 * To dto.
	 *
	 * @param c the c
	 * @return the client dto
	 */
	public static ClientDto toDto(Client c) {
		 ClientDto dto = new ClientDto();

		 dto.id = c.getId();
		 dto.version = c.getVersion();

		 dto.dni = c.getDni();
		 dto.name = c.getName();
		 dto.surname = c.getSurname();

		 return dto;
	}

	/**
	 * To client dto list.
	 *
	 * @param clientes the clientes
	 * @return the list
	 */
	public static List<ClientDto> toClientDtoList(List<Client> clientes) {
		List<ClientDto> res = new ArrayList<>();
		for(Client c: clientes) {
			res.add( DtoAssembler.toDto( c ) );
		}
		return res;
	}

	/**
	 * To dto.
	 *
	 * @param m the m
	 * @return the mechanic dto
	 */
	public static MechanicDto toDto(Mechanic m) {
		MechanicDto dto = new MechanicDto();
		dto.id = m.getId();
		dto.version = m.getVersion();

		dto.dni = m.getDni();
		dto.name = m.getName();
		dto.surname = m.getSurname();
		return dto;
	}

	/**
	 * To mechanic dto list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<MechanicDto> toMechanicDtoList(List<Mechanic> list) {
		List<MechanicDto> res = new ArrayList<>();
		for(Mechanic m: list) {
			res.add( toDto( m ) );
		}
		return res;
	}

	/**
	 * To voucher dto list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<VoucherDto> toVoucherDtoList(List<Voucher> list) {
		List<VoucherDto> res = new ArrayList<>();
		for(Voucher b: list) {
			res.add( toDto( b ) );
		}
		return res;
	}

	/**
	 * To dto.
	 *
	 * @param v the v
	 * @return the voucher dto
	 */
	public static VoucherDto toDto(Voucher v) {
		VoucherDto dto = new VoucherDto();
		dto.id = v.getId();
		dto.version = v.getVersion();

		dto.clientId = v.getClient().getId();
		dto.accumulated = v.getAccumulated();
		dto.code = v.getCode();
		dto.description = v.getDescription();
		//dto.available = v.getDisponible();
		dto.available = v.getDisponible();
		return dto;
	}

	/**
	 * To dto.
	 *
	 * @param cc the cc
	 * @return the card dto
	 */
	public static CardDto toDto(CreditCard cc) {
		CardDto dto = new CardDto();
		dto.id = cc.getId();
		dto.version = cc.getVersion();

		dto.clientId = cc.getClient().getId();
		dto.accumulated = cc.getAccumulated();
		dto.cardNumber = cc.getNumber();
		dto.cardExpiration = cc.getValidThru();
		dto.cardType = cc.getType();
		return dto;
	}

	/**
	 * To dto.
	 *
	 * @param m the m
	 * @return the cash dto
	 */
	public static CashDto toDto(Cash m) {
		CashDto dto = new CashDto();
		dto.id = m.getId();
		dto.version = m.getVersion();

		dto.clientId = m.getClient().getId();
		dto.accumulated = m.getAccumulated();
		return dto;
	}

	/**
	 * To dto.
	 *
	 * @param invoice the invoice
	 * @return the invoice dto
	 */
	public static InvoiceDto toDto(Invoice invoice) {
		InvoiceDto dto = new InvoiceDto();
		dto.id = invoice.getId();
		dto.version = invoice.getVersion();

		dto.number = invoice.getNumber();
		dto.date = invoice.getDate();
		dto.total = invoice.getAmount();
		dto.vat = invoice.getVat();
		dto.status = invoice.getStatus().toString();
		return dto;
	}

	/**
	 * To payment mean dto list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<PaymentMeanDto> toPaymentMeanDtoList(List<PaymentMean> list) {
		return list.stream()
				.map( mp -> toDto( mp ) )
				.collect( Collectors.toList() );
	}

	/**
	 * To dto.
	 *
	 * @param mp the mp
	 * @return the payment mean dto
	 */
	private static PaymentMeanDto toDto(PaymentMean mp) {
		if (mp instanceof Voucher) {
			return toDto( (Voucher) mp );
		}
		else if (mp instanceof CreditCard) {
			return toDto( (CreditCard) mp );
		}
		else if (mp instanceof Cash) {
			return toDto( (Cash) mp);
		}
		else {
			throw new RuntimeException("Unexpected type of payment mean");
		}
	}

	/**
	 * To dto.
	 *
	 * @param a the a
	 * @return the work order dto
	 */
	public static WorkOrderDto toDto(WorkOrder a) {
		WorkOrderDto dto = new WorkOrderDto();
		dto.id = a.getId();
		dto.version = a.getVersion();

		dto.vehicleId = a.getVehicle().getId();
		dto.description = a.getDescription();
		dto.date = a.getDate();
		dto.total = a.getAmount();
		dto.status = a.getStatus().toString();

		dto.invoiceId = a.getInvoice() == null ? null : a.getInvoice().getId();

		return dto;
	}

	/**
	 * To dto.
	 *
	 * @param v the v
	 * @return the vehicle dto
	 */
	public static VehicleDto toDto(Vehicle v) {
		VehicleDto dto = new VehicleDto();
		dto.id = v.getId();
		dto.version = v.getVersion();

		dto.plate = v.getPlateNumber();
		dto.clientId = v.getClient().getId();
		dto.make = v.getMake();
		dto.vehicleTypeId = v.getVehicleType().getId();
		dto.model = v.getModel();

		return dto;
	}

	/**
	 * To work order dto list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<WorkOrderDto> toWorkOrderDtoList(List<WorkOrder> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

	/**
	 * To certificate dto list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<CertificateDto> toCertificateDtoList(
			List<Certificate> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

	/**
	 * To dto.
	 *
	 * @param c the c
	 * @return the certificate dto
	 */
	public static CertificateDto toDto(Certificate c) {
		CertificateDto dto = new CertificateDto();
		dto.id = c.getId();
		dto.version = c.getVersion();

		dto.mechanic = toDto( c.getMechanic() );
		dto.vehicleType = toDto( c.getVehicleType() );
		dto.obtainedAt = c.getDate();

		return dto;
	}

	/**
	 * To dto.
	 *
	 * @param vt the vt
	 * @return the vehicle type dto
	 */
	public static VehicleTypeDto toDto(VehicleType vt) {
		VehicleTypeDto dto = new VehicleTypeDto();

		dto.id = vt.getId();
		dto.version = vt.getVersion();

		dto.name = vt.getName();
		dto.pricePerHour = vt.getPricePerHour();
		dto.minTrainigHours = vt.getMinTrainingHours();

		return dto;
	}

	/**
	 * To dto.
	 *
	 * @param c the c
	 * @return the course dto
	 */
	public static CourseDto toDto(Course c) {
		CourseDto dto = new CourseDto();

		dto.id = c.getId();
		dto.version = c.getVersion();

		dto.code = c.getCode();
		dto.name = c.getName();
		dto.description = c.getDescription();
		dto.startDate = c.getStartDate();
		dto.endDate = c.getEndDate();

		for (Dedication d: c.getDedications()) {
			dto.percentages.put( d.getVehicleType().getId(), d.getPercentage() );
		}

		return dto;
	}

	/**
	 * To course dto list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<CourseDto> toCourseDtoList(
			List<Course> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

	/**
	 * To vehicle type dto list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<VehicleTypeDto> toVehicleTypeDtoList(
			List<VehicleType> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

	/**
	 * To enrollment dto list.
	 *
	 * @param list the list
	 * @return the list
	 */
	public static List<EnrollmentDto> toEnrollmentDtoList(
			List<Enrollment> list) {
		return list.stream()
				.map( a -> toDto( a ) )
				.collect( Collectors.toList() );
	}

	/**
	 * To dto.
	 *
	 * @param e the e
	 * @return the enrollment dto
	 */
	public static EnrollmentDto toDto(Enrollment e) {
		EnrollmentDto dto = new EnrollmentDto();

		dto.id = e.getId();
		dto.version = e.getVersion();

		dto.courseId = e.getCourse().getId();
		dto.mechanicId = e.getMechanic().getId();
		dto.attendance = e.getAttendance();
		dto.passed = e.isPassed();

		dto.mechanic = toDto( e.getMechanic() );
		dto.course = toDto( e.getCourse() );

		return dto;
	}

}
