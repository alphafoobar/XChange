/**
 * The MIT License
 * Copyright (c) 2012 Xeiam LLC http://xeiam.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.xeiam.xchange.cryptonit.v2.service.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeiam.xchange.cryptonit.v2.dto.marketdata.CryptonitRate;
import com.xeiam.xchange.cryptonit.v2.dto.marketdata.CryptonitTicker;

/**
 * Test CryptonitTicker JSON parsing
 */
public class CryptonitTickerJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = CryptonitTickerJSONTest.class.getResourceAsStream("/marketdata/example-ticker-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    CryptonitTicker cryptonitTicker = mapper.readValue(is, CryptonitTicker.class);
    CryptonitRate cryptonitTickerRate = cryptonitTicker.getRate();

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptonitTickerRate.getLast()).isEqualTo(new BigDecimal("605.997"));
    assertThat(cryptonitTickerRate.getHigh()).isEqualTo(new BigDecimal("610.00000000"));
    assertThat(cryptonitTickerRate.getLow()).isEqualTo(new BigDecimal("572.73768613"));
    assertThat(cryptonitTicker.getVolume().getVolume("BTC")).isEqualTo(new BigDecimal("8.28600851"));
    assertThat(cryptonitTickerRate.getAsk()).isEqualTo(new BigDecimal("604.44900000"));
    assertThat(cryptonitTickerRate.getBid()).isEqualTo(new BigDecimal("584.79532163"));

  }

}