package com.example.calcdemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatorRepository extends JpaRepository<Cache,Integer> {
    Cache findByFirstArgumentAndSecondArgumentAndOperator(String firstArgument, String secondArgument, String operator);
}
