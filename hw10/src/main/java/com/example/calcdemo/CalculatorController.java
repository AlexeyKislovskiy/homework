package com.example.calcdemo;

import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {
    private final CachedCalculator cachedCalculator;


    public CalculatorController(CachedCalculator cachedCalculator) {
        this.cachedCalculator = cachedCalculator;
    }


    @GetMapping("/add")
    @RequestMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam(defaultValue = "0") String a, @RequestParam(defaultValue = "0") String b) {
        return cachedCalculator.calculate(Double.parseDouble(a),Double.parseDouble(b),"+");
    }

    @GetMapping("/subtract")
    @RequestMapping(value = "/subtract", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String subtract(@RequestParam(defaultValue = "0") String a, @RequestParam(defaultValue = "0") String b) {
        return cachedCalculator.calculate(Double.parseDouble(a),Double.parseDouble(b),"-");
    }

    @GetMapping("/multiply")
    @RequestMapping(value = "/multiply", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String multiply(@RequestParam(defaultValue = "1") String a, @RequestParam(defaultValue = "1") String b) {
        return cachedCalculator.calculate(Double.parseDouble(a),Double.parseDouble(b),"*");
    }

    @GetMapping("/divide")
    @RequestMapping(value = "/divide", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String divide(@RequestParam(defaultValue = "1") String a, @RequestParam(defaultValue = "1") String b) {
        return cachedCalculator.calculate(Double.parseDouble(a),Double.parseDouble(b),"/");
    }
}
