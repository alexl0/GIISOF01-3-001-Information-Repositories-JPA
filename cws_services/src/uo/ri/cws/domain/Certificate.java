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

import java.util.Date;

/**
 * The Class Certificate.
 */
public class Certificate extends BaseEntity{
	
	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The vehicle type. */
	private VehicleType vehicleType;
	
	/** The date. */
	private Date date;

	/**
	 * Instantiates a new certificate.
	 */
	Certificate(){

	}
	
	/**
	 * Instantiates a new certificate.
	 *
	 * @param mechanic the mechanic
	 * @param vehicleType the vehicle type
	 */
	public Certificate(Mechanic mechanic, VehicleType vehicleType) {
		Associations.Certify.link(mechanic, this, vehicleType);
		this.date=new Date();
	}

	/**
	 * Gets the mechanic.
	 *
	 * @return the mechanic
	 */
	public Mechanic getMechanic() {
		return mechanic;
	}
	
	/**
	 * Sets the mechanic.
	 *
	 * @param mechanic the mechanic
	 */
	void _setMechanic(Mechanic mechanic) {
		this.mechanic=mechanic;
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
	 * Sets the vehicle type.
	 *
	 * @param vehicleType the vehicle type
	 */
	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType=vehicleType;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return new Date(this.date.getTime());
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
		result = prime * result + ((mechanic == null) ? 0 : mechanic.hashCode());
		result = prime * result + ((vehicleType == null) ? 0 : vehicleType.hashCode());
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
		Certificate other = (Certificate) obj;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
			return false;
		if (vehicleType == null) {
			if (other.vehicleType != null)
				return false;
		} else if (!vehicleType.equals(other.vehicleType))
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
		return "Certificate [mechanic=" + mechanic + ", vehicleType=" + vehicleType + ", date=" + date + "]";
	}


}
