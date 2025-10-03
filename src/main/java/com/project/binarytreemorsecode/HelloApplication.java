package com.project.binarytreemorsecode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lists.Treee;

public class HelloApplication extends Application {

    private VBox contentBox;
    private Label resultLabel;
    Treee t = new Treee();

    @Override
    public void start(Stage primaryStage) {
        Button btnInserir = new Button("Inserir");
        Button btnCodificar = new Button("Codificar");
        Button btnDecodificar = new Button("Decodificar");

        String btnStyle = "-fx-background-color: transparent; " +
                "-fx-border-color: white; " +
                "-fx-border-radius: 12; " +
                "-fx-background-radius: 12; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 14px; " +
                "-fx-padding: 6 18;";

        btnInserir.setStyle(btnStyle);
        btnCodificar.setStyle(btnStyle);
        btnDecodificar.setStyle(btnStyle);

        HBox topMenu = new HBox(15, btnInserir, btnCodificar, btnDecodificar);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.setPadding(new Insets(20));

        contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);

        resultLabel = new Label("");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox mainLayout = new VBox(20, topMenu, contentBox, resultLabel);
        mainLayout.setStyle("-fx-background-color: #111;");
        mainLayout.setPadding(new Insets(20));

        Scene scene = new Scene(mainLayout, 600, 400);

        btnInserir.setOnAction(e -> mostrarInserir());
        btnCodificar.setOnAction(e -> mostrarCodificar());
        btnDecodificar.setOnAction(e -> mostrarDecodificar());

        primaryStage.setTitle("Árvore Binária - Código Morse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarInserir() {
        contentBox.getChildren().clear();

        TextField txtCodigo = new TextField();
        txtCodigo.setPromptText("Digite o código Morse");
        txtCodigo.setStyle("-fx-background-color: #222; -fx-text-fill: white; " +
                "-fx-border-color: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 6;");

        TextField txtLetra = new TextField();
        txtLetra.setPromptText("Digite a letra");
        txtLetra.setStyle("-fx-background-color: #222; -fx-text-fill: white; " +
                "-fx-border-color: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 6;");

        Button btnEnviar = new Button("Enviar");
        btnEnviar.setStyle("-fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-radius: 8; -fx-background-radius: 8; -fx-text-fill: white; -fx-padding: 4 10;");

        btnEnviar.setOnAction(e -> {
            String codigo = txtCodigo.getText();
            String letra = txtLetra.getText();
            try {
                t.insert(letra, codigo);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            resultLabel.setText("Inserido: " + codigo + " = " + letra);
        });

        contentBox.getChildren().addAll(txtCodigo, txtLetra, btnEnviar);
    }

    private void mostrarCodificar() {
        contentBox.getChildren().clear();

        TextField txtPalavra = new TextField();
        txtPalavra.setPromptText("Digite uma palavra");
        txtPalavra.setStyle("-fx-background-color: #222; -fx-text-fill: white; " +
                "-fx-border-color: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 6;");

        Button btnEnviar = new Button("Enviar");
        btnEnviar.setStyle("-fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-radius: 8; -fx-background-radius: 8; -fx-text-fill: white; -fx-padding: 4 10;");

        btnEnviar.setOnAction(e -> {
            String palavra = txtPalavra.getText();
            String code = null;
            try {
                code = t.encode(palavra);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            resultLabel.setText(palavra + " fica igual = "+code);
            System.out.println(code);
        });

        contentBox.getChildren().addAll(txtPalavra, btnEnviar);
    }

    private void mostrarDecodificar() {
        contentBox.getChildren().clear();

        TextField txtCodigo = new TextField();
        txtCodigo.setPromptText("Digite o código Morse");
        txtCodigo.setStyle("-fx-background-color: #222; -fx-text-fill: white; " +
                "-fx-border-color: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 6;");

        Button btnEnviar = new Button("Enviar");
        btnEnviar.setStyle("-fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-radius: 8; -fx-background-radius: 8; -fx-text-fill: white; -fx-padding: 4 10;");

        btnEnviar.setOnAction(e -> {
            String codigo = txtCodigo.getText();
            String resultText = null;
            try {
                resultText = t.decode(codigo);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            resultLabel.setText(codigo + " fica igual = " + resultText);
        });

        contentBox.getChildren().addAll(txtCodigo, btnEnviar);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
