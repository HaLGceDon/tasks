package Utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.testng.Assert;

import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class JsonParse {

    public JsonParse(InputStream jsonIS) {
        this.jsonIS = jsonIS;
    }

    private InputStream jsonIS;
    private DocumentContext jsonDoc;

    private DocumentContext getJson() {
        return JsonPath.parse(jsonIS);
    }

    private JSONArray getParentKeyValues(String parentKey) {
        if (jsonDoc == null) {
            jsonDoc = getJson();
        }

        Object parentKeyValue = jsonDoc.read("$." + parentKey);
        JSONArray parentKeyValues;

        if (parentKeyValue instanceof JSONArray) {
            parentKeyValues = (JSONArray) parentKeyValue;
        } else {
            throw new RuntimeException(String.format("По ключе '%s' ожидался объект класса JSONArray", parentKey));
        }
        return parentKeyValues;
    }

    public String getRandomValueForKeys(List<String> keys) {
        Object keyValue = getParentKeyValues(keys.get(0));
        Random random = new Random();
        int randomCount;

        for (int i = 1; i < keys.size(); i++) {
            if (keyValue instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) keyValue;
                randomCount = random.nextInt(jsonArray.size());
                keyValue = JsonPath.read(jsonArray.get(randomCount), "$." + keys.get(i));
            } else {
                keyValue = JsonPath.read(keyValue, "$." + keys.get(i));
            }
        }

        Assert.assertTrue(keyValue instanceof String, String.format("Ожидалось, что значением поиска по ключу '%s' является строка.", keys.toString()));
        return (String) keyValue;
    }

    /**
     * Проверка, что в json имеется заданное количество записей с указанным ключем, содержащие определенное значение
     *
     * @param parentKey     - ключ, предшествующий заданному
     * @param key           - ключ с ожидаемым значением
     * @param value         - ожидаемое значение
     * @param expectedCount - ожидаемое количество значений
     */
    public void checkJsonContainsCountObjectByKeyAndValue(String parentKey, String key, String value, int expectedCount) {
        int actualCount = 0;

        JSONArray jsonArray = getParentKeyValues(parentKey);
        for (Object object : jsonArray) {
            String keyValue = JsonPath.read(object, "$." + key);
            String errorMessage = "Количество записей с ключом '%s', содержащие значение '%s', не равно ожидаемому;" +
                                  "\n ожидалось - %d, получено - %d." +
                                  "\n Json: \n%s";
            Assert.assertTrue(keyValue.contains(value), String.format(errorMessage, key, value, expectedCount, actualCount, jsonArray.toString()));
        }
    }
}
