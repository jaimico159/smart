package Sistema;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.repackaged.com.google.common.io.CharStreams;

import structure.PointOfInterest;

import utilities.PointOfInterestUtilities;

import utilities.buildPolygon2;

@WebServlet(
		name = "getInterestingZone", 
        urlPatterns = { "/getPointsOfInterest" })
@SuppressWarnings("serial")
public class getInterestingZone extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String test = CharStreams.toString(req.getReader());
		test = test.replaceAll("\"", "");
		test = test.substring(1, test.length()-1);
		
		//System.out.println(mapa);
		Map<String,String> requestmap = new HashMap<String,String>();
		List<String> lista = Arrays.asList(test.split(","));
		String[] element;
		for(String a:lista) {
			element = a.split(":");
			requestmap.put(element[0], element[1]);
		}
		System.out.println(requestmap);
		System.out.println("Rescatando zona");
		
		String longSupDerecha=requestmap.get("longSupDerecha");
		String latSupDerecha= requestmap.get("latSupDerecha");
		String longSupIzquieda= requestmap.get("longSupIzquieda");
		String latSupIzquieda= requestmap.get("latSupIzquieda");
		String longInfDerecha= requestmap.get("longInfDerecha");
		String latInfDerecha= requestmap.get("latInfDerecha");
		String longInfIzquierda= requestmap.get("longInfIzquierda");
		String latInfIzquierda= requestmap.get("latInfIzquierda");
		resp.setContentType("application/json");
		//Esta clase retorna las zonas de interes de acuerdo a la pantalla
		
		/*
		String longSupDerecha=req.getParameter("longSupDerecha");
		String latSupDerecha= req.getParameter("latSupDerecha");
		String longSupIzquieda= req.getParameter("longSupIzquieda");
		String latSupIzquieda= req.getParameter("latSupIzquieda");
		String longInfDerecha= req.getParameter("longInfDerecha");
		String latInfDerecha= req.getParameter("latInfDerecha");
		String longInfIzquierda= req.getParameter("longInfIzquierda");
		String latInfIzquierda= req.getParameter("latInfIzquierda");
		*/
		/*String longSupDerecha="-71.54312";
		String latSupDerecha= "16.231221";
		String longSupIzquieda= "-74.23212";
		String latSupIzquieda= "16.23122";
		String longInfDerecha= "-71.542312";
		String latInfDerecha= "-17.1231212";
		String longInfIzquierda= "-74.12312";
		String latInfIzquierda= "-16.432223";*/
		
		PrintWriter writer = resp.getWriter();
		
		
		JSONObject entrega = new JSONObject();
	
		PointOfInterestUtilities retriever = new PointOfInterestUtilities();
		List<PointOfInterest> zones = retriever.loadPointOfInterest();
		JSONArray arrayFinal = new JSONArray();
		System.out.println("hola");
		for (PointOfInterest aux : zones) {
			boolean verificador = true;
				
			JSONObject json = new JSONObject();
			JSONArray arrayCoordenadas = new JSONArray();
			if(verificador==true) {
				json.put("id", aux.getId());
				json.put("name",aux.getName());
				arrayCoordenadas.put(aux.getLatitude());
				arrayCoordenadas.put(aux.getLongitude());
				json.put("position",arrayCoordenadas );
				json.put("description", aux.getDescription());
			
				arrayFinal.put(json);
			}		
		}

		entrega.put("points", arrayFinal);
		resp.setCharacterEncoding("UTF-8");
		writer.print(entrega);
		writer.flush();

	}
}
//revisar