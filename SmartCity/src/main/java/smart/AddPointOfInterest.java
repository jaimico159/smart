package smart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import structure.PointOfInterest;
import utilities.PointOfInterestUtilities;

/**
 * Servlet implementation class AddPointOfInterest
 */
@WebServlet("/AddPointOfInterest")
public class AddPointOfInterest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPointOfInterest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String point[] = request.getParameter("point").split(",");
		double latitude = Double.parseDouble(point[0]);
		double longitude = Double.parseDouble(point[1]);
		PointOfInterestUtilities persister = new PointOfInterestUtilities();
		PointOfInterest zone = new PointOfInterest();
		zone.setDescription(description);
		zone.setLatitude(latitude);
		zone.setLongitude(longitude);
		zone.setName(name);
		persister.createPointOfInterest(zone);
	}

}
