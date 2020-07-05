package com.bat.dao;

import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class AnnotationDao extends HelperDao {
    public int occurrenceOfAValue(String table, String column, String value, int exceptId) {
        BigInteger result = null;
        if(exceptId == 0) {
            result = (BigInteger) hibernateQuery("SELECT COUNT(*) FROM "+ table +" WHERE "+ column +"=:value")
                    .setParameter("value", value)
                    .getSingleResult();
        } else {
            result = (BigInteger) hibernateQuery("SELECT COUNT(*) FROM "+ table +" WHERE "+ column +"=:value AND id!=:exceptId")
                    .setParameter("value", value)
                    .setParameter("exceptId", exceptId)
                    .getSingleResult();
        }
        return result.intValue();
    }
}
