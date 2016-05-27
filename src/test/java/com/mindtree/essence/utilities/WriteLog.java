/**
 * This class uses apache logger to log the information. 
 */

package com.mindtree.essence.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author M1027330
 *
 */



public class WriteLog {
	public String logfile = "conf/log4j.properties";
	public Logger logger = Logger.getLogger("Essence");

	{
		try {

			PropertyConfigurator.configure(logfile);

			getLogger();

		} catch (Exception e) {
			logger.error("Error:" + e.getMessage());
		}
	}

	public org.apache.log4j.Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger("Essence");

		}
		return logger;
	}

}
