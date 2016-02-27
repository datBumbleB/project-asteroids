package com.asteroids.level;

import com.asteroids.entity.Entity;
import com.asteroids.entity.mob.Asteroid;
import com.asteroids.entity.projectile.Projectile;
import com.asteroids.graphics.Screen;

import java.util.ArrayList;
import java.util.List;


public class Level {

    protected int width, height;
    protected int[] tiles;
    public int score = 0;
    public boolean ended = false;
    public List<Asteroid> asteroids = new ArrayList<>();
    public List<Projectile> projectiles = new ArrayList<Projectile>();

    public Level(int width, int height)
    {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();

    }

    public Level(String path) {
        loadLevel(path);
    }

    protected void generateLevel() {

    }

    private void loadLevel(String path) {

    }

    public void update() {
        for(int i = 0; i < asteroids.size(); i ++)
            asteroids.get(i).update();
        for(int i = 0; i < projectiles.size(); i ++)
            projectiles.get(i).update();

        for(Projectile p : projectiles) {
            for(Asteroid a : asteroids) {
                if(checkSphereCollision(a, p)) {
                    a.health -= p.getDamage();
                    if(a.health <= 0)
                    {
                        score ++;
                        a.remove();
                    }

                    p.remove();
                    System.out.println("collision");
                }
            }
        }
    }

    public boolean checkSphereCollision(Asteroid a, Projectile b) {
        double distance = Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));

        return (distance < a.radius + b.radius) ? true : false;
    }

    private void time() {

    }

    public void endLevel() {
        ended = true;
    }


    public void addProjectile(Projectile p) {
        projectiles.add(p);
    }

    public void addAsteroid(Asteroid a) {
        asteroids.add(a);
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);

        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.getWidth() + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.getHeight() + 16) >> 4;

        for(int y = y0; y < y1; y ++) {
            for(int x = x0; x < x1; x ++) {
                getTile(x, y).render(x, y, screen);
            }
        }

        for(int i = 0; i < asteroids.size(); i ++)
            asteroids.get(i).render(screen);
        for(int i = 0; i < projectiles.size(); i ++)
            projectiles.get(i).render(screen);
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        switch(tiles[x + y * width])
        {
            case 0:
                return Tile.space;

            case 1:
                return Tile.space2;

            case 2:
                return Tile.space3;
        }

        return Tile.voidTile;
    }

}
