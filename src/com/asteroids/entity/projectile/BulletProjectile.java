package com.asteroids.entity.projectile;

import com.asteroids.graphics.Screen;
import com.asteroids.graphics.Sprite;


public class BulletProjectile extends Projectile {

    public static final int FIRE_RATE = 10;

    public BulletProjectile(int x, int y, double dir) {
        super(x, y, dir);
        damage = 50;
        speed = 10.0;
        range = 3000;
        radius = 4;
        sprite = Sprite.rotate(Sprite.bullet, dir);
        nx = Math.cos(angle) * speed;
        ny = Math.sin(angle) * speed;
    }

    public void update() {
        move();
    }

    protected void move() {
        x += nx;
        y += ny;
        if(distance() > range) remove();
    }

    private double distance() {
        double dist;
        dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
       return dist;
    }

    public void render(Screen screen) {
        screen.renderProjectile((int)x,(int)y, sprite);
    }
}
