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
package uo.ri.cws.application.service.training.report.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

/**
 * The Class FindTrainigByMechanicId.
 */
public class FindTrainigByMechanicId implements Command<List<TrainingForMechanicRow>> {

	/** The cr. */
	private CourseRepository cr=Factory.repository.forCourse();

	/** The mr. */
	private MechanicRepository mr=Factory.repository.forMechanic();

	/** The id. */
	String id;

	/**
	 * Instantiates a new find trainig by mechanic id.
	 *
	 * @param id the id
	 */
	public FindTrainigByMechanicId(String id) {
		this.id=id;
	}

	/**
	 * Execute.
	 *
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<TrainingForMechanicRow> execute() throws BusinessException {

		/**
		 * Primero comprobar que el mecánico existe
		 */
		Optional<Mechanic> om=mr.findById(id);
		BusinessCheck.isTrue(om.isPresent(),"This mechanic does not exist");

		/**
		 * Lista de objetos que retorna el repositorio.
		 * Cada objeto es un vector.
		 */
		List<?> lista=cr.findTrainingByMechanicId(id);

		/**
		 * Lista de filas que se le pasa a la interfaz de usuario para que imprima los resultados.
		 */
		List<TrainingForMechanicRow> rows=new ArrayList<TrainingForMechanicRow>();

		//Para cada objeto en la lista (que en realidad es un vector)
		for (Object o : lista) {

			//Se crea una fila
			TrainingForMechanicRow row=new TrainingForMechanicRow();

			//Se le añaden los datos (hay que hacer cast a vector de Objects)
			row.attendedHours=Math.round(((Long)((Object[])o)[0]));
			row.enrolledHours=Math.round(((Long)((Object[])o)[1]));
			row.vehicleTypeName=(String) ((Object[])o)[2];

			//Se añade la fila a la lista de filas que se le pasará a la ui.
			rows.add(row);
		}

		return rows;
	}

}
