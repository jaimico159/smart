package utilities;

import java.util.ArrayList; 
public class buildPolygon2 
{ 
    private static double PI = 3.14159265; 
    private static double TWOPI = 2*PI; 
    private  ArrayList<Double> lat_array;
    private  ArrayList<Double> long_array;
    private  ArrayList<String> polygon_lat_long_pairs;
    public buildPolygon2() 
    {
    	polygon_lat_long_pairs = new ArrayList<String>(); 
    	lat_array = new ArrayList<Double>(); 
        long_array = new ArrayList<Double>(); 
    }
    
    public void addPoint(String coordenada) {
    	polygon_lat_long_pairs.add(coordenada); 
    	
    }
    public  void makePolygon() { 

    //Convert the strings to doubles.  
    for(String s : polygon_lat_long_pairs){ 
     long_array.add(Double.parseDouble(s.split(",")[0])); 
     lat_array.add(Double.parseDouble(s.split(",")[1])); 
   
    } 



} 
public  boolean coordinate_is_inside_polygon(
    double longitude, double latitude) 
{  
     int i; 
     double angle=0; 
     double point1_lat; 
     double point1_long; 
     double point2_lat; 
     double point2_long; 
     int n = lat_array.size(); 

     for (i=0;i<n;i++) { 
      point1_lat = lat_array.get(i) - latitude; 
      point1_long = long_array.get(i) - longitude; 
      point2_lat = lat_array.get((i+1)%n) - latitude; 
      //you should have paid more attention in high school geometry. 
      point2_long = long_array.get((i+1)%n) - longitude; 
      angle += Angle2D(point1_lat,point1_long,point2_lat,point2_long); 
     } 

     if (Math.abs(angle) < PI) 
      return false; 
     else 
      return true; 
} 

public static double Angle2D(double y1, double x1, double y2, double x2) 
{ 
    double dtheta,theta1,theta2; 

    theta1 = Math.atan2(y1,x1); 
    theta2 = Math.atan2(y2,x2); 
    dtheta = theta2 - theta1; 
    while (dtheta > PI) 
     dtheta -= TWOPI; 
    while (dtheta < -PI) 
     dtheta += TWOPI; 

    return(dtheta); 
} 

public static boolean is_valid_gps_coordinate(double latitude, 
    double longitude) 
{ 
    //This is a bonus function, it's unused, to reject invalid lat/longs. 
    if (latitude > -90 && latitude < 90 && 
      longitude > -180 && longitude < 180) 
    { 
     return true; 
    } 
    return false; 
} 
} 