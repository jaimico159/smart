package API;

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

import com.googlecode.objectify.Key;

import Builder.ZonePolygonJson;
import Modules.Polygon.buildPolygon2;
import structure.PointOfInterest;
import structure.Polygon;
import utilities.PointOfInterestDAO;
import utilities.PolygonDAO;

@WebServlet(
		name = "getIntZonepolygon", 
        urlPatterns = { "/getIntPointsPolygon" })
@SuppressWarnings("serial")
public class getInZonePolygonServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//Esta clase retorna un JSON con las zonas de interes en un poligono
		
		resp.setContentType("application/json");
		PolygonDAO retriever = new PolygonDAO();
		Long idPolygon = 5770237022568448L;
		List<structure.Point> puntos = new ArrayList<structure.Point>();
		PointOfInterestDAO interestRetriver = new PointOfInterestDAO();
		buildPolygon2 buildPolygon = new buildPolygon2();
		PrintWriter writer = resp.getWriter();
		Polygon polygon = retriever.get(Key.create(Polygon.class, idPolygon));

		System.out.println("hola");
		System.out.println(polygon.getName());

		puntos = polygon.getPoints();
		System.out.println(puntos.size());
		for (Object i : puntos) {
			if (i instanceof structure.Point) {
				buildPolygon.addPoint(((structure.Point) i).getLongitude() + "," + ((structure.Point) i).getLatitude());
			}
		}
		buildPolygon.makePolygon();

		List<PointOfInterest> zones = interestRetriver.getList();
        ZonePolygonJson zoneJson = new ZonePolygonJson(buildPolygon, zones );
        zoneJson.build();
		resp.setCharacterEncoding("UTF-8");
		writer.print(zoneJson.getJson());
		writer.flush();

	}
}
//revisar