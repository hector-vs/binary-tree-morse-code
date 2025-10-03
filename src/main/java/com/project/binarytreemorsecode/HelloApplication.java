package com.project.binarytreemorsecode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import lists.Treee;
import lists.HNode;

public class HelloApplication extends Application {

    // ====== constantes visuais ======
    private static final double NODE_R = 16;        // raio do nó
    private static final double LEVEL_GAP = 90;     // distância vertical entre níveis
    private static final double MARGIN = 40;        // margem do canvas

    private static final Color BG_COLOR        = Color.BLACK;
    private static final Color EDGE_COLOR      = Color.WHITE;
    private static final Color NODE_FILL_COLOR = Color.web("#1f2937"); // cinza escuro
    private static final Color NODE_STROKE     = Color.WHITE;
    private static final Color TEXT_COLOR      = Color.WHITE;

    private VBox contentBox;
    private TextField resultLabel;
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

        resultLabel = new TextField("");
        resultLabel.setEditable(false);
        resultLabel.setStyle("-fx-background-color: transparent; -fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;");

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
                resultLabel.setText("Inserido: " + codigo + " = " + letra);
            } catch (Exception ex) {
                resultLabel.setText("Erro ao inserir: " + ex.getMessage());
            }
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
            try {
                String code = t.encode(palavra);
                resultLabel.setText(palavra + " fica igual = " + code);
            } catch (Exception ex) {
                resultLabel.setText("Erro ao codificar: " + ex.getMessage());
            }
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
            if (change.getText().matches("[._ ]*")) {
                return change;
            }
            return null;
        }));

        Button btnEnviar = new Button("Enviar");
        btnEnviar.setStyle("-fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-radius: 8; -fx-background-radius: 8; -fx-text-fill: white; -fx-padding: 4 10;");

        btnEnviar.setOnAction(e -> {
            String codigo = txtCodigo.getText();
            try {
                String resultText = t.decode(codigo);
                resultLabel.setText(codigo + " fica igual = " + resultText);
            } catch (Exception ex) {
                resultLabel.setText("Erro ao decodificar: " + ex.getMessage());
            }
        });

        contentBox.getChildren().addAll(txtCodigo, btnEnviar);
    }

    private void mostrarArvore() {
        contentBox.getChildren().clear();

        int depth = getDepth(t.getRoot());
        if (depth <= 0) depth = 1;

        // largura aproximada: 2^(depth-1) nós na base, cada "slot" ~ (NODE_R*4)
        double baseSlots = Math.pow(2, Math.max(0, depth - 1));
        double approxWidth = baseSlots * (NODE_R * 4);
        double width = Math.max(approxWidth + MARGIN * 2, 800);
        double height = depth * LEVEL_GAP + MARGIN * 2;

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // fundo preto
        gc.setFill(BG_COLOR);
        gc.fillRect(0, 0, width, height);

        // estilos padrão
        gc.setLineWidth(2);
        gc.setStroke(EDGE_COLOR);
        gc.setFill(TEXT_COLOR);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        // desenha árvore centralizada
        double rootX = width / 2.0;
        double rootY = MARGIN + NODE_R;
        double initialOffset = Math.max((approxWidth / 4), 60); // espaçamento horizontal inicial

        desenharNo(gc, t.getRoot(), rootX, rootY, initialOffset);

        // Scroll + Pan + Zoom
        Group group = new Group(canvas);
        ScrollPane scrollPane = new ScrollPane(group);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(550, 350);
        scrollPane.setFitToWidth(false);
        scrollPane.setFitToHeight(false);

        // Zoom com Ctrl + scroll
        final double[] scale = {1.0};
        group.setOnScroll(evt -> {
            if (!evt.isControlDown()) return;
            double delta = (evt.getDeltaY() > 0) ? 1.1 : 0.9;
            scale[0] = clamp(scale[0] * delta, 0.3, 3.0);
            group.setScaleX(scale[0]);
            group.setScaleY(scale[0]);
            evt.consume();
        });

        contentBox.getChildren().add(scrollPane);
    }

    private void desenharNo(GraphicsContext gc, HNode<String> node, double x, double y, double offset) {
        if (node == null) return;

        // ramos
        if (node.getLeft() != null) {
            gc.setStroke(EDGE_COLOR);
            gc.strokeLine(x, y, x - offset, y + LEVEL_GAP);
            desenharNo(gc, node.getLeft(), x - offset, y + LEVEL_GAP, Math.max(offset / 1.6, 30));
        }
        if (node.getRight() != null) {
            gc.setStroke(EDGE_COLOR);
            gc.strokeLine(x, y, x + offset, y + LEVEL_GAP);
            desenharNo(gc, node.getRight(), x + offset, y + LEVEL_GAP, Math.max(offset / 1.6, 30));
        }

        // nó (círculo)
        gc.setFill(NODE_FILL_COLOR);
        gc.fillOval(x - NODE_R, y - NODE_R, NODE_R * 2, NODE_R * 2);

        gc.setStroke(NODE_STROKE);
        gc.strokeOval(x - NODE_R, y - NODE_R, NODE_R * 2, NODE_R * 2);

        // texto centralizado
        if (node.getElement() != null) {
            gc.setFill(TEXT_COLOR);
            gc.fillText(node.getElement(), x, y);
        }
    }

    // ====== helpers ======
    private int getDepth(HNode<String> node) {
        if (node == null) return 0;
        return 1 + Math.max(getDepth(node.getLeft()), getDepth(node.getRight()));
    }

    private double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
