module sample2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;

    opens view;
    opens dao;
    opens daoFactory;
    opens controller;
    opens model;
}