package Factories;
import structure.*;
import utilities.*;

public class objectFactory implements utilitiesFactory, structureFactory {
	public objectFactory() {
		
	}

	@Override
	public Location makeLocation() {
		// TODO Auto-generated method stub
		return new Location();
	}

	@Override
	public Point makePoint() {
		// TODO Auto-generated method stub
		return new Point();
	}

	@Override
	public PointOfInterest makePointOfInterest() {
		// TODO Auto-generated method stub
		return new PointOfInterest();
	}

	@Override
	public Polygon makePolygon() {
		// TODO Auto-generated method stub
		return new Polygon();
	}

	@Override
	public Vehicle makeVehicle() {
		// TODO Auto-generated method stub
		return new Vehicle();
	}

	@Override
	public PointOfInterestUtilities makePointOfInterestUtilities() {
		// TODO Auto-generated method stub
		return new PointOfInterestUtilities();
	}

	@Override
	public LocationUtilities makeLocationUtilities() {
		// TODO Auto-generated method stub
		return new LocationUtilities();
	}

	@Override
	public PointUtilities makePointUtilities() {
		// TODO Auto-generated method stub
		return new PointUtilities();
	}

	@Override
	public PolygonUtilities makePolygonUtilities() {
		// TODO Auto-generated method stub
		return new PolygonUtilities();
	}

	@Override
	public VehicleUtilities makeVehicleUtilities() {
		// TODO Auto-generated method stub
		return new VehicleUtilities();
	}

	


}
