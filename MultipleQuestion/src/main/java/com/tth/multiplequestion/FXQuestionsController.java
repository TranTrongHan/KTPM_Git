/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.tth.multiplequestion;

import com.tth.pojo.Category;
import com.tth.pojo.Choice;
import com.tth.pojo.JdbcUtils;
import com.tth.pojo.Question;
import com.tth.services.CategoryServices;
import com.tth.services.QuestionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FXQuestionsController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private RadioButton rdoA;
    @FXML
    private RadioButton rdoB;
    @FXML
    private RadioButton rdoC;
    @FXML
    private RadioButton rdoD;
    @FXML
    private TextField txtA;
    @FXML
    private TextField txtB;
    @FXML
    private TextField txtC;
    @FXML
    private TextField txtD;
    @FXML
    private TextField txtQuestion;
    @FXML
    private TableView<Question> tbQuestions;
    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryServices c = new CategoryServices();

        try {
            this.cbCates.setItems(FXCollections.observableList(c.getCategories()));
        } catch (SQLException ex) {
            Logger.getLogger(FXQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadColumns();
        loadTableData("");
        txtSearch.textProperty().addListener((e) -> {
            loadTableData(txtSearch.getText());
        });
    }

    public void addHandler(ActionEvent e) {
        Question q = new Question(UUID.randomUUID().toString(), this.txtQuestion.getText(),
                this.cbCates.getSelectionModel().getSelectedItem().getId());
        
        Choice c1 = new Choice(UUID.randomUUID().toString(), txtA.getText(), rdoA.isSelected(), q.getId());
        Choice c2 = new Choice(UUID.randomUUID().toString(), txtB.getText(), rdoB.isSelected(), q.getId());
        Choice c3 = new Choice(UUID.randomUUID().toString(), txtC.getText(), rdoC.isSelected(), q.getId());
        Choice c4 = new Choice(UUID.randomUUID().toString(), txtD.getText(), rdoD.isSelected(), q.getId());
        
        
        List<Choice> choices = new ArrayList<>();
        choices.add(c1);
        choices.add(c2);
        choices.add(c3);
        choices.add(c4);
        
        QuestionServices s = new QuestionServices();
        try {
            s.addQuestion(q, choices);
            Alert a = new Alert(Alert.AlertType.INFORMATION,"Add successfully!",ButtonType.OK);
            a.show();
            loadTableData("");
            
        } catch (SQLException ex) {
            Logger.getLogger(FXQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadTableData(String kw) {
        QuestionServices s = new QuestionServices();
        try {
            this.tbQuestions.setItems(FXCollections.observableList(s.getQuestions(0, kw)));
        } catch (SQLException ex) {
            Logger.getLogger(FXQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadColumns() {
        TableColumn colContent = new TableColumn("question content");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(300);

        TableColumn colCate = new TableColumn("category");
        colCate.setCellValueFactory(new PropertyValueFactory("categoryId"));
        colCate.setPrefWidth(400);

        this.tbQuestions.getColumns().addAll(colContent, colCate);
    }

}
