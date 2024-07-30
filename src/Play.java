import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Play extends Application {
	private Image im = new Image("file:///C:/Users/as_14/eclipse-workspace/EE364Project/src/unoi.png");

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException, Exception, IllegalArgumentException {
		VBox vb = new VBox(5);
		vb.setOpaqueInsets(new Insets(10));
		vb.setAlignment(Pos.CENTER);

		vb.setStyle("-fx-background-Color: Black ");

		ImageView imf = new ImageView(im);
		imf.setFitHeight(350);
		imf.setFitWidth(600);
		Button b00 = new Button("play");
		Button b10 = new Button("Exit");

		imf.setTranslateY(-30);

		vb.getChildren().addAll(imf, b00, b10);

		b00.setPrefSize(90, 60);
		b10.setPrefSize(90, 60);

		b00.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
		b10.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));

		b00.setStyle("-fx-border-color: blue");
		b10.setStyle("-fx-border-color: blue");

		b00.setStyle("-fx-background-color: Red");
		b10.setStyle("-fx-background-color: Red");

		b00.setTextFill(Color.ANTIQUEWHITE);
		b10.setTextFill(Color.ANTIQUEWHITE);

		b00.setOnAction(e -> {
			primaryStage.close();
			Stage st = new Stage();

			Button[] buttons = { new Button("red"), new Button("blue"), new Button("green"), new Button("yellow") };
			for (Button button : buttons) {
				button.setAlignment(Pos.CENTER);
				button.setPrefSize(90, 60);
				if (button.getText().equals("red")) {
					button.setTextFill(Color.RED);
				}
				if (button.getText().equals("blue")) {
					button.setTextFill(Color.BLUE);
				}
				if (button.getText().equals("green")) {
					button.setTextFill(Color.GREEN);
				}
				if (button.getText().equals("yellow")) {
					button.setTextFill(Color.YELLOW);
				}
				button.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
			}
			Pane centerPane = new Pane();
			VBox paneButton = new VBox();
			Pane pane1 = new Pane();
			Pane pane2 = new Pane();

			BorderPane pane = new BorderPane();
			Label label = new Label();
			Button button = new Button("Draw");
			button.setAlignment(Pos.CENTER);
			button.setPrefSize(90, 60);
			button.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
			pane.setPadding(new Insets(5, 5, 5, 5));
			paneButton.setPadding(new Insets(5, 5, 5, 5));
			centerPane.setPadding(new Insets(5, 5, 5, 5));
			pane1.setPadding(new Insets(5, 5, 5, 5));
			pane2.setPadding(new Insets(5, 5, 5, 5));

			try {
				CardDeck.initDefaultDeck("src/cards/", "png");
				CardDeck.shuffle();
				Controller o = new Controller();
				o.onePlayer(pane, pane1, pane2, centerPane, label, paneButton, buttons, button, st, primaryStage);
			} catch (Exception r) {
				System.out.println(r);
			}

			pane.setStyle(
					"-fx-background-image: url(\"file:///C:/Users/as_14/eclipse-workspace/EE364Project/src/unopo.png\");\r\n"
							+ "    -fx-background-repeat: stretch;   \r\n" + "    -fx-background-size: cover;\r\n");// +

			Button ba = new Button("Back");
			pane.setRight(ba);
			ba.setPrefSize(60, 50);

			ba.setPrefSize(90, 60);
			ba.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
			Scene scene1 = new Scene(pane,1200,660);
			st.setScene(scene1);
			st.setTitle("UNO");
			st.show();

			ba.setOnAction(ex -> {
				st.close();
				primaryStage.show();
			});
		});

		b10.setOnAction(e -> {

			System.exit(0);
		});

		Scene scene = new Scene(vb, 800, 600);
		primaryStage.setTitle("UNO"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("javafx.runtime.version: " + System.getProperties().get("javafx.runtime.version"));
		launch(args);
		

	}
}
