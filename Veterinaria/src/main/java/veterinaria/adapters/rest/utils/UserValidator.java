
package veterinaria.adapters.rest.utils;

import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Lopera
 */

@Component
public class UserValidator extends SimpleValidator {
    
     
    public String userNameValidator(String value) throws Exception {
        return stringValidator(value, " nombre de usuario ");
    }

  
    public String passwordValidator(String value) throws Exception {
        return stringValidator(value, " contraseña de usuario ");
    }
}


