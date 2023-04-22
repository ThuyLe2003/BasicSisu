module fi.tuni.prog3.sisu {
    requires javafx.controls;
    exports fi.tuni.prog3.sisu;
    requires com.google.gson;
    requires org.json;
    requires javafx.fxml;
    requires java.base;
    opens fi.tuni.prog3.sisu to javafx.fxml;

}
