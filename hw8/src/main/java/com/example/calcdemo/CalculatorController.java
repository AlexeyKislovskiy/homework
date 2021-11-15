package com.example.calcdemo;

import org.example.Calculator;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {
    private final Calculator calculator;


    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/add")
    @RequestMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam(defaultValue = "0") String a, @RequestParam(defaultValue = "0") String b) {
        return String.valueOf(calculator.add(Double.parseDouble(a), Double.parseDouble(b)));
    }

    @GetMapping("/subtract")
    @RequestMapping(value = "/subtract", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String subtract(@RequestParam(defaultValue = "0") String a, @RequestParam(defaultValue = "0") String b) {
        return String.valueOf(calculator.subtract(Double.parseDouble(a), Double.parseDouble(b)));
    }

    @GetMapping("/multiply")
    @RequestMapping(value = "/multiply", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String multiply(@RequestParam(defaultValue = "1") String a, @RequestParam(defaultValue = "1") String b) {
        return String.valueOf(calculator.multiply(Double.parseDouble(a), Double.parseDouble(b)));
    }

    @GetMapping("/divide")
    @RequestMapping(value = "/divide", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String divide(@RequestParam(defaultValue = "1") String a, @RequestParam(defaultValue = "1") String b) {
        if (Double.parseDouble(b) == 0.0) return "Can't divide by 0";
        else return String.valueOf(calculator.divide(Double.parseDouble(a), Double.parseDouble(b)));
    }
}
