package com.findhotel.dwh.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.findhotel.dwh.misc.CommonFunction;
import com.findhotel.dwh.misc.Constant;
import com.findhotel.dwh.misc.QueryBean;

public class MysqlPersist implements Persistance {

	private Properties prop;
	private Connection conn;
	
	public void init(Properties obj) {
		// TODO Auto-generated method stub
		
		this.prop = obj;
		
	}

	public void initConnection() {
		
		System.out.println(prop.get(Constant.DEST_MYSQL+"."+Constant.DB_URL).toString()+"\t"+
				prop.get(Constant.DEST_MYSQL+"."+Constant.DB_USER).toString()+"\t"+
				prop.get(Constant.DEST_MYSQL+"."+Constant.DB_PASSWORD).toString());
		conn = CommonFunction.getDBConnection(prop.get(Constant.DEST_MYSQL+"."+Constant.DB_URL).toString(),
				prop.get(Constant.DEST_MYSQL+"."+Constant.DB_USER).toString(),
				prop.get(Constant.DEST_MYSQL+"."+Constant.DB_PASSWORD).toString(), null);
		
	}
	
	public void execute(Object ob) {
		// TODO Auto-generated method stub
	
		initConnection();
	
		QueryBean qb = (QueryBean)ob; 
		
		String sql = "LOAD DATA LOCAL INFILE '" + qb.getOutputFile() + "' INTO TABLE " + qb.getOutputDBName() +
				"." + qb.getOutputTableName() + " FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\n'";
		
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.executeUpdate();
			
			System.out.println("Loaded file to Table");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
