package com.example.calcdemo;

public abstract class BasicDecorator implements Calculator {
    protected Calculator calculator;

    public BasicDecorator(Calculator calculator) {
        this.calculator = calculator;
    }

    public BasicDecorator() {
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public String calculate(double a, double b, String operator) {
        if (calculator != null) return calculator.calculate(a, b, operator);
        else return null;
    }
}
