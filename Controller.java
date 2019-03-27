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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

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
    private SortData data = new SortData();
    private BubbleSort bbSort = new BubbleSort();
    private SelectionSort selSort = new SelectionSort();
    private InsertionSort insSort = new InsertionSort();

    /* ===== user selection containers ===== */
    private SortAlgorithm selectedSort = new BubbleSort();
    private int[] dataSet;

    /* ===== table view lost ===== */
    private ObservableList<SortAlgorithm> tableData = FXCollections.observableArrayList();

    /* stack and queue tab ------------------------------------------------------------------------------------------ */
    /* ===== stack & queue objects ===== */
    Stack stackObj = new Stack();

    /* ===== buttons ===== */
    @FXML private Button btnStackPop, btnStackPush, btnQuePop, btnQuePush;

    /* ===== text fields ===== */
    @FXML private TextField txfStackInput, txfQueInput;

    /* ===== stack labels & labels list ===== */
    // create stack labels to be added to proceeding label list
    @FXML private Label lblS1, lblS2, lblS3, lblS4, lblS5, lblS6, lblS7, lblS8, lblS9, lblS10;
    @FXML private List<Label> stackLabels; // label list to hold preceding 10 stack labels

    /* ===== queue labels & label array ===== */
    //@FXML private Label lblQ1, lblQ2, lblQ3, lblQ4, lblQ5, lblQ6, lblQ7, lblQ8, lblQ9, lblQ10;
    private Label queueLabels[] = new Label[10];// = {lblQ1, lblQ2, lblQ3, lblQ4, lblQ5, lblQ6, lblQ7, lblQ8, lblQ9, lblQ10};
    private int[] stack;



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
        for (Label label : stackLabels) {
            label.setText("");
            label.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));


        }
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

    /** Print information alert dialog displaying contents of methods string parameter -
     *  char type {c : confirmation | e : error | i : information | w : warning} */
    private void displayAlertDialog(char type, String title, String header, String message) {

        // create specified alert dialog type
        Alert alert = new Alert(Alert.AlertType.NONE);
        switch(type) {
            case 'c':
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            case 'e':
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            case 'i':
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case 'w':
                alert = new Alert(Alert.AlertType.WARNING);
                break;
        }

        // construct dialog contents
        if(!title.isEmpty())
            alert.setTitle(title);
        if(!header.isEmpty())
            alert.setHeaderText(header);
        if(!message.isEmpty())
            alert.setContentText(message);

        // set dialog icon image
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("icon.png").toString()));

        // Message dialog OK button click action
        Optional<ButtonType> result = alert.showAndWait();
        if (!result.isPresent() || result.get() == ButtonType.OK) {

            // ...
        }
    }

    /** Print stack contents to stack GUI labels list */
    private void printStack(){

        for (int i = 0; i < 10; i++) {
            if (i < stack.length) {
                stackLabels.get(i).setText(String.valueOf(stack[i]));
                stackLabels.get(i).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else {
                stackLabels.get(i).setText("");
                stackLabels.get(i).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    /** Print queue contents to queue GUI labels list */
    private void printQueue(){

        for (int i = 0; i < 10; i++) {
            if (i < stack.length)
                stackLabels.get(i).setText(String.valueOf(stack[i]));
            else
                stackLabels.get(i).setText("");
        }
    }

    /* Events ------------------------------------------------------------------------------------------------------- */
    /* ===== sort assessor tab ===== */
    /** Run sort assessment on sort button click */
    @FXML
    private void btnSortClick(){

        getSelections();
        runAssessment();
        printResults();
    }

    /* ===== sort assessor tab ===== */
    // - Stack -
    /** Run sort assessment on sort button click */
    @FXML
    private void btnStackPush(){

        try {
            stackObj.push(Integer.valueOf(txfStackInput.getText()));
            stack = stackObj.getStack();
            printStack();
            txfStackInput.clear();

        }
        catch(IndexOutOfBoundsException e) {
            displayAlertDialog('e', "Error", e.getMessage(), "Stack is full!");
        }
        catch(NumberFormatException e) {
            displayAlertDialog('e', "Input Data Error", "Please enter integer values [0-9]", "");
        }
        catch(NegativeArraySizeException e){}
    }

    /** Run sort assessment on sort button click */
    @FXML
    private void btnStackPop(){

        try {
            stackObj.pop();
            stack = stackObj.getStack();
            printStack();
        }
        catch(IndexOutOfBoundsException e) {
            displayAlertDialog('e', "Error", e.getMessage(), "Stack is empty!");
        }
        catch(NegativeArraySizeException e){
            stackLabels.get(0).setText("");
            stackLabels.get(0).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }
    @FXML
    private void keyPressedStackInput(KeyEvent e){

        if(e.getCode().equals(KeyCode.ENTER))
            btnStackPush();
    }

    // - Queue -
    /** Run sort assessment on sort button click */
    @FXML
    private void btnQueuePush(){

        try {
            stackObj.push(Integer.valueOf(txfStackInput.getText()));
            stack = stackObj.getStack();
            printStack();
            txfStackInput.clear();

        }
        catch(IndexOutOfBoundsException e) {
            displayAlertDialog('e', "Error", e.getMessage(), "Stack is full!");
        }
        catch(NumberFormatException e) {
            displayAlertDialog('e', "Input Data Error", "Please enter integer values [0-9]", "");
        }
        catch(NegativeArraySizeException e){}
    }

    /** Run sort assessment on sort button click */
    @FXML
    private void btnQueuePop(){

        getSelections();
        runAssessment();
        printResults();
    }

    @FXML
    private void keyPressedQueueInput(KeyEvent e){

        if(e.getCode().equals(KeyCode.ENTER))
            btnQueuePush();
    }
}