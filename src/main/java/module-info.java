module com.smma.hoppenhelm {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens com.smma.hoppenhelm to javafx.fxml;
    exports com.smma.hoppenhelm;
    exports com.smma.hoppenhelm.controller;
}