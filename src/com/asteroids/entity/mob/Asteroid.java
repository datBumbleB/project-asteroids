package com.asteroids.entity.mob;

import com.asteroids.graphics.Screen;
import com.asteroids.graphics.Sprite;

public class Asteroid extends Mob {

    protected Sprite sprite, rotatedSprite;
    public int health = 100;
    private float speed = (float)Math.random() * 3;
    private double rot = 0;
    private final double ROT_SPEED;
    private double nx, ny;
    private double x, y;
    public static final int SPAWN_RATE = 5, DESPAWN_TIME = 500;
    private double rotSpeed;
    private int despawnTime;


    // TODO: better DESPAWN AND COLLISION and fix player hit detection



    public Asteroid(float x, float y, double dir, Sprite s) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.sprite = s;
        this.rotatedSprite = s;
        radius = 15;
        nx = Math.cos(dir) * speed;
        ny = Math.sin(dir) * speed;
        ROT_SPEED = Math.random() * 5;
        rotSpeed = ROT_SPEED;
        despawnTime = DESPAWN_TIME;
    }

    public void update() {
        move();

        if(rotSpeed > 0) rotSpeed --;
        if(rotSpeed <= 0) {
            rotSpeed = ROT_SPEED;
            rot += 2;
            rotatedSprite = Sprite.rotate(sprite, Math.toRadians(rot));
        }
        double distanceToPlayer = Math.sqrt((Player.getX() - Sprite.player.SIZE/2 - this.getX()) * (Player.getX() - Sprite.player.SIZE/2 - this.getX()) + (Player.getY() - Sprite.player.SIZE/2 - this.getY()) * (Player.getY() - Sprite.player.SIZE/2 - this.getY()));
        if(distanceToPlayer <= 25)
            level.endLevel();

        if(despawnTime > 0) despawnTime --;
        if(despawnTime <= 0) {
            despawnTime = DESPAWN_TIME;

            if(distanceToPlayer > 400)
                remove();

        }

    }

    public void render(Screen screen) {
        screen.renderProjectile((int)x,(int)y, rotatedSprite);
        screen.renderPlayer((int)x, (int)y, Sprite.center);
    }

    protected void move() {
        x += nx;
        y += ny;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
