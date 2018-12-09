package Sistema;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import structure.Interesting_Zone;

import utilities.InterestingZoneUtilities;

import utilities.buildPolygon2;

@WebServlet(
		name = "getIntZonepolygon", 
        urlPatterns = { "/getIntPoints" })
@SuppressWarnings("serial")
public class getInterestingZone extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
		String longSupDerecha="-71.54312";
		String latSupDerecha= "16.231221";
		String longSupIzquieda= "-74.23212";
		String latSupIzquieda= "16.23122";
		String longInfDerecha= "-71.542312";
		String latInfDerecha= "-17.1231212";
		String longInfIzquierda= "-74.12312";
		String latInfIzquierda= "-16.432223";
		buildPolygon2 polygon = new buildPolygon2();

		PrintWriter writer = resp.getWriter();
		
		
		polygon.addPoint(longInfIzquierda+","+latInfIzquierda);
		polygon.addPoint(longInfDerecha+","+latInfDerecha);
		polygon.addPoint(longSupDerecha+","+latSupDerecha);
		polygon.addPoint(longSupIzquieda+","+latSupIzquieda);
		
		polygon.makePolygon();
		 JSONObject entrega = new JSONObject();
	
		InterestingZoneUtilities Czone = new InterestingZoneUtilities();
		List<Object> zones = Czone.loadZone();
		JSONArray arrayFinal = new JSONArray();
		System.out.println("hola");
		for (Object j : zones) {
			boolean verificador = false;
			if (j instanceof Interesting_Zone) {
				
				Interesting_Zone aux = ((Interesting_Zone) j);
				verificador = polygon.coordinate_is_inside_polygon(aux.getLongitude(),aux.getLatitude());
				JSONObject json = new JSONObject();
				JSONArray arrayCoordenadas = new JSONArray();
				if(verificador==true) {
					json.put("id", aux.getId());
					json.put("name",aux.getName());
					arrayCoordenadas.put(aux.getLongitude());
					arrayCoordenadas.put(aux.getLatitude());
					json.put("position",arrayCoordenadas );
					json.put("description", aux.getDescription());
				
					arrayFinal.put(json);
				}

			}
		}


		entrega.put("points", arrayFinal);
		resp.setCharacterEncoding("UTF-8");
		writer.print(entrega);
		writer.flush();

	}
}
//revisar