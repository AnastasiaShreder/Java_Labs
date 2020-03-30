package ru.spbstu.main.shapes;

public class Dot implements Point {
    private float x;
    private float y;

    public Dot(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public Dot(Dot dot) {
       this.x = dot.getX();
       this.y = dot.getY();
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
