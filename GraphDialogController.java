

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphDialogController {

    /* ===== create chart and x-category + y-number axises ===== */
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private BarChart<String, Number> bc;//  = new BarChart<Number, String>(xAxis, yAxis);

    /* ===== chart series ===== */
    private XYChart.Series seriesBubbleStandard = new XYChart.Series();
    private XYChart.Series seriesBubbleEnhanced = new XYChart.Series();
    private XYChart.Series seriesSelection = new XYChart.Series();
    private XYChart.Series seriesInsertion  = new XYChart.Series();

    private ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();

    private String[] chartLabels = {
            "1k-sorted", "1k-inverse", "1k-random",
            "10k-sorted", "10k-inverse", "10k-random",
            "100k-sorted", "100k-inverse", "100k-random",
    };

    @FXML
    private void initialize(){

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        bc.setTitle("Results");
        xAxis.setLabel("Algorithm");
        yAxis.setLabel("Time(m-secs)");
        bc = new BarChart<>(xAxis, yAxis);

        seriesBubbleStandard.getData().add(new XYChart.Data(chartLabels[0], 80));

        chartData.addAll(seriesBubbleStandard);
        bc.setData(chartData);

    }

    @FXML
    public void setGraphContents(int[][] sortRunTimeLog) {

        seriesBubbleStandard.setName("Bubble (s)");
        seriesBubbleEnhanced.setName("Bubble (e)");
        seriesSelection.setName("Selection");
        seriesInsertion.setName("Insertion");

        for (int log = 0; log < sortRunTimeLog.length; log++){
            for (int i = 0; i < sortRunTimeLog[log].length; i++) {

                switch (log) {
                    case 0:
                        seriesBubbleStandard.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        break;
                    case 1:
                        seriesBubbleEnhanced.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        break;
                    case 2:
                        seriesSelection.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        break;
                    case 3:
                        seriesInsertion.getData().add(new XYChart.Data(chartLabels[i], sortRunTimeLog[log][i]));
                        break;
                }
            }
        }
        chartData.addAll(seriesBubbleStandard,seriesBubbleEnhanced,seriesSelection,seriesInsertion);
        bc.setData(chartData);

//        Timeline tl = new Timeline();
//        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500),
//                new EventHandler<ActionEvent>() {
//                    @Override public void handle(ActionEvent actionEvent) {
//                        for (XYChart.Series<String, Number>series : bc.getData()) {
//                            for (XYChart.Data<String, Number> data : series.getData()) {
//                                data.setXValue(Math.random() * 100);
//                            }
//                        }
//                    }
//                }));
//        tl.setCycleCount(Animation.INDEFINITE);
//        tl.play();
    }
}
