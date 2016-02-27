package com.asteroids.entity.mob;

import com.asteroids.entity.Entity;
import com.asteroids.entity.projectile.BulletProjectile;
import com.asteroids.entity.projectile.Projectile;
import com.asteroids.graphics.Screen;
import com.asteroids.graphics.Sprite;
import com.asteroids.input.Keyboard;

public class Player extends Mob {

    public Keyboard input;
    private double angle;
    private Sprite rotatedSprite;
    private int fireRate = BulletProjectile.FIRE_RATE;
    private int spawnRate = Asteroid.SPAWN_RATE;

    public static int x, y;

    private double speed = 0;
    private double nx, ny;

    public Player(Keyboard input) {
        this.input = input;
        this.angle = -90;
        this.rotatedSprite = Sprite.player;

    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        this.angle = -90;
        this.rotatedSprite = Sprite.rotate(Sprite.player, Math.toRadians(angle));
    }


    public void update() {


        nx = Math.cos(Math.toRadians(angle)) * speed;
        ny = Math.sin(Math.toRadians(angle)) * speed;

        if(fireRate > 0) fireRate --;
        if(spawnRate > 0) spawnRate --;

        if(input.up && speed <= 3.5) {
            speed += 0.1;
        }

        if(input.down && speed > 0) {
            speed -= 0.1;
        }

        if(input.left) {
            angle -= 2;
            if(angle <= -360) angle = 0;
            rotatedSprite = Sprite.rotate(Sprite.player, Math.toRadians(angle));

        }
        if(input.right){
            angle += 2;
            if(angle >= 360) angle = 0;
            rotatedSprite = Sprite.rotate(Sprite.player, Math.toRadians(angle));
        }

        if(nx != 0 || ny != 0) {
            move((int)nx, (int)ny, angle);
            x += nx;
            y += ny;
        }

        if(input.space && fireRate <= 0) {

            shoot(x - 9, y - 8, Math.toRadians(angle));

            fireRate = BulletProjectile.FIRE_RATE;
        }

        if(spawnRate <= 0) {
            generateAsteroid(x, y);
            spawnRate = Asteroid.SPAWN_RATE;
        }
        clear();
    }

    private void clear() {
        for(int i = 0; i < level.projectiles.size(); i ++) {
            Projectile p = level.projectiles.get(i);
            if(p.isRemoved()) level.projectiles.remove(i);
        }

        for(int i = 0; i < level.asteroids.size(); i ++) {
            Entity a = level.asteroids.get(i);
            if(a.isRemoved()) level.asteroids.remove(i);
        }
    }


    public void render(Screen screen) {
        screen.renderPlayer(x - Sprite.player.SIZE/2, y - Sprite.player.SIZE/2, rotatedSprite);
        screen.renderPlayer(x - Sprite.player.SIZE/2, y - Sprite.player.SIZE/2, Sprite.center);
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }
}
