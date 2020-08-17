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
 * The Class Client.
 */
public class Client extends BaseEntity{
	
	/** The dni. */
	private String dni;
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;
	
	/** The email. */
	private String email;
	
	/** The phone. */
	private String phone;
	
	/** The address. */
	private Address address;

	/** The vehicles. */
	//set<> obliga que todos los elementos del grupo sean distintos (por eso el hash y el equals)
	private Set<Vehicle> vehicles=new HashSet<>();//client porque es el atributo de la clase Vechicle
	
	/** The payment means. */
	private Set<PaymentMean> paymentMeans=new HashSet<>();

	/**
	 * Instantiates a new client.
	 */
	Client() {

	}
	
	/**
	 * Instantiates a new client.
	 *
	 * @param dni the dni
	 */
	public Client(String dni) {
		super();
		this.dni = dni;
	}
	
	/**
	 * Instantiates a new client.
	 *
	 * @param dni the dni
	 * @param name the name
	 * @param surname the surname
	 */
	public Client(String dni, String name, String surname) {
		//Siempre hay que usar el constructor super
		//this.dni=dni;
		this(dni);
		this.name=name;
		this.surname=surname;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Gets the vehicles.
	 *
	 * @return the sets the
	 */
	Set<Vehicle> _getVehicles() {
		return vehicles;//No puedo permitir que devuelva un set de vehiculos de forma publica porque lo puede manipular cualquiera, se crea otro getter
	}
	
	/**
	 * Gets the vehicles.
	 *
	 * @return the vehicles
	 */
	public Set<Vehicle> getVehicles() {
		return new HashSet<>(vehicles);//solo pueden acceder clases del mismo paquete, como associations.java Ojo! devuelvo una copia
	}

	/**
	 * Gets the payment means.
	 *
	 * @return the sets the
	 */
	Set<PaymentMean> _getPaymentMeans() {
		return paymentMeans;
	}
	
	/**
	 * Gets the payment means.
	 *
	 * @return the payment means
	 */
	public Set<PaymentMean> getPaymentMeans() {
		return new HashSet<PaymentMean>(paymentMeans);
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
		Client other = (Client) obj;
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
	//no incluir listado
	@Override
	public String toString() {
		return "Client [dni=" + dni + ", name=" + name + ", surname=" + surname + ", email=" + email + ", phone="
				+ phone + ", address=" + address + "]";
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address=address;
	}

}

