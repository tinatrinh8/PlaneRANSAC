/**
 * TINA TRINH - 300175427
 * CSI2120
 * Project Part 1
 * Feb 7th, 2023
 */

public class Point3D {

    public double x,y,z;

    /**
     * This is a constructor for Point3D for x,y,z
     * @param x value for Point3D
     * @param y value for Point3D
     * @param z value for Point3D
     */

    public Point3D (double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return the x value from getter
     **/ 
    public double getX() {

        return x;
    }

     /**
     * @return the y value from getter
     **/ 
    public double getY() {

        return y;
    }

     /**
     * @return the y value from getter
     **/ 
    public double getZ() {

        return z;
    }

    /**
     * Prints out our output format for the points
     */
    public String toString() {

        return "[ " + getX() + ", " + getY() + ", " + getZ() + " ]";
    }
}
