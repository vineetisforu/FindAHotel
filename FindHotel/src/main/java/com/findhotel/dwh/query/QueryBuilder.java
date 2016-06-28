package com.findhotel.dwh.query;

import java.util.Properties;

import com.findhotel.dwh.misc.QueryBean;
import com.findhotel.dwh.persistance.Persistance;

public interface QueryBuilder {

	public void init(Properties prop);
	public void setQuery(QueryBean queryBean);
	public void setPersistance(Persistance persist);
	public void execute();
	
	
}
