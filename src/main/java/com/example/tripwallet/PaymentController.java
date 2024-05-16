package com.example.tripwallet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PaymentController {

    @GetMapping
    public ModelAndView twLanding(Map<String, Object> model) {
        model.put("index", "Trip Summary");
        return new ModelAndView("twlanding",model);
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
