package Builder;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Modules.Polygon.buildPolygon2;
import structure.Point;
import structure.PointOfInterest;
import structure.Polygon;

public class PolygonJson {
	
	public JsonBuilder<Polygon> polJson;

    public PolygonJson(JsonBuilder<Polygon> polJson) {
    	this.polJson = polJson;
    }
    public Json<Polygon> getJson() {
		return this.polJson.getJson();
	}
	public void build(List<Polygon> lista, buildPolygon2 polygon) throws Exception{
		polJson.buildObjectJson();
		polJson.buildList(lista);
		
		
		polJson.getJson().setPolygon(polygon);
		JSONArray array = new JSONArray();
		 
		for (Polygon aux: polJson.getJson().getLista()) {
			 JSONObject recolector = new JSONObject();		 	
			 boolean verificador =false;
			 int contador = 0;
			 ArrayList<Point> aux2 = new ArrayList<Point>();
			 List<Point> points = aux.getPoints();
			 for(Point j : points ) {
			 	 aux2.add(j);
				 verificador = polJson.getJson().getPolygon().coordinate_is_inside_polygon(j.getLongitude(), j.getLatitude());
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
		polJson.getJson().getJson().put("polygons", array);		
	}
	

}
