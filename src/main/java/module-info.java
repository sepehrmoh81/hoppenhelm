module com.smma.hoppenhelm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.smma.hoppenhelm to javafx.fxml;
    exports com.smma.hoppenhelm;
    opens com.smma.hoppenhelm.controller to javafx.fxml;
    exports com.smma.hoppenhelm.controller;
    opens com.smma.hoppenhelm.model to javafx.fxml;
    exports com.smma.hoppenhelm.model;
    exports com.smma.hoppenhelm.model.drawable;
    opens com.smma.hoppenhelm.model.drawable to javafx.fxml;
    exports com.smma.hoppenhelm.util;
    opens com.smma.hoppenhelm.util to javafx.fxml;
}