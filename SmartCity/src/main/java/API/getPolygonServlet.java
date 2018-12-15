package API;

import java.io.IOException;

import Builder.*;
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

import Builder.InterestingZoneJson;
import Builder.JsonBuilder;
import Builder.PolygonJson;
import Modules.Polygon.buildPolygon2;
import structure.Point;
import structure.PointOfInterest;
import structure.Polygon;
import utilities.PointOfInterestDAO;
import utilities.PolygonDAO;

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
		PolygonDAO Cpolygon = new PolygonDAO();
		PrintWriter writer = resp.getWriter();
		List<Polygon> polygons = Cpolygon.getList();
		
		polygon.addPoint(longInfIzquierda+","+latInfIzquierda);
		polygon.addPoint(longInfDerecha+","+latInfDerecha);
		polygon.addPoint(longSupDerecha+","+latSupDerecha);
		polygon.addPoint(longSupIzquieda+","+latSupIzquieda);
		
		polygon.makePolygon();
		Json<Polygon> json = new Json<Polygon>();
	
		try {
			PointOfInterestDAO retriever = new PointOfInterestDAO();
					
			JsonBuilder<Polygon> InterestingZone = new JsonBuilder<Polygon>();
		
			PolygonJson wrapper = new PolygonJson(InterestingZone);
			wrapper.build(polygons, polygon);
			json =  wrapper.getJson();
			
		} catch (Exception e) {
			System.out.println("la lista de poligonos es nula!");
		}
		
		resp.setCharacterEncoding("UTF-8");
	    writer.print(json.getJson());
	    System.out.println("Hizo flush");
	    writer.flush();
	}
}

