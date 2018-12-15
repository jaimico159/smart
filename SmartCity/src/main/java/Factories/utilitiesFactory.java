package Factories;

import utilities.*;

public interface utilitiesFactory {
	public PointOfInterestDAO makePointOfInterestUtilities();
	public LocationDAO makeLocationUtilities();
	public PointDAO makePointUtilities();
	public PolygonDAO makePolygonUtilities();
	public VehicleDAO makeVehicleUtilities();
	

}
