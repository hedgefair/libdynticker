package mobi.boilr.libdynticker.exchanges;

import java.io.File;
import java.io.IOException;
import java.util.List;

import mobi.boilr.libdynticker.core.ExchangeTest;
import mobi.boilr.libdynticker.core.Pair;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoinMateExchangeTest extends ExchangeTest {
	@Override
	@Before
	public void setUp() throws Exception {
		testExchange = new CoinMateExchange(1000);
	}

	@Override
	@After
	public void tearDown() throws Exception {}

	@Test
	public void testGetPairs() {
		List<Pair> pairs;
		try {
			pairs = testExchange.getPairs();
			Assert.assertTrue(pairs.contains(new Pair("BTC", "USD")));
			Assert.assertFalse(pairs.contains(new Pair("Invalid", "BTC")));
		} catch(IOException e) {
			Assert.fail();
		}
	}

	@Test
	public void testParseJson() {
		try {
			Pair pair = new Pair("BTC", "USD");
			JsonNode node = (new ObjectMapper().readTree(new File("src/test/json/coinmate-ticker.json")));
			String lastValue = testExchange.parseJSON(node, pair);
			Assert.assertEquals("287.61", lastValue);
		} catch(IOException e) {
			Assert.fail();
		}
	}

}