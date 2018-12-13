package Sistema;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.repackaged.com.google.common.io.CharStreams;
import com.google.gson.JsonSerializer;

import structure.Point;
import structure.Polygon;
import utilities.PolygonUtilities;
import utilities.buildPolygon2;

@WebServlet(
		name = "Polygons",
		urlPatterns = {"/getPolygons"}
		)
@SuppressWarnings( "serial" )
public class getPolygonServlet extends HttpServlet{
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
		
		String longSupDerecha=requestmap.get("longSupDerecha");
		String latSupDerecha= requestmap.get("latSupDerecha");
		String longSupIzquieda= requestmap.get("longSupIzquieda");
		String latSupIzquieda= requestmap.get("latSupIzquieda");
		String longInfDerecha= requestmap.get("longInfDerecha");
		String latInfDerecha= requestmap.get("latInfDerecha");
		String longInfIzquierda= requestmap.get("longInfIzquierda");
		String latInfIzquierda= requestmap.get("latInfIzquierda");
		
		buildPolygon2 polygon = new buildPolygon2();
		PolygonUtilities Cpolygon = new PolygonUtilities();
		PrintWriter writer = resp.getWriter();
		List<Polygon> polygons = Cpolygon.loadPolygon();
		
		polygon.addPoint(longInfIzquierda+","+latInfIzquierda);
		polygon.addPoint(longInfDerecha+","+latInfDerecha);
		polygon.addPoint(longSupDerecha+","+latSupDerecha);
		polygon.addPoint(longSupIzquieda+","+latSupIzquieda);
		
		polygon.makePolygon();
		JSONObject entrega = new JSONObject();
		JSONArray array = new JSONArray();
		 
		for (Polygon aux: polygons) {
			 JSONObject recolector = new JSONObject();		 	
			 boolean verificador =false;
			 int contador = 0;
			 ArrayList<Point> aux2 = new ArrayList<Point>();
			 List<Point> points = aux.getPoints();
			 for(Point j : points ) {
			 	 aux2.add(j);
				 verificador = polygon.coordinate_is_inside_polygon(j.getLongitude(), j.getLatitude());
                 if(verificador==true) {
                	 contador++;
                 }
			 }
			 recolector.put("id", aux.getId());
			 recolector.put("name", aux.getName());
			 recolector.put("description", aux.getDescription());

			 JSONArray arfinal = new JSONArray();
	
			 for(int k=0; k<=aux2.size();k++) {
				JSONArray ar = new JSONArray();
				if(k==aux2.size()) {
					ar.put(aux2.get(0).getLatitude());
					ar.put(aux2.get(0).getLongitude());
					arfinal.put(ar);
				}else {
				ar.put(aux2.get(k).getLatitude());
				ar.put(aux2.get(k).getLongitude());
				arfinal.put(ar);}
				
			 }
			 recolector.put("path", arfinal);
			 array.put(recolector);
		}
		entrega.put("polygons", array);
		resp.setCharacterEncoding("UTF-8");
	    writer.print(entrega);
	    System.out.println("Hizo flush");
	    writer.flush();
	}
}

