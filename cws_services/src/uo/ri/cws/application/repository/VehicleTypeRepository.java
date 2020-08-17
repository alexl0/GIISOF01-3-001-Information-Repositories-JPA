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
package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.VehicleType;

/**
 * The Interface VehicleTypeRepository.
 */
public interface VehicleTypeRepository extends Repository<VehicleType> {

	/**
	 * Encuentra todos los tipos de vehículo.
	 *
	 * @return todos los tipos de vehículo
	 */
	List<VehicleType> findAll();

	/**
	 * Encuentra los tipos de vehículo en los que un mecánico no está certificado aún.
	 *
	 * @param mechanicId the mechanic id
	 * @return the list
	 */
	List<VehicleType> findNotCertificatedByMechanic(String mechanicId);

}
