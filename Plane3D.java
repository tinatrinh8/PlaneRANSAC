/**
 * TINA TRINH - 300175427
 * CSI2120
 * Project Part 1
 * Feb 7th, 2023
 */

public class Plane3D {

    private double a,b,c,d;

    /**
     * Constructor from paramaters with 4 values
     * @param a value obtained from calculation from points in Point3D
     * @param b value obtained from calculation from points in Point3D
     * @param c value obtained from calculation from points in Point3D
     * @param d value obtained from calculation from points in Point3D
     */
    public Plane3D(double a, double b, double c, double d) {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d; 
    }

    /**
     * Constructor for Plane3D that takes the 3 points form Point3D and compute the plane using vectors
     * @param p1 point from Point3D to calculate vector
     * @param p2 point from Point3D to calculate vector
     * @param p3 point from Point3D to calculate vector
     */
    public Plane3D(Point3D p1, Point3D p2, Point3D p3) {

        // calculate the vectors from the three points
        double a1 = p2.getX() - p1.getX();
        double b1 = p2.getY() - p1.getY();
        double c1 = p2.getZ() - p1.getZ();

        double a2 = p3.getX() - p1.getX();
        double b2 = p3.getY() - p1.getY();
        double c2 = p3.getZ() - p1.getZ();

        // calculate cross product of the three points to finx 3d plane
        this.a = b1 * c2 - b2 * c1;
        this.b = a2 * c2 - a1 * c2;
        this.c = a1 * b2 - b1 * a2;

        // set the coefficients of plane equation as well
        this.d = -(this.a * p1.getX() - this.b * p1.getY() - this.c * p1.getZ());
        
        // print the equation of plane
        System.out.println(" The equation of plane is: " + this.a + " x + " + this.b + " y + " + this.c +" z + " + this.d + " = 0 ");
    }

    /**
     * Calculates the distance from Plane3D
     * @param pt is the point that we pass to get points from Point3D
     * @return the distance from a point to the Plane3D
     */
    public double getDistance(Point3D pt) {
     
        // ax+by+cz+d = 0 from a point x0,y0,z0 and we use distance formula:
        double distance = (a * pt.getX() +  b * pt.getY() + c + pt.getZ() + d) / (Math.sqrt((a * a) + (b * b) + (c * c)));
        return distance;
    }

    public String toString(){
        return("The Plane equation is: "+ a + "x + " + b + "y + " + c + "z + " + d + " = 0");
    }
}