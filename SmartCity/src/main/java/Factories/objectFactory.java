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
	public PointOfInterestDAO makePointOfInterestUtilities() {
		// TODO Auto-generated method stub
		return new PointOfInterestDAO();
	}

	@Override
	public LocationDAO makeLocationUtilities() {
		// TODO Auto-generated method stub
		return new LocationDAO();
	}

	@Override
	public PointDAO makePointUtilities() {
		// TODO Auto-generated method stub
		return new PointDAO();
	}

	@Override
	public PolygonDAO makePolygonUtilities() {
		// TODO Auto-generated method stub
		return new PolygonDAO();
	}

	@Override
	public VehicleDAO makeVehicleUtilities() {
		// TODO Auto-generated method stub
		return new VehicleDAO();
	}

	


}
