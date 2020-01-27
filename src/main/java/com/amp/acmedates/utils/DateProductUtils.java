package com.amp.acmedates.utils;

import com.amp.acmedates.domain.DateProduct;
import com.amp.acmedates.service.IDateProductService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class DateProductUtils {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
    private static final NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
    private static final Logger log = LoggerFactory.getLogger(DateProductUtils.class);

    public static Set<Map.Entry<String, JsonElement>> insertEntries(JsonElement jsonElement, IDateProductService dateProductService) throws ParseException {
        Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            try {
                String key = entry.getKey();
                JsonArray jsonArray = entry.getValue().getAsJsonArray();
                String date = jsonArray.get(0).getAsString();
                String value = jsonArray.get(1).getAsString();
                Number parsed = nf.parse(value);
                ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(formatter.parse(date).toInstant(), ZoneId.systemDefault());
                DateProduct dateProduct = new DateProduct();
                dateProduct.setDeclaredValue(parsed.doubleValue());
                dateProduct.setName(key);
                dateProduct.setDeliveryDate(zonedDateTime);
                dateProductService.save(dateProduct);
            } catch (Exception exception) {
                log.info("Got error {}, continuing", exception.getLocalizedMessage());
            }
        }
        return entries;
    }
}
