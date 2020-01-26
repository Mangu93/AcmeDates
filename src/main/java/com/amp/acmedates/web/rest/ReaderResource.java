package com.amp.acmedates.web.rest;

import com.amp.acmedates.domain.DateProduct;
import com.amp.acmedates.service.IDateProductService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.amp.acmedates.utils.DateProductUtils.insertEntries;

@RestController
@RequestMapping("/api")
public class ReaderResource {
    private final Logger log = LoggerFactory.getLogger(ReaderResource.class);

    @Autowired
    private IDateProductService dateProductService;

    public ReaderResource() {

    }

    @PostMapping("/dates")
    public ResponseEntity<String> receiveDates(@RequestBody String payload) throws ParseException {
        log.info("Received {}", payload);
        JsonElement jsonElement = JsonParser.parseString(payload);
        Set<Map.Entry<String, JsonElement>> entries = insertEntries(jsonElement, dateProductService);
        log.info("Inserted {} entities", entries.size());
        return new ResponseEntity<>("{\"success\":1}", HttpStatus.OK);
    }

    @GetMapping("/dates")
    public ResponseEntity<Object> getDates() {
        return new ResponseEntity<>(dateProductService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/dates/{name}")
    public ResponseEntity<Object> getDateByName(@PathVariable String name) {
        List<DateProduct> collect = dateProductService.findAll().stream().
            filter(dateProduct -> dateProduct.getName() != null && dateProduct.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        if (collect.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(collect.get(0), HttpStatus.OK);
    }
}
