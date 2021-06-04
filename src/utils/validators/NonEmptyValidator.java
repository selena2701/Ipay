package utils.validators;

import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.scene.Node;

public class NonEmptyValidator extends RequiredFieldValidator {
    public NonEmptyValidator(String message) {
        setMessage(message);
        setIcon(null);
    }

    @Override
    public void setMessage(String msg) {
        super.setMessage(msg);
    }

    @Override
    public void setIcon(Node icon) {
        super.setIcon(new MaterialIconView(MaterialIcon.WARNING));
    }
}
