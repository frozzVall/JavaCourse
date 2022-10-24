package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {
  @Test
  public void testPoint() {
    Point p1 = new Point(-1, 3);
    Point p2 = new Point(6, 2);
    double delta = 0.001;
    Assert.assertEquals(p2.distance(p1), 7.071, delta);
  }

  @Test
  public void testPoint1() {
    Point p1 = new Point(-1, 3);
    Point p2 = new Point(6, 2);
    double delta = 0.001;
    Assert.assertEquals(p2.distance(p1), 7.0710678118654755, delta);
  }
}
