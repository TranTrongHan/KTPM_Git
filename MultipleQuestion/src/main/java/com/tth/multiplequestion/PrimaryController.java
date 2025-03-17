package com.tth.multiplequestion;

import com.tth.pojo.Question;
import com.tth.services.QuestionServices;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

public class PrimaryController implements Initializable{
    @FXML private Text txtContent;
    @FXML private RadioButton rdoA;
    @FXML private RadioButton rdoB;
    @FXML private RadioButton rdoC;
    @FXML private RadioButton rdoD;
    private List<Question> questions;
    @FXML private Text textA;
    @FXML private Text textB;
    @FXML private Text textC;
    @FXML private Text textD;
    private int currentIdx = 0;
    
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    public void checkHandler(ActionEvent e) throws SQLException{
        QuestionServices s = new QuestionServices();
        List<Question> questions = s.getQuestions(2,null);
        
        Alert a = new Alert(Alert.AlertType.INFORMATION,questions.get(0).getContent(),ButtonType.OK);
        a.show();
    }
    public void nextHandler(ActionEvent e){
        this.currentIdx++;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        QuestionServices s = new QuestionServices();
        try {
            this.questions = s.getQuestions(3,null);
            loadQuestionToUI();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    private void loadQuestionToUI() throws SQLException{
        QuestionServices s = new QuestionServices();
        Question q = this.questions.get(currentIdx);
        txtContent.setText(q.getContent());
        
        if(q.getChoices() == null){
            q.setChoices(s.getChoices(q.getId()));
            textA.setText(q.getChoices().get(0).toString());
            textB.setText(q.getChoices().get(1).toString());
            textC.setText(q.getChoices().get(2).toString());
            textD.setText(q.getChoices().get(3).toString());
            
        }
    }
}
