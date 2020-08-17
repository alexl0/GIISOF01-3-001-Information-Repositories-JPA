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
package uo.ri.cws.application.service.invoice;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;

/**
 * This service is intended to be used by the Cashier
 * It follows the ISP principle (@see SOLID principles from RC Martin).
 */
public interface SettleInvoiceService {

	/**
	 * Find invoice.
	 *
	 * @param number the number
	 * @return the optional
	 * @throws BusinessException the business exception
	 */
	Optional<InvoiceDto> findInvoice(Long number) throws BusinessException;

	/**
	 * Find pay methods for invoice.
	 *
	 * @param invoiceId the invoice id
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	List<PaymentMeanDto> findPayMethodsForInvoice(String invoiceId)
			throws BusinessException;

	/**
	 * Settle invoice.
	 *
	 * @param invoiceId the invoice id
	 * @param charges the charges
	 * @throws BusinessException the business exception
	 */
	void settleInvoice(String invoiceId, Map<Long, Double> charges)
			throws BusinessException;

}
