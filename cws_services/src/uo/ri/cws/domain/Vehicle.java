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
package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class Vehicle.
 */
public class Vehicle extends BaseEntity{
	
	/** The plate number. */
	private String plateNumber;
	
	/** The make. */
	//@Column(name="brand")
	private String make;
	
	/** The model. */
	private String model;

	/** The client. */
	private Client client;
	
	/** The vehicle type. */
	private VehicleType vehicleType;
	
	/** The work orders. */
	private Set<WorkOrder>workOrders=new HashSet<>();

	/**
	 * Instantiates a new vehicle.
	 */
	Vehicle(){

	}
	
	/**
	 * Instantiates a new vehicle.
	 *
	 * @param plateNumber the plate number
	 */
	public Vehicle(String plateNumber) {
		super();
		this.plateNumber = plateNumber;
	}
	
	/**
	 * Instantiates a new vehicle.
	 *
	 * @param plateNumber the plate number
	 * @param make the make
	 * @param model the model
	 */
	public Vehicle(String plateNumber, String make, String model) {
		this(plateNumber);
		this.make=make;
		this.model=model;
	}

	/**
	 * Gets the plate number.
	 *
	 * @return the plate number
	 */
	public String getPlateNumber() {
		return plateNumber;
	}
	
	/**
	 * Gets the make.
	 *
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Gets the work orders.
	 *
	 * @return the sets the
	 */
	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}
	
	/**
	 * Gets the work orders.
	 *
	 * @return the work orders
	 */
	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<>(workOrders);
	}

	/**
	 * Gets the client.
	 *
	 * @return the client
	 */
	public Client getClient	() {
		return client;
	}
	
	/**
	 * Sets the client.
	 *
	 * @param client the client
	 */
	void _setClient(Client client) {
		this.client=client;
	}

	/**
	 * Sets the vehicle type.
	 *
	 * @param vehicleType the vehicle type
	 */
	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType=vehicleType;

	}
	
	/**
	 * Gets the vehicle type.
	 *
	 * @return the vehicle type
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plateNumber == null) ? 0 : plateNumber.hashCode());
		return result;
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (plateNumber == null) {
			if (other.plateNumber != null)
				return false;
		} else if (!plateNumber.equals(other.plateNumber))
			return false;
		return true;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Vehicle [plateNumber=" + plateNumber + ", make=" + make + ", model=" + model + "]";
	}




}
