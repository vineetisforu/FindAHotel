package com.findhotel.dwh.misc;

public class QueryBean {

	private String query;
	private String outputType;
	private String tempDirectory;
	private String outputFile;
	private String outputTableName;
	private String outputDBName;
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	public String getTempDirectory() {
		return tempDirectory;
	}
	public void setTempDirectory(String tempDirectory) {
		this.tempDirectory = tempDirectory;
	}
	
	
	public String getOutputTableName() {
		return outputTableName;
	}
	public void setOutputTableName(String outputTableName) {
		this.outputTableName = outputTableName;
	}
	public String getOutputDBName() {
		return outputDBName;
	}
	public void setOutputDBName(String outputDBName) {
		this.outputDBName = outputDBName;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
	
}
