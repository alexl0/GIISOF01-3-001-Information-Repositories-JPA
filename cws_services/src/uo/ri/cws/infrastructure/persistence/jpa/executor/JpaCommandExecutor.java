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
package uo.ri.cws.infrastructure.persistence.jpa.executor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.application.util.command.CommandExecutor;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

/**
 * The Class JpaCommandExecutor.
 */
public class JpaCommandExecutor implements CommandExecutor {

	/**
	 * Execute.
	 *
	 * @param <T> the generic type
	 * @param cmd the cmd
	 * @return the t
	 * @throws BusinessException the business exception
	 */
	@Override
	public <T> T execute(Command<T> cmd) throws BusinessException {
		EntityManager mapper = Jpa.createEntityManager();
		try {
			EntityTransaction trx = mapper.getTransaction();
			trx.begin();
			
			try {
				T res = cmd.execute();
				trx.commit();
				
				return res;
				
			} catch (BusinessException | PersistenceException ex) {
				if ( trx.isActive() ) {
					trx.rollback();
				}
				throw ex;
			}
		} finally {
			if ( mapper.isOpen() ) {
				mapper.close();
			}
		}
	}
}
