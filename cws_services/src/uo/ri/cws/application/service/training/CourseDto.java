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
package uo.ri.cws.application.service.training;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class CourseDto.
 */
public class CourseDto {

	/** The id. */
	public String id;
	
	/** The version. */
	public long version;
	
	/** The code. */
	public String code;
	
	/** The name. */
	public String name;
	
	/** The description. */
	public String description;
	
	/** The start date. */
	public Date startDate;
	
	/** The end date. */
	public Date endDate;
	
	/** The hours. */
	public int hours;
	
	/** A map of the form: 	Vehicle type id -> percentage 	 asd-234-sdc -> 25% 	 fcd-346-tyc -> 25% 	 cdy-469-ycf -> 50%. */
	public Map<String, Integer> percentages = new HashMap<>();
}
