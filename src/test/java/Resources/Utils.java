package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    public static RequestSpecification request;
    public RequestSpecification requestSpecification() throws IOException {

            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            RequestSpecification request = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseURI"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).
                            addQueryParam("key", "qaclick123").setContentType("application/json").build();
            return request;



    }

    public String getGlobalProperties (String key ) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/subhadipmoulick/subhadipmoulick/documents/APIAutomation/src/test/java/Resources/global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }

    public String getJsonPath(Response response, String key){
        String resp =response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
}
