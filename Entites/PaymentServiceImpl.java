/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;
import Interfaces.PaymentService;
import java.util.HashMap;
import java.util.Map;


import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Order;
import com.stripe.model.Token;
import static com.stripe.net.ApiResource.request;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Walid
 */
public class PaymentServiceImpl implements PaymentService {
    private static final String TEST_STRIPE_SECRET_KEY = "sk_test_bh5IZWGbVLRt3AgnFrlj4ZQG";

  public PaymentServiceImpl() {
    Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
  }
    @Override
    public String createToken(String card_num, String exp_month, String exp_year, String cvc) {
               
        Map<String, Object> tokenParams = new HashMap<String, Object>();
                Map<String, Object> cardParams = new HashMap<String, Object>();
                cardParams.put("number", card_num);
                cardParams.put("exp_month", exp_month);
                cardParams.put("exp_year", exp_year);
                cardParams.put("cvc", cvc);
                tokenParams.put("card", cardParams);

        try {
            return Token.create(tokenParams).getId();
        } catch (StripeException ex) {
            Logger.getLogger(PaymentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
        }

    @Override
    public void chargeCreditCard(String amount,String tok) throws StripeException {
            
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", amount);
            chargeParams.put("currency", "eur");
            chargeParams.put("description", "Charge for jenny.rosen@example.com");
            chargeParams.put("source", tok);
            // ^ obtained with Stripe.js
            Charge.create(chargeParams);
                }
    
}
