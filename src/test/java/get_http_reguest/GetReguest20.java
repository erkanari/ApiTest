package get_http_reguest;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GetReguest20 extends JsonPlaceHolderBaseUrl {
     /*
https://jsonplaceholder.typicode.com/todos/2
1) Status kodunun 200,
2) respose body'de,
         "completed": değerinin false
         "title": değerinin "quis ut nam facilis et officia qui"
         "userId" sinin 1 ve
    header değerlerinden
         "via" değerinin "1.1 vegur" ve
         "Server" değerinin "cloudflare" olduğunu test edin…
*/
    @Test
    public void test20(){
        spec04.pathParams("1","todos","2","2");
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("userId", 1);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("Server", "cloudflare");

        Response response=given().spec(spec04).when().get("/{1}/{2}");
       // response.prettyPrint();

        //Matcher ile
        response.then().assertThat()
                .statusCode((Integer)expectedData.get("statusCode"))
                .headers("via",equalTo(expectedData.get("via")),"Server",equalTo(expectedData.get("Server")))
                .body("completed",equalTo(expectedData.get("completed"))
                        ,"title",equalTo(expectedData.get("title")),"userId", equalTo(expectedData.get("userId")));
        // Json ile

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedData.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedData.get("completed"),json.getBoolean("completed"));
        Assert.assertEquals(expectedData.get("title"),json.getString("title"));
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));

        // 3. yol De Serialization ile

        HashMap<String,Object>actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));

    }
}
