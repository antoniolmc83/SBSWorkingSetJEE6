package pe.almc.util.parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.almc.sbs.bean.InfoTasaDiaria;
import pe.almc.util.http.SBSParametros;

public class SBSHtmlParser {
	
	private static Logger logger = LoggerFactory.getLogger(SBSHtmlParser.class);
	
	public static void main(String args[]){
		
		SBSHtmlParser s = new SBSHtmlParser();
		s.parserFilaTasa("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head id=\"Head1\"><title></title></head><script type=\"text/javascript\" src=\"../Scripts/jquery-latest.js\"></script> <script type=\"text/javascript\" src=\"../Scripts/jquery.tablesorter.js\"></script> <link rel=\"stylesheet\" href=\"../css/jq.css\" type=\"text/css\" media=\"print, projection, screen\" /><script language=\"JavaScript\">    window.onbeforeprint = removeelements    window.onafterprint = revertback</script><body><script>    $(document).ready(function () {        $(\"#myTable\").tablesorter();    }); </script><style type=\"text/css\">        .black_overlay{            display: hide;            position: absolute;            top: 0%;            left: 0%;            width: 100%;            height: 100%;            background-color: black;            z-index:1001;            -moz-opacity: 0.8;            opacity:.80;            filter: alpha(opacity=80);        }        .white_content {            display: hide;            position: absolute;            top: 25%;            left: 25%;            width: 300px;            height: 250px;            padding: 16px;            border: 16px solid orange;            background-color: white;            z-index:1002;            overflow: auto;        }table.leyenda {	font-family:\"Segoe UI\", Arial, sans-serif;	background-color: #ffffff;	font-size: 7pt;	text-align: left;	margin:0px 0pt 15px;}       table.chart {	border-width: 0px;	border-spacing: 1px;	border-style: solid;	border-color: #cccccc;	border-collapse: separate;	background-color: white;}table.chart th {	border-width: 1px;	padding: 1px;	border-style: solid;	border-color: #cccccc;	background-color: white;	-moz-border-radius: ;}table.chart td {	border-width: 1px;	padding: 1px;	border-style: solid;	border-color: #cccccc;	background-color: white;	-moz-border-radius: ;}    table.tablehide {	font-family:\"Segoe UI\", Arial, sans-serif;	background-color: #CDCDCD;	margin:10px 0pt 0px;	font-size: 8pt;	text-align: left;}table.tablesorter {	font-family:\"Segoe UI\", Arial, sans-serif;	background-color: #CDCDCD;	margin:10px 0pt 0px;	font-size: 8pt;	text-align: left;}table.tablesorter thead tr th, table.tablesorter tfoot tr th {	background-color: #168acb;	color: #ffffff;	border: 1px solid #FFF;	font-size: 8pt;	padding: 4px;}table.tablesorter thead tr .header {	background-image: url(bg.gif);	background-repeat: no-repeat;	background-position: center right;	cursor: pointer;}table.tablesorter tbody td {	color: #3D3D3D;	padding: 4px;	background-color: #FFF;	vertical-align: top;}table.tablesorter tbody tr.odd td {	background-color:#F0F0F6;}table.tablesorter thead tr .headerSortUp {	background-image: url(asc.gif);}table.tablesorter thead tr .headerSortDown {	background-image: url(desc.gif);}table.tablesorter thead tr .headerSortDown, table.tablesorter thead tr .headerSortUp {background-color: #0d608e;}</style><style type=\"text/css\" media=\"print\">@media print {#remove {display:none;}}</style><script>    function imprime() {        removeelements();        window.print();        revertback();    }    function removeelements() {        //var remove_el = document.all.remove        var remove_el = document.getElementById('remove');        alert(remove_el);        if (remove_el != '' && remove_el.length == null)            remove_el.style.display = 'none'        else {            for (i = 0; i < remove_el.length; i++)                remove_el[i].style.display = 'none'        }        //var tablehide_el = document.all.tablehide        var tablehide_el = document.getElementById('tablehide');        if (tablehide_el != '' && tablehide_el.length == null)            tablehide_el.style.display = ''        else {            for (i = 0; i < tablehide_el.length; i++)                tablehide_el[i].style.display = ''        }    }    function revertback() {        //var remove_el = document.all.remove        var remove_el = document.getElementById('remove');        if (remove_el != '' && remove_el.length == null)            remove_el.style.display = ''        else {            for (i = 0; i < remove_el.length; i++)                remove_el[i].style.display = ''        }        //var tablehide_el = document.all.tablehide        var tablehide_el = document.getElementById('tablehide');        if (tablehide_el != '' && tablehide_el.length == null)            tablehide_el.style.display = 'none'        else {            for (i = 0; i < tablehide_el.length; i++)                tablehide_el[i].style.display = 'none'        }    }</script><form name=\"form1\" method=\"post\" action=\"RetasasResultados.aspx\" id=\"form1\"><div><input type=\"hidden\" name=\"__VIEWSTATE\" id=\"__VIEWSTATE\" value=\"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\" /></div><script type=\"text/javascript\">//<![CDATA[var theForm = document.forms['form1'];if (!theForm) {    theForm = document.form1;}function __doPostBack(eventTarget, eventArgument) {    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {        theForm.__EVENTTARGET.value = eventTarget;        theForm.__EVENTARGUMENT.value = eventArgument;        theForm.submit();    }}//]]></script><script src=\"/app/Retasas/WebResource.axd?d=DKfniBMNoAZyrZtTHUpXyw21JMbt_1P7oK5Cobb8BcvZMOMRpWDi0SGauOGv8j4hVt0ckSEtGG_N-mWn7mj1uNFqRjM1&amp;t=634487693315900077\" type=\"text/javascript\"></script><script src=\"/app/Retasas/WebResource.axd?d=9PYXlSU5eumMXew8md_k9x5JZAkiOGfW7NAvLS8NaFMQqMsh_gYGKFQfAmEVih_GaSBFHaLJIHVLUqdQX2AYPhkYLR1vmNgXmOJeiakchWQDjKXfALJdkvuZrrGalTWiOapwJw2&amp;t=634834742480000000\" type=\"text/javascript\"></script><script src=\"/app/Retasas/WebResource.axd?d=McdSWkG0Yq0ymrDziJrhYzlYadJcxLvIu5TYDsAOksTk0h-xNyWmlootzZc4KeoB4vMpjr4OttqatO_GTJMBKZ1XLL2clmXXzhz_XsX2v0_NMgEuCQePgVan6XOlr53n9tFDXw2&amp;t=634834742480000000\" type=\"text/javascript\"></script><script src=\"/app/Retasas/WebResource.axd?d=8HDAIwylMEmBNTyoNrDIsU5YF2E8tkhkzloaP5lZ0sbS7whY5Ao86i_TBHoXuKztlEvXXRlKtgqwXzPdB1UwGsj7Rg0FaMhMTXUZStNsrDv7mUH5Esyg0q78YAu9noFXoRHz_g2&amp;t=634834742480000000\" type=\"text/javascript\"></script><script type=\"text/javascript\">//<![CDATA[var charthcVendas;$(document).ready(function() {    Highcharts.setOptions({                        lang: {}});    charthcVendas = new Highcharts.Chart({        chart: {\"renderTo\":\"hcVendas\",\"defaultSeriesType\":\"line\"},        credits: { enabled: false },    plotOptions: { series: {\"dataLabels\":{\"enabled\":true}} },    title: {\"text\":\"\"},        legend: {\"enabled\":true},    exporting: {\"enabled\":false},    xAxis: [{\"categories\":[\"CMAC TACNA\",\"CMAC SULLANA\",\"CMAC AREQUIPA\",\"CMCP LIMA\",\"FINAN. PROEMPRESA\",\"CMAC PISCO\",\"CMAC PIURA\",\"INTERBANK\",\"BANCO DE COMERCIO\",\"MIBANCO\"],\"labels\":{\"align\":\"right\",\"enabled\":true,\"rotation\":-90},\"title\":{\"text\":\"\"}}],    yAxis: [{\"title\":{\"text\":\"TCEA (%)\"}}],    tooltip: {\"formatter\":function(event){ var tmp = '<b>'+ this.series.name + ((typeof(this.point.name) != 'undefined') ? '->'  +this.point.name : '' )+ ' </b><br/>'+ this.x +': '+ this.y; if(typeof(tmp) == 'function'){return tmp(this);}else{ return tmp;} }} ,    series: [{\"name\":\"TCEA\",\"data\":[45.99,48.2,49.36,51.12,54.28,56.54,57.35,57.41,57.56,60.24],\"showInLegend\":false,\"visible\":true}]	});        });//]]></script><div>	<input type=\"hidden\" name=\"__SCROLLPOSITIONX\" id=\"__SCROLLPOSITIONX\" value=\"0\" />	<input type=\"hidden\" name=\"__SCROLLPOSITIONY\" id=\"__SCROLLPOSITIONY\" value=\"0\" />	<input type=\"hidden\" name=\"__EVENTTARGET\" id=\"__EVENTTARGET\" value=\"\" />	<input type=\"hidden\" name=\"__EVENTARGUMENT\" id=\"__EVENTARGUMENT\" value=\"\" /></div>    <div>    <table width=\"490\">            <tr><td >                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"490\">                        <tr><td colspan=\"2\">&nbsp;</td></tr>                        <tr><td><span id=\"LblFecha\">Al 05/10/2013</span></td>                            <td align=right id=\"remove\">                            <input type=\"image\" name=\"ImageButton1\" id=\"ImageButton1\" src=\"../imagenes/ico_excel.gif\" style=\"border-width:0px;\" />                            <A HREF=\"javascript:imprime()\"><img src=\"../imagenes/ico_print.gif\" border=\"0\"></A>                            </td>                       </tr>                    </table>                </td>            </tr>    </table>        <table width=\"490\" class=\"tablehide\" id=\"tablehide\" style=\"display:none\">            <tr><td width=\"150\">DEPARTAMENTO:</td><td width=\"340\"><span id=\"Lbl_dpto\">LIMA</span></td></tr>            <tr><td width=\"150\">TIPO DE PRODUCTO:</td><td  width=\"340\"><span id=\"Lbl_tip_prod\">CREDITOS</span></td></tr>            <tr><td width=\"150\">PRODUCTO:</td><td width=\"340\"><span id=\"Lbl_prod\">ACTIVO FIJO</span></td></tr>            <tr><td width=\"150\">CONDICIONES:</td width=\"340\"><td><span id=\"Lbl_condicion\">PRESTAMO ACTIVO FIJO POR S/. 2 000 A 24 MESES</span></td></tr>        </table><table><tr>    <td>        <table id=\"myTable\" cellspacing=\"1\" class=\"tablesorter\" width=\"350\">         <thead>         <tr>             <th>Entidad</th>             <th>TCEA(*)</th>                             <th>Cuota</th>                     </tr>         </thead>         <tbody> 				                    <tr>                         <td>CMAC TACNA</td>                         <td align=right>45.99 % </td>                                                     <td align=right>120.69</td>                                                 </tr> 					                    <tr>                         <td>CMAC SULLANA</td>                         <td align=right>48.2 % </td>                                                     <td align=right>122.37</td>                                                 </tr> 					                    <tr>                         <td>CMAC AREQUIPA</td>                         <td align=right>49.36 % </td>                                                     <td align=right>123.80</td>                                                 </tr> 					                    <tr>                         <td>CMCP LIMA</td>                         <td align=right>51.12 % </td>                                                     <td align=right>124.56</td>                                                 </tr> 					                    <tr>                         <td>FINAN. PROEMPRESA</td>                         <td align=right>54.28 % </td>                                                     <td align=right>126.95</td>                                                 </tr> 					                    <tr>                         <td>CMAC PISCO</td>                         <td align=right>56.54 % </td>                                                     <td align=right>128.57</td>                                                 </tr> 					                    <tr>                         <td>CMAC PIURA</td>                         <td align=right>57.35 % </td>                                                     <td align=right>129.16</td>                                                 </tr> 					                    <tr>                         <td>INTERBANK</td>                         <td align=right>57.41 % </td>                                                     <td align=right>129.21</td>                                                 </tr> 					                    <tr>                         <td>BANCO DE COMERCIO</td>                         <td align=right>57.56 % </td>                                                     <td align=right>129.36</td>                                                 </tr> 					                    <tr>                         <td>MIBANCO</td>                         <td align=right>60.24 % </td>                                                     <td align=right>131.31</td>                                                 </tr> 					                    <tr>                         <td>CMAC DEL SANTA</td>                         <td align=right>60.67 % </td>                                                     <td align=right>101.56</td>                                                 </tr> 					                    <tr>                         <td>CMAC HUANCAYO</td>                         <td align=right>60.71 % </td>                                                     <td align=right>132.38</td>                                                 </tr> 					                    <tr>                         <td>CRAC CREDINKA</td>                         <td align=right>61.9 % </td>                                                     <td align=right>132.20</td>                                                 </tr> 					                    <tr>                         <td>EDPYME SOLIDARIDAD</td>                         <td align=right>62.52 % </td>                                                     <td align=right>132.88</td>                                                 </tr> 					                    <tr>                         <td>BANCO FINANCIERO</td>                         <td align=right>63.67 % </td>                                                     <td align=right>132.96</td>                                                 </tr> 					                    <tr>                         <td>CRAC PRYMERA</td>                         <td align=right>65.95 % </td>                                                     <td align=right>136.60</td>                                                 </tr> 					                    <tr>                         <td>EDPYME CREDIJET</td>                         <td align=right>66.46 % </td>                                                     <td align=right>136.53</td>                                                 </tr> 					                    <tr>                         <td>CREDISCOTIA</td>                         <td align=right>67.57 % </td>                                                     <td align=right>137.28</td>                                                 </tr> 					                    <tr>                         <td>EDPYME ALTERNATIVA</td>                         <td align=right>67.77 % </td>                                                     <td align=right>136.70</td>                                                 </tr> 					                    <tr>                         <td>EDPYME RAIZ</td>                         <td align=right>68.98 % </td>                                                     <td align=right>138.33</td>                                                 </tr> 					                    <tr>                         <td>EDPYME CREDIVISION</td>                         <td align=right>70.15 % </td>                                                     <td align=right>137.87</td>                                                 </tr> 					                    <tr>                         <td>FINANCIERA TFC S A</td>                         <td align=right>73.2 % </td>                                                     <td align=right>145.56</td>                                                 </tr> 					                    <tr>                         <td>CMAC ICA</td>                         <td align=right>74.59 % </td>                                                     <td align=right>141.43</td>                                                 </tr> 					                    <tr>                         <td>FINANCIERA EFECTIVA</td>                         <td align=right>76.91 % </td>                                                     <td align=right>144.13</td>                                                 </tr> 					                    <tr>                         <td>COMPARTAMOS FINANCIE</td>                         <td align=right>79.38 % </td>                                                     <td align=right>146.15</td>                                                 </tr> 					                    <tr>                         <td>CRAC SENOR DE LUREN</td>                         <td align=right>79.58 % </td>                                                     <td align=right>146.39</td>                                                 </tr> 					                    <tr>                         <td>FINANCIERA EDYFICAR</td>                         <td align=right>80.45 % </td>                                                     <td align=right>147.00</td>                                                 </tr> 					                    <tr>                         <td>CMAC TRUJILLO</td>                         <td align=right>80.67 % </td>                                                     <td align=right>145.68</td>                                                 </tr> 					                    <tr>                         <td>FINANCIERA CONFIANZA</td>                         <td align=right>81.34 % </td>                                                     <td align=right>146.64</td>                                                 </tr> 					            </tbody>         </table>    </td>    <td valign=\"top\"><img src=\"../imagenes/tooltip_credito.gif\" /></td></tr></table> <table class=\"leyenda\"><td>*Se registra la TCEA máxima aplicable al producto.<br />TCEA: Tasa de Costo Efectivo Anual<br />MAS INFORMACIÓN:   <a href=\"http://www.sbs.gob.pe/download/TipoTasa/files/00083_2_15.htm\" target=\"_blank\">Comisiones, Seguros, tasas moratorias y mucho más.</a></td></table>        <table class=\"leyenda\" width=\"470\">               <tr><td>El gráfico muestra información de los 10 primeros registros, acceda <a href=\"RetasasChart.aspx\" target=_blank>aquí (MAS INFORMACIÓN)</a> para ver el gráfico con todos los registros</td></tr>        </table>            <table class=\"chart\">        <tr>        <td width=\"470\">            <div id=\"hcVendas\" style=\"height:400px;width:480px;\"></div>        </td>        </tr>        </table>    </div>    <script type=\"text/javascript\">//<![CDATA[theForm.oldSubmit = theForm.submit;theForm.submit = WebForm_SaveScrollPositionSubmit;theForm.oldOnSubmit = theForm.onsubmit;theForm.onsubmit = WebForm_SaveScrollPositionOnSubmit;//]]></script></form></body></html>");
	}
	
