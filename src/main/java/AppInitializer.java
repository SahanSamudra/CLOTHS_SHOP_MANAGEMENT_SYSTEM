import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class AppInitializer extends Application {


    public static Stage mainStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoadingPage.fxml"))));
        primaryStage.setTitle("Shalini Fashion v.1.0  ");
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/download-removebg-preview-removebg-preview.png")));

        Parent loginRoot = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Stage logStage= new Stage();
        logStage.setScene(new Scene(loginRoot));
        logStage.centerOnScreen();
        logStage.getIcons().add(new Image(getClass().getResourceAsStream("img/download-removebg-preview-removebg-preview.png")));
        this.mainStage=logStage;

        PauseTransition delay = new PauseTransition(Duration.seconds(6));
        delay.setOnFinished( event -> primaryStage.close() );
        delay.play();
        PauseTransition delayLog = new PauseTransition(Duration.seconds(6));
           delayLog.setOnFinished( event -> logStage.show() );
        delayLog.play();

    }
}
