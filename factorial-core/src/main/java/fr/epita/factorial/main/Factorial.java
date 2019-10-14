package fr.epita.factorial.main;

public class Factorial {

	public static long calculateFactorial(int number)
	{
		if (number < 0)
		     throw new IllegalArgumentException("Negative numbers not allowed!"); 
		if (number == 0 || number == 1)
			return 1;
		return number * calculateFactorial(number - 1);
	}
	
	public static void main(String[] args)
	{
		System.out.println(calculateFactorial(5));
	}
}