
package com.example.stripe.controller;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;



@RestController
public class PaypalController {
    @Value("${paypal.client.id}")
    String clientId;

    @Value("${paypal.client.secret}")
    String secretKey;
    private PayerInfo payerInfo;
    APIContext context = new APIContext(clientId, secretKey, "sandbox");

    @GetMapping("/yes")
    public String paypal() throws PayPalRESTException, IOException {
        Amount amount = new Amount();
        amount.setTotal("10.00");
        amount.setCurrency("USD");
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Payment description");
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();

        System.out.println(payer.getPayerInfo());

     /* payer.setPayerInfo(payerInfo);*/
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo=new PayerInfo();
        System.out.println(payerInfo);


        System.out.println(payer);
        System.out.println(payerInfo);


        String payerId = payer.getPayerInfo().getPayerId();

        Payment payment = new Payment();

        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        APIContext apiContext = new APIContext(clientId, secretKey, "sandbox");

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl("http://locallost:8080/paypal/complete");
        redirectUrls.setCancelUrl("http://localhost:8080/paypal/cancel");
        payment.setRedirectUrls(redirectUrls);



        Payment payment1 = payment.create(apiContext);
        System.out.println(payment1);
        String id = payment1.getId();


        String paymentRedirect;
        return paymentRedirect = payment1.getLinks().stream()
                .filter(link -> link.getRel().equalsIgnoreCase("approval_url"))
                .findFirst()
                .map(Links::getHref)
                .orElseThrow(() -> new RuntimeException("No redirect URL found"));
        /*String payerId = payment1.getPayer().getPayerInfo().getPayerId();*/


        /*String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");*/

       /* String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");*/
        /*Payment payment2 = new Payment();
        payment2.setId(id);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(paymentRedirect);

        payment2.execute(context, paymentExecution);
        return  payment2.getId();*/









        /* return new ResponseEntity<>("yes", HttpStatus.FOUND);*/

    }
    @PostMapping("execute")
    public String execute(@RequestParam String payerId,String paymentId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        payment.execute(context, paymentExecution);
        return  payment.getId();


    }

    /*public String execute(){
        Payment payment=new Payment();

    }*/
}


//    public static void main(String[] args) throws IOException, IOException {
//        URL url = new URL("https://api-m.sandbox.paypal.com/v1/payments/payment");
//        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//        httpConn.setRequestMethod("POST");
//
//        httpConn.setRequestProperty("X-PAYPAL-SECURITY-CONTEXT", "{\"actor\":{\"account_number\":\"1659371090107732880\",\"party_id\":\"1659371090107732880\",\"auth_claims\":[\"AUTHORIZATION_CODE\"],\"auth_state\":\"ANONYMOUS\",\"client_id\":\"zf3..4BQ0T9aw-ngFr9dmOUZMwuKocrqe72Zx9D-Lf4\"},\"auth_token\":\"A015QQVR4S3u79k.UvhQ-AP4EhQikqOogdx-wIbvcvZ7Qaw\",\"auth_token_type\":\"ACCESS_TOKEN\",\"last_validated\":1393560555,\"scopes\":[\"https://api-m.sandbox.paypal.com/v1/payments/.*\",\"https://api-m.sandbox.paypal.com/v1/vault/credit-card/.*\",\"openid\",\"https://uri.paypal.com/services/payments/futurepayments\",\"https://api-m.sandbox.paypal.com/v1/vault/credit-card\",\"https://api-m.sandbox.paypal.com/v1/payments/.*\"],\"subjects\":[{\"subject\":{\"account_number\":\"2245934915437588879\",\"party_id\":\"2245934915437588879\",\"auth_claims\":[\"PASSWORD\"],\"auth_state\":\"LOGGEDIN\"}}]}");
//
//        httpConn.setDoOutput(true);
//        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
//        writer.write("{ \"intent\": \"sale\", \"payer\": { \"payment_method\": \"paypal\" }, \"transactions\": [ { \"amount\": { \"total\": \"30.11\", \"currency\": \"USD\", \"details\": { \"subtotal\": \"30.00\", \"tax\": \"0.07\", \"shipping\": \"0.03\", \"handling_fee\": \"1.00\", \"shipping_discount\": \"-1.00\", \"insurance\": \"0.01\" } }, \"description\": \"The payment transaction description.\", \"custom\": \"EBAY_EMS_90048630024435\", \"invoice_number\": \"48787589673\", \"payment_options\": { \"allowed_payment_method\": \"INSTANT_FUNDING_SOURCE\" }, \"soft_descriptor\": \"ECHI5786786\", \"item_list\": { \"items\": [ { \"name\": \"hat\", \"description\": \"Brown hat.\", \"quantity\": \"5\", \"price\": \"3\", \"tax\": \"0.01\", \"sku\": \"1\", \"currency\": \"USD\" }, { \"name\": \"handbag\", \"description\": \"Black handbag.\", \"quantity\": \"1\", \"price\": \"15\", \"tax\": \"0.02\", \"sku\": \"product34\", \"currency\": \"USD\" } ], \"shipping_address\": { \"recipient_name\": \"Brian Robinson\", \"line1\": \"4th Floor\", \"line2\": \"Unit #34\", \"city\": \"San Jose\", \"country_code\": \"US\", \"postal_code\": \"95131\", \"phone\": \"011862212345678\", \"state\": \"CA\" } } } ], \"note_to_payer\": \"Contact us for any questions on your order.\", \"redirect_urls\": { \"return_url\": \"https://example.com/return\", \"cancel_url\": \"https://example.com/cancel\" } }");
//        writer.flush();
//        writer.close();
//        httpConn.getOutputStream().close();
//
//        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
//                ? httpConn.getInputStream()
//                : httpConn.getErrorStream();
//        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
//        String response = s.hasNext() ? s.next() : "";
//        System.out.println(response);
//    }
//}









