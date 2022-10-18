package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    /*hello("Lera");
    hello("Evgeniy");
    Square s = new Square(5);
    System.out.println("square area with side " + s.l + "=" + s.area());
    Rectangle r= new Rectangle(4,6);
    System.out.println("area of a rectangle with sides  " + r.a +" and " + r.b + "=" + r.area());*/
    Point p1=new Point(-1,3);
    Point p2=new Point(6,2);
    System.out.println("Distance between two points:"+p2.distance(p1));




  }


  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }


  }

