package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance1() {
    Point p1 = new Point(5, 6);
    Point p2 = new Point(7, 8);
    Assert.assertEquals(p1.distance(p2), 2.8284271247461903);
  }

  @Test
  public void testDistance2() {
    Point p3 = new Point(100, 20);
    Point p4 = new Point(75, 18);
    Assert.assertEquals(p3.distance(p4), 25.079872407968907);
  }

  @Test
  public void testDistance3() {
    Point p5 = new Point(-300, 10);
    Point p6 = new Point(69, -3);
    Assert.assertEquals(p5.distance(p6), 369.2289262774519);
  }

  @Test
  public void testDistance4() {
    Point p7 = new Point(18.1, 0);
    Point p8 = new Point(36.3, 3);
    Assert.assertEquals(p7.distance(p8), 18.445595680270124);

  }
}
