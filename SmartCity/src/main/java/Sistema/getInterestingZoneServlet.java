package Sistema;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import structure.Interesting_Zone;
import utilities.InterestingZoneUtilities;
import utilities.PolygonUtilities;
import utilities.buildPolygon2;

@WebServlet(
		name = "getInterestingZone", 
        urlPatterns = { "/getIntPoints" })
@SuppressWarnings("serial")
public class getInterestingZoneServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		PolygonUtilities Cpolygon = new PolygonUtilities();
		// String idPolygon=req.getParameter("id");
		String idPolygon = "5770237022568448";
		List<structure.Point> puntos = new ArrayList<structure.Point>();
		InterestingZoneUtilities Czone = new InterestingZoneUtilities();
		buildPolygon2 buildPolygon = new buildPolygon2();
		PrintWriter writer = resp.getWriter();
		structure.Polygon polygon = Cpolygon.loadOnePolygon(idPolygon);

		System.out.println("hola");
		System.out.println(polygon.getName());

		puntos = polygon.getPoints();
		System.out.println(puntos.size());
		for (Object i : puntos) {
			if (i instanceof structure.Point) {
				System.out.println(((structure.Point) i).getLatitude() + "," + ((structure.Point) i).getLongitude());
				buildPolygon.addPoint(((structure.Point) i).getLatitude() + "," + ((structure.Point) i).getLongitude());
			}
		}
		buildPolygon.makePolygon();

		List<Object> zones = Czone.loadZone();
		JSONObject principal = new JSONObject();
        JSONObject entrega = new JSONObject();

		for (Object j : zones) {
			boolean verificador = false;
			if (j instanceof Interesting_Zone) {
		
				Interesting_Zone aux = ((Interesting_Zone) j);
				verificador = buildPolygon.coordinate_is_inside_polygon(aux.getLatitude(), aux.getLongitude());
				JSONObject json = new JSONObject();
				if(verificador==true) {
					json.put("id", aux.getId());
					json.put("latitude", aux.getLatitude());
					json.put("longitude", aux.getLongitude());
					json.put("description", aux.getDescription());
					//array.put(json);
					principal.put(aux.getName(),json);
				}

			}
		}
		entrega.put("Points", principal);
		resp.setCharacterEncoding("UTF-8");
		writer.print(entrega);
		writer.flush();

	}
}
//revisar