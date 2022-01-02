package util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Button;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class Validation {
    public static Object validate(LinkedHashMap<JFXTextField, Pattern> map, Button btn , String colour ) {
        for (JFXTextField textFieldKey:map.keySet()) {
            Pattern patternValue = map.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (! textFieldKey.getText().isEmpty()) {
                    textFieldKey.setStyle("-fx-text-fill: red ");
                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-text-fill: "+colour);
        }
        btn.setDisable(false);
        return true;
    }
}
