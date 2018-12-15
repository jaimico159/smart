package System;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Factories.objectFactory;
import structure.Point;
import structure.Polygon;
import utilities.PolygonDAO;

/**
 * Servlet implementation class AddPolygon
 */
@WebServlet("/AddPolygon")
public class AddPolygon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public objectFactory factory = new objectFactory();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPolygon() {
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
		Polygon pol = new Polygon();
		String name = request.getParameter("name");
		String path = request.getParameter("path");
		String[] coordenadas = path.split(";");
		ArrayList<Point> puntos = new ArrayList<Point>();
		double lat;
		double lng;
		for(int i = 0; i < coordenadas.length; i++){
			String[] aux = coordenadas[i].split(",");
			lat = Double.parseDouble(aux[0]);
			lng = Double.parseDouble(aux[1]);
			puntos.add(new Point(lat,lng,i+1));
		}
		pol.setName(name);
		pol.setPoints(puntos);

		PolygonDAO persister = new PolygonDAO();
		persister.save(pol);
		
	}

}
