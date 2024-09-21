package com.hackerrank.validator.validation;

import com.hackerrank.validator.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object employeeObject, Errors errors) {
        // write validation logic here
        Employee employee =(Employee) employeeObject;
        String name = employee.getFullName();
        Long phone = employee.getMobileNumber();
        String email = employee.getEmailId();
        String dob = employee.getEmailId();
        String dobPattern = "\\d{4}\\d{2}\\d{2}";
        fieldValidation(errors, name, phone, email, dob, dobPattern);
    }
    private static void fieldValidation(Errors errors, String name, Long phone, String email, String dob, String dobPattern) {
        ValidName(errors, name);
        ValidPhone(errors, phone);
        ValidEmail(errors, email);
        ValidDob(errors, dob, dobPattern);
    }

    private static void ValidDob(Errors errors, String dob, String dobPattern) {
        if (dob ==null|| dob.isEmpty()){
            errors.rejectValue("dateOfBirth","400","The dateOfBirth is a mandatory field");
        }
        else if (!dob.matches(dobPattern)){
            errors.rejectValue("dateOfBirth","400","The dateOfBirth should be in YYYY-MM-DD format");
        }
    }

    private static void ValidEmail(Errors errors, String email) {
        if (email ==null || email.trim().isEmpty()){
            errors.rejectValue("emailId","400","The emailId is a mandatory field");
        }
        else if(!email.contains("@")){
            errors.rejectValue("emailId","400","The emailId should be in a valid email format");
        }
    }

    private static void ValidPhone(Errors errors, Long phone) {
        if (phone ==null|| phone.toString().length()!=10){
            errors.rejectValue("mobileNumber","400","The mobileNumber is a mandatory field");
        }
    }

    private static void ValidName(Errors errors, String name) {
        if (name ==null || name.isEmpty()) {
            errors.rejectValue("fullName","400","The fullName is a mandatory field");
        }
    }
}
