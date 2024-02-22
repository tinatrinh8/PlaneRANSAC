# 🚀 PlaneRANSAC
Algorithm for fitting model to data that contains large outliers.

## 🧘🏻‍♀️ Overview
The RANSAC algorithm identifies the dominant planes in 3D point clouds, simulating LiDAR data processing for autonomous vehicles.

## 🪐 Objective
Detect the three most dominant planes in each provided point cloud (`PointCloud1.xyz`, `PointCloud2.xyz`, `PointCloud3.xyz`), and segregate the points belonging to these planes.

## 🎡 How to Solve ?
- Use RANSAC to iteratively find planes with the max number of points within a threshold distance.
- Output the points of each dominant plane into separate `.xyz` files, and the residual cloud into another file.
