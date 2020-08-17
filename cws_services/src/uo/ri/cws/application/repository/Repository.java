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
package uo.ri.cws.application.repository;

import java.util.Optional;

/**
 * 	Common interface for a repository that follows the Collection metaphor,
 * 	 in the same way as any Collection in Java (List, Set, etc). You only put 
 * 	 and remove objects (references indeed), thus there is no update method. 
 * 	 That is an important difference with the TDG/DAO pattern.
 *
 * @param <T> the generic type
 */
public interface Repository<T> {
	
	/**
	 * Adds the.
	 *
	 * @param t the t
	 */
	void add(T t);
	
	/**
	 * Removes the.
	 *
	 * @param t the t
	 */
	void remove(T t);
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	Optional<T> findById(String id);
}

