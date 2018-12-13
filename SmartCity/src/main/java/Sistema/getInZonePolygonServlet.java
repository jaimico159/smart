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

import IBuilder.ZonePolygonJson;
import structure.PointOfInterest;
import utilities.PointOfInterestUtilities;
import utilities.PolygonUtilities;
import utilities.buildPolygon2;

@WebServlet(
		name = "getIntZonepolygon", 
        urlPatterns = { "/getIntPointsPolygon" })
@SuppressWarnings("serial")
public class getInZonePolygonServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//Esta clase retorna un JSON con las zonas de interes en un poligono
		
		resp.setContentType("application/json");
		PolygonUtilities retriever = new PolygonUtilities();
		//recibe el id del poligono
		// String idPolygon=req.getParameter("id");
		String idPolygon = "5770237022568448";
		List<structure.Point> puntos = new ArrayList<structure.Point>();
		PointOfInterestUtilities interestRetriver = new PointOfInterestUtilities();
		buildPolygon2 buildPolygon = new buildPolygon2();
		PrintWriter writer = resp.getWriter();
		structure.Polygon polygon = retriever.loadOnePolygon(idPolygon);

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

		List<PointOfInterest> zones = interestRetriver.loadPointOfInterest();
        ZonePolygonJson zoneJson = new ZonePolygonJson(buildPolygon, zones );
        zoneJson.build();
		resp.setCharacterEncoding("UTF-8");
		writer.print(zoneJson.getJson());
		writer.flush();

	}
}
//revisar