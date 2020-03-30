package ru.spbstu.main;

import ru.spbstu.main.shapes.*;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[10];
        shapes[0] = new Circle(new Dot(8.3f, 4.5f), 3.0f);
        shapes[1] = new Triangle(new Dot(8.3f, 4.5f), new Dot(5.0f, 6.3f), new Dot(6.9f, 3.5f), 55);
        shapes[2] = new Rectangle(7.3f, 5.0f, 90);
        shapes[3] = new Circle(new Dot(11.3f, 9.5f), 1.0f);
        shapes[4] = new Circle(new Dot(1.3f, 7.5f), 1.0f);
        shapes[5] = new Rectangle(8.3f, 4.5f, 5);
        shapes[6] = new Triangle(new Dot(3.3f, 55.5f), new Dot(11.7f, 6.9f), new Dot(15.9f, 10.5f), 78);
        shapes[7] = new Circle(new Dot(8.3f, 4.5f), 5.0f);
        shapes[8] = new Rectangle(4.3f, 8.0f, 65);
        shapes[9] = new Circle(new Dot(5.5f, 5.5f), 5.5f);

        for (Shape shape : shapes) {
            System.out.println(shape.getArea() + " " + shape.getClass().getSimpleName());
        }
        findMaxArea(shapes);
    }
        private static void findMaxArea(Shape [] shapes) {
        if (shapes.length == 0) {
            throw new RuntimeException("В массиве нет фигур");
        }
            float maxArea = 0.0f;
            Shape maxShape = shapes[0];
            for (Shape shape:shapes) {
                if (shape.getArea() > maxArea) {
                    maxArea = shape.getArea();
                    maxShape = shape;
                }

            }
            System.out.println("Фигура с максимальной площадью - " + maxShape.getClass().getSimpleName() + " " + maxArea);
        }
        /*
         * TODO: Выполнить действия над массивом 'shapes'
         *
         * 1. Проинициализировать переменную 'shapes' массивом
         *    содержащим 10 произвольных фигур. Массив должен
         *    содержать экземпляры классов Circle, Rectangle
         *    и Triangle.
         *
         * 2. Найти в массиве 'shapes' фигуру с максимальной
         *    площадью. Для поиска фигуры необходимо создать
         *    статический метод в текущем классе (Main).
         */
    }

