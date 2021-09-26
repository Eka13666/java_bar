package ru.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {

    Point p1 = new Point(5, 6);
    Point p2 = new Point(7, 8);

    double d1 = Point.distance(p1, p2);
    System.out.println("Расстояние между точками равно" + " = " + d1);


    Point p3 = new Point(100, 20);
    Point p4 = new Point(75, 18);

    double d2 = Point.distance(p3, p4);
    System.out.println("Расстояние между точками равно" + " = " + d2);
  }

}
