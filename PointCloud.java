/**
 * TINA TRINH - 300175427
 * CSI2120
 * Project Part 1
 * Feb 7th, 2023
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class PointCloud {

    // Declare a list for our points and use randomGenerator to receive a random point
    public List<Point3D> points;
    public Random randomGenerator;


    // An empty constructor that constructs an empty PointCloud
    public PointCloud() {

        points = new ArrayList<Point3D>();
        this.points = new ArrayList<Point3D>();
        this.randomGenerator = new Random();
    }

    /**
     * Constructor from xyz file to read the file, split it, and parse through the coordinates to get points to add to our PointCloud.
     * @param filename the file ib which we will read the info from to put in out PointCloud 
     * @throws FileNotFoundException throws exception in case that we have a problem with reading the file
     */

    public PointCloud(String filename) throws FileNotFoundException {

        //read file (better than scanner because it is faster and read an array list
        points = new ArrayList<>();

        try {

            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while((line = br.readLine()) != null) {

                // Split will into the coodrdinates from below
                String[] coordinates = line.split("\\s+");

                // We will create an object from the point3d with those coordinates
                double x,y,z;

                // We will use double parsing to convert string into double values from a file
                x = Double.parseDouble(coordinates[0]);
                y = Double.parseDouble(coordinates[1]);
                z = Double.parseDouble(coordinates[2]);

                // add it to our new point arraylist
                Point3D point = new Point3D(x,y,z);
                points.add(point);
            }
            br.close();
            fr.close();
        }
        // if there is an error withreading the file, then an error will be printed
        catch (IOException e){
            System.out.println("Error with reading the file: " + e.getMessage());
        }
    }

    /**
     * Add our points from Point3D to the PointCloud
     * @param pt passes the points from Point3D to add it here
    */
    public void addPoint(Point3D pt) {

        this.points.add(pt);
    }

    /**
     * Method that returns a random point from the cloud
     * @return a random Point3D from the list stored in the point variable
     * 
     */
    public Point3D getPoint() {

        randomGenerator = new Random();

        if (this.points.size() > 0) {

            // We also use nextInt() to generate a random index in the points list
            int randomIndex = this.randomGenerator.nextInt(this.points.size());
            return this.points.get(randomIndex);

        } else {

            return null;
        }
    }

    /**
     * Save method that saves the point cloud into a xyz file. We need to write into our new file and for each point, we will 
     *       write in the toString format.
     * @param filename is the file in which we will save the read file from before into this new one.
     */
    public void save(String filename) {

        try {
            // write file this time
            File file = new File(filename);
            BufferedWriter write = new BufferedWriter(new FileWriter(file));
            for (Point3D point : points) {

                // we are writing it out in a proper format in point3D to toString
                write.write(point.toString());
                write.newLine();
            }
            write.close();
        }
        catch (IOException e) {
            System.out.println("Error with writing the file: " + e.getMessage());
        }
    }

    /**
     * Iterator method that returns an iterator (from above) to the points in the cloud
     * @return the iterator from Point3D
     */
    public Iterator<Point3D> iterator() {
        
        Iterator<Point3D> iterator = new Iterator<Point3D>() {
            int currentIndex = 0;

            /**
             * @return a boolean value that is next
             */
            public boolean hasNext() {

                return currentIndex < points.size();
            }

            /**
             * @return the current index
             */
            public Point3D next() {

                if (!hasNext()) {

                    throw new NoSuchElementException();
                }
                return points.get(currentIndex++);
            }

            /**
             * @return none since it is a void, but removes the current index from PointCloud
             */
            public void remove() {

                if (currentIndex == 0) {

                    throw new IllegalStateException();
                }
                points.remove(--currentIndex);             
            }
        };
        return iterator;
    }

    /**
     * @param args main method to add points to debug
     */
    public static void main(String[] args) {

        try {

            PointCloud cloudFile = new PointCloud("PointCloud2.xyz");
            cloudFile.addPoint(new Point3D(3,1,4));
            cloudFile.save("newPointsFile.xyz");

        } catch (FileNotFoundException e) {

            System.out.println("The file PointCloud2.xyz could not be found or opened.");
        }
    }
}



