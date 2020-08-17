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
package uo.ri.cws.application.service.vehicle.crud.commands;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.vehicle.VehicleDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;

/**
 * The Class FindVehicleByPlate.
 */
public class FindVehicleByPlate implements Command<Optional<VehicleDto>> {

	/** The plate. */
	private String plate;
	
	/** The repo. */
	private VehicleRepository repo = Factory.repository.forVehicle();
	
	/**
	 * Instantiates a new find vehicle by plate.
	 *
	 * @param plate the plate
	 */
	public FindVehicleByPlate(String plate) {
		this.plate = plate;
	}

	/**
	 * Execute.
	 *
	 * @return the optional
	 * @throws BusinessException the business exception
	 */
	@Override
	public Optional<VehicleDto> execute() throws BusinessException {
		Optional<Vehicle> m = repo.findByPlate( plate );
		return m.isPresent()
				? Optional.of( DtoAssembler.toDto( m.get() ))
				: Optional.empty();
	}

}
