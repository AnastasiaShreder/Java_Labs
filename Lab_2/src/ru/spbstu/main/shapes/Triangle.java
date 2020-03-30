package ru.spbstu.main.shapes;

/**
 * Представление о треугольнике.
 * <p>
 * Треуго́льник (в евклидовом пространстве) — геометрическая
 * фигура, образованная тремя отрезками, которые соединяют
 * три точки, не лежащие на одной прямой. Указанные три
 * точки называются вершинами треугольника, а отрезки —
 * сторонами треугольника. Часть плоскости, ограниченная
 * сторонами, называется внутренностью треугольника: нередко
 * треугольник рассматривается вместе со своей внутренностью
 * (например, для определения понятия площади).
 *
 * @see <a href="https://ru.wikipedia.org/wiki/%D0%A2%D1%80%D0%B5%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA">Треугольник</a>
 */
public class Triangle implements Polygon {
     private Dot pointA, pointB, pointC;
     private int angle;
     private float sideA, sideB, sideC;

     public Triangle (Dot pointA, Dot pointB, Dot pointC, int angle) {
         this.pointA = new Dot(pointA);
         this.pointB = new Dot(pointB);
         this.pointC = new Dot(pointC);
         this.angle = angle;
         this.sideA = (float) Math.sqrt(Math.pow((pointA.getX() - pointB.getX()), 2) + Math.pow((pointA.getY() - pointB.getY()), 2));
         this.sideB = (float) Math.sqrt(Math.pow((pointB.getX() - pointC.getX()), 2) + Math.pow((pointB.getY() - pointC.getY()), 2));
         this.sideC = (float) Math.sqrt(Math.pow((pointC.getX() - pointA.getX()), 2) + Math.pow((pointC.getY() - pointA.getY()), 2));
         if (!(((sideA + sideB) > sideC) && ((sideA + sideC) > sideB) && ((sideC + sideB) > sideA))){
             throw new RuntimeException("Данная фигура не является треугольником");
         }
     }

     @Override
     public float getPerimeter() { return (sideA + sideB + sideC); }
     @Override
     public float getArea() {
         float p =  getPerimeter()/2; //p - полупериметр
         return (float) Math.sqrt(p*(p - sideA)*(p - sideB)*(p - sideC));
     }
     @Override
     public int getRotation(){ return angle; }

    /*
     * TODO: Реализовать класс 'Triangle'
     * 1. Используйте наследование.
     * 2. Реализуйте все абстрактные методы.
     */
}
