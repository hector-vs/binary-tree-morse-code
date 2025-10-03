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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import lists.Treee;
import lists.HNode;

public class TreeeApplication extends Application {
    private static final double NODE_R    = 16;
    private static final double LEVEL_GAP = 90;
    private static final double MARGIN    = 40;

    private static final Color BG_COLOR        = Color.WHITE;
    private static final Color EDGE_COLOR      = Color.BLACK;
    private static final Color NODE_FILL_COLOR = Color.web("#1f2937");
    private static final Color NODE_STROKE     = Color.BLACK;
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

        TextFormatter<String> morseFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[._]*") && newText.length() <= 6) return change;
            return null;
        });
        txtCodigo.setTextFormatter(morseFormatter);

        TextField txtLetra = new TextField();
        txtLetra.setPromptText("Digite a letra");
        txtLetra.setStyle("-fx-background-color: #222; -fx-text-fill: white; " +
                "-fx-border-color: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 6;");

        Button btnEnviar = new Button("Enviar");
        btnEnviar.setStyle("-fx-background-color: transparent; -fx-border-color: white; " +
                "-fx-border-radius: 8; -fx-background-radius: 8; -fx-text-fill: white; -fx-padding: 4 10;");

        btnEnviar.setOnAction(e -> {
            String codigo = txtCodigo.getText();
            String letra  = txtLetra.getText();
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
            if (change.getText().matches("[._ ]*")) return change;
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

        double colsBase   = Math.pow(2, Math.max(0, depth - 1));
        double slotMin    = NODE_R * 4;
        double usableWide = Math.max(colsBase * slotMin, 600 - MARGIN * 2);

        double width  = usableWide + MARGIN * 2;
        double height = depth * LEVEL_GAP + MARGIN * 2;

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(BG_COLOR);
        gc.fillRect(0, 0, width, height);

        gc.setLineWidth(2);
        gc.setStroke(EDGE_COLOR);
        gc.setFill(TEXT_COLOR);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        desenharNoGrid(gc, t.getRoot(), 0, 0, depth, width);

        Group group = new Group(canvas);
        group.setCache(false);
        canvas.setCache(false);

        ScrollPane scrollPane = new ScrollPane(group);
        scrollPane.setCache(false);
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(550, 350);
        scrollPane.setFitToWidth(false);
        scrollPane.setFitToHeight(false);

        final double[] scale = {1.0};
        group.setOnScroll(evt -> {
            if (!evt.isControlDown()) return;
            double delta = (evt.getDeltaY() > 0) ? 1.1 : 0.9;
            scale[0] = clamp(scale[0] * delta, 0.5, 2.0);
            group.setScaleX(scale[0]);
            group.setScaleY(scale[0]);
            evt.consume();
        });

        contentBox.getChildren().add(scrollPane);
    }

    private void desenharNoGrid(GraphicsContext gc,
                                HNode<String> node,
                                int level,
                                int indexAtLevel,
                                int maxDepth,
                                double totalWidth) {
        if (node == null) return;

        double usable = totalWidth - MARGIN * 2;
        int cols      = 1 << level;
        double slotW  = usable / cols;

        double x = MARGIN + slotW * (indexAtLevel + 0.5);
        double y = MARGIN + NODE_R + level * LEVEL_GAP;

        int childLevel = level + 1;
        if (childLevel < maxDepth) {
            int childCols  = 1 << childLevel;
            double childW  = usable / childCols;

            if (node.getLeft() != null) {
                int li = indexAtLevel * 2;
                double lx = MARGIN + childW * (li + 0.5);
                double ly = MARGIN + NODE_R + childLevel * LEVEL_GAP;
                gc.setStroke(EDGE_COLOR);
                gc.strokeLine(x, y, lx, ly);
                desenharNoGrid(gc, node.getLeft(), childLevel, li, maxDepth, totalWidth);
            }
            if (node.getRight() != null) {
                int ri = indexAtLevel * 2 + 1;
                double rx = MARGIN + childW * (ri + 0.5);
                double ry = MARGIN + NODE_R + childLevel * LEVEL_GAP;
                gc.setStroke(EDGE_COLOR);
                gc.strokeLine(x, y, rx, ry);
                desenharNoGrid(gc, node.getRight(), childLevel, ri, maxDepth, totalWidth);
            }
        }

        gc.setFill(NODE_FILL_COLOR);
        gc.fillOval(x - NODE_R, y - NODE_R, NODE_R * 2, NODE_R * 2);

        gc.setStroke(NODE_STROKE);
        gc.strokeOval(x - NODE_R, y - NODE_R, NODE_R * 2, NODE_R * 2);

        if (node.getElement() != null) {
            gc.setFill(TEXT_COLOR);
            gc.fillText(node.getElement(), x, y);
        }
    }

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
