package com.asteroids.entity;

import com.asteroids.graphics.Screen;
import com.asteroids.level.Level;

import java.util.Random;

public abstract class Entity {

    public int x, y;
    private boolean removed = false;
    public int radius;
    protected static Level level;
    protected final Random random = new Random();


    public void update() {

    }

    public void render(Screen screen) {

    }

    public static void init(Level l) {
        level = l;
    }

    public void remove() {
        //sterge din nivel
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}
