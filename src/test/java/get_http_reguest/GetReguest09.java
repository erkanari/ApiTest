package get_http_reguest;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetReguest09 extends DummyBaseUrl {
    /*
http://dummy.restapiexample.com/api/v1/employee/12 URL'E GiT.
1) Matcher CLASS ile
2) JsonPath ile dogrulayin.

*/
    @Test
    public void test09(){
        spec02.pathParams("birinci", "api", "ikinci", "v1", "ucuncu", "employee","dorduncu","12");

        Response response = given().spec(spec02).when().get("/{birinci}/{ikinci}/{ucuncu}/{dorduncu}");

        response.prettyPrint();

        // Matchers Class ile dogrulayin
        response.then().statusCode(200).contentType(ContentType.JSON);

        response.then().assertThat().body("status", equalTo("success"),
                "data.id", equalTo(12),
                "data.employee_name", equalTo("Quinn Flynn"),
                "data.employee_salary", equalTo(342000),
                "data.employee_age", equalTo(22),
                "data.profile_image", equalTo(""),
                "message", equalTo("Successfully! Record has been fetched."));


          // Json Path ile
        JsonPath jsonPath=response.jsonPath();

       System.out.println(jsonPath.getString("data.employee_name"));
        System.out.println(jsonPath.getInt("data.employee_salary"));
       System.out.println(jsonPath.getInt("data.employee_age"));


        Assert.assertEquals("Quinn Flynn",jsonPath.getString("data.employee_name"));
        Assert.assertEquals("342000",jsonPath.getString("data.employee_salary"));
        Assert.assertEquals("22",jsonPath.getString("data.employee_age"));
    }
}
