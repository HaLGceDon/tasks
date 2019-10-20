package tests;


import Utils.HttpRequest;
import Utils.ImageUtils;
import Utils.JsonParse;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class WileyApiTest {

    private HttpRequest httpRequest = new HttpRequest();

    private JsonParse jsonParse;


    @Test (description = "Check GET request values")
    public void test1_checkGetRequestValues() {
        InputStream result = httpRequest.httpGetRequestResult("https://www.wiley.com/en-us/search/autocomplete/comp_00001H9J?term=Java");
        jsonParse = new JsonParse(result);

        jsonParse.checkJsonContainsCountObjectByKeyAndValue("suggestions", "term",
                                                            "<span class=\"search-highlight\">java</span>",
                                                            4);

        jsonParse.checkJsonContainsCountObjectByKeyAndValue("products", "name",
                                                            "<span class='search-highlight'>Java</span>",
                                                            4);

        jsonParse.checkJsonContainsCountObjectByKeyAndValue("pages",
                                                            "title",
                                                            "Wiley",
                                                            4);
    }

    @Test (description = "Check image has got width 300 px")
    public void test2_checkImageWidth() {
        InputStream result = httpRequest.httpGetRequestResult("https://www.wiley.com/en-us/search/autocomplete/comp_00001H9J?term=Java");
        jsonParse = new JsonParse(result);

        String uri = jsonParse.getRandomValueForKeys(Arrays.asList("products", "images", "url"));
        result = httpRequest.httpGetRequestResult(uri);

        ImageUtils imageUtils = new ImageUtils();
        imageUtils.checkImageWidth(result, 300);
    }

    @Test (description = "Returns a delayed POST response (10 seconds)")
    public void test3_checkPostResponseWithDelay() {
        InputStream result = httpRequest.httpPostRequestResult("https://httpbin.org/delay/10");

        Scanner scanner = new Scanner(result).useDelimiter("\\A");
        String s = scanner.hasNext() ? scanner.next() : "";
    }
}
