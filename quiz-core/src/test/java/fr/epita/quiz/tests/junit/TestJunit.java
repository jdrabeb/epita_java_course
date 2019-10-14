package fr.epita.quiz.tests.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestJunit {
	
	@Before
	public void prepareForEach()
	{
		System.out.println("beforeEach");
	}
	
	@Test
	public void firstSuccessTest()
	{
		System.out.println("success");
	}
	
	@Test
	public void firstFailTest()
	{
		System.out.println("fail");
		Assert.fail();
	}

	@After
	public void prepareAfterForEach()
	{
		System.out.println("afterEach");
	}
	
	@AfterClass
	public static void afterAll()
	{
		System.out.println("afterAll");
	}
}
