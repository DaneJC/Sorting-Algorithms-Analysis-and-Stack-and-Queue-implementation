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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

    /* ===== table view list ===== */
    private ObservableList<SortAlgorithm> tableData = FXCollections.observableArrayList();

    /* ===== sort attempts & run time logs required to averaged down multiple test results for graph population ===== */
    // standard bubble sort | [0]
    private int[] stdBubbleRunTimeLog = new int[9];
    private int[] stdBubbleCount = new int[9];
    // enhanced bubble sort | [1]
    private int[] enhBubbleRunTimeLog = new int[9];
    private int[] enhBubbleCount = new int[9];
    // selection sort | [2]
    private int[] selectionRunTimeLog = new int[9];
    private int[] selectionCount = new int[9];
    // insertion sort | [3]
    private int[] insertionRunTimeLog = new int[9];
    private int[] insertionCount = new int[9];

    // lists to hold run time and attemps list
    private int[][] runTimeLogs = {stdBubbleRunTimeLog, enhBubbleRunTimeLog, selectionRunTimeLog, insertionRunTimeLog};
    private int[][] sortCountLogs = {stdBubbleCount, enhBubbleCount, selectionCount, insertionCount};

    // log selector used to specify which logs to access when logging run times and attempts.
    private int logSelector = 0; // 0: bubble std | 1: bubble enh | 2: selection | 3: insertion |
    private int logIndexSelector = 0;

    /* 0: 1,000-sorted   | 1: 1,000-inverse   |  2: 1,000-random   */
    /* 3: 10,000-sorted  | 4: 10,000-inverse  |  5: 10,000-random  */
    /* 6: 100,000-sorted | 7: 100,000-inverse |  8: 100,000-random */

    /* ===== data and sort object containers ===== */
    private SortData data = new SortData();
    private BubbleSort bbSort = new BubbleSort();
    private SelectionSort selSort = new SelectionSort();
    private InsertionSort insSort = new InsertionSort();

    /* ===== user selection containers ===== */
    private SortAlgorithm selectedSort = new BubbleSort();
    private int[] dataSet;

    /* stack and queue tab ------------------------------------------------------------------------------------------ */
    /* ===== stack & queue objects ===== */
    private Stack stackObj = new Stack();
    private Queue queueObj = new Queue();

    /* ===== buttons ===== */
    @FXML private Button btnStackPop, btnStackPush, btnQueRemove, btnQueInsert;

    /* ===== text fields ===== */
    @FXML private TextField txfStackInput, txfQueInput;

    /* ===== stack properties ===== */
    // create stack labels to be added to proceeding stack label list
    @FXML private Label lblS1, lblS2, lblS3, lblS4, lblS5, lblS6, lblS7, lblS8, lblS9, lblS10;
    @FXML private List<Label> stackLabels; // stack label list to hold preceding 10 stack labels
    private int[] stack;  // to retrieve occupied elements of stack objects array and display contents via stack labels list

    /* ===== queue properties ===== */
    // create queue labels to be added to proceeding queue label list
    @FXML private Label lblQ1, lblQ2, lblQ3, lblQ4, lblQ5, lblQ6, lblQ7, lblQ8, lblQ9, lblQ10;
    @FXML private List<Label> queueLabels;  // queue label list to hold preceding 10 queue labels
    private int[] queue;  // to retrieve occupied elements of queue objects array and display contents via queue labels list

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
        for (int i=0; i<10; i++){
            stackLabels.get(i).setText("");
            queueLabels.get(i).setText("");
            stackLabels.get(i).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            queueLabels.get(i).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));


        }
    }

    /* Class methods ------------------------------------------------------------------------------------------------ */

    /** Prepare data set & sort algorithm with reference to user selections */
    private void getSelections() {

        /* ===== get data set selections ===== */
        if(cBoxSize.getSelectionModel().getSelectedIndex() == 0) { /* => 1000 -------------- */

            if (cBoxDSet.getSelectionModel().getSelectedIndex() == 0) { // => sorted
                dataSet = data.getSorted1000();
                logIndexSelector = 0;
            } else if (cBoxDSet.getSelectionModel().getSelectedIndex() == 1){ // => inverse
                dataSet = data.getInverse1000();
                logIndexSelector = 1;
            }
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 2) { // => random
                dataSet = data.getRandom1000();
                logIndexSelector = 2;
            }
        }
        else if(cBoxSize.getSelectionModel().getSelectedIndex() == 1) { /* => 10000 -------- */

            if(cBoxDSet.getSelectionModel().getSelectedIndex() == 0) { // => sorted
                dataSet = data.getSorted10000();
                logIndexSelector = 3;
            }
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 1) { // => inverse
                dataSet = data.getInverse10000();
                logIndexSelector = 4;
            }
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 2) { // => random
                dataSet = data.getRandom10000();
                logIndexSelector = 5;
            }
        }
        else if(cBoxSize.getSelectionModel().getSelectedIndex() == 2) { /* => 100000 ------- */

            if(cBoxDSet.getSelectionModel().getSelectedIndex() == 0) { // => sorted
                dataSet = data.getSorted100000();
                logIndexSelector = 6;
            }
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 1) { // => inverse
                dataSet = data.getInverse100000();
                logIndexSelector = 7;
            }
            else if(cBoxDSet.getSelectionModel().getSelectedIndex() == 2) { // => random
                dataSet = data.getRandom100000();
                logIndexSelector = 8;
            }
        }

        /* ===== get sort algorithm selection ===== */
        if(cBoxSort.getSelectionModel().getSelectedIndex() == 0 || cBoxSort.getSelectionModel().getSelectedIndex() == 1) { // => bubble sort
            selectedSort = new BubbleSort();
            logSelector = 0;
        }
        else if(cBoxSort.getSelectionModel().getSelectedIndex() == 2) { // => selection
            selectedSort = new SelectionSort();
            logSelector = 2;
        }
        else if(cBoxSort.getSelectionModel().getSelectedIndex() == 3){ // => insertion
            selectedSort = new InsertionSort();
            logSelector = 3;
        }

        /* print information --------------------------------------------------------------- */
        selectedSort.setAlgoName((String) cBoxSort.getSelectionModel().getSelectedItem());
        selectedSort.setDataSet((String) cBoxDSet.getSelectionModel().getSelectedItem());
        selectedSort.setDataSize((String) cBoxSize.getSelectionModel().getSelectedItem());
    }

    /** Execute algorithm analysis */
    private void runAssessment() {

        if(selectedSort instanceof BubbleSort && cBoxSort.getSelectionModel().getSelectedIndex() == 1) {

            logSelector = 1;
            selectedSort.start();
            selectedSort.SortENH(dataSet);  // reason an interface was not used for the enhanced algorithms - produces error here
            selectedSort.stop();
        }
        else {
            selectedSort.start();
            selectedSort.Sort(dataSet);
            selectedSort.stop();
        }
        // log sort time for graph result population
        runTimeLogs[logSelector][logIndexSelector] += selectedSort.getElapsedTime();
        // count amount of assessments to average down the results
        sortCountLogs[logSelector][logIndexSelector] += 1;
        System.out.println(selectedSort.getElapsedTime());
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
            if (i < queue.length) {
                queueLabels.get(i).setText(String.valueOf(queue[i]));
                queueLabels.get(i).setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            else {
                queueLabels.get(i).setText("");
                queueLabels.get(i).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    /** Compute average algorithm run times  */
    private void computeRunTimes(){

        // current run time log index / amount of times algorithm was executed = average
        for(int log = 0; log < runTimeLogs.length; log++) {
            for (int i = 0; i < runTimeLogs[log].length; i++) {

                try {
                    runTimeLogs[log][i] = runTimeLogs[log][i] / sortCountLogs[log][i];
                } catch (ArithmeticException e) {/* System.out.println("ArithmeticException: "+e.getMessage());*/}
            }
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

    /** Submit & review assessment via graph on submit button click */
    @FXML
    private void btnSubmitClick(ActionEvent event){

        computeRunTimes();
        try {
            onOpenDialog(event);
        }
        catch(IOException e){
            System.out.println("IOExeption: "+e.getMessage());
        }
        // create custom graph alert dialog

    }

    /** Open custom graph dialog when sort tab submit button pressed */
    @FXML
    void onOpenDialog(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GraphDialog.fxml"));
        Parent parent = fxmlLoader.load();
        GraphDialogController dialogController = fxmlLoader.getController();
        dialogController.setGraphContents(runTimeLogs);

        Scene scene = new Scene(parent, 1200, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /* ===== sort assessor tab ===== */
    // ----- Stack ------
    /** Get stack input text field data -> confirm integer -> push value onto stack -> display updated stack */
    @FXML
    private void btnStackPush(){

        try {
            String text = txfStackInput.getText();
            txfStackInput.clear();
            stackObj.push(Integer.valueOf(text));
            stack = stackObj.printFormatStack();
            printStack();
        }
        catch(IndexOutOfBoundsException e) {
            displayAlertDialog('e', "Error", e.getMessage(), "Stack is full!");
        }
        catch(NumberFormatException e) {
            displayAlertDialog('e', "Input Data Error", "Please enter integer values [0-9]", "");
        }
        catch(NegativeArraySizeException e){}
    }

    /** Pop index at top of stack -> display updated stack */
    @FXML
    private void btnStackPop(){

        try {
            stackObj.pop();
            stack = stackObj.printFormatStack();
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

    /** Push stack input textField contents onto stack on Enter key pressed event */
    @FXML
    private void keyPressedStackInput(KeyEvent e){

        if(e.getCode().equals(KeyCode.ENTER))
            btnStackPush();
    }

    // ----- Queue -----
    /** Get queue input text field data -> confirm integer -> insert value into queue -> display updated queue */
    @FXML
    private void btnQueueInsert(){

        try {
            queueObj.insert(Integer.valueOf(txfQueInput.getText()));
            queue = queueObj.printFormatQueue();
            printQueue();
        }
        catch(IndexOutOfBoundsException e) {
            displayAlertDialog('e', "Error", e.getMessage(), "Queue is full!");
        }
        catch(NumberFormatException e) {
            displayAlertDialog('e', "Input Data Error", "Please enter integer values [0-9]", "");
        }
        catch(NegativeArraySizeException e){}
        txfQueInput.clear();
    }

    /** Remove oldest index of queue if present -> display updated queue */
    @FXML
    private void btnQueueRemove(){

        try {
            queueObj.remove();
            queue = queueObj.printFormatQueue();
            printQueue();
        }
        catch(IndexOutOfBoundsException e) {
            displayAlertDialog('e', "Error", e.getMessage(), "Queue is empty!");
        }
        catch(NegativeArraySizeException e){
            queueLabels.get(0).setText("");
            queueLabels.get(0).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /** Push queue input textField contents onto queue on Enter key pressed event */
    @FXML
    private void keyPressedQueueInput(KeyEvent e){

        if(e.getCode().equals(KeyCode.ENTER))
            btnQueueInsert();
    }
}