	/**
	 * Recupera la informacion de tasas en cada fila de una html
	 * @param htmlInput
	 * @return
	 */
	public List<InfoTasaDiaria> parserFilaTasa(String htmlInput){
		List<InfoTasaDiaria> resp = null;
		
		//Obteniendo indices de la cabecera
		int iCIndex = htmlInput.indexOf("<thead>");
		int fCIndex = htmlInput.indexOf("</thead>", iCIndex);
		
		//Valida que exista informacion de las columnas
		if( iCIndex == -1 && fCIndex == -1 ) {
			return resp;
		}
		
		String cabTasa = htmlInput.substring(iCIndex, fCIndex);
		Map<Integer, String> cabecera = parcerCabecera(cabTasa);
		
		logger.info("%%%%%%"+ cabecera+"%%%%%%");
		
		//Obteniendo indices del cuerpo		
		int iIndex = htmlInput.indexOf("<tbody>");
		int fIndex = htmlInput.indexOf("</tbody>", iIndex);
		
		if( iIndex > 0 && fIndex > 0 ){
			//Si se valido un inicio y fin correcto para el indice
			String infoTasaDiaria = htmlInput.substring(iIndex, fIndex);			
			resp = analizarInfoTasaDiaria(infoTasaDiaria, cabecera);
		}
				
		return resp;
	}
	
