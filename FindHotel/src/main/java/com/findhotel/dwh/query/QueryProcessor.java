package com.findhotel.dwh.query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import com.findhotel.dwh.misc.CommonFunction;
import com.findhotel.dwh.misc.Constant;
import com.findhotel.dwh.misc.QueryBean;
import com.findhotel.dwh.persistance.Persistance;

public class QueryProcessor implements QueryBuilder{

	private Properties prop;
	private QueryBean queryBean;
	private Persistance persistance;
	private Connection conn; 
	
	
	public void init(Properties prop) {
		// TODO Auto-generated method stub
		this.prop = prop;
	}

	
	public void setQuery(QueryBean queryBean) {
		// TODO Auto-generated method stub
		this.queryBean = queryBean;
	}
	
	public QueryBean getQuery() {
		
		return this.queryBean;
	}
	
	public void setPersistance(Persistance persistance) {
		// TODO Auto-generated method stub
		this.persistance = persistance;
	}

	public void execute() {
		// TODO Auto-generated method stub
		
		long counter = 0;
		BufferedWriter writer = null;
		
		System.out.println("Making Connection. . .");
		Connection conn = CommonFunction.getDBConnection(prop.get(Constant.DB_URL).toString(), prop.get(Constant.DB_USER).toString(), prop.get(Constant.DB_PASSWORD).toString(), null);
		
		System.out.println("Done With Connection, Firing Query. . .");
		
		try {
			
			File file = new File(queryBean.getTempDirectory(),queryBean.getOutputDBName()+"."+queryBean.getOutputTableName());
			writer = new BufferedWriter(new FileWriter(file));
		
			PreparedStatement prep = conn.prepareStatement(queryBean.getQuery());
			ResultSet res = prep.executeQuery();
			
			int count = res.getMetaData().getColumnCount();
			
			System.out.println("Query executed ");
			
			while(res.next()) {
				
				StringBuffer buffer = new StringBuffer();
				
				for(int i=0;i<count; i++) {
					
					buffer.append("\""+res.getString(i+1)+"\",");
				}
				
				writer.write(buffer.toString().substring(0,buffer.length()-1)+"\n");
				
				if(counter++%1000==0) {
					System.out.println("Writing " + counter + " records to File " + file.getAbsolutePath());
					writer.flush();
					
				}
				
			}
			
			queryBean.setOutputFile(file.getAbsolutePath());
			
			writer.flush();
			System.out.println("Wrote " + counter  +" record.");
			
			persistance.execute(queryBean);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
