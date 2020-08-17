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

import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * The Class InvoiceJpaRepository.
 */
public class InvoiceJpaRepository 
		extends BaseJpaRepository<Invoice>
		implements InvoiceRepository {

	/**
	 * Find by number.
	 *
	 * @param numero the numero
	 * @return the optional
	 */
	@Override
	public Optional<Invoice> findByNumber(Long numero) {
		return Jpa.getManager().createNamedQuery("Invoice.findByNumber", Invoice.class)
				.setParameter(1, numero)
				.getResultStream()
				.findFirst();
	}

	/**
	 * Gets the next invoice number.
	 *
	 * @return the next invoice number
	 */
	@Override
	public Long getNextInvoiceNumber() {
		return Jpa.getManager().createNamedQuery("Invoice.getNextInvoiceNumber", Long.class)
				.getSingleResult();//Se usa getSingleResult porque siempre va a haber un numer de factura máximo
	}

}
