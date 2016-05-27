package com.mindtree.essence.reports;

import java.util.concurrent.TimeUnit;

public class MilliSecondsToHMS {

	public static String TimeConvert(long takeTime)
	{ 
		  long millis=0;
		  millis= takeTime;
		    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
		            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
		            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
		    return hms;
		  	}
}
