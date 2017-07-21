package com.demoaut.newtours.farmework.jenkins;

import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class JenkinsRESTClient 
{
	private String schema;
	private String hostname;
	private int port;
	private String user;
	private String password;

	private String baseUrl;
	private CloseableHttpClient httpClient;
	
	public JenkinsRESTClient(String schema, String hostName, int port, String user, String password) 
	{
		this.schema = schema !=null?schema:"http";
		this.hostname = hostName;
		this.port = port;
		this.user = user;
		this.password = password;
		this.baseUrl = this.schema + ":\\\\" + hostName + ":" + port;
 	}
	
	private String getBaseUrl()
	{
		return baseUrl;
	}
	
	public void postXMLData(String endpoint, String data) throws IOException
	{
		String uri = getBaseUrl() + endpoint;

		System.out.println("Posting xml data to: " + uri);

		CloseableHttpResponse response;

		HttpPost post = new HttpPost(uri);

		post.setHeader("Content-type", "text/xml; charset=ISO-8859-1");
		post.setEntity(new StringEntity(data));

		response = getHttpClient().execute(post);

		System.out.println("Request status line: " + response.getStatusLine());

		try
		{
			response.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public CloseableHttpClient getHttpClient()
	{
		if(httpClient == null)
		{
			HttpClientBuilder builder = HttpClients.custom();

			/*if(schema.equals("https"))
			{
				SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
				SSLConnectionSocketFactory sslConnectionSocketFactory = null;
				try
				{
					sslContextBuilder.loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new TrustSelfSignedStrategy());
					sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				builder.setSSLSocketFactory(sslConnectionSocketFactory);
			}*/

			if(user != null && password != null)
			{
				CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
				credentialsProvider.setCredentials(new AuthScope(hostname, 8443), new UsernamePasswordCredentials(user, password));

				builder.setDefaultCredentialsProvider(credentialsProvider);
			}

			httpClient = builder.build();
		}

		return httpClient;
	}
}
