package com.asteroids;

import com.asteroids.entity.Entity;
import com.asteroids.entity.mob.Player;
import com.asteroids.graphics.Screen;
import com.asteroids.input.Keyboard;
import com.asteroids.level.Level;
import com.asteroids.level.RandomLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Clasa responsabila cu crearea ferestrei si rularea programului
 *
 */

public class Game extends Canvas implements Runnable {

    public static int width;
    public static int height;
    public static int scale;
    public static String title = "Asteroids";

    private Thread thread;
    private Keyboard key;
    public JFrame frame;
    private boolean running = false;

    private Screen screen;

    private Level level;
    private Player player;

    private BufferedImage image;
    private int[] pixels ;

    public Game(int resolution, int quality) {

        if(resolution == 1) {
            width = 900;
            scale = 1;
        }

        else if(resolution == 0) {
            width = 300;
            scale = 3;
        }
        height = width / 16 * 9;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = new RandomLevel(5000, 5000);
        player = new Player(2500 * 16, 2500 * 16, key);
        Entity.init(level);
        addKeyListener(key);
    }

    /**
     * deoarece clasa implementeaza Runnable, in momentul in care functia start este apelata
     * este generat un thread nou si functia run porneste
     */

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;

        requestFocus();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                update();
                updates ++;
                delta --;
            }

            render();
            frames ++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + "fps");
                frame.setTitle(title + " | " + updates + " ups, " + frames + "fps");
                updates = 0;
                frames = 0;
            }
        }

        stop();
    }

    public void update(){
        key.update();
        if(!level.ended){
            player.update();
            level.update();
        }

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        int xScroll = player.x - screen.getWidth()/2;
        int yScroll = player.y - screen.getHeight()/2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);


        for(int i = 0; i < pixels.length; i ++)
            pixels[i] = screen.pixels[i];

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", 0, 14));
        g.drawString("X: " + player.x + ", Y: " + player.y, 10, 20);
        if(!level.ended)g.drawString("Score: " + level.score, (width/2 - 40) * scale, 20);
        if(level.ended) {
            g.setFont(new Font("Verdana", 0, 25));
            g.drawString("Sfarsitul jocului. Punctaj:  " + level.score, (height/2 - 12) * scale, (width/2 - 70) * scale);
        }
        g.dispose();
        bs.show();
    }

}
