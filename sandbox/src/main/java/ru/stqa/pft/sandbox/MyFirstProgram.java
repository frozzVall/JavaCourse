package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("Lera");
    hello("Evgeniy");
    double len = 5;
    System.out.println("square area with side " + len + "=" + area(len));
    double side1=4;
    double side2=6;
    System.out.println("area of a rectangle with sides  " + side1 +" and " + side2 + "=" + area(side1,side2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double area(double I) {
    return I * I;
  }
  public static double area(double a, double b){
    return a*b;
  }
}