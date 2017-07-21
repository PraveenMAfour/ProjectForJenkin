package com.demoaut.newtours.utilities.jenkins;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.demoaut.newtours.farmework.jenkins.JenkinsRESTClient;

public class SuitesManagementTask 
{
	public static String currentDir = System.getProperty("user.dir");
	
	@Test
	public void createRegressionSuite()
	{
		try
		{
			String suiteConfigFilePath = currentDir+ "\\src\\com\\demoaut\\newtours\\resources\\suite1.json";
			
			String content = getResourceContent(suiteConfigFilePath);
			
			JSONObject suiteConfiguration = new JSONObject(content);
			
			String suiteName = suiteConfiguration.getString("name");
			
			JenkinsRESTClient client = new JenkinsRESTClient("http", "localhost", 8080, null, null);

			String suiteViewTemplate = getResourceContent(currentDir+ "\\src\\com\\demoaut\\newtours\\resources\\view_config_template.xml");

			String suiteViewConfig = suiteViewTemplate
					.replace("[view_name]", suiteName)
					.replace("[include_jobs_regex]", suiteName + ".*");
			
			client.postXMLData(getCreateViewEndpoint(suiteName), suiteViewConfig);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getResourceContent(String name)
	{
		
		String content = "";

		try
		{
			File file = new File(name);
			content = FileUtils.readFileToString(file);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Could not read the content of the file '" + name + "'.");
		}

		return content;
	}
	
	public static String getCreateViewEndpoint(String jobName)
	{
		String encodedJobName;
		try
		{
			encodedJobName = URLEncoder.encode(jobName, "UTF-8").replaceAll("\\+", "%20");
		}
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Can not connect to Jenkins. See the Exception stacktrace in the console output.");
		}

		return "\\createView?name=" + encodedJobName;
	}
}
