package IBuilder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import structure.Point;
import structure.Polygon;
import structure.Vehicle;
import utilities.buildPolygon2;

public class PolygonJson extends AbstractJson {
	
	public List<Polygon> polygons;

    public PolygonJson(buildPolygon2 polygon, List<Polygon> polygons) {
    	this.polygons = new ArrayList<Polygon>();
    	this.polygon = polygon;
    	this.polygons = polygons;
    }
    
	public void build() {
		JSONObject entrega = new JSONObject();
		JSONArray array = new JSONArray();
		 
		for (Polygon aux: polygons) {
			 JSONObject recolector = new JSONObject();		 	
			 boolean verificador =false;
			 int contador = 0;
			 ArrayList<Point> aux2 = new ArrayList<Point>();
			 List<Point> points = aux.getPoints();
			 for(Point j : points ) {
			 	 aux2.add((structure.Point)j);
				 verificador = polygon.coordinate_is_inside_polygon(j.getLongitude(), j.getLatitude());
                 if(verificador==true) {
                	 contador++;
                 }
			 }
			 recolector.put("id", aux.getId());
			 recolector.put("name", aux.getName());
			 recolector.put("description", aux.getDescription());

			 JSONArray arfinal = new JSONArray();
	
			 for(int k=0; k<=aux2.size();k++) {
				JSONArray ar = new JSONArray();
				if(k==aux2.size()) {
					ar.put(aux2.get(0).getLatitude());
					ar.put(aux2.get(0).getLongitude());
					arfinal.put(ar);
				}else {
				ar.put(aux2.get(k).getLatitude());
				ar.put(aux2.get(k).getLongitude());
				arfinal.put(ar);}
				
			 }
			 recolector.put("path", arfinal);
			 array.put(recolector);
		}
		entrega.put("polygons", array);
		
	}
	

}
