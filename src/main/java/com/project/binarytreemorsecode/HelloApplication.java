package com.project.binarytreemorsecode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lists.Treee;
import lists.HNode;

public class HelloApplication extends Application {

    private VBox contentBox;
    private Label resultLabel;
    Treee t = new Treee();

    @Override
    public void start(Stage primaryStage) {
        Button btnInserir = new Button("Inserir");
        Button btnCodificar = new Button("Codificar");
        Button btnDecodificar = new Button("Decodificar");
        Button btnMostrarArvore = new Button("Mostrar Árvore");

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
        btnMostrarArvore.setStyle(btnStyle);

        HBox topMenu = new HBox(15, btnInserir, btnCodificar, btnDecodificar, btnMostrarArvore);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.setPadding(new Insets(20));

        contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);

        resultLabel = new Label("");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;");

        VBox mainLayout = new VBox(20, topMenu, contentBox, resultLabel);
        mainLayout.setStyle("-fx-background-color: #111;");
        mainLayout.setPadding(new Insets(20));

        Scene scene = new Scene(mainLayout, 600, 500);

        btnInserir.setOnAction(e -> mostrarInserir());
        btnCodificar.setOnAction(e -> mostrarCodificar());
        btnDecodificar.setOnAction(e -> mostrarDecodificar());
        btnMostrarArvore.setOnAction(e -> mostrarArvore());

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

        txtCodigo.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getText().matches("[._]*")) {
                return change;
            }
            return null;
        }));

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
            resultLabel.setText(palavra + " fica igual = " + code);
        });

        contentBox.getChildren().addAll(txtPalavra, btnEnviar);
    }

    private void mostrarDecodificar() {
        contentBox.getChildren().clear();

        TextField txtCodigo = new TextField();
        txtCodigo.setPromptText("Digite o código Morse");
        txtCodigo.setStyle("-fx-background-color: #222; -fx-text-fill: white; " +
                "-fx-border-color: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 6;");

        txtCodigo.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getText().matches("[._]*")) {
                return change;
            }
            return null;
        }));

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

    private void mostrarArvore() {
        contentBox.getChildren().clear();

        Canvas canvas = new Canvas(2000, 1200);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setStroke(javafx.scene.paint.Color.WHITE);
        gc.setLineWidth(2);

        desenharNo(gc, t.getRoot(), 1000, 40, 250);

        ScrollPane scrollPane = new ScrollPane(canvas);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(550, 350);

        contentBox.getChildren().add(scrollPane);
    }


    private void desenharNo(GraphicsContext gc, HNode<String> node, double x, double y, double offset) {
        if (node == null) return;

        if (node.getLeft() != null) {
            gc.strokeLine(x, y, x - offset, y + 60);
            desenharNo(gc, node.getLeft(), x - offset, y + 60, offset / 1.5);
        }
        if (node.getRight() != null) {
            gc.strokeLine(x, y, x + offset, y + 60);
            desenharNo(gc, node.getRight(), x + offset, y + 60, offset / 1.5);
        }

        gc.setFill(javafx.scene.paint.Color.DARKSLATEGRAY);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setStroke(javafx.scene.paint.Color.WHITE);
        gc.strokeOval(x - 15, y - 15, 30, 30);

        if (node.getElement() != null) {
            gc.setFill(javafx.scene.paint.Color.WHITE);
            gc.fillText(node.getElement(), x - 5, y + 5);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
