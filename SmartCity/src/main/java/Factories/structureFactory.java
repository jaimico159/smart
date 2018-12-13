package Factories;
import structure.*;

public interface structureFactory {
	public Location makeLocation();
    public Point makePoint();
    public PointOfInterest makePointOfInterest();
    public Polygon makePolygon();
    public Vehicle makeVehicle();
}
