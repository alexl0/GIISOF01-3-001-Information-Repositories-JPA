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

/**
 * The Class Dedication.
 */
public class Dedication extends BaseEntity{
	
	/** The vehicle type. */
	private VehicleType vehicleType;
	
	/** The course. */
	private Course course;
	
	/** The percentage. */
	private int percentage;

	/**
	 * Instantiates a new dedication.
	 */
	Dedication(){

	}
	
	/**
	 * Instantiates a new dedication.
	 *
	 * @param vehicleType the vehicle type
	 * @param course the course
	 */
	Dedication(VehicleType vehicleType, Course course) {
		Associations.Dedicate.link(vehicleType, this, course);
	}
	
	/**
	 * Instantiates a new dedication.
	 *
	 * @param vehicleType the vehicle type
	 * @param course the course
	 * @param percentage the percentage
	 */
	Dedication(VehicleType vehicleType, Course course, int percentage) {
		this(vehicleType, course);
		this.percentage=percentage;
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
	 * Gets the course.
	 *
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}
	
	/**
	 * Sets the course.
	 *
	 * @param course the course
	 */
	void _setCourse(Course course) {
		this.course=course;
	}
	
	/**
	 * Gets the percentage.
	 *
	 * @return the percentage
	 */
	public int getPercentage() {
		return percentage;
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
		result = prime * result + ((course == null) ? 0 : course.hashCode());
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
		Dedication other = (Dedication) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
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
		return "Dedication [vehicleType=" + vehicleType + ", course=" + course + ", percentage=" + percentage + "]";
	}



}
