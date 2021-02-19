package sample;

import com.sun.javafx.event.EventHandlerManager;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;

import static java.lang.System.nanoTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        long a = 0;
        long b = 1;
        long c = 0;
        int index = 0;
        long x;
        long startTime = System.nanoTime();

        NumberAxis xAxis = new NumberAxis(0.0,48.0,2);
        xAxis.setLabel("Nth Sequence Number");

        NumberAxis yAxis = new NumberAxis(0,300000000,25000000);
        yAxis.setLabel("Nanoseconds");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("Recursive");
        while(index <= 40) {
            long start = System.nanoTime();
            x = fib(index);
            long stop = System.nanoTime();
            long time = stop - start;
            series.getData().add(new XYChart.Data(index, time));
            index++;
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Iterative");

        for(int i=0;i <=40;i++) {
            c = a + b;
            a = b;
            b = c;
            series2.getData().add(new XYChart.Data(i, startTime/100000000));
        }

        lineChart.getData().addAll(series, series2);

        Group root = new Group(lineChart);
        Scene scene = new Scene(root,600,400);
        primaryStage.setTitle("Fibonacci Line Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static long fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }else {
           return  fib(n - 1) + fib(n - 2);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
