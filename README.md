# PlaneRANSAC 🚀📀🤹🏻‍♀️🍣🐚
Algorithm for fitting model to data that contains large outliers.

## 🧘🏻‍♀️ Overview
The RANSAC algorithm identifies the dominant planes in 3D point clouds, simulating LiDAR data processing for autonomous vehicles.

## 🪐 Objective
Detect the three most dominant planes in each provided point cloud (`PointCloud1.xyz`, `PointCloud2.xyz`, `PointCloud3.xyz`), and segregate the points belonging to these planes.

## 🎡 How to Solve ?
- Use RANSAC to iteratively find planes with the max number of points within a threshold distance.
- Output the points of each dominant plane into separate `.xyz` files, and the residual cloud into another file.
- Languages used: Java, GO, Prolog and Scheme. The implementation adheres to the object-oriented programming paradigm in Java.

## 🛋️ Classes
- `Point3D.java`: Represents a 3D point with getX(), getY(), and getZ() methods.
- `Plane3D.java`: Defines a plane from 3 Point3D instances or plane equation parameters, 7 calculates the distance from a point to the plane.
- `PointCloud.java`: Manages Point3D objects and helps add points, find a random point, and save the cloud to an XYZ file.
- `PlaneRANSAC.java`: Performs the RANSAC algorithm using a PointCloud, including setting epsilon value, estimating the number of iterations for a given confidence level, and save the dominant plane.
