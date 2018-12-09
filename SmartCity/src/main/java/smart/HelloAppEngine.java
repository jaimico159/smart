package smart;

import java.io.IOException;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.CloudDatastoreRemoteServiceConfig.AppId.Location;

import Sistema.getInZonePolygonServlet;
import structure.Point;
import utilities.buildPolygon2;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {
	private ObjectifyWebFilter webFiltrer; 
	private getInZonePolygonServlet s;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
		webFiltrer = new ObjectifyWebFilter();
	//  webFiltrer.createPoint(1.23232, 2.12312312312, 1);
   // webFiltrer.createZone("Catedral2", "catedral de arequipa3", -16.398012383, -71.536123642);
	//List<Point> Points = webFiltrer.loadPointers();
		//webFiltrer.createVehicle("auto3");
		//webFiltrer.createLocation("auto2", 1.8231231, 4.3123, 50, 1);
		//webFiltrer.createLocation("auto3", 2.2231231, 4.3123, 50, 1);
		//webFiltrer.createLocation("auto1", 1.9231231, 3.3123, 50, 1);
		//webFiltrer.createLocation("auto3", 1.9231231, 3.0123, 50, 1);
		//webFiltrer.createLocation("auto1", 5.21111, 1.9123, 46, 1);
		//webFiltrer.createPolygon("poligono3", "holi2", 1);

	//List<structure.Location> locations = webFiltrer.loadHistorialVehicle("auto2");
		// s = new getInterestingZoneServlet();
		//List<structure.Polygon> polyg = webFiltrer.loadPolygon();
		//List<structure.Point> puntos = polyg.get(0).getPoints();
		buildPolygon2 poligons = new buildPolygon2();

    response.setContentType("text/plain");
    response.getWriter().print(poligons.coordinate_is_inside_polygon(26.896598, -79.938355));
    response.setCharacterEncoding("UTF-8");
    
   // response.getWriter().print("Hello App Engine!\r\n");
  
    //s.go();
   //for(int i = 0; i<polyg.size();i++) {
    	//response.getWriter().print(puntos.get(0).getLatitude());
    	//response.getWriter().println();
    //}
    
  }
}