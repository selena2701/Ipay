package utils.validators;

import com.jfoenix.validation.base.ValidatorBase;
import database.authAPI.Authenticate;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;
import utils.exceptions.UserExistsException;

import java.sql.SQLException;

public class UserExistedValidator extends ValidatorBase {
    @Override
    protected void eval() {
    }

    @Override
    public void validate() {
        TextInputControl textField = (TextInputControl) this.srcControl.get();
        try {
            this.hasErrors.set(Authenticate.checkUserExists(textField.getText()));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void setMessage(String msg) {
        super.setMessage("User is existed");
    }

    @Override
    public void setIcon(Node icon) {
        super.setIcon(new MaterialIconView(MaterialIcon.CANCEL));
    }
}
