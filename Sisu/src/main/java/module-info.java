module fi.tuni.prog3.sisu {
    requires javafx.controls;
    requires java.base;
    requires javafx.graphicsEmpty;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.json;
    
    opens fi.tuni.prog3.sisu to javafx.fxml;
    exports fi.tuni.prog3.sisu;

}
