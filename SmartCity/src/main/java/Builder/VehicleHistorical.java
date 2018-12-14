package Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.objectify.Key;

import Modules.Polygon.buildPolygon2;
import structure.Location;
import structure.Polygon;
import structure.Vehicle;
import utilities.LocationUtilities;
import utilities.VehicleUtilities;

public class VehicleHistorical{
	public JsonBuilder<Vehicle> HistJson;
	
	public VehicleHistorical(JsonBuilder<Vehicle> json) {
		this.HistJson = json;
	    	
	    }
	 public Json<Vehicle> getJson() {
			return this.HistJson.getJson();
		}


	public void build(List<Vehicle> lista, Date inicio, Date fin) throws Exception{
		HistJson.buildList(lista);
		HistJson.buildObjectJson();
		
		LocationUtilities locationRetriever = new LocationUtilities();
		
		
		JSONArray arrayEntrega = new JSONArray();
		for(Vehicle i: HistJson.getJson().getLista()) {
			JSONArray arrayFinal = new JSONArray();
			JSONObject provisional = new JSONObject();
			Key<Vehicle> aux = Key.create(Vehicle.class, i.getId());
			System.out.println(aux.getRaw());
			System.out.println(aux.getId());
			List<Location> history = locationRetriever.loadHistorical(aux);
			provisional.put("id",i.getId());
			for(Location j: history) {
				Date date = j.getDatetime2();
				JSONArray arrayCoordenada = new JSONArray();
				if(date.before(fin)) {
					if(inicio.before(date)) {				  
				        arrayCoordenada.put(j.getLatitude());
				        
				        arrayCoordenada.put(j.getLongitude());
				        arrayFinal.put(arrayCoordenada);
					}
		        }
			}
			provisional.put("history",arrayFinal);
			arrayEntrega.put(provisional);
		}
		HistJson.getJson().getJson().put("cars", arrayEntrega);	
		
		
	}

}
