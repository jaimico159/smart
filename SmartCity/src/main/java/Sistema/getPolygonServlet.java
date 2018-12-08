package Sistema;







import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import structure.Point;
import utilities.PolygonUtilities;
import utilities.buildPolygon2;



@WebServlet(
		name = "Polygons",
		urlPatterns = {"/getPolygons"}
		)
@SuppressWarnings( "serial" )
public class getPolygonServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
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
		String longSupDerecha="1000";
		String latSupDerecha= "1000";
		String longSupIzquieda= "-1000";
		String latSupIzquieda= "1000";
		String longInfDerecha= "1000";
		String latInfDerecha= "-1000";
		String longInfIzquierda= "-1000";
		String latInfIzquierda= "-1000";
		buildPolygon2 polygon = new buildPolygon2();
		PolygonUtilities Cpolygon = new PolygonUtilities();
		PrintWriter writer = resp.getWriter();
		List<Object> polygons = Cpolygon.loadPolygon();
		
		polygon.addPoint(latInfIzquierda+","+longInfIzquierda);
		polygon.addPoint(latInfDerecha+","+longInfDerecha);
		polygon.addPoint(latSupDerecha+","+longSupDerecha);
		polygon.addPoint(latSupIzquieda+","+longSupIzquieda);
		
		polygon.makePolygon();
		 JSONObject entrega = new JSONObject();
		 entrega.put("type", "FeatureCollection");
		 
		 
		 JSONArray array = new JSONArray();
		 
		 for (Object i: polygons) {
			 JSONObject recolector = new JSONObject();
			 if(i instanceof structure.Polygon) {
				
				 boolean verificador =false;
				 int contador = 0;
				 structure.Polygon aux = ((structure.Polygon)i);
				 ArrayList<structure.Point> aux2 = new ArrayList<Point>();
				 List<structure.Point> points = aux.getPoints();
				 for(Object j : points ) {
					 if(j instanceof structure.Point) {
						 aux2.add((structure.Point)j);
						 verificador = polygon.coordinate_is_inside_polygon(((structure.Point)j).getLatitude(), ((structure.Point)j).getLongitude());
                         if(verificador==true) {
                        	 contador++;
                         }
					 }
				 }
				 if(contador == 4) {
					 recolector.put("type", "Feature");
					 recolector.put("name", aux.getName() );
					 recolector.put("properties",new JSONObject() );
					 JSONObject geometry = new JSONObject();
					 JSONArray arfinal = new JSONArray();
					 JSONArray arfinal2 = new JSONArray();
					for(int k=0; k<=aux2.size();k++) {
						JSONArray ar = new JSONArray();
						if(k==aux2.size()) {
							ar.put(aux2.get(0).getLatitude());
						}else {
						ar.put(aux2.get(k).getLatitude());
						ar.put(aux2.get(k).getLongitude());
						arfinal.put(ar);}
						
					}
					arfinal2.put(arfinal);
					geometry.put("type", "Polygo");
					geometry.put("coordinates", arfinal2);
					 recolector.put("geometry", geometry);
				 }
				 array.put(recolector);
			 }
			
		    }
		 entrega.put("features", array);
		 resp.setCharacterEncoding("UTF-8");
	      writer.print(entrega);
	      writer.flush();
		
       
	}
}

