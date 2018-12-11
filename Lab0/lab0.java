


import java.util.Scanner;                         //import Scanner package

public class lab0 {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    int numProblems = scanner.nextInt();          //retrieve the number of lines
  
    for(int i = 0; i < numProblems; ++i){
      int a = scanner.nextInt();                  //retrieve the first integer
      int b = scanner.nextInt();                  //retrieve the second integer
 
      int g = gcd(a,b);
      int l = lcm(a,b);
 
      System.out.println(g + " " + l);
    }

    scanner.close();
 }

 public static int gcd(int a, int b){
  // Find the greatest common divisor of a and b
  // Hint: Use Euclids Algorithm
  // SOURCE for Euclids Algorithm: http://sites.math.rutgers.edu/~greenfie/gs2004/euclid.html
  
  //algorithm states we have found the gcd when remainder is 0
  if (b == 0){
  	  return a;
  }
  
  //cycle through and divide by the smaller number
  else{
  	  return gcd(b, a%b);
  }
  
 }

 public static int lcm(int a, int b){
  // Find the least common multiple of a and b
  // Hint: Use the gcd of a and b
  // SOURCE for LCM: https://www.mathportal.org/calculators/numbers-calculators/gcd-lcm-calculator.php
  // LCM equation = (a*b) / gcd(a,b)
  
  int lcmMult = (a*b);
  int lcmVal = (lcmMult / gcd(a,b));
  return lcmVal;
  
 }
}
