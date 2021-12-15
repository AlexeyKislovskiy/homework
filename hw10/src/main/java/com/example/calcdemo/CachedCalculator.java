package com.example.calcdemo;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CachedCalculator extends BasicDecorator {
    private final Calculator calculator;
    private final CalculatorRepository calculatorRepository;

    @Autowired
    public CachedCalculator(Calculator calculator, CalculatorRepository calculatorRepository) {
        this.calculator = calculator;
        this.calculatorRepository = calculatorRepository;
    }

    public String calculate(double a, double b, @NotNull String operator) {
        String result;
        Cache cache = calculatorRepository.findByFirstArgumentAndSecondArgumentAndOperator(String.valueOf(a), String.valueOf(b), operator);
        if (cache == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = calculator.calculate(a, b, operator);
            calculatorRepository.save(new Cache(String.valueOf(a), String.valueOf(b), operator, result));
        } else {
            result = cache.getResult();
        }
        return result;
    }
}
