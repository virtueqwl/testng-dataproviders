package com.github.sergueik.testng;
/**
 * Copyright 2018-2019 Serguei Kouzmine
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JSONProviderTest extends CommonTest {

	@Test(enabled = true, singleThreaded = false, threadPoolSize = 1, invocationCount = 1, description = "# of articless for specific keyword", dataProvider = "JSON", dataProviderClass = JSONParametersProvider.class)
	@JSONDataFileParameters(name = "data.json", dataKey = "test", columns = "keyword,count"
	/* columns attribute should not be empty */)
	public void testWithJSONDataFile(String searchKeyword, String expectedCount)
			throws InterruptedException {
		dataTest(searchKeyword, expectedCount);
	}

	@Test(enabled = true, singleThreaded = false, threadPoolSize = 1, invocationCount = 1, description = "# of articless for specific keyword", dataProvider = "JSON", dataProviderClass = JSONParametersProvider.class, expectedExceptions = java.lang.AssertionError.class)
	@JSONDataFileParameters(name = "data.json", dataKey = "test", columns = "count,keyword"
	/* columns attribute swapped */)
	public void testParamColumnSwap(String searchKeyword, String expectedCount)
			throws InterruptedException {
		dataTest(searchKeyword, expectedCount);
	}

	// Method JSONProviderTest.test_with_static_JSON_missed_parameter_order should
	// throw an exception of type class java.lang.AssertionError
	@Test(enabled = true, dataProvider = "static disconnected data provider", expectedExceptions = java.lang.AssertionError.class)
	public void testMissedParameterOrder(Object expectedCount,
			Object searchKeyword) throws InterruptedException {
		dataTest(searchKeyword.toString(), expectedCount.toString());
	}

	@Test(enabled = true, dataProvider = "static disconnected data provider")
	public void testWithStaticJSON(Object searchKeyword, Object expectedCount)
			throws InterruptedException {
		dataTest(searchKeyword.toString(), expectedCount.toString());
	}

	// static disconnected data provider
	@DataProvider(parallel = true, name = "static disconnected data provider")
	public Object[][] dataProviderInline() {
		return new Object[][] { { "junit", 100.0 }, { "testng", 30.0 },
				{ "spock", 10.0 }, };
	}

}
