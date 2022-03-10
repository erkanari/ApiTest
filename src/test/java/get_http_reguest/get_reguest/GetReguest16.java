package get_http_reguest.get_reguest;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetReguest16 extends JsonPlaceHolderBaseUrl {

      /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false

    */

@Test
    public void test16(){

    // 1) url oluşturma

    spec04.pathParams("bir","todos","iki",7);

    // 2) expected (beklenen) data oluştur

    Map<String,Object> expectedData=new HashMap<>();
    expectedData.put("userId",1);
    expectedData.put("id",7);
    expectedData.put("title","illo expedita consequatur quia in");
    expectedData.put("completed",false);

    System.out.println("EXPECTED DATA : "+expectedData);

    //3) Request ve response

   Response response= given().spec(spec04).when().get("/{bir}/{iki}");


   response.prettyPrint();

    //DATAYI JSON'DAN -> JAVA'YA De-Serialization
    //DATAYI JAVA'DAN -> JSON'A Serialization

     Map<String,Object> actualData=response.as(HashMap.class); //De-Serialization

    System.out.println("ACTUAL DATA : "+actualData);

    Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
    Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
    Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
    Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));




}

}
