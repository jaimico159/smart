package Sistema;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import structure.Interesting_Zone;
import utilities.InterestingZoneUtilities;

@WebServlet(
		name="addInterestingZone",
		urlPatterns = {"/addZone"}
)
@SuppressWarnings("serial")
public class addInterestingZoneServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		
		InterestingZoneUtilities Czone = new InterestingZoneUtilities();
		Interesting_Zone zone = new Interesting_Zone();
		zone.setDescription(description);
		zone.setLatitude(Double.parseDouble(latitude));
		zone.setLongitude(Double.parseDouble(longitude));
		zone.setName(name);
		Czone.createZone(zone);
		
		//agregar redireccion resp.sendRedirect("index.html");
	}
}
