package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LSystem extends Application {

    static String start="F+F+F+F"/*F-F-F-F*/;
    static int depth = 1;
    static float angle = 0;
    static double x1 = 100, y1 = 100, length=5;

    static final int WIDTH = 600;
    static final int HEIGHT = 600;

    static Canvas canvas = new Canvas(WIDTH, HEIGHT);
    static GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("L-systems");
        stage.show();
        System.out.println(generatestring(depth).length());
        draw(generatestring(depth));
    }

    public static String generatestring(int g)
    {
        StringBuilder result= new StringBuilder();
        String rule="F+f-FF+F+FF+Ff+FF-f+FF-F-FF-Ff-FFF"/*"F+FF-FF-F-F+F+FF-F-F+F+FF+FF-F"*/;
        String rule2="ffffff";
        for (int i=0; i<g; i++)
        {
            result = new StringBuilder();
            for(int j=0; j<start.length(); j++)
            {
                char letter=start.charAt(j);
                if(letter=='F')
                {
                    result.append(rule);
                }
                else if(letter=='f')
                {
                    result.append(rule2);
                }
                else
                {
                    result.append(letter);
                }
            }
        start = result.toString();
        }
     return result.toString();
    }

    public void draw(String lsystem) {
        graphicsContext.setStroke(Color.FIREBRICK);
        graphicsContext.setLineWidth(1);
        for(int i=0; i<lsystem.length(); i++)
        {
            char letter = lsystem.charAt(i);
            if(letter=='+')
            {
                angle+=Math.PI/2;
            }
            else if(letter=='-')
            {
                angle-=Math.PI/2;
            }
            else if(letter=='f')
            {
                x1=x1+length*Math.cos(angle);
                y1=y1+length*Math.sin(angle);
                graphicsContext.lineTo(x1, y1);
            }
            else
            {
                graphicsContext.beginPath();
                graphicsContext.lineTo(x1, y1);
                x1=x1+length*Math.cos(angle);
                y1=y1+length*Math.sin(angle);
                graphicsContext.lineTo(x1, y1);
                graphicsContext.stroke();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}