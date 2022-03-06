package get_http_reguest;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetReguest03 {

  /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
    GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in "Sally"
    ve lastname'in "Ericsson"
    ve checkin date'in 2018-10-07"
     ve checkout date'in 2020-09-30 oldugunu test edin
     */

    @Test
    public void test03(){

        String url="https://restful-booker.herokuapp.com/booking/7";

        Response response=given().when().get(url);

        response.prettyPrint();

        response.then().contentType("application/json").statusCode(200);

      response.then().assertThat().body("firstname", equalTo("Eric")
                                       ,"lastname",equalTo("Smith")
                                       ,"bookingdates.checkin",equalTo("2021-06-14"));


    }
}