	/**
	 * Recupera la informacion de tasa diaria
	 * @param infoTasaDiaria
	 * @param cabecera
	 * @return
	 */
	private List<InfoTasaDiaria> analizarInfoTasaDiaria( String infoTasaDiaria, Map<Integer, String> cabecera ){
		ArrayList<InfoTasaDiaria> resp = null;
		
		String items[] = infoTasaDiaria.split("<tr>");
		resp = new ArrayList<InfoTasaDiaria>();		
						
		List<String> cols = null;
		InfoTasaDiaria pf = null;
		for(int i=1; i<items.length; i++){
			cols = parserTD(items[i].trim());
			if( cols!=null ){
				pf = new InfoTasaDiaria();
				//Recuperar el tipo de informacion en cada columna
				for(int j=0; j<cabecera.size(); j++){
					BigDecimal monto = BigDecimal.valueOf( Double.valueOf(cols.get(j).replace("%", "").trim()));
					if( cabecera.get(i).equals("Entidad") ){
						pf.setEntidad( cols.get(j) );		
					}
					setearInfoTasaDiaria(cabecera.get(j), pf, monto);
				}
				//Agregar info tasa diaria
				resp.add(pf);
			}
		}							
		return resp;
	}
	
	private void setearInfoTasaDiaria(String columna, InfoTasaDiaria infoTasaDiaria, BigDecimal monto ){
		
		if( columna.equals("TCEA(*)") ){
			infoTasaDiaria.setTcea ( monto  );		
		}else if( columna.equals("Cuota") ){
			infoTasaDiaria.setCuota ( monto );		
		}else if( columna.equals("Prima(*)") ){
			infoTasaDiaria.setPrima( monto );		
		}else if( columna.equals("TREA Mínima S/.") ){
			infoTasaDiaria.setTreamin( monto );
			infoTasaDiaria.setMonedaTrea(SBSParametros.MONEDA_SOLES);
		}else if( columna.equals("TREA Mínima US$") ){
			infoTasaDiaria.setTreamin( monto );
			infoTasaDiaria.setMonedaTrea(SBSParametros.MONEDA_DOLARES);
		}else if( columna.contains( "Tasa de Rendimiento") ){
			infoTasaDiaria.setTrea( monto );		
		}else if( columna.startsWith( "TREA (US$ 1000)") ){
			infoTasaDiaria.setTrea1000( monto );
			infoTasaDiaria.setMonedaTrea(SBSParametros.MONEDA_DOLARES);
		}else if( columna.startsWith( "TREA (US$ 3000)") ){
			infoTasaDiaria.setTrea3000( monto );
			infoTasaDiaria.setMonedaTrea(SBSParametros.MONEDA_DOLARES);
		}else if( columna.startsWith( "TREA (US$ 10000)") ){
			infoTasaDiaria.setTrea10000( monto );
			infoTasaDiaria.setMonedaTrea(SBSParametros.MONEDA_DOLARES);
		}else if( columna.startsWith( "TREA (S/. 1000)") ){
			infoTasaDiaria.setTrea1000( monto );
			infoTasaDiaria.setMonedaTrea(SBSParametros.MONEDA_SOLES);
		}else if( columna.startsWith( "TREA (S/. 3000)") ){
			infoTasaDiaria.setTrea3000( monto );
			infoTasaDiaria.setMonedaTrea(SBSParametros.MONEDA_SOLES);
		}else if( columna.startsWith( "TREA (S/. 10000)") ){
			infoTasaDiaria.setTrea10000( monto );
			infoTasaDiaria.setMonedaTrea(SBSParametros.MONEDA_SOLES);
		}
		
	}
	
	private Map<Integer, String> parcerCabecera(String strCab){
		Map<Integer, String> resp = null;
		final int minimunCols = 3;
		
		String cols[] = strCab.split("<th");
		if( cols.length > minimunCols ){
			resp = new HashMap<Integer, String>();
			int beginWord;
			int endWord;
			for(int i=2; i<cols.length; i++){	
				beginWord = cols[i].indexOf('>'); 
				endWord = cols[i].indexOf("</th>");
				resp.put(i-2, cols[i].substring(beginWord+1, endWord));
			}			
		}
		return resp;		
	}
	
	private List<String> parserTD(String strTD){
		List<String> resp = null;		
		String cols[] = strTD.split("<td");
		if( cols.length > 2 ){
			resp = new ArrayList<String>();
			int beginWord;
			int endWord;
			for(int i=1; i<cols.length; i++){	
				beginWord = cols[i].indexOf('>'); 
				endWord = cols[i].indexOf("</td>");
				resp.add(cols[i].substring(beginWord+1, endWord));
			}
		}
		
		return resp;
	}

}
