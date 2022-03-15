package get_http_reguest;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;

public class PostReguestPojos02 extends HerOkuAppBaseUrl {
    /*
https://restful-booker.herokuapp.com/booking
request body
{ "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-03-01",
               "checkout": "2022-03-11"
            }
}}
Status code is 200
response body
{
   "bookingid": 11,
      "booking": {
        "firstname": "Ali",
        "lastname": "Can",
        "totalprice": 500,
        "depositpaid": true,
        "bookingdates": {
           "checkin": "2022-03-01",
           "checkout": "2022-03-11"
                            }
                        }
                    }
 */
    @Test
    public void test01(){
        //1) url oluşturalım
        spec05.pathParam("bir","booking");
        //2) expectedData
        BookingDatesPojo bookingDates=new BookingDatesPojo("2020-09-14","2021-01-21");
        BookingPojo bookingpojo=new BookingPojo("Suat","Oruç",10000,true,bookingDates);
        //3) Request ve Response
        Response response=given().spec(spec05).
                contentType(ContentType.JSON)
                .auth().basic("admin","password123")
                .body(bookingpojo).when().post("/{bir}");
        response.prettyPeek();

        BookingResponsePojo actual= response.as(BookingResponsePojo.class);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(bookingpojo.getFirstname(),actual.getBookingPojo().getFirstname());
        Assert.assertEquals(bookingpojo.getLastname(),actual.getBookingPojo().getLastname());
        Assert.assertEquals(bookingpojo.getTotalprice(),actual.getBookingPojo().getTotalprice());
        Assert.assertEquals(bookingpojo.isDepositpaid(),actual.getBookingPojo().isDepositpaid());
        Assert.assertEquals(bookingpojo.getBookingDatesPojo().getCheckin(),actual.getBookingPojo().getBookingDatesPojo().getCheckin());
        Assert.assertEquals(bookingpojo.getBookingDatesPojo().getCheckout(),actual.getBookingPojo().getBookingDatesPojo().getCheckout());
    }

}