package pe.almc.sbs.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.sbs.bean.SBSTasaRequest;
import pe.almc.sbs.service.SbsWebClientService;
import pe.almc.util.http.SBSParametros;
import pe.almc.util.parser.SBSHtmlParser;


@Service
public class SBSWebClientServiceImpl implements SbsWebClientService  {

	private static Logger logger = LoggerFactory.getLogger(SBSWebClientServiceImpl.class);
				
	public SBSWebClientServiceImpl(){
		
	}
	
	/**
	 * Devuelve todo el contenido de la pagina
	 * @return
	 * @throws MalformedURLException 
	 * @throws IOException
	 */
	private String enviarPeticionGet(String direccion) throws MalformedURLException{
		BufferedReader in = null;
		String inputLine = null;
		StringBuffer inputText = new StringBuffer();
		URL urlSBS = new URL(direccion);				
		HttpURLConnection urlConnection = null;
		try{
			urlConnection = (HttpURLConnection)urlSBS.openConnection();
			String cookie = "__utma=197714925.1040027574.1379857726.1380986399.1380988537.12; __utmz=197714925.1380483868.6.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); ASP.NET_SessionId=tjnr1f3bosfg4cqhxxved345; __utmc=197714925; __utmb=197714925.5.10.1380988537";
			urlConnection.setRequestProperty("Cookie", cookie);
			urlConnection.setRequestProperty("Connection", "keep-alive");
			
			in = new BufferedReader(new InputStreamReader(urlSBS.openStream()));
				
			while( ( inputLine = in.readLine() )!= null ){
					inputText.append(inputLine);
			}		
			
		}catch (IOException e) {
			logger.error("Error en peticion GET a URL " + direccion +": ", e);
		}finally{
			if(in != null) {
				try {
					in.close();
				}catch (IOException e) {
					logger.error("Error:", e);
				}
			}
			if(urlConnection !=null) {
				urlConnection.disconnect();
			}
		}
		
		return inputText.length()!=0 ? inputText.toString() : null;
	}
	
	
	private String enviarPeticionPost(String direccion, Map<String, String> parametros) throws MalformedURLException{
		String resp = null;
		StringBuffer inputText = new StringBuffer();
		HttpURLConnection urlConnection = null;
		URL urlSBS = new URL(direccion);
		BufferedReader in = null;
        String cookie = "__utma=197714925.1040027574.1379857726.1380986399.1380988537.12; __utmz=197714925.1380483868.6.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); ASP.NET_SessionId=tjnr1f3bosfg4cqhxxved345; __utmc=197714925; __utmb=197714925.5.10.1380988537";       	   


		try {
			urlConnection = (HttpURLConnection)urlSBS.openConnection();
			urlConnection.setUseCaches(false);
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");				
			urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:23.0) Gecko/20100101 Firefox/23.0");
			urlConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			urlConnection.setRequestProperty("Accept-Language", "es-MX,es-ES;q=0.8,es-AR;q=0.7,es;q=0.5,en-US;q=0.3,en;q=0.2");
			urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			urlConnection.setRequestProperty("Referer", "	http://www.sbs.gob.pe/app/retasas/paginas/retasasInicio.aspx");
			urlConnection.setRequestProperty("Cookie", cookie);
			urlConnection.setRequestProperty("Connection", "keep-alive");

			//Enviando la peticion
			OutputStream outputStream = urlConnection.getOutputStream();
			StringBuffer urlParameters = new StringBuffer();

			if( parametros != null ){
				Iterator<String> it = parametros.keySet().iterator();
				String key = null;
				while( it.hasNext() ){
					urlParameters.append( urlParameters.length() > 0 ? "&":"");
					key = it.next();
					urlParameters.append(key).append("=").append(parametros.get(key));
				}
				
				outputStream.write(urlParameters.toString().getBytes("UTF-8"));
			}
			
			outputStream.flush();
			outputStream.close();
			
			//Recuperando la respuesta
			in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()) );
			String inputLine = null;		

			while( ( inputLine = in.readLine() )!= null ){
				inputText.append(inputLine);
			}

		} catch (IOException e) {
			logger.error("Error en peticion POST a URL " + direccion +": ", e);
		} finally{
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("Error:", e);
				}
			}
			if(urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		
		resp = inputText.toString(); 
		
		return resp;
	}
	
	private Map<String, String> recuperarItemsRegion(){
		String contenido = null;
		String inicioRegion = "RadDepartamento_DropDown";
		String finRegion = "</ul></div>";
		Map<String, String> lstRegion = new TreeMap<String, String>();
		
		String inicioJSRegion = "\"_uniqueId\":\"RadDepartamento\"";
		String finJSRegion = ",\"localization\"";
				
		try {
			contenido = enviarPeticionGet(SBSParametros.LINK_TEA);
		} catch (MalformedURLException e) {
			logger.error("Error al recuperar regiones:", e);
		}
		
		if( contenido != null ){
			
			//Analiza parte html
			int indexRegion = contenido.indexOf(inicioRegion);
			String temp = contenido.substring(indexRegion);
			int endIndexRegion = temp.indexOf(finRegion);			
			temp = temp.substring(0, endIndexRegion);
			 
			String[] htmlTempArray = temp.split("<li class=\"rcbItem \">");
			String[] htmlArray = new String[htmlTempArray.length-2]; 
			for( int i=2; i<htmlTempArray.length; i++ ){
				htmlArray[i-2] =  htmlTempArray[i].replace("</li>", "");
			}
			
			//Analiza parte json (javascript)
			int indexJSRegion = contenido.indexOf( inicioJSRegion );
			indexJSRegion = contenido.indexOf("\"itemData\"", indexJSRegion);
			int endJSRegion = contenido.indexOf(finJSRegion, indexJSRegion);
			temp = contenido.substring(indexJSRegion, endJSRegion);
			String[] jsonTempArray = temp.split("\\},\\{");
			String[] jsonArray = new String[jsonTempArray.length-1];				
			for(int i=1, j=0; i<jsonTempArray.length; i++){
				if( jsonTempArray[i].startsWith("\"value\"") ){
					jsonArray[j] = jsonTempArray[i].replace("\"", "").replace(",selected:true", "").replace("}]", "").replace(":", "");
					jsonArray[j] = jsonArray[j].replace("value", ""); 
					j++;
				}			
			}
						
			for(int i=0; i<jsonArray.length; i++){
				lstRegion.put(jsonArray[i], htmlArray[i]);
			}
			
		}
		
		return lstRegion.size() > 0 ? lstRegion : null;
	}
	
	 
	
	/**
	 * Devuelve todo el contenido de la pagina
	 * @return
	 * @throws IOException
	 */
	private Map<String,String> recuperarItemsAjax(String callBackId, String codigoItem){
		Map<String,String> resp = null;
		String inputText = null;
		resp = new TreeMap<String,String>();
		
		final String strDepartamento = "LIMA";
				
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("ctl02_TSM", "ctl02_TSM=%3B%3BSystem.Web.Extensions%2C%20Version%3D3.5.0.0%2C%20Culture%3Dneutral%2C%20PublicKeyToken%3D31bf3856ad364e35%3Aes-PE%3A3de828f0-5e0d-4c7d-a36b-56a9773c0def%3Aea597d4b%3Ab25378d2%3BTelerik.Web.UI%2C%20Version%3D2012.2.724.35%2C%20Culture%3Dneutral%2C%20PublicKeyToken%3D121fae78165ba3d4%3Aes-PE%3Ac8b048d6-c2e5-465c-b4e7-22b9c95f45c5%3A16e4e7cd%3Af7645509%3A24ee1bba%3Af46195d3%3A2003d0b8%3A1e771326%3Aaa288e2d");
		parametros.put("__EVENTTARGET", "");
		parametros.put("__EVENTARGUMENT", "");
		parametros.put("__VIEWSTATE", "%2FwEPDwULLTE2NzY2OTIyMjFkGAUFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYEBQ9SYWREZXBhcnRhbWVudG8FD1JhZFRpcG9Qcm9kdWN0bwULUmFkUHJvZHVjdG8FDFJhZENvbmRpY2lvbgUMUmFkQ29uZGljaW9uDxQrAAJlZWQFC1JhZFByb2R1Y3RvDxQrAAJlZWQFD1JhZFRpcG9Qcm9kdWN0bw8UKwACZWVkBQ9SYWREZXBhcnRhbWVudG8PFCsAAgUETElNQQUCMTVk");
		parametros.put("__SCROLLPOSITIONX", "0");
		parametros.put("__SCROLLPOSITIONY", "0");
		parametros.put("RadDepartamento", strDepartamento);
		parametros.put("RadDepartamento_ClientState", "");
		parametros.put("RadTipoProducto", "");
		parametros.put("RadTipoProducto_ClientState", "");
		parametros.put("RadProducto", "");
		parametros.put("RadProducto_ClientState", "");
		parametros.put("RadCondicion", "");
		parametros.put("RadCondicion_ClientState", "");			
		parametros.put("__CALLBACKID", callBackId);
		parametros.put("__CALLBACKPARAM", "%7B%22Command%22%3A%22LOD%22%2C%22Text%22%3A%22" + codigoItem + "%22%2C%22ClientState%22%3A%7B%22value%22%3A%22%22%2C%22text%22%3A%22%22%2C%22enabled%22%3Atrue%2C%22logEntries%22%3A%5B%5D%2C%22checkedIndices%22%3A%5B%5D%2C%22checkedItemsTextOverflows%22%3Afalse%7D%2C%22Context%22%3A%7B%22Text%22%3A%22" + 
				codigoItem + "%22%2C%22NumberOfItems%22%3A0%7D%2C%22NumberOfItems%22%3A0%7D");
			
		try {
			inputText = enviarPeticionPost(SBSParametros.LINK_TEA, parametros);
		} catch (MalformedURLException e) {
			logger.error("Error al recuperar items Ajax:", e);
		}
			
		if( inputText != null ){	
			//Analizar respuesta de peticion
			String strMitad = "_\\$\\$_";			
			
			String parts[] = inputText.length() > 0 ? inputText.split(strMitad) : null;
			
			if( parts!=null && parts.length > 2 ){
				String jsonPart = parts[0];
				String htmlPart = parts[1];
							
				//Analizando parte de json				
				String[] jsonArray = formatJsonPart(jsonPart);
				
				//Analizando parte de html				
				String[] htmlArray = formatHtmlPart(htmlPart);
								
				for(int i=0; i<jsonArray.length; i++ ){
					if(jsonArray[i] != null){
						resp.put(jsonArray[i], htmlArray[i]);
					}
				}											
			}
		}
		
		return resp.size()>0 ? resp : null;
	}
	
	/**
	 * Formatea la informacion de json
	 * @param jsonPart
	 * @return
	 */
	private String[] formatJsonPart(String jsonPart){
		String[] jsonArray = null;
		
		//Analizando parte de json
		String[] jsonTempArray = jsonPart.split(",");
		jsonArray = new String[jsonTempArray.length-1];
		
		for(int i=1, j=0; i<jsonTempArray.length; i++){
			if( jsonTempArray[i].contains("\"value\"") ){
				jsonArray[j] = jsonTempArray[i].replace("\"", "").replace("value", "").replace("}]", "").replace(":", "");
				jsonArray[j] = jsonArray[j].replace("{", "").replace("}", ""); 
				j++;
			}			
		}
		
		return jsonArray;
	}

	/**
	 * Formatea la informacion de html
	 * @param jsonPart
	 * @return
	 */
	private String[] formatHtmlPart(String htmlPart){
		String[] htmlArray = null;
		
		//Analizando parte de json
		String[] htmlTempArray = htmlPart.split("rcbItem");
		htmlArray = new String[htmlTempArray.length-2];
		
		for(int i=2; i<htmlTempArray.length; i++){
			htmlArray[i-2] = htmlTempArray[i].replace("</li><li class=\"", "").replace("\">", "").replace("</li>", "").trim();
		}
		
		return htmlArray;
	}

	
	/* (non-Javadoc)
	 * @see pe.almc.util.http.SbsWebClientService#recuperarRegion()
	 */
	@Override
	public Map<String,String> recuperarRegion(){
		Map<String,String> resp = null;			
		resp = recuperarItemsRegion();		
		return resp; 
	}

	/* (non-Javadoc)
	 * @see pe.almc.util.http.SbsWebClientService#recuperarTiposOperacion(java.lang.String)
	 */
	@Override
	public Map<String,String> recuperarTiposOperacion( String codRegion ){
		Map<String,String> resp = null;			
		resp = recuperarItemsAjax( "RadTipoProducto", codRegion );		
		return resp; 
	}

	/* (non-Javadoc)
	 * @see pe.almc.util.http.SbsWebClientService#recuperarProductos(java.lang.String)
	 */
	@Override
	public Map<String,String> recuperarProductos( String codTipoProducto ){
		Map<String,String> resp = null;			
		resp = recuperarItemsAjax( "RadProducto", codTipoProducto );		
		return resp;  
	}

	/* (non-Javadoc)
	 * @see pe.almc.util.http.SbsWebClientService#recuperarCondiciones(java.lang.String)
	 */
	@Override
	public Map<String,String> recuperarCondiciones( String codProducto ){
		Map<String,String> resp = null;			
		resp = recuperarItemsAjax( "RadCondicion", codProducto );		
		return resp;  
	}
	
	/* (non-Javadoc)
	 * @see pe.almc.util.http.SbsWebClientService#consultarTasas(pe.almc.sbs.bean.SBSTasaRequest)
	 */
	@Override
	public List<InfoTasaDiaria> consultarTasas(SBSTasaRequest a){
		
		Map<String, String> param = new HashMap<String, String>();
		SBSHtmlParser sbsHtmlParser = new SBSHtmlParser(); 			
		
		param.put("txt_condicion", a.getTxtCondicion());
		param.put("txt_departamento", a.getTxtDepartamento());
		param.put("txt_producto", a.getTxtProducto());				
		param.put("txt_tip_producto", a.getTxtTipoProducto());
		param.put("as_condicion", a.getAsCondicion());
		param.put("as_dpto", a.getAsDpto());
		
		List<InfoTasaDiaria> resp = null;
		String htmlResponse = "";
		try {
			htmlResponse = enviarPeticionPost(SBSParametros.LINK_TASA_TEA, param);
			logger.info( htmlResponse );
			resp = sbsHtmlParser.parserFilaTasa(htmlResponse);
		} catch (IOException e) {
			logger.error("Error al enviar peticion post", e);
		}
		
		return resp;
		
	}

}
