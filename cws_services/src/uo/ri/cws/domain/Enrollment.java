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
 * The Class Enrollment.
 */
public class Enrollment extends BaseEntity{
	
	/** The mechanic. */
	private Mechanic mechanic;
	
	/** The course. */
	private Course course;
	
	/** The attendance. */
	private int attendance;
	
	/** The passed. */
	private boolean passed;

	/**
	 * Instantiates a new enrollment.
	 */
	public Enrollment() {

	}
	
	/**
	 * Instantiates a new enrollment.
	 *
	 * @param mechanic the mechanic
	 * @param course the course
	 */
	public Enrollment(Mechanic mechanic, Course course) {
		Associations.Enroll.link(mechanic, this, course);
	}

	/**
	 * Instantiates a new enrollment.
	 *
	 * @param mechanic the mechanic
	 * @param course the course
	 * @param attendance the attendance
	 * @param passed the passed
	 */
	public Enrollment(Mechanic mechanic, Course course, int attendance, boolean passed) {
		this(mechanic,course);
		if(attendance<85 && passed)
			throw new IllegalArgumentException("attendance<85 && passed no puede ser.");
		this.attendance=attendance;
		this.passed=passed;
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
	 * @param m the m
	 */
	void _setMechanic(Mechanic m) {
		this.mechanic=m;
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
	 * @param c the c
	 */
	void _setCourse(Course c) {
		this.course=c;
	}
	
	/**
	 * Gets the attendance.
	 *
	 * @return the attendance
	 */
	public int getAttendance() {
		return attendance;
	}
	
	/**
	 * Checks if is passed.
	 *
	 * @return true, if is passed
	 */
	public boolean isPassed() {
		return passed;
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
		result = prime * result + ((mechanic == null) ? 0 : mechanic.hashCode());
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
		Enrollment other = (Enrollment) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
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
		return "Enrollment [mechanic=" + mechanic + ", course=" + course + ", attendance=" + attendance + ", passed="
				+ passed + "]";
	}

	/**
	 * Gets the attended hours for.
	 *
	 * @param vehicleType the vehicle type
	 * @return the attended hours for
	 */
	public int getAttendedHoursFor(VehicleType vehicleType) {
		double percentageDedication=0.0;
		for (Dedication d:course.getDedications()) {
			if(d.getVehicleType()==vehicleType)
				percentageDedication=d.getPercentage();
		}
		percentageDedication/=100;

		return (int)(getAttendance()*percentageDedication);
	}



}
