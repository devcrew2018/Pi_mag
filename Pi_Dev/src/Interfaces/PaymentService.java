/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entites.User;
import com.stripe.exception.StripeException;
import com.stripe.model.Order;

/**
 *
 * @author Walid
 */
public interface PaymentService {
    public String createToken(String card_num,String exp_month,String exp_year,String cvc);
    public void chargeCreditCard(String amount,String tok) throws StripeException;
}
