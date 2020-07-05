package com.bat.service.interfaces;

public interface AnnotationService {
    boolean exists(String table, String column, String value);
    boolean unique(String table, String column, String value);
    boolean unique(String table, String column, String value, String exceptId, String salt);
}
