
package validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "myValidator")
public class MyValidator implements Validator {

  @SuppressWarnings("unchecked")
  public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    //
  }

}
