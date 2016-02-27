package com.asteroids.math;

public class Vector2d {
    private double x;
    private double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public static double distance(Vector2d a, Vector2d b) {
        return Math.sqrt(Math.abs((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY())));
    }

    public double max() {
        return Math.max(x, y);
    }

    public Vector2d add(Vector2d r) {
        return new Vector2d(x + r.getX(), y + r.getY());
    }

    public Vector2d add(double r) {
        return new Vector2d(x + r, y + r);
    }

    public Vector2d sub(Vector2d r) {
        return new Vector2d(x - r.getX(), y - r.getY());
    }

    public Vector2d sub(double r) {
        return new Vector2d(x - r, y - r);
    }

    public Vector2d mul(Vector2d r) {
        return new Vector2d(x * r.getX(), y * r.getY());
    }

    public Vector2d mul(double r) {
        return new Vector2d(x * r, y * r);
    }

    public Vector2d div(Vector2d r) {
        return new Vector2d(x / r.getX(), y / r.getY());
    }

    public Vector2d div(double r) {
        return new Vector2d(x / r, y / r);
    }

    public Vector2d abs() {
        return new Vector2d(Math.abs(x), Math.abs(y));
    }

    public Vector2i toVector2i() {
        return new Vector2i((int) x, (int) y);
    }

    public String toString() {
        return "(" + x + " " + y + ")";
    }

    public Vector2d set(double x, double y) { this.x = x; this.y = y; return this; }
    public Vector2d set(Vector2d r) { set(r.getX(), r.getY()); return this; }
    public Vector2d set(Vector2i r) {set(r.getX(), r.getY()); return this; };

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean equals(Vector2d r) {
        return x == r.getX() && y == r.getY();
    }
}
