package com.findhotel.dwh.persistance;

import java.util.List;

import com.findhotel.dwh.misc.CommonFunction;
import com.findhotel.dwh.misc.Constant;
import com.findhotel.dwh.misc.QueryBean;
import com.findhotel.dwh.query.QueryBuilder;
import com.findhotel.dwh.query.QueryProcessor;

public class PersistanceFactory {

	public static void setPersistanceType(List<QueryBuilder> qbuilder) {
		
		
		for(QueryBuilder  qbuild : qbuilder) {
			
			QueryBean qbean = ((QueryProcessor)qbuild).getQuery();
			Persistance persistance = null;
			
			if(qbean.getOutputType().equals(Constant.DEST_MYSQL)) {
				persistance = new MysqlPersist();
				persistance.init(CommonFunction.prop);
			}
			
			qbuild.setPersistance(persistance);
			
		}
		
	}
	
}
