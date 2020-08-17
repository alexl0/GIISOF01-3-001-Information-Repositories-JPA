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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import alb.util.date.Dates;

/**
 * The Class Course.
 */
public class Course extends BaseEntity{

	/** The code. */
	private String code;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/** The hours. */
	private int hours;

	/** The dedications. */
	private Set<Dedication>dedications=new HashSet<>();

	/** The enrollments. */
	private Set<Enrollment>enrollments=new HashSet<>();

	/**
	 * Instantiates a new course.
	 */
	Course(){

	}

	/**
	 * Instantiates a new course.
	 *
	 * @param code the code
	 */
	public Course(String code) {
		if(code!=null && !code.trim().isEmpty())
			this.code = code;
		else
			throw new IllegalArgumentException("code es null o es la cadena vacía, no puede ser.");
	}

	/**
	 * Instantiates a new course.
	 *
	 * @param code the code
	 * @param name the name
	 * @param description the description
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param hours the hours
	 */
	public Course(String code, String name, String description, Date startDate, Date endDate, int hours) {
		this(code);

		//Nombre o descripción null o vacios
		if(name==null||description==null || name.trim().isEmpty()||description.trim().isEmpty())
			throw new IllegalArgumentException("name o description estan vacías o son nulas.");

		//Comprobar fechas
		if(startDate==null|endDate==null)
			throw new IllegalArgumentException("alguna de las fechas es null");
		if(startDate.after(endDate))
			throw new IllegalArgumentException("la fecha de inicio debe ser antes de la de fin");
		if(hours<=0)
			throw new IllegalArgumentException("hours es <=0");

		//Comprobar si da tiempo a dar ese curso
		int days=Dates.diffDays(endDate, startDate)+1;//+1 porque contamos el dia que empieza y el que acaba el curso
		if(hours>Math.abs(((days)*12)))
			throw new IllegalArgumentException("No da tiempo a dar ese curso, ni siquiera impartiendo clase 12 horas al día todos los días.");

		//Todo bien, podemos añadir los parametros
		this.name=name;
		this.description=description;
		this.startDate=new Date(startDate.getTime());
		this.endDate=new Date(endDate.getTime());
		this.hours=hours;
	}

	/**
	 * Instantiates a new course.
	 *
	 * @param code the code
	 * @param name the name
	 * @param description the description
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param hours the hours
	 * @param percentages the percentages
	 */
	//Hay que crear un nuevo constructor para pasarle los porcentajes de dedicaciones
	public Course(String code, String name, String description, Date startDate, Date endDate, int hours, Map<VehicleType,Integer> percentages) {
		this(code, name, description, startDate, endDate, hours);
		addDedications(percentages);
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return new Date(this.startDate.getTime());
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return new Date(this.endDate.getTime());
	}

	/**
	 * Gets the hours.
	 *
	 * @return the hours
	 */
	public int getHours() {
		return hours;
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
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	//Setters para poder hacer el UpdatCourse
	public void setCode(String code) {
		this.code = code;
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
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Sets the hours.
	 *
	 * @param hours the new hours
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * Sets the dedications.
	 *
	 * @param dedications the new dedications
	 */
	public void setDedications(Set<Dedication> dedications) {
		this.dedications = dedications;
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
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Course other = (Course) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
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
		return "Course [code=" + code + ", name=" + name + ", description=" + description + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", hours=" + hours + "]";
	}

	/**
	 * Adds the dedications.
	 *
	 * @param dedications2 the dedications 2
	 */
	public void addDedications(Map<VehicleType, Integer> dedications2) {

		//Comprobación: dedications2 está vacío
		if(dedications2.isEmpty())
			throw new IllegalStateException("Dedications is empty.");

		//Comprobación: ya tiene dedicaciones
		if(!dedications.isEmpty())
			throw new IllegalStateException("El curso ya tiene dedicaciones, no se pueden insertar más dedicaciones");

		//Comprobación: la suma es exactamente 100
		List<Integer>suma2=new ArrayList<>();
		dedications2.forEach((v,i)->suma2.add(i));
		int total=0;
		for(Integer i:suma2) {
			total+=i;
		}
		if(total!=100)
			throw new IllegalArgumentException("total!=100");

		//Suma 100, se pueden añadir
		dedications2.forEach((v, i)->dedications.add(new Dedication(v, this,i)));
	}

	/**
	 * Clear dedications.
	 */
	public void clearDedications() {
		/*
		 * Se le pasa una COPIA de las dedicaciones, el proceso de ir iterando se hace fuera de éste metodo
		 * y fuera de esta clase para que no haya excepción al modificar desde dos sitios algún objeto.
		 */		
		Associations.Dedicate.unlink(getDedications());
	}


}
