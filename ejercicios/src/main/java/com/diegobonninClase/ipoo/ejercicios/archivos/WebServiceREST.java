package com.diegobonninClase.ipoo.ejercicios.archivos;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class WebServiceREST {

	public WebServiceREST(){
		
	}
	
	public String ejecutar(String url, String parametros){
		
		String contentType = "application/json";
		
		HttpURLConnection con = null;
		InputStreamReader r = null;
		OutputStreamWriter w = null;
	
		StringBuilder sb = new StringBuilder();
	
		try {
			
			con = (HttpURLConnection) new URL(url).openConnection();
			
			con.setConnectTimeout(30000);
			con.setReadTimeout(120000);
	
			con.setRequestProperty("Content-Type", contentType);
			con.setRequestProperty("Accept", contentType);
			
			if(parametros!=null){
	
				con.setDoOutput(true);
				w = new OutputStreamWriter(new BufferedOutputStream(con.getOutputStream()), "UTF-8");
				w.write(parametros);
				w.flush();
				w.close();
				
			}
			
			System.out.println("status: " + con.getResponseCode());
	
			r = new InputStreamReader(con.getInputStream());
	
			int c;
			while ((c = r.read()) != -1) sb.append((char) c);
	
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(con!=null) con.disconnect();
		}
		
		return sb.toString();
		
	}
	
	
}
