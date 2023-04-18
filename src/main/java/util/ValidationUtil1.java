package util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil1 {
    public static Object validate1(LinkedHashMap<JFXTextField, Pattern> map1, Button btn) {
        btn.setDisable(true);
        for (TextField textFieldKey : map1.keySet()) {
            Pattern patternValue = map1.get(textFieldKey);
            if (!patternValue.matcher(textFieldKey.getText()).matches()) {
                if (!textFieldKey.getText().isEmpty()) {
                    textFieldKey.setStyle("-fx-border-color: red");

                }
                return textFieldKey;
            }
            textFieldKey.setStyle("-fx-border-color: green");

        }
        btn.setDisable(false);
        return true;
    }
}
