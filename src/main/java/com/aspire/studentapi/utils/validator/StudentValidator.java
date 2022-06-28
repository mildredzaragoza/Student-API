package com.aspire.studentapi.utils.validator;

import com.aspire.studentapi.models.StudentModel;
import com.aspire.studentapi.utils.exceptions.APIUnprocessableEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public interface StudentValidator {

    void validator(StudentModel studentModel) throws APIUnprocessableEntity;
}
