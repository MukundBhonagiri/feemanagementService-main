package com.wrightapps.smartedu.feemanagementservice.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class GraphqlConnector {
	
	@Value("${microservices.ps.getStudents.endpoint}")
    private String personServiceUrl;
	
    private static final Logger LOG = LoggerFactory.getLogger(GraphqlConnector.class);
    
    
    public String fetchCurrentAssignment(StringEntity requestObj) {
    	
    	String responseObj = null;
        CloseableHttpClient client= null;
        CloseableHttpResponse response= null;

        client= HttpClients.createDefault();
        HttpPost httpPost= new HttpPost(personServiceUrl);


       // httpPost.addHeader("Authorization","Bearer myToken");
        httpPost.addHeader("Accept","application/json");

        

        try {
           // StringEntity entity= new StringEntity(jsonObj.toString());

            httpPost.setEntity(requestObj);
            response= client.execute(httpPost);

        }

        catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch(ClientProtocolException e){
            e.printStackTrace();
        }
        catch(IOException e){
             e.printStackTrace();
        }

        try{
            BufferedReader reader= new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line= null;
            StringBuilder builder= new StringBuilder();
            while((line=reader.readLine())!= null){

                builder.append(line);

            }
            responseObj = builder.toString();
            LOG.info("Response from person service "+responseObj );
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return responseObj;
    }


	

}
