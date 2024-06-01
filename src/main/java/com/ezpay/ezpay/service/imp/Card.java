package com.ezpay.ezpay.service.imp;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Card {
    public ResponseEntity<?> addCard(String cardNumber, String expirationMonth, String expirationYear, String cvc, String email) {
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expirationMonth);
        cardParams.put("exp_year", expirationYear);
        cardParams.put("cvc", cvc);

        Map<String, Object> paymentMethodParams = new HashMap<>();
        paymentMethodParams.put("type", "card");
        paymentMethodParams.put("card", cardParams);
        return null;
    }
}
