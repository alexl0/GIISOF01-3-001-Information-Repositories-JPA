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
 * The Class SparePart.
 */
public class SparePart extends BaseEntity{
	
	/** The code. */
	private String code;
	
	/** The description. */
	private String description;
	
	/** The price. */
	private double price;
	
	/** The substitutions. */
	private Set<Substitution> substitutions=new HashSet<>();

	/**
	 * Instantiates a new spare part.
	 */
	SparePart(){
		
	}
	
	/**
	 * Instantiates a new spare part.
	 *
	 * @param code the code
	 */
	public SparePart(String code) {
		super();
		this.code = code;
	}
	
	/**
	 * Instantiates a new spare part.
	 *
	 * @param code the code
	 * @param description the description
	 * @param price the price
	 */
	public SparePart(String code, String description, double price) {
		this(code);
		this.description=description;
		this.price=price;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Gets the substitutions.
	 *
	 * @return the sets the
	 */
	Set<Substitution> _getSubstitutions() {
		return substitutions;
	}
	
	/**
	 * Gets the sustituciones.
	 *
	 * @return the sustituciones
	 */
	public Set<Substitution> getSustituciones() {
		return new HashSet<>(substitutions);
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
		SparePart other = (SparePart) obj;
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
		return "SparePart [code=" + code + ", description=" + description + ", price=" + price + "]";
	}


}
