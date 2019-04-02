/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Bar chart analysis review dialog control/logic class.
 * Date: 10/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/** Bar chart analysis review dialog control/logic class. */
public class GraphDialogController {

    /* ===== create chart and x-category + y-number axises ===== */
    @FXML private CategoryAxis xAxis1000, xAxis10000, xAxis100000;
    @FXML private NumberAxis yAxis1000, yAxis10000, yAxis100000;
    @FXML private BarChart<String, Number> bc1000, bc10000, bc100000;

    /* ===== chart series ===== */
    private XYChart.Series BubbleS1000 = new XYChart.Series();
    private XYChart.Series BubbleS10000 = new XYChart.Series();
    private XYChart.Series BubbleS100000 = new XYChart.Series();
    private XYChart.Series BubbleE1000 = new XYChart.Series();
    private XYChart.Series BubbleE10000 = new XYChart.Series();
    private XYChart.Series BubbleE100000 = new XYChart.Series();
    private XYChart.Series Selection1000 = new XYChart.Series();
    private XYChart.Series Selection10000 = new XYChart.Series();
    private XYChart.Series Selection100000 = new XYChart.Series();
    private XYChart.Series Insertion1000  = new XYChart.Series();
    private XYChart.Series Insertion10000  = new XYChart.Series();
    private XYChart.Series Insertion100000  = new XYChart.Series();

    private ObservableList<XYChart.Series<String, Number>> chartData1000 = FXCollections.observableArrayList();
    private ObservableList<XYChart.Series<String, Number>> chartData10000 = FXCollections.observableArrayList();
    private ObservableList<XYChart.Series<String, Number>> chartData100000 = FXCollections.observableArrayList();

    private String[] chartLabels = {"Sorted", "Inverse", "Random","Sorted", "Inverse", "Random","Sorted", "Inverse", "Random"};

    @FXML
    private void initialize(){

        /* ===== bar chart properties ===== */
        bc1000.setData(chartData1000);
        bc10000.setData(chartData10000);
        bc100000.setData(chartData100000);
        bc1000.setTitle("1K");
        bc10000.setTitle("10K");
        bc100000.setTitle("100K");
        bc1000.setBarGap(1);
        bc10000.setBarGap(1);
        bc100000.setBarGap(1);

        /* ===== bar chart axis properties ===== */
        xAxis1000.setLabel("Algorithm");
        yAxis1000.setLabel("Time(m-secs)");
        xAxis10000.setLabel("Algorithm");
        yAxis10000.setLabel("Time(m-secs)");
        xAxis100000.setLabel("Algorithm");
        yAxis100000.setLabel("Time(m-secs)");

        /* ===== bar chart series properties ===== */
        BubbleS1000.setName("Bubble (s)");
        BubbleE1000.setName("Bubble (e)");
        Selection1000.setName("Selection");
        Insertion1000.setName("Insertion");
        BubbleS10000.setName("Bubble (s)");
        BubbleE10000.setName("Bubble (e)");
        Selection10000.setName("Selection");
        Insertion10000.setName("Insertion");
        BubbleS100000.setName("Bubble (s)");
        BubbleE100000.setName("Bubble (e)");
        Selection100000.setName("Selection");
        Insertion100000.setName("Insertion");
    }

    /** Set contents of graph with contents 2D array parameter */
    public void setGraphContents(int[][] sortRunTimeLog) {

        for (int log = 0; log < sortRunTimeLog.length; log++){
            for (int i = 0; i < sortRunTimeLog[log].length; i++) {

                switch (log) {
                    case 0:
                        if(i<3)
                            BubbleS1000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        else if(i>=3 && i<6)
                            BubbleS10000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        else if(i>=6)
                            BubbleS100000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        break;
                    case 1:
                        if(i<3)
                            BubbleE1000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        else if(i>=3 && i<6)
                            BubbleE10000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        else if(i>=6)
                            BubbleE100000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        break;
                    case 2:
                        if(i<3)
                            Selection1000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        else if(i>=3 && i<6)
                            Selection10000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        else if(i>=6)
                            Selection100000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        break;
                    case 3:
                        if(i<3)
                            Insertion1000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        else if(i>=3 && i<6)
                            Insertion10000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        else if(i>=6)
                            Insertion100000.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        break;
                }
            }
        }
        chartData1000.addAll(BubbleS1000,BubbleE1000,Selection1000,Insertion1000);
        bc1000.setData(chartData1000);
        chartData10000.addAll(BubbleS10000,BubbleE10000,Selection10000,Insertion10000);
        bc10000.setData(chartData10000);
        chartData100000.addAll(BubbleS100000,BubbleE100000,Selection100000,Insertion100000);
        bc100000.setData(chartData100000);

    }
}
