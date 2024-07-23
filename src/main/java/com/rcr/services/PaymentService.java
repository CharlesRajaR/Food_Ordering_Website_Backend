package com.rcr.services;

import com.rcr.model.Order;
import com.rcr.response.PaymentResponse;
import com.stripe.exception.StripeException;

public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
