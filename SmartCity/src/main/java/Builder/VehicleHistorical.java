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

public class VehicleHistorical extends AbstractJsonBuilder {
	public Iterable<Vehicle> vehicles;
	public Date inicio;
	public Date fin;
	
	public VehicleHistorical(List<Vehicle> vehicles, Date inicio, Date fin) {
		this.vehicles = new ArrayList<Vehicle>();
		//this.inicio = new Date();
		//this.fin = new Date();
    	this.vehicles = vehicles;
    	this.inicio = inicio;
    	this.fin = fin;
	
	    }

	@Override
	public void build() {
		
		LocationUtilities locationRetriever = new LocationUtilities();
		VehicleUtilities vehiclesRetriever = new VehicleUtilities();
		
		JSONArray arrayEntrega = new JSONArray();
		for(Vehicle i: vehicles) {
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
		entrega.put("cars", arrayEntrega);	
		
		
	}

}
