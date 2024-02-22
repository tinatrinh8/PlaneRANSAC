/**
 * TINA TRINH - 300175427
 * CSI2120
 * Project Part 1
 * Feb 7th, 2023
 */

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.io.IOException;

public class PlaneRANSAC {

    private PointCloud pc;
    private double eps;                                                                             
   // private ArrayList<Point3D> dominantPoints;

    /**
     *  Constructor for ransac class
     * @param pc that is passed from PointCloud
     */
    public PlaneRANSAC(PointCloud pc) {

        this.pc = pc;
        this.eps = 0;
    }

    /**
     * @param eps value for setter
    */
    public void setEps(double eps) {

        this.eps = eps;
    }

    /**
     * Getter method that get the erps
     * @return eps value
     */
    public double getEps() {

        return eps;
    }

    // a method that returns the estimated number of iterations required to obtain a certain level 
    //            of confidence to identify a plane made of a certain percentage of points

    /**
     * Calculates the number of iterations for the level of confidence
     * @param confidence desired percentage of identifying a plane of a certain percentage of points 
     * @param percentageOfPointsOnPlane the proportion of points on a plane in which would be likely selected 
     * @return the estimated number of iterations required to obtain a certain level of confidence to identify 
     *                  a plane made of a certain percentage of points.
     */
    public int getNumberOfIterations (double confidence, double percentageOfPointsOnPlane) {

        return (int) (Math.log(1 - confidence) / Math.log(1 - Math.pow(percentageOfPointsOnPlane, 3)));
    }
    
    /**
     * Method that finds the dominant plane from the PointCloud and put it into a new file. 
     *                 We do this by going through the num of iterations and selecting a random 
     *                 point. We then take random indexes to find the dominant points. We need to
     *                 compare these indexes to eps to do that as well.
     * @param numberOfIterations of random planes that will iterated to find random points to match 
     *                                  the random plane.
     * @param filename file with the dominant plane
     */
    public void run(int numberOfIterations, String filename) throws IndexOutOfBoundsException {

        // Load point cloud data into arraylist
        List<Point3D> dominantPoints = new ArrayList<Point3D>();

        for (int i = 0; i < numberOfIterations; i++) {

            ArrayList<Point3D> randomPoints = new ArrayList<Point3D>();
            int randomIndex = (int) (Math.random() * pc.points.size());;
            int bestSupport = 0;

            for (int j = 0; j < 3; j++) {
                randomIndex = pc.points.size();
                randomPoints.add(pc.points.get(randomIndex));  
            }

            // Get the random points from the plane to compare
            Plane3D randomPlane = new Plane3D(randomPoints.get(0), randomPoints.get(1), randomPoints.get(2));
            
            for (Point3D point : pc.points) {
                if (randomPlane.getDistance(point) < eps) {
                    bestSupport ++;
                }
            }
            // add those dominants points in the plane until iteration ends
            for (Point3D point : pc.points) {
                if (randomPlane.getDistance(point) < eps) {
                    dominantPoints.add(point);
                }
            }
        }
        // remove all points from the cloud pf the most dominant plane
        pc.points.removeAll(dominantPoints); 

        //save the dominant plane to the new file (WHICH DOESN'T WORK) taken from PointCloud
        PointCloud dominantPlaneFromPC = new PointCloud();
        dominantPlaneFromPC.points = dominantPoints;
        dominantPlaneFromPC.save(filename);
        
    }

     /**
     * @param args main method to remove and save points to debug
     */
    public static void main(String[] args) {

        try {

            PointCloud pointCloudFile = new PointCloud("PointCloud2.xyz");
            PlaneRANSAC ransacAlgorithm = new PlaneRANSAC(pointCloudFile);
            ransacAlgorithm.run(ransacAlgorithm.getNumberOfIterations(1,10) , "newPointsFile2.xyz");

        } catch (FileNotFoundException e) {

            System.out.println("The file PointCloud2.xyz could not be found or opened.");
        }
    }
}
