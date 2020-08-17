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
 * The Class VehicleType.
 */
public class VehicleType extends BaseEntity{
	
	/** The name. */
	private String name;
	
	/** The price per hour. */
	private double pricePerHour;

	/** The vehicles. */
	private Set<Vehicle>vehicles=new HashSet<>();

	/** The min training hours. */
	private int minTrainingHours;
	
	/** The certificates. */
	private Set<Certificate>certificates=new HashSet<>();
	
	/** The dedications. */
	private Set<Dedication>dedications=new HashSet<>();

	/**
	 * Instantiates a new vehicle type.
	 */
	VehicleType(){

	}
	
	/**
	 * Instantiates a new vehicle type.
	 *
	 * @param name the name
	 */
	public VehicleType(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Instantiates a new vehicle type.
	 *
	 * @param name the name
	 * @param pricePerHour the price per hour
	 */
	public VehicleType(String name, double pricePerHour) {
		this(name);
		this.pricePerHour=pricePerHour;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the price per hour.
	 *
	 * @return the price per hour
	 */
	public double getPricePerHour() {
		return pricePerHour;
	}

	/**
	 * Gets the vehicles.
	 *
	 * @return the sets the
	 */
	Set<Vehicle> _getVehicles(){
		return vehicles;
	}
	
	/**
	 * Gets the vehicles.
	 *
	 * @return the vehicles
	 */
	public Set<Vehicle> getVehicles() {
		return new HashSet<>(vehicles);
	}


	/**
	 * Gets the certificates.
	 *
	 * @return the sets the
	 */
	Set<Certificate> _getCertificates() {
		return certificates;
	}
	
	/**
	 * Gets the certificates.
	 *
	 * @return the certificates
	 */
	public Set<Certificate> getCertificates() {
		return new HashSet<>(this.certificates);
	}
	
	/**
	 * Gets the dedications.
	 *
	 * @return the sets the
	 */
	Set<Dedication> _getDedications() {
		return dedications;
	}
	
	/**
	 * Gets the dedications.
	 *
	 * @return the dedications
	 */
	public Set<Dedication> getDedications() {
		return new HashSet<>(this.dedications);
	}
	
	/**
	 * Gets the min training hours.
	 *
	 * @return the min training hours
	 */
	public int getMinTrainingHours() {
		return minTrainingHours;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		VehicleType other = (VehicleType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		return "VehicleType [name=" + name + ", pricePerHour=" + pricePerHour + "]";
	}

}
