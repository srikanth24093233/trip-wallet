package com.example.tripwallet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PaymentController {

    @GetMapping
    public String twLanding() {
        return "twlanding";
    }
    @GetMapping("/pay")
    public String twPayments() {
        return "twpayments";
    }
    @GetMapping("/transactions")
    public String twTransactions() {
        return "twtransactions";
    }
    @GetMapping("/qr")
    public String twQrread() {
        return "twqrread";
    }
}
