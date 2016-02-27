package com.asteroids.graphics;

/**
 *
 */
public class Sprite {

    public final int SIZE;
    private int x, y;
    public int[] pixels;

    private SpriteSheet sheet;

    public static Sprite space = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite space2 = new Sprite(16, 0, 1, SpriteSheet.tiles);
    public static Sprite space3 = new Sprite(16, 0, 2, SpriteSheet.tiles);

    public static Sprite bullet = new Sprite(16, 4, 0, SpriteSheet.tiles);

    public static Sprite voidSprite = new Sprite(16, 0);

    public static Sprite player = new Sprite(32, 1, 0, SpriteSheet.tiles);

    public static Sprite center = new Sprite(32, 0, 2, SpriteSheet.tiles);
    public static Sprite asteroid = new Sprite(32, 2, 1, SpriteSheet.tiles);

    public static Sprite asteroid2 = new Sprite(32, 1, 1, SpriteSheet.tiles);

    public Sprite(int size, int x, int y, SpriteSheet spriteSheet){
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = spriteSheet;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        setColour(color);
    }

    public Sprite(int[] pixels, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.pixels = new int[pixels.length];
        for(int i = 0; i < pixels.length; i ++) {
            this.pixels[i] = pixels[i];
        }
    }

    public static Sprite rotate(Sprite sprite, double angle) {
        return new Sprite(rotate(sprite.pixels, sprite.SIZE, sprite.SIZE, angle), sprite.SIZE, sprite.SIZE);
    }

    private static int[] rotate(int[] pixels, int width, int height, double angle) {
        int[] result = new int[width * height];

        double nx_x = rotX(-angle, 1.0, 0.0);
        double nx_y = rotY(-angle, 1.0, 0.0);
        double ny_x = rotX(-angle, 0.0, 1.0);
        double ny_y = rotY(-angle, 0.0, 1.0);

        double x0 = rotX(-angle, -width/2.0, -height/2.0) + width/2.0;
        double y0 = rotY(-angle, -width/2.0, -height/2.0) + height/2.0;

        for(int y = 0; y < height; y ++) {
            double x1 = x0;
            double y1 = y0;
            for(int x = 0; x < width; x ++) {
                int xx = (int) x1;
                int yy = (int) y1;
                int col;
                if(xx < 0 || xx >= width || yy < 0 || yy >= height)
                    col = 0xFFFF00FF;
                else
                    col = pixels[xx + yy * width];
                result[x + y * width] = col;
                x1 += nx_x;
                y1 += nx_y;
            }
            x0 += ny_x;
            y0 += ny_y;
        }

        return result;
    }

    private static double rotX(double angle, double x, double y) {
        double cos = Math.cos(angle - Math.PI/2);
        double sin = Math.sin(angle - Math.PI/2);
        return x * cos + y * -sin;
    }

    private static double rotY(double angle, double x, double y) {
        double cos = Math.cos(angle - Math.PI/2);
        double sin = Math.sin(angle - Math.PI/2);
        return x * sin + y * cos;
    }

    private void setColour(int color) {
        for(int i = 0; i < SIZE * SIZE; i ++) {
            pixels[i] = color;
        }
    }
    private void load() {
        for(int y = 0; y < SIZE; y ++)
            for(int x = 0; x < SIZE; x ++) {
                pixels [x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
    }

}
