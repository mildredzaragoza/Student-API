package com.aspire.studentapi.utils.validator;

import com.aspire.studentapi.models.StudentModel;
import com.aspire.studentapi.utils.exceptions.APIUnprocessableEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class StudentValidatorImpl implements StudentValidator{
    @Override
    public void validator(StudentModel studentModel) throws APIUnprocessableEntity {
        String nameWithoutSpaces = studentModel.getName().replace(" ", "");
        if(studentModel.getName() == null || studentModel.getName().isEmpty()){
            setMessage("Name is required.");
        }
        for (char character : nameWithoutSpaces.toCharArray()) {
            if (!Character.isLetter(character)) {
                setMessage("Enter a valid name.");
            }
        }

        if (studentModel.getPhone().length() != 10) {
            setMessage("Your phone number must contain 10 digits.");
        } else {
            for (char number : studentModel.getPhone().toCharArray()) {
                if (!Character.isDigit(number)) {
                    setMessage("Enter a valid number.");
                }
            }
        }

        String emailFormat = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailFormat);
        if (!pattern.matcher(studentModel.getEmail()).find()) {
            setMessage("Enter a valid email.");
        }
    }

    private void setMessage(String message) throws APIUnprocessableEntity{
        throw new APIUnprocessableEntity(message);
    }
}
