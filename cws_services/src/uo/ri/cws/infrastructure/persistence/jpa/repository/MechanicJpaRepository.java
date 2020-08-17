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
package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * The Class MechanicJpaRepository.
 */
public class MechanicJpaRepository
			extends BaseJpaRepository<Mechanic>
			implements MechanicRepository {
/*
	@Override
	public void add(Mechanic t) {
		//El commandexecutor crea el entityManager (patrón singleton) de manera que solo puede haber una instancia y todos los métodos la utilizan
		Jpa.getManager().persist(t);
		
	}

	@Override
	public void remove(Mechanic t) {
		Jpa.getManager().remove(t);
	}

	@Override
	public Optional<Mechanic> findById(String id) {
		Mechanic m=Jpa.getManager().find(Mechanic.class, id);
		return m!=null ? Optional.of(m) : Optional.empty();
	}

	@Override
	public List<Mechanic> findAll() {
		// 
		return null;
	}
*/
	
	/**
 * Find by dni.
 *
 * @param dni the dni
 * @return the optional
 */
@Override
	public Optional<Mechanic> findByDni(String dni) {
		// Consulta
		return Jpa.getManager()
				.createNamedQuery("Mechanic.findByDni", Mechanic.class)
				.setParameter(1, dni)
				.getResultStream()
				.findFirst();//Se utiliza findFirst() porque devuelve un Optional (puede estar vacío o no)
	}

}
