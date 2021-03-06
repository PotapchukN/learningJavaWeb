package by.scoring.validator;

import by.scoring.model.service.IUserService;
import by.scoring.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link by.scoring.model.entity.User} class,
 * implements {@link org.springframework.validation.Validator} interface.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private IUserService userService;

    //    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    //    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (user.getEmail().length() < 8 || user.getEmail().length() > 32 || !user.getEmail().contains("@")) {
            errors.rejectValue("email", "Size.userForm.email");
        }

        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}

