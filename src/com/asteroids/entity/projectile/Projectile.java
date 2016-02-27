package com.asteroids.entity.projectile;

import com.asteroids.entity.Entity;
import com.asteroids.graphics.Sprite;


public abstract class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle; // in rad
    protected Sprite sprite;
    protected double x, y;
    protected double nx, ny;
    protected double speed, range, damage;


    public Projectile(int x, int y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;

    }

    protected void move() {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDamage() {
        return damage;
    }
}
