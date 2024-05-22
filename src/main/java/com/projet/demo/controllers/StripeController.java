package com.projet.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.demo.dto.CheckoutSessionResponse;
import com.projet.demo.entities.Member;
import com.projet.demo.repositories.MemberRepository;
import com.projet.demo.services.AbonnementServiceImpl;
import com.projet.demo.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

@RestController
public class StripeController {
    @Autowired
    private StripeService stripeService;

    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private AbonnementServiceImpl abonnementService;

    @PostMapping("/create-checkout-session")
    public Session createCheckoutSession(@RequestParam Long offreId) throws StripeException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("Member not found");
        }

        Long memberId = memberOptional.get().getId();
        String successUrl = "http://localhost:8090/success";
        String cancelUrl = "http://localhost:8090/cancel";

        return stripeService.createCheckoutSession(offreId, memberId, successUrl, cancelUrl);
    }

    @GetMapping("/success")
    public String handleSuccess(@RequestParam("session_id") String sessionId) {
        try {
            Session session = Session.retrieve(sessionId);
            Long offreId = Long.parseLong(session.getMetadata().get("offreId"));
            Long memberId = Long.parseLong(session.getMetadata().get("memberId"));

            abonnementService.addAbonnement(offreId, memberId);
            return "Payment successful and subscription created!";
        } catch (StripeException e) {
            return "Payment failed: " + e.getMessage();
        }
    }

    @GetMapping("/cancel")
    public String handleCancel() {
        return "Payment canceled.";
    }
}
