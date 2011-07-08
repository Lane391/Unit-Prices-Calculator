package com.hyanwang.methods;

import java.math.BigDecimal;


/**
 * PROJECT NAME: UPCal
 * 
 * @version Jun 18, 2011 2:18:40 AM
 * 
 * 
 * @author kut@an gmail: robinwong51@gmail.com blog:
 *         http://kutanworld.tumblr.com/
 * @date Jun 18, 2011
 * 
 * @comment:
 * 
 */

public class CalClass implements CalInterface {

	@Override
	public String untilpkString(double totalpricesvalue, double netvalue) {
		if (totalpricesvalue == 0 | netvalue == 0) {
			return "都为零了，那还有什么意义呢？";
		}else {
			BigDecimal upcal = new BigDecimal(totalpricesvalue / netvalue);

			return String.valueOf(upcal.setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		
		
	}

}
