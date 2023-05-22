package mainStart;

import Dictionary.SQL;
import Dictionary.Dictionary;
import Dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;

public class Controller{
    File f = new File("C:\\Users\\Admin\\IdeaProjects\\dic_uet2\\src\\main\\resources\\Css\\mainStart.css");
    @FXML
    private Button searchButton;
    Dictionary dictionary = new Dictionary();
    Word word = new Word();
    public static SQL myConnect;

    static {
        try {
            myConnect = new SQL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Controller() throws SQLException {
    }

    @FXML
    void searchButton(ActionEvent event) throws Exception{
        URL url = new File("C:\\Users\\Admin\\IdeaProjects\\dic_uet2\\src\\main\\resources\\FXML\\mainScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        Stage window = (Stage) searchButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    //nút thoát
    @FXML
    public void Exit (ActionEvent event) {
        System.exit(0);
    }
}
