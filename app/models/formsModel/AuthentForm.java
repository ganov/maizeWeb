package models.formsModel;

import models.User;
import play.cache.CacheApi;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.i18n.Messages;
import services.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AuthentForm class used by Authentication Form.
 */
public class AuthentForm {


    @Inject CacheApi cache;

    /**
     * champ email du form.
     */
    @Constraints.Required
    @Constraints.Email
    public String email;

    /**
     * champ password du form.
     */
    @Constraints.Required
    public String password;

    /**
     * Validate the authentication.
     *
     * @return null if validation ok, string with details otherwise
     */
    public final Map<String, List<ValidationError>> validate() {
        User user = null;
        Map<String, List<ValidationError>> errors = new HashMap<>();
        List<ValidationError> list = new ArrayList<>();
        try {
            user = UserService.authenticate(email, password, cache);
        } catch (Exception e) {
            list.add(new ValidationError("email",  Messages.get("form.authent.invalid.user.or.password")));
            errors.put("email", list);
        }

        if (user == null) {
            list.add(new ValidationError("email",  Messages.get("form.authent.invalid.user.or.password")));
            errors.put("email", list);
        }

        if(!errors.isEmpty()){
            return errors;
        }else {
            return null;
        }
    }
}
