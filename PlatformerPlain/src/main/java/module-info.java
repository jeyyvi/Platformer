/** The main module for the Platformer Game application.
 * <p>
 *     The com.example.platformerplain module includes all the necessary packages
 *     and dependencies required for the game application.
 *     <br><br>
 *     The module specifies the required JavaFX and logging modules, exports key packages,
 *     and opens packages for reflection.
 * </p>
 */
module com.example.platformerplain {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    exports com.example.platformerplain;
    exports com.example.platformerplain.model;
    exports com.example.platformerplain.controller to javafx.fxml, org.junit.jupiter.api;
    exports com.example.platformerplain.view;
    exports com.example.platformerplain.util;

    opens com.example.platformerplain to javafx.fxml, org.junit.jupiter.api;
    opens com.example.platformerplain.model to javafx.fxml, org.junit.jupiter.api;
    opens com.example.platformerplain.controller to javafx.fxml, org.junit.jupiter.api;
    opens com.example.platformerplain.view to javafx.fxml, org.junit.jupiter.api;
    opens com.example.platformerplain.util to javafx.fxml, org.junit.jupiter.api;
    exports com.example.platformerplain.observer;
    opens com.example.platformerplain.observer to javafx.fxml, org.junit.jupiter.api;
}
