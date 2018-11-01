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

  phhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh