package com.demoaut.newtours.farmework.jenkins;

public class JenkinsRESTClient 
{
	private String schema;
	private String hostname;
	private int port;
	private String user;
	private String password;

	private String baseUrl;
	
	public JenkinsRESTClient(String schema, String hostName, int port, String user, String password) 
	{
		this.schema = schema !=null?schema:"http";
		this.hostname = hostName;
		this.port = port;
		this.user = user;
		this.password = password;
		this.baseUrl = this.schema + ":\\" + hostName + ":" + port;
 	}
}
