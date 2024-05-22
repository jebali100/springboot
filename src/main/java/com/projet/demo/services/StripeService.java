package com.projet.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.projet.demo.dto.CheckoutSessionResponse;
import com.projet.demo.entities.Offre;
import com.projet.demo.repositories.OffreRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.annotation.PostConstruct;

@Service
public class StripeService {
	
    @Value("${stripe.apiKey}")
    private String apiKey;

    @Autowired
    private OffreRepository offreRepository;

   

    @PostConstruct
    public void init() {
        System.out.println("Initializing Stripe API with key: " + apiKey);  // Debug line
        Stripe.apiKey = apiKey;
    }

    public Session createCheckoutSession(Long offreId, Long memberId, String successUrl, String cancelUrl) throws StripeException {
        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new RuntimeException("Offre not found"));

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount((long) (offre.getPrix() * 100))
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName(offre.getNom())
                                        .build())
                                .build())
                        .build())
                .putMetadata("offreId", offreId.toString())
                .putMetadata("memberId", memberId.toString())
                .build();

        return Session.create(params);
    }
}
