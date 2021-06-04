package utils.validators;

import com.jfoenix.validation.base.ValidatorBase;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;

public class PasswordValidator extends ValidatorBase {
    @Override
    protected void eval() {
        if (this.srcControl.get() instanceof TextInputControl) {
            this.evalTextInputField();
        }
    }

    private void evalTextInputField() {
        TextInputControl textField = (TextInputControl) this.srcControl.get();

        if (textField.getText() == null || textField.getText().isEmpty()) {
            this.hasErrors.set(true);
        }
    }

    @Override
    public void setMessage(String msg) {
        super.setMessage("Password cannot be empty");
    }

    @Override
    public void setIcon(Node icon) {
        super.setIcon(new MaterialIconView(MaterialIcon.WARNING));
    }
}
