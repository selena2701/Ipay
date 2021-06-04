package utils.validators;

import com.jfoenix.validation.base.ValidatorBase;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends ValidatorBase {

    @Override
    public void setMessage(String msg) {
        super.setMessage("Email is invalid");
    }

    @Override
    public void setIcon(Node icon) {
        super.setIcon(new MaterialIconView(MaterialIcon.WARNING));
    }

    @Override
    protected void eval() {
        if (this.srcControl.get() instanceof TextInputControl) {
            this.evalTextInputField();
        }
    }

    private void evalTextInputField() {
        TextInputControl textField = (TextInputControl) this.srcControl.get();
        System.out.println(textField.getText());

        String emailRegex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(textField.getText());

        if (textField.getText() == null || textField.getText().isEmpty()) {
            this.hasErrors.set(true);
        } else if (!matcher.matches()) {
            this.hasErrors.set(true);
        }
        this.hasErrors.set(false);
    }

    @Override
    public void validate() {
        super.validate();
    }
}
