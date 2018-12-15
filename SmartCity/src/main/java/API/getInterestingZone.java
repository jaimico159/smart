package API;

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

import Builder.InterestingZoneJson;
import Modules.Polygon.buildPolygon2;
import structure.PointOfInterest;

import utilities.PointOfInterestDAO;

@WebServlet(
		name = "getInterestingZone", 
        urlPatterns = { "/getPointsOfInterest" })
@SuppressWarnings("serial")
public class getInterestingZone extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String test = CharStreams.toString(req.getReader());
		test = test.replaceAll("\"", "");
		test = test.substring(1, test.length()-1);
		
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
			
		PrintWriter writer = resp.getWriter();
	
		PointOfInterestDAO retriever = new PointOfInterestDAO();
					
		InterestingZoneJson json = new InterestingZoneJson(retriever.getList());
		
		json.build();
		resp.setCharacterEncoding("UTF-8");
		writer.print(json.getJson());
		writer.flush();

	}
}