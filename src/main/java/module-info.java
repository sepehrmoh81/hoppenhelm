module com.smma.hoppenhelm {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens com.smma.hoppenhelm to javafx.fxml;
    exports com.smma.hoppenhelm;
    opens com.smma.hoppenhelm.controller to javafx.fxml;
    exports com.smma.hoppenhelm.controller;
    opens com.smma.hoppenhelm.model to javafx.fxml;
    exports com.smma.hoppenhelm.model;
}