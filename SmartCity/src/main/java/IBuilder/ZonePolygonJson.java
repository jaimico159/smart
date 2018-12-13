package IBuilder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import structure.Point;
import structure.PointOfInterest;
import structure.Polygon;
import structure.Vehicle;
import utilities.buildPolygon2;

public class ZonePolygonJson extends AbstractJson {
	
	public List<PointOfInterest> zones;

    public ZonePolygonJson(buildPolygon2 polygon, List<PointOfInterest> zones) {
    	this.zones = new ArrayList<PointOfInterest>();
    	this.polygon = polygon;
    	this.zones = zones;
    }
    
	public void build() {
		JSONObject entrega = new JSONObject();
        JSONArray arrayFinal = new JSONArray();
		for (PointOfInterest aux : zones) {
			boolean verificador = false;			
			verificador = polygon.coordinate_is_inside_polygon(aux.getLongitude(), aux.getLatitude());
			JSONObject json = new JSONObject();
			JSONArray arrayCoordenadas = new JSONArray();
			if(verificador==true) {
				json.put("id", aux.getId());
				json.put("name",aux.getName());
				arrayCoordenadas.put(aux.getLongitude());
				arrayCoordenadas.put(aux.getLatitude());
				json.put("position",arrayCoordenadas );
				json.put("description", aux.getDescription());
			
				arrayFinal.put(json);
			}

		
		}
		entrega.put("points", arrayFinal);
		
	}
	

}
