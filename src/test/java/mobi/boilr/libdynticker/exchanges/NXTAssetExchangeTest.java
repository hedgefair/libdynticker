package mobi.boilr.libdynticker.exchanges;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mobi.boilr.libdynticker.core.ExchangeTest;
import mobi.boilr.libdynticker.core.Pair;

public class NXTAssetExchangeTest extends ExchangeTest {
	@Override
	@Before
	public void setUp() throws Exception {
		testExchange = new NXTAssetExchange(1000);
	}

	@Override
	@After
	public void tearDown() throws Exception {}

	@Override
	@Test
	public void testGetPairs() {
		List<Pair> pairs;
		try {
			pairs = testExchange.getPairs();
			Assert.assertTrue(pairs.contains(new Pair("nXtGenGHS", "NXT")));
			Assert.assertFalse(pairs.contains(new Pair("Invalid", "NXT")));
		} catch(IOException e) {
			Assert.fail();
		}
	}

	@Test
	public void testParseTicker() {
		try {
			Pair pair = new Pair("mgwBTC", "NXT", "17554243582654188572");
			JsonNode node = (new ObjectMapper().readTree(new File("src/test/json/nxtassetexchange-ticker.json")));
			String lastValue = testExchange.parseTicker(node, pair);
			Assert.assertEquals(".00019755", lastValue);
		} catch(IOException e) {
			Assert.fail();
		}
	}

}
