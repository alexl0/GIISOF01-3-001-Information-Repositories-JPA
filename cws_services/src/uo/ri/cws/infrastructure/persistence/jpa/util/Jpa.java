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
package uo.ri.cws.infrastructure.persistence.jpa.util;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The Class Jpa.
 */
public class Jpa {

	/** The emf. */
	private static volatile EntityManagerFactory emf = null;
	
	/** The em thread. */
	private static ThreadLocal<EntityManager> emThread = 
		new ThreadLocal<EntityManager>();
	
	/**
	 * Creates the entity manager.
	 *
	 * @return the entity manager
	 */
	public static EntityManager createEntityManager() {
		EntityManager entityManager = getEmf().createEntityManager();
		emThread.set(entityManager);
		return entityManager;
	}

	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	public static EntityManager getManager() {
		return emThread.get();
	}

	/**
	 * Close.
	 */
	public synchronized static void close() {
		if (emf == null) return;
		if (emf.isOpen()) {
			emf.close();
		}
		emf = null;
	}

	/**
	 * Gets the emf.
	 *
	 * @return the emf
	 */
	private static EntityManagerFactory getEmf() {
		if (emf == null){
			// Avoids the remote possibility of multiple initialization in
			// a concurrent environment
			// emf field must be volatile for this to work
			// Broken under Java 1.4
			synchronized(Jpa.class) {
				if (emf == null) {  // double-check pattern 
					String name = loadPersistentUnitName();
					emf = Persistence.createEntityManagerFactory(name);
				}
			}
		}
		return emf;
	}

	/**
	 * Load persistent unit name.
	 *
	 * @return the string
	 */
	private static String loadPersistentUnitName() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(Jpa.class.getResourceAsStream("/META-INF/persistence.xml"));

			doc.getDocumentElement().normalize();
			NodeList nl = doc.getElementsByTagName("persistence-unit");
			
			return ((Element)nl.item(0)).getAttribute("name");

		} catch (ParserConfigurationException e1) {
			throw new RuntimeException(e1);
		} catch (SAXException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}

}
