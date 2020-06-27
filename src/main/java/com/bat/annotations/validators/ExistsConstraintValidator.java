package com.bat.annotations.validators;

import com.bat.annotations.Exists;
import com.bat.service.interfaces.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsConstraintValidator implements ConstraintValidator<Exists, String> {
    private String tableName;
    private String columnName;

    @Autowired
    AnnotationService annotationService;

    @Override
    public void initialize(Exists constraintAnnotation) {
        this.tableName = constraintAnnotation.table();
        this.columnName = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return annotationService.exists(tableName, columnName, value);
    }
}
