package com.bat.service;

import com.bat.dao.AnnotationDao;
import com.bat.service.interfaces.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnnotationServiceImpl implements AnnotationService {
    @Autowired
    AnnotationDao annotationDao;

    @Override
    public boolean exists(String table, String column, String value) {
        return annotationDao.occurrenceOfAValue(table, column, value) == 1;
    }
}
