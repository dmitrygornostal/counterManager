package com.example.countersManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
class RestCountersController {

    private final CountersService countersService;

    public RestCountersController(CountersService countersService) {
        this.countersService = countersService;
    }

    @PutMapping("/createCounter")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCounter(@RequestParam("name") String name) {
        countersService.createCounter(name);
    }

    @PostMapping("/incrementCounter")
    @ResponseStatus(HttpStatus.OK)
    public void incrementCounter(@RequestParam("name") String name) {
        countersService.incrementCounter(name);
    }

    @GetMapping("/getCounterValue")
    @ResponseStatus(HttpStatus.OK)
    public Long getCounterValue(@RequestParam("name") String name) {
        return countersService.getCounterValue(name);
    }

    @DeleteMapping("/deleteCounter")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCounter(@RequestParam("name") String name) {
        countersService.deleteCounter(name);
    }

    @GetMapping("/getCountersValuesSum")
    @ResponseStatus(HttpStatus.OK)
    public Long getCountersValuesSum() {
        return countersService.getCountersValuesSum();
    }

    @GetMapping("/getCountersNames")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getCountersNames() {
        return countersService.getCounterNames();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus
    public ResponseEntity<String> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
