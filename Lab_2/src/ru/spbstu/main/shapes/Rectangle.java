package ru.spbstu.main.shapes;

/**
 * Представление о прямоугольнике.
 * <p>
 * Прямоугольник — четырехугольник, у которого все углы
 * прямые (равны 90 градусам).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D1%8F%D0%BC%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Прямоугольник</a>
 */
public class Rectangle implements Polygon {
    private float width, height;
    private int angle;

    public Rectangle(float width, float height, int angle) {
        this.width = width;
        this.height = height;
        this.angle = angle;
    }

    @Override
    public float getPerimeter() { return ((width + height)*2); }

    @Override
    public float getArea() { return (width * height); }

    @Override
    public int getRotation() {
        return angle;
    }
    /*
     * TODO: Реализовать класс 'Rectangle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */
}
