package com.github.andrefbsantos.libdynticker.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Abstract template for Exchange
 * 
 * @author andre
 * 
 */
public abstract class Exchange {

	/**
	 * 
	 * @return Returns a list of pairs coin/exchange
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 */
	public abstract List<Pair> getPairs() throws IOException;

	/**
	 * 
	 * 
	 * @param pair
	 *            of exchange/coin
	 * 
	 * @return Returns the last value of the exchange for a given pair
	 *         coin/exchange. We have to use a double because some exchanges
	 *         measure values in satoshis (10^-8). A float has just 24 bits 
	 *         of precision which is not enough to represent 8 decimal places.
	 * @throws ExchangeException
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws JsonProcessingException
	 */
	public double getLastValue(Pair pair) throws IOException {
		return Double.parseDouble(this.getTicker(pair));
	}

	/**
	 * 
	 * @param pair
	 * @return
	 * @throws IOException
	 */
	protected abstract String getTicker(Pair pair) throws IOException;

	/**
	 * Returns the url of a ticker for a given pair
	 * 
	 * @param pair
	 * @return
	 */
	//protected abstract String getTickerURL(Pair pair);
	
	/**
	 * Parses a Json and extracts its last value
	 * 
	 * @param node
	 * @param pair
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws JsonProcessingException 
	 */
	//protected abstract String parseJSON(JsonNode node, Pair pair) throws IOException;

}
