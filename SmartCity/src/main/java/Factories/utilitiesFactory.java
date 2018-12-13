package Factories;

import utilities.*;

public interface utilitiesFactory {
	public PointOfInterestUtilities makePointOfInterestUtilities();
	public LocationUtilities makeLocationUtilities();
	public PointUtilities makePointUtilities();
	public PolygonUtilities makePolygonUtilities();
	public VehicleUtilities makeVehicleUtilities();
	

}
