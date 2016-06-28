package com.findhotel.dwh.query;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.findhotel.dwh.misc.CommonFunction;
import com.findhotel.dwh.misc.Constant;
import com.findhotel.dwh.misc.QueryBean;

public class QueryFactory {

	public static List<QueryBuilder> getQueryBuilder(String queryFileAbsolutePath) {
		
		QueryBean queryBean = new QueryBean();
		QueryBuilder queryBuilder = new QueryProcessor();
		List<QueryBuilder> builder = new ArrayList<QueryBuilder>();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(queryFileAbsolutePath));
			String s = null;
			
			while((s=reader.readLine())!=null) {
				
				queryBean.setOutputDBName(s.split("\\.")[0]);
				queryBean.setOutputTableName(s.split("\\.")[1]);
				
				String outputType = reader.readLine();
				if(outputType.trim().equalsIgnoreCase(Constant.DEST_MYSQL))
					queryBean.setOutputType(Constant.DEST_MYSQL);
				
				queryBean.setQuery(reader.readLine().trim());
				
				queryBean.setTempDirectory(CommonFunction.prop.getProperty(Constant.TEMP_PATH));
				
				queryBuilder.init(CommonFunction.prop);
				queryBuilder.setQuery(queryBean);
				
				builder.add(queryBuilder);

				queryBean = new QueryBean();
				queryBuilder = new QueryProcessor();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
		
		return builder;
		
	}
	
}
