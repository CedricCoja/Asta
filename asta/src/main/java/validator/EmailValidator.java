
package validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    String mail = String.valueOf(value);
    if (mail.length() != 0 && !mail.matches("^[^@]*@[^@.]*\\.[^@]*")) {
      ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
      String text = bundle.getString("wrongEmail");
      throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, text, text));
    }
  }

}
