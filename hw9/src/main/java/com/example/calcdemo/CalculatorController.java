package com.example.calcdemo;

import org.example.Calculator;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalculatorController {
    private final Calculator calculator;


    public CalculatorController(Calculator calculator, Session session) {
        this.calculator = calculator;
        this.session = session;
    }

    private final Session session;

    private String returnResult(String a, String b, String operator) {
        String result;
        session.beginTransaction();
        Query query = session.createQuery("from Cache where firstArgument=:a and secondArgument=:b and operator=:operator");
        query.setParameter("a", a);
        query.setParameter("b", b);
        query.setParameter("operator", operator);
        List list = query.list();
        if (list.isEmpty()) {
            switch (operator) {
                case "+":
                    result = String.valueOf(calculator.add(Double.parseDouble(a), Double.parseDouble(b)));
                    break;
                case "-":
                    result = String.valueOf(calculator.subtract(Double.parseDouble(a), Double.parseDouble(b)));
                    break;
                case "/":
                    if (Double.parseDouble(b) == 0.0) result = "Can't divide by 0";
                    else result = String.valueOf(calculator.divide(Double.parseDouble(a), Double.parseDouble(b)));
                    break;
                default:
                    result = String.valueOf(calculator.multiply(Double.parseDouble(a), Double.parseDouble(b)));
                    break;
            }
            session.save(new Cache(a, b, operator, result));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Cache cache = (Cache) list.get(0);
            result = cache.getResult();
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @GetMapping("/add")
    @RequestMapping(value = "/add", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String add(@RequestParam(defaultValue = "0") String a, @RequestParam(defaultValue = "0") String b) {
        return returnResult(a, b, "+");
    }

    @GetMapping("/subtract")
    @RequestMapping(value = "/subtract", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String subtract(@RequestParam(defaultValue = "0") String a, @RequestParam(defaultValue = "0") String b) {
        return returnResult(a, b, "-");
    }

    @GetMapping("/multiply")
    @RequestMapping(value = "/multiply", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String multiply(@RequestParam(defaultValue = "1") String a, @RequestParam(defaultValue = "1") String b) {
        return returnResult(a, b, "*");
    }

    @GetMapping("/divide")
    @RequestMapping(value = "/divide", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String divide(@RequestParam(defaultValue = "1") String a, @RequestParam(defaultValue = "1") String b) {
        return returnResult(a, b, "/");
    }
}
