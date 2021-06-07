package utils.validators;

import com.jfoenix.validation.base.ValidatorBase;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;

public class PhoneValidator extends ValidatorBase {

    public PhoneValidator() {
        setMessage(null);
        setIcon(null);
    }

    @Override
    protected void eval() {

    }


    @Override
    public void validate() {
        TextInputControl textField = (TextInputControl) this.srcControl.get();
        if (textField.getText().isEmpty() || textField.getText().length() != 10) {
            this.hasErrors.set(true);
        } else {
            try {
                Integer.parseInt(textField.getText());
                this.hasErrors.set(false);
            } catch (NumberFormatException exception) {
                this.hasErrors.set(true);
            }
        }
    }

    @Override
    public void setMessage(String msg) {
        super.setMessage("Invalid phone number");
    }

    @Override
    public void setIcon(Node icon) {
        super.setIcon(new MaterialIconView(MaterialIcon.CANCEL));
    }
}
