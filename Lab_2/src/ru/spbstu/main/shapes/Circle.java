package ru.spbstu.main.shapes;


/**
 * Представление об окружности.
 * <p>
 * Окру́жность — замкнутая плоская кривая, которая состоит из
 * всех точек на плоскости, равноудалённых от заданной точки.
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%9E%D0%BA%D1%80%D1%83%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D1%8C">Окружность</a>
 */
public class Circle implements Ellipse {
    private Dot center;
    private float radius_;

    public Circle (Dot center, float radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус круга должен быть больше нуля!");
        }
        this.center = new Dot(center);
        this.radius_ = radius;
    }

    @Override
    public float getLength() {
        return (float) (2*Math.PI*radius_);
    }

    @Override
    public float getArea() {
        return (float) (Math.PI*Math.pow(radius_, 2));
    }
    /*
     * TODO: Реализовать класс 'Circle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */
}
