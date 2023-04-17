package step;

import com.mirai.importback.entities.Coupon;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CouponStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    @When("A Coupon Request is sent with values {string}, {string}, {string}, {string}, {string}, {string}")
    public void aCouponRequestIsSentWithValues(String title, String discount, String code, String validDate, String situation, String description) {
        Coupon coupon = new Coupon(0L, title, discount, code, validDate, situation, description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Coupon> request = new HttpEntity<>(coupon, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A Coupon with status {int} is received")
    public void aCouponWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
    }

    @When("A Coupon delete request is sent with id value {string}")
    public void aCouponDeleteRequestIsSentWithIdValue(String id_coupon) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_coupon);
        testRestTemplate.delete(endpointPath + "/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @When("A Coupon selected is sent with id value {string}")
    public void aCouponSelectedIsSentWithIdValue(String id_coupon) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_coupon);
        Coupon coupon = testRestTemplate.getForObject(endpointPath + "/{id}", Coupon.class,params);
        responseEntity = new ResponseEntity<>(coupon.toString(), HttpStatus.OK);
        System.out.println(coupon.toString());
    }
}
