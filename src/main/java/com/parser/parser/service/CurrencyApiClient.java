package com.parser.parser.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyApiClient {
    private static final String API_URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";

    public float getUSDBuyRate() {
        RestTemplate restTemplate = new RestTemplate();
        
        
        String response = restTemplate.getForObject(API_URL, String.class);
   
        JSONArray jsonArray = new JSONArray(response);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currency = jsonArray.getJSONObject(i);

            if (currency.getString("ccy").equals("USD")) {
                return currency.getFloat("buy");
            }
        }

        return -1;
    }
}
