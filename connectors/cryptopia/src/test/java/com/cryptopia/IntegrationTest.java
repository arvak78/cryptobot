package com.cryptopia;

import com.commons.exceptions.MarshallException;
import com.commons.model.Response;
import com.cryptopia.pub.CryptopiaPublicApi;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by manel on 19/01/18.
 */
public class IntegrationTest {

    @Test
    public void testGetCurrencies() throws MarshallException {

        CryptopiaPublicApi app = new CryptopiaPublicApi();
        Response currencies = app.getCurrencies();

        Assert.assertNotNull(currencies);
        Assert.assertNotNull(currencies.getData());
        Assert.assertTrue(currencies.isSuccess());
        Assert.assertFalse("Void currencies", currencies.getData().size() == 0);

    }

    @Test(expected = UnrecognizedPropertyException.class)
    public void givenJsonHasUnknownValues_whenDeserializing_thenException()
            throws JsonParseException, JsonMappingException, IOException {
        String jsonAsString = "{\n" +
                "  \"Success\": true,\n" +
                "  \"Message\": null,\n" +
                "  \"Data\": {\n" +
                "    \"TradePairId\": 100,\n" +
                "    \"Label\": \"DOT/BTC\",\n" +
                "    \"AskPrice\": 0.00000376,\n" +
                "    \"BidPrice\": 0.00000375,\n" +
                "    \"Low\": 0.00000363,\n" +
                "    \"High\": 0.00000397,\n" +
                "    \"Volume\": 940468.90658830,\n" +
                "    \"LastPrice\": 0.00000376,\n" +
                "    \"BuyVolume\": 82153379.28739277,\n" +
                "    \"SellVolume\": 30394960.57077220,\n" +
                "    \"Change\": 2.45,\n" +
                "    \"Open\": 0.00000367,\n" +
                "    \"Close\": 0.00000376,\n" +
                "    \"BaseVolume\": 3.48974219,\n" +
                "    \"BuyBaseVolume\": 77.21129891,\n" +
                "    \"SellBaseVolume\": 512227866.96933958\n" +
                "  },\n" +
                "  \"Error\": null\n" +
                "}";


        ObjectMapper mapper = new ObjectMapper();

        Response readValue = mapper.readValue(jsonAsString, Response.class);

        System.out.println(readValue);
    }

}
