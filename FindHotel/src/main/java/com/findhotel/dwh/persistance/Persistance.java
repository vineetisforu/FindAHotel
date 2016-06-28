package com.findhotel.dwh.persistance;

import java.util.Properties;

public interface Persistance {

	public void init(Properties obj);
	public void execute(Object ob);
}
