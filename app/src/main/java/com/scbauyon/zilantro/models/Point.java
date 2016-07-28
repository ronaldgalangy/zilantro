package com.scbauyon.zilantro.models;

import java.util.ArrayList;

/**
 * Created by rygalang on 1/28/16.
 */
public class Point {
    private int point;

    private ArrayList<Point> connectedPoints;

    public Point() {
    }

    public Point(int point) {
        this.point = point;
    }

    public Point(int point, ArrayList<Point> connectedPoints) {
        this.point = point;
        this.connectedPoints = connectedPoints;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ArrayList<Point> getConnectedPoints() {
        return connectedPoints;
    }

    public void setConnectedPoints(ArrayList<Point> connectedPoints) {
        this.connectedPoints = connectedPoints;
    }
}
