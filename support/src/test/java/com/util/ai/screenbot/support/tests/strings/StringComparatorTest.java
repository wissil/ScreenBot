package com.util.ai.screenbot.support.tests.strings;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.util.ai.screenbot.support.tests.config.SupportTestBase;

import static com.util.ai.screenbot.support.strings.StringComparator.consideredEqual;

public class StringComparatorTest extends SupportTestBase {
	
	@Test
	public void stringSimilarityTest_SimilarStrings() {		
		final String s1 = 
				"lsen Lou vs Neflyanik Atmet13usk";
		
		final String s2 = 
				"Tsen Tou vs Neftyanik Almetievsk";
		
		assertTrue(consideredEqual(s1, s2));
	}
	
	@Test
	public void stringSimilarityTest_NonSimilarStrings() {		
		final String s1 = 
				"Manchester United vs Manchester City";
		
		final String s2 = 
				"Tsen Tou vs Neftyanik Almetievsk";
		
		assertFalse(consideredEqual(s1, s2));
	}
}
