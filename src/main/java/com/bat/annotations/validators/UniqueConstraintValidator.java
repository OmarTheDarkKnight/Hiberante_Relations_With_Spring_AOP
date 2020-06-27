package com.bat.annotations.validators;

import com.bat.annotations.Unique;
import com.bat.service.interfaces.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, String> {
    private String tableName;
    private String columnName;

    @Autowired
    AnnotationService annotationService;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.tableName = constraintAnnotation.table();
        this.columnName = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return annotationService.unique(tableName, columnName, value);
    }
}
