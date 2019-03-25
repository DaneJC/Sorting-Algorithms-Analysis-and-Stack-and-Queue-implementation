/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Gary Cullen
 * Description: Algorithm assessment logic & GUI control class
 *              This GUI was constructed using the scene builder tool for convenience.
 *              All interactive/functional controls are contained in this class and link to the fxml file using the fxid.
 * Date: 15/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/** Sort algorithm assessment GUI control class */
public class Controller {

    /* sort assessor tab -------------------------------------------------------------------------------------------- */
    /* ===== combobox controls ===== */
    @FXML private ComboBox cBoxSort, cBoxDSet, cBoxSize;

    /* ===== table view controls ===== */
    @FXML private TableView<SortAlgorithm> tableView;
    @FXML private TableColumn<SortAlgorithm, String> algoCol;
    @FXML private TableColumn<SortAlgorithm, String> dataCol;
    @FXML private TableColumn<SortAlgorithm, String> sizeCol;
    @FXML private TableColumn<SortAlgorithm, Integer> compsCol;
    @FXML private TableColumn<SortAlgorithm, Integer> swapsCol;
    @FXML private TableColumn<SortAlgorithm, Long> timeCol;

    /* ===== data and sort object containers ===== */
    private DataStructures data = new DataStructures();
    private BubbleSort bbSort = new BubbleSort();
    private SelectionSort selSort = new SelectionSort();
    private InsertionSort insSort = new InsertionSort();


    /* ===== user selection containerse ===== */
    private SortAlgorithm selectedSort = new BubbleSort();
    private int[] dataSet;

    /* ===== table view lost ===== */
    private ObservableList<SortAlgorithm> tableData = FXCollections.observableArrayList();

    /* stack and queue tab ------------------------------------------------------------------------------------------ */

    /** Initialise/configure GUI controls */
    @FXML
    private void initialize(){

        /* sort assessor tab ---------------------------------------------------------------------------------------- */
        /* ===== combobox setup ===== */
        // sort combobox setup
        String[] sortItemList = {"Bubble (St)", "Bubble (En)", "Selection", "Insertion"};
        ObservableList<String> observableSortItemList = FXCollections.observableArrayList(sortItemList);
        cBoxSort.setItems(observableSortItemList);
        cBoxSort.getSelectionModel().selectFirst();

        // size combobox setup
        String[] sizeItemList = {"1,000", "10,000", "100,000"};
        ObservableList<String> observableSizeItemList = FXCollections.observableArrayList(sizeItemList);
        cBoxSize.setItems(observableSizeItemList);
        cBoxSize.getSelectionModel().selectFirst();

        // data set combobox setup
        String[] dSetItemList = {"Sorted", "Inverse", "Random"};
        ObservableList<String> observableDSetItemList = FXCollections.observableArrayList(dSetItemList);
        cBoxDSet.setItems(observableDSetItemList);
        cBoxDSet.getSelectionModel().selectFirst();

        /* ===== tableview setup ===== */
        algoCol.setCellValueFactory(new PropertyValueFactory<>("algoName"));
        dataCol.setCellValueFactory(new PropertyValueFactory<>("dataSet"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("dataSize"));
        compsCol.setCellValueFactory(new PropertyValueFactory<>("comparisons"));
        swapsCol.setCellValueFactory(new PropertyValueFactory<>("swaps"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("elapsedTime"));

        tableView.setItems(tableData);

        /* stack & queue tab ---------------------------------------------------------------------------------------- */

    }

    /* Class methods ------------------------------------------------------------------------------------------------ */

    /** Prepare data set & sort algorithm with reference to user selections */
    private void getSelections() {

        /* ===== get data set selections ===== */
        if(cBoxSize.getSelectionModel().getSelectedIndex() == 0) { /* => 1000 -------------- */

            if(cBoxDSet.getSelectionModel().getSelectedIndex() == 0) // => sorted
                dataSet = data.getSorted1000();
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 1) // => inverse
                dataSet = data.getInverse1000();
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 2) // => random
                dataSet = data.getRandom1000();
        }
        else if(cBoxSize.getSelectionModel().getSelectedIndex() == 1) { /* => 10000 -------- */

            if(cBoxDSet.getSelectionModel().getSelectedIndex() == 0) // => sorted
                dataSet = data.getSorted10000();
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 1) // => inverse
                dataSet = data.getInverse10000();
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 2) // => random
                dataSet = data.getRandom10000();
        }
        else if(cBoxSize.getSelectionModel().getSelectedIndex() == 2) { /* => 100000 ------- */

            if(cBoxDSet.getSelectionModel().getSelectedIndex() == 0) // => sorted
                dataSet = data.getSorted100000();
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 1) // => inverse
                dataSet = data.getInverse100000();
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 2) // => random
                dataSet = data.getRandom100000();
        }

        /* ===== get sort algorithm selection ===== */
        if(cBoxSort.getSelectionModel().getSelectedIndex() == 0 || cBoxSort.getSelectionModel().getSelectedIndex() == 1) // => bubble sort
            selectedSort = new BubbleSort();
        else if(cBoxSort.getSelectionModel().getSelectedIndex() == 2) // => selection
            selectedSort = new SelectionSort();
        else if(cBoxSort.getSelectionModel().getSelectedIndex() == 3) // => insertion
            selectedSort = new InsertionSort();

        /* print information --------------------------------------------------------------- */
        selectedSort.setAlgoName((String) cBoxSort.getSelectionModel().getSelectedItem());
        selectedSort.setDataSet((String) cBoxDSet.getSelectionModel().getSelectedItem());
        selectedSort.setDataSize((String) cBoxSize.getSelectionModel().getSelectedItem());
    }

    /** Execute algorithm analysis */
    private void runAssessment() {

        if(selectedSort instanceof BubbleSort && cBoxSort.getSelectionModel().getSelectedIndex() == 1) {

            selectedSort.start();
            selectedSort.SortENH(dataSet);  // reason an interface was not used for the enhanced algorithms - produces error here
            selectedSort.stop();
        }
        else {
            selectedSort.start();
            selectedSort.Sort(dataSet);
            selectedSort.stop();
        }
    }

    /** Log results in tableview */
    private void printResults(){

        tableData.add(selectedSort);
    }

    /* Events ------------------------------------------------------------------------------------------------------- */

    /** Run sort assessment on sort button click */
    @FXML
    private void buttonClicked(){

        getSelections();
        runAssessment();
        printResults();
    }
}