package com.example.tripwallet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

@Controller
@RequestMapping("/")
public class PaymentController {

    @GetMapping
    public ModelAndView twLanding(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) throws IOException {

        List<WalletSummary> summary = null;
        TripWalletUtility twu = new TripWalletUtility();
        String fn = getTripSess(req);
        String fileName = twu.buildWallet(fn);
        try{
            summary = twu.fetchSummary(fileName);
        }catch(Exception e){
            //
        }
        model.put("summary",summary);
        addCookie(res, fileName);
        return new ModelAndView("hotelReservationDetails",model);
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
        try{
            new GenerateQRCode().generate();
        }catch(Exception e){
         //
        }
        return "twqrread";
    }
    @PostMapping("/fetchSummary")
    public ModelAndView fetchSummary(Map<String, Object> model,HttpServletRequest req) {
        String tripId = getTripSess(req);
        List<WalletSummary> summary = null;
        try{
            summary = new TripWalletUtility().fetchSummary(tripId);
        }catch(Exception e){
            //
        }

        model.put("summary",summary);
        return new ModelAndView("hotelReservationDetails",model);
    }
    @PostMapping("/topUp")
    public ModelAndView buildWallet(Map<String, Object> model, HttpServletRequest req) {
        String tripId = req.getParameter("tripId");
        String date = req.getParameter("date");
        String transType = req.getParameter("transType");
        String lastFour = req.getParameter("lastFour");
        String cardType = req.getParameter("cardType");
        String topUpAmount = req.getParameter("topUpAmount");
        String pointsAmount = req.getParameter("pointsAmount");
        String service = req.getParameter("service");
        String item = req.getParameter("item");
        double topUpAmt = 0.0;
        if(topUpAmount != null){
            topUpAmt = Double.parseDouble(topUpAmount);
        }
        long pointsAmt = 0;
        if(pointsAmount != null){
            pointsAmt = ((long)Double.parseDouble(pointsAmount));
        }
        long trpId = 0;
        if(tripId != null){
            trpId = Long.parseLong(tripId);
        }
        TopUp top = new TopUp(trpId,date,transType,lastFour,cardType,topUpAmt,pointsAmt,service,item);
        new TripWalletUtility().insertTopup(top,getTripSess(req));

        ///
        List<WalletSummary> summary = null;
        try{
            summary = new TripWalletUtility().fetchSummary(getTripSess(req));
        }catch(Exception e){
            //
        }
        model.put("summary",summary);
        return new ModelAndView("hotelReservationDetails",model);
    }
    public String getTripSess(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("tripSess")) {
                   return cookie.getValue();
                }
            }
        }
        return null;
    }
    public void addCookie(HttpServletResponse response, String fileName){
        SimpleDateFormat COOKIE_EXPIRES_HEADER_FORMAT = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        COOKIE_EXPIRES_HEADER_FORMAT.setTimeZone(new SimpleTimeZone(0, "GMT"));
        Date d = new Date();
        d.setTime(d.getTime() + 10*24*3600 * 1000); //1 hour
        String cookieLifeTime = COOKIE_EXPIRES_HEADER_FORMAT.format(d);
        String c = "tripSess="+fileName+"; Expires=";
        response.setHeader("Set-Cookie", c + cookieLifeTime + "; Path=/; HTTPSOnly");
    }
}
