package com.bat.dao;

import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class AnnotationDao extends HelperDao {
    public int occurrenceOfAValue(String table, String column, String value) {
        BigInteger result = (BigInteger) hibernateQuery("SELECT COUNT(*) FROM "+ table +" WHERE "+ column +"=:value")
                .setParameter("value", value)
                .getSingleResult();
        return result.intValue();
    }
}
