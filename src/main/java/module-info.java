module com.example.c42project {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.c42project to javafx.fxml;
    exports com.example.c42project;
}