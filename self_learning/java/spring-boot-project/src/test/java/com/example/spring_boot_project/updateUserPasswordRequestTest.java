package com.example.spring_boot_project;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring_boot_project.model.UpdateUserPasswordRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


@SpringBootTest
public class updateUserPasswordRequestTest {

    private Validator validator;
    
    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testValidUpdateUserPasswordRequest(){
        UpdateUserPasswordRequest request = new UpdateUserPasswordRequest("pwd123");
        Set<ConstraintViolation<UpdateUserPasswordRequest>> violations = validator.validate(request);
        assert violations.isEmpty();
    }

    @Test
    void testPasswordTooShortThrowsValidationError() {
        UpdateUserPasswordRequest request = new UpdateUserPasswordRequest("pwd");
        Set<ConstraintViolation<UpdateUserPasswordRequest>> violations = validator.validate(request);
        assert !violations.isEmpty();
    }
}
