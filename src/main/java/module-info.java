module com.project.binarytreemorsecode {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.binarytreemorsecode to javafx.fxml;
    exports com.project.binarytreemorsecode;
}