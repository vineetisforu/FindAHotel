package com.findhotel.dwh.misc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesLoader {

	public static Properties getProperties() {
		
		try {
			Properties properties = new Properties();
			URL url = ClassLoader.getSystemResource(Constant.PROPERTIES_FILE);
			properties.load(url.openStream());
			return properties;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
