package com.findhotel.dwh;

import java.util.List;

import com.findhotel.dwh.misc.CommonFunction;
import com.findhotel.dwh.misc.Constant;
import com.findhotel.dwh.persistance.PersistanceFactory;
import com.findhotel.dwh.query.QueryBuilder;
import com.findhotel.dwh.query.QueryFactory;

public class Invoker {

	public static void main(String[] args) {
		
		//Initializing the Properties
		CommonFunction common = new CommonFunction();
		
		//Creating QueryBuilder Object
		List<QueryBuilder>  qbuilder = QueryFactory.getQueryBuilder(CommonFunction.prop.getProperty(Constant.SOURCE_QUERY_FILE));
		
		//Setting Storage/Persistance Type to QueryBuilder
		PersistanceFactory.setPersistanceType(qbuilder);
		
		
		for(QueryBuilder qb : qbuilder) {
			
			qb.execute();
			
		}
		
		
		
	}
}
