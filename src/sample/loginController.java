package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.CachedAnimation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    public int page = 0;

    public String q = "";

    public String inspirationalQuote = "";

    public String username = "";

    public Stage prevStage;

    public boolean firstTime= true;

    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }

    public ObservableList<Course> data = FXCollections.observableArrayList();

    @FXML
    public AnchorPane pane;

    @FXML
    public AnchorPane loginPane;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private Label GPA1 = new Label("0.0");

    @FXML
    private Label GPA2 = new Label("0.0");

    @FXML
    private Label GPA3 = new Label("0.0");

    @FXML
    private TableColumn<Course, String> classCol;

    @FXML
    private TableColumn<Course, String> g1Col;

    @FXML
    private TableColumn<Course, String> g2Col;

    @FXML
    private TableView<Course> table;

    @FXML
    private Label userID = new Label("Hello, ");

    @FXML
    private Label quote = new Label("by Rameez Saiyid");

    @FXML
    void doLogin(ActionEvent event) throws IOException {
        login();
        dataParse();
    }

    @FXML
    void handleKeyPress(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            login();
            dataParse();
        }
    }

    @FXML
    private JFXButton refreshButton;

    @FXML
    void refresher(ActionEvent event) throws IOException{
        firstTime = false;
        updateData();
    }


        @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (page % 2 == 0) {
            System.out.println("Login Page");
            Service<Void> quoteThread;
            quoteThread = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            q = Quote.getQuote();
                            return null;
                        }
                    };
                }
            };
            quoteThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event){
                    try {
                        quote.setText(q);
                        updateData();
                    }catch(IOException e){}
                }
            });
            quoteThread.restart();
        }
        page++;
    }

    public JFXTextField getUser() {
        return user;
    }

    public JFXPasswordField getPass() {
        return pass;
    }

    public void login() throws IOException {
        username = getUser().getText();
        Stage stage = new Stage();
        Pane myPane = FXMLLoader.load(getClass().getResource("load.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        prevStage.close();
        stage.show();
        setPrevStage(stage);
    }

    public void goData() throws IOException {
        try {
            Stage stage = new Stage();
            Pane myPane = FXMLLoader.load(getClass().getResource("data.fxml"));
            Scene scene = new Scene(myPane);
            stage.setScene(scene);
            prevStage.close();
            stage.setTitle("Powerschool - MPHS");
            stage.show();
            setPrevStage(stage);
        } catch (IOException e) {
        }
    }

    private Service<Void> calcThread;
    public void dataParse() throws IOException {
        calcThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        //Data
                        Calculator calculator = new Calculator(getUser().getText(), getPass().getText());
                        return null;
                    }
                };
            }
        };
        calcThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    System.out.println("Done!" + Calculator.auth());
                    if (Calculator.auth()) {
                        goData();
                    } else {
                        Stage stage = new Stage();
                        Pane myPane = FXMLLoader.load(getClass().getResource("login.fxml"));
                        Scene scene = new Scene(myPane);
                        stage.setScene(scene);
                        prevStage.close();
                        stage.setTitle("Powerschool - MPHS");
                        stage.show();
                        setPrevStage(stage);
                    }
                } catch (IOException e) {
                }
            }
        });
        calcThread.restart();
    }

    private Service<Void> msgThread;
    public String gpa1 = "";
    public String gpa2 = "";
    public String gpa3 = "";

    public void updateData() throws IOException {
        msgThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Calculator.calcGPA();
                        gpa1 = ((double)Math.round(Calculator.getcGPAs1()*1000)/1000) + "";
                        gpa2 = ((double)Math.round(Calculator.getcGPAs2()*1000)/1000) + "";
                        gpa3 = (((double)Math.round(Calculator.getcGPAs1()*1000)/1000) + ((double)Math.round(Calculator.getcGPAs2()*1000)/1000))/2 + "";
                        return null;
                    }
                };
            }
        };
        msgThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                try {
                    GPA1.setText(gpa1);
                    GPA2.setText(gpa2);
                    GPA3.setText(gpa3);
                    userID.setText("Hello, " + Calculator.user);
                    updateTable();
                }catch (IOException e){}
            }
        });
        msgThread.restart();
    }

    private Service<Void> tableThread;
    public void updateTable() throws IOException {
        tableThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        classCol = new TableColumn("Class");
                        g1Col = new TableColumn("S1");
                        g2Col = new TableColumn("S2");
                        classCol.setCellValueFactory(new PropertyValueFactory<Course, String>("cname"));
                        g1Col.setCellValueFactory(new PropertyValueFactory<Course, String>("grade1"));
                        g2Col.setCellValueFactory(new PropertyValueFactory<Course, String>("grade2"));
                        return null;
                    }
                };
            }
        };
        tableThread.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                table.setItems(getCourse());
                table.getColumns().setAll(classCol, g1Col, g2Col);
                System.out.println("Done Table Thread");
            }
        });
        tableThread.restart();
    }

    public ObservableList<Course> getCourse(){
        if(data.size() > 0) {
            for (int x = 0; x < data.size(); x++) {
                data.remove(0);
            }
        }
        if(!firstTime) {
            for(int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }
        }
        int i = 0;
        for(Course x : Calculator.Courses){
            data.add(x);
            System.out.println("Adding..." + data.get(i).getCname());
            i++;
        }
        return data;
    }


}



