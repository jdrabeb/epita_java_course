package fr.epita.factorial.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import fr.epita.factorial.main.Factorial;

public class FactorialTest {
	
	@Test
	public void testNegativeFactorial()
	{
		Exception e = null;
		try
		{
			Factorial.calculateFactorial(-1);
		}
		catch(IllegalArgumentException iae)
		{
			e = iae;
		}
		Assert.assertNotNull(e);
	}
	
	@Test
	public void testCalculateFactorialOne()
	{
        assertEquals(1, Factorial.calculateFactorial(0));
	}

	@Test
	public void testCalculateFactorialFive()
	{
        assertEquals(120, Factorial.calculateFactorial(5));
	}
}
