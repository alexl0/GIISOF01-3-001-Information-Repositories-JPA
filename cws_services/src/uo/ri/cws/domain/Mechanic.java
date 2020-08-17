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
 * The Class Mechanic.
 */
public class Mechanic extends BaseEntity{
	
	/** The dni. */
	private String dni;
	
	/** The surname. */
	private String surname;
	
	/** The name. */
	private String name;

	/** The work orders. */
	private Set<WorkOrder>workOrders=new HashSet<>();
	
	/** The interventions. */
	private Set<Intervention> interventions=new HashSet<>();
	
	/** The certificates. */
	private Set<Certificate>certificates=new HashSet<>();
	
	/** The enrollments. */
	private Set<Enrollment>enrollments=new HashSet<>();

	/**
	 * Instantiates a new mechanic.
	 */
	Mechanic(){

	}
	
	/**
	 * Instantiates a new mechanic.
	 *
	 * @param dni the dni
	 */
	public Mechanic(String dni) {
		super();
		this.dni = dni;
	}
	
	/**
	 * Instantiates a new mechanic.
	 *
	 * @param dni the dni
	 * @param nombre the nombre
	 * @param apellidos the apellidos
	 */
	public Mechanic(String dni, String nombre, String apellidos) {
		this(dni);
		this.name=nombre;
		this.surname=apellidos;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	
	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
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
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the interventions.
	 *
	 * @return the sets the
	 */
	Set<Intervention> _getInterventions() {
		return interventions;
	}
	
	/**
	 * Gets the interventions.
	 *
	 * @return the interventions
	 */
	public Set<Intervention> getInterventions() {
		return new HashSet<Intervention>(interventions);
	}

	/**
	 * Gets the assigned.
	 *
	 * @return the sets the
	 */
	Set<WorkOrder> _getAssigned() {
		return workOrders;
	}
	
	/**
	 * Gets the assigned.
	 *
	 * @return the assigned
	 */
	public Set<WorkOrder> getAssigned() {
		return new HashSet<>(workOrders);
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
	 * Gets the enrollments.
	 *
	 * @return the sets the
	 */
	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}
	
	/**
	 * Gets the enrollments.
	 *
	 * @return the enrollments
	 */
	public Set<Enrollment> getEnrollments() {
		return new HashSet<>(this.enrollments);
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
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Mechanic other = (Mechanic) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
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
		return "Mechanic [dni=" + dni + ", surname=" + surname + ", name=" + name + "]";
	}
	
	/**
	 * Gets the enrollments for.
	 *
	 * @param vehicleType the vehicle type
	 * @return the enrollments for
	 */
	public Set<Enrollment> getEnrollmentsFor(VehicleType vehicleType) {
		Set<Enrollment> en=new HashSet<Enrollment>();
		for(Enrollment e:getEnrollments()) {
			for(Dedication d:e.getCourse().getDedications())
				if(d.getVehicleType()==vehicleType)
					en.add(e);
		}
		return en;
	}
	
	/**
	 * Checks if is certified for.
	 *
	 * @param vehicleType the vehicle type
	 * @return true, if is certified for
	 */
	public boolean isCertifiedFor(VehicleType vehicleType) {
		boolean res=false;
		for(Certificate c:certificates) {
			if(c.getVehicleType()==vehicleType)
				res=true;
		}
		return res;
	}


}
