package mainStart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;


public class MainStart extends Application {
    double x,y = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{

        //tải lên giao diên chính
        URL url = new File("C:\\Users\\Admin\\IdeaProjects\\dic_uet2\\src\\main\\resources\\FXML\\mainStartScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("C:\\Users\\Admin\\IdeaProjects\\dic_uet2\\src\\main\\resources\\FXML\\mainScene.fxml"));
        //fxmlLoader.setRoot(new AnchorPane());
        //Parent root = fxmlLoader.load();

        //thêm file css
        File f = new File("C:\\Users\\Admin\\IdeaProjects\\dic_uet2\\src\\main\\resources\\Css\\mainStart.css");
        root.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
