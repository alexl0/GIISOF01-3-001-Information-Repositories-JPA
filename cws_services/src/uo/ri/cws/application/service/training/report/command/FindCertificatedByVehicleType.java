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
package uo.ri.cws.application.service.training.report.command;

import java.util.Comparator;
import java.util.List;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;

/**
 * The Class FindCertificatedByVehicleType.
 */
public class FindCertificatedByVehicleType implements Command<List<CertificateDto>> {

	/** The cr. */
	private CertificateRepository cr=Factory.repository.forCertificate();

	/**
	 * Execute.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<CertificateDto> execute() throws BusinessException {

		//Encontrar todos los certificados
		List<Certificate> certificates=cr.findAll();

		//En éste caso la lógica de negocio es comprobar que haya cursos
		BusinessCheck.isFalse(certificates.isEmpty(),"No hay certificados que mostrar.");

		//Pasarlos a dto para operar con ellos
		List<CertificateDto> certificatesDto=DtoAssembler.toCertificateDtoList(certificates);

		//Crear un comparador para ordenarlos por tipo de vehículo
		class SortByVehicleType implements Comparator<CertificateDto>{
			public int compare(CertificateDto a, CertificateDto b){
				return a.vehicleType.name.compareTo(b.vehicleType.name); 
			} 
		} 

		//Ordenarlos acorde al comparador creado
		certificatesDto.sort(new SortByVehicleType());

		//Retornar la lista de certificados ordenados para que la ui se encargue de mostrarlos
		return certificatesDto;
	}

}
