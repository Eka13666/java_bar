package ru.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {

    Point p1 = new Point(5, 6);
    Point p2 = new Point(7, 8);

    double d1 = p1.distance(p2);
    System.out.println("Расстояние между точками равно" + " = " + d1);


    Point p3 = new Point(100, 20);
    Point p4 = new Point(75, 18);

    double d2 = p3.distance(p4);
    System.out.println("Расстояние между точками равно" + " = " + d2);

    Point p5 = new Point(-300, 10);
    Point p6 = new Point(69, -3);

    double d3 = p5.distance(p6);
    System.out.println("Расстояние между точками равно" + " = " + d3);

    Point p7 = new Point(18.1, 0);
    Point p8 = new Point(36.3, 3);

    double d4 = p7.distance(p8);
    System.out.println("Расстояние между точками равно" + " = " + d4);

  }

}
