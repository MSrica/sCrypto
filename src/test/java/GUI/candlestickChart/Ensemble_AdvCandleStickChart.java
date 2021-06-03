//package GUI.candlestickChart;
///*
// * Part of Ensemble code
// * related to AdvCandleStickChart
// *
// * extracted by Nicolas
// * July 20th, 2016
// *
// * Exemple shown at the bottom of
// * http://docs.oracle.com/javafx/2/charts/chart-overview.htm
// *
// * Ensemble launch page:
// * http://download.oracle.com/otndocs/products/javafx/2/samples/Ensemble/index.html
// *
// * Initial source code:
// * http://grepcode.com/file/repo1.maven.org/maven2/org.jbundle.javafx.example/org.jbundle.javafx.example.ensemble/0.9.0/ensemble/samples/charts/custom/AdvCandleStickChartSample.java?av=f
// *
// */
//
///*
// * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
// * All rights reserved. Use is subject to license terms.
// *
// * This file is available and licensed under the following license:
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions
// * are met:
// *
// *  - Redistributions of source code must retain the above copyright
// *    notice, this list of conditions and the following disclaimer.
// *  - Redistributions in binary form must reproduce the above copyright
// *    notice, this list of conditions and the following disclaimer in
// *    the documentation and/or other materials provided with the distribution.
// *  - Neither the name of Oracle Corporation nor the names of its
// *    contributors may be used to endorse or promote products derived
// *    from this software without specific prior written permission.
// *
// * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
// */
//import java.util.ArrayList;
//import java.util.Iterator;
//import javafx.animation.FadeTransition;
//import javafx.collections.FXCollections;
//import javafx.scene.Group;
//import javafx.scene.chart.Axis;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.scene.control.Tooltip;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.LineTo;
//import javafx.scene.shape.MoveTo;
//import javafx.scene.shape.Path;
//import javafx.scene.layout.Pane;
//import java.util.List;
//import javafx.scene.control.Label;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Region;
//import javafx.application.Application;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
///**
// * Ensemble Main Application
// */
//public class Ensemble_AdvCandleStickChart extends Application {
//
//    @Override
//    public void start(Stage stage) {
//        stage.setTitle("AdvCandleStickChart from Ensemble");
//        Scene scene = new Scene(new AdvCandleStickChart());
//        scene.getStylesheets().add(getClass().getResource("/stylesheets/candlestick_chart.css").toExternalForm());
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}