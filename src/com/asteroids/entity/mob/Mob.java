package com.asteroids.entity.mob;

import com.asteroids.entity.Entity;
import com.asteroids.entity.projectile.BulletProjectile;
import com.asteroids.entity.projectile.Projectile;
import com.asteroids.graphics.Screen;
import com.asteroids.graphics.Sprite;

import java.util.Random;


public abstract class Mob extends Entity {

    protected double dir = 0;
    protected boolean moving = false;


    public void move(int xa, int ya, double dir) {

        this.dir = dir;

        if(!collision()) {
            x += xa;
            y += ya;
        }
    }

    protected void shoot(int x, int y, double dir) {
        Projectile p = new BulletProjectile(x, y, dir);
        level.addProjectile(p);
    }

    protected void generateAsteroid(int x, int y) {

        double angle = Math.random()*Math.PI*2;

        float xx = (float)Math.cos(angle)*500;
        float yy = (float)Math.sin(angle)*500;
        Random rand = new Random();
        Asteroid a = new Asteroid(x + xx, y + yy, - angle, (( rand.nextInt(2) == 1) ? Sprite.asteroid : Sprite.asteroid2));
        level.addAsteroid(a);
    }

    public void update() {

    }

    private boolean collision() {
        return false;
    }

    public void render(Screen screen) {

    }
}
