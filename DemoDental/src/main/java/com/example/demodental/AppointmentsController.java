package com.example.demodental;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentsController  implements Initializable {
    @FXML private TableView<Consultation> appointmentsTable;
    @FXML private TableView<Consultation> appointmentsTableT;
    @FXML private TableColumn<Consultation, Integer> id;
    @FXML private TableColumn<Consultation, String> date;
    @FXML private TableColumn<Consultation, String> hour;
    @FXML private TableColumn<Consultation, String> description;
    @FXML private TableColumn<Consultation, String> tooth;
    @FXML private TableColumn<Consultation, Integer> patientId;
    @FXML private TableColumn<Consultation, Integer> idT;
    @FXML private TableColumn<Consultation, String> dateT;
    @FXML private TableColumn<Consultation, String> hourT;
    @FXML private TableColumn<Consultation, String> descriptionT;
    @FXML private TableColumn<Consultation, String> toothT;
    @FXML private TableColumn<Consultation, Integer> patientIdT;

    @FXML private TextField nameAdd;
    @FXML private TextField descriptionAdd;
    @FXML private TextField toothAdd;
    @FXML private TextField dateAdd;
    @FXML private TextField hourAdd;
    @FXML private TextField namePhoneSearch;
    @FXML private TextField descriptionAddT;
    @FXML private TextField toothAddT;
    @FXML private TextField dateAddT;
    @FXML private TextField hourAddT;

    @FXML private TextField consultId;
    @FXML private TextArea textAreaDetails;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        try {
            ObservableList<Consultation> consultations = getAllRecordsAppointments(con);
            id.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("id"));
            date.setCellValueFactory(new PropertyValueFactory<Consultation, String>("date"));
            hour.setCellValueFactory(new PropertyValueFactory<Consultation, String>("hour"));
            description.setCellValueFactory(new PropertyValueFactory<Consultation, String>("description"));
            tooth.setCellValueFactory(new PropertyValueFactory<Consultation, String>("tooth"));
            patientId.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("patientId"));
            appointmentsTable.setItems(consultations);

            ObservableList<Consultation> consultationsT = getAllRecordsAppointmentsForToday(con);
            idT.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("id"));
            dateT.setCellValueFactory(new PropertyValueFactory<Consultation, String>("date"));
            hourT.setCellValueFactory(new PropertyValueFactory<Consultation, String>("hour"));
            descriptionT.setCellValueFactory(new PropertyValueFactory<Consultation, String>("description"));
            toothT.setCellValueFactory(new PropertyValueFactory<Consultation, String>("tooth"));
            patientIdT.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("patientId"));
            appointmentsTableT.setItems(consultationsT);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Patient> getAllRecordsPatients(Connection con) throws SQLException {
        //System.out.println("PREIAU DIN DATABASE");
        String sql = "SELECT * FROM `patients`";
        try(Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Patient> patientsList = FXCollections.observableArrayList();
            while(rs.next()){
                Patient p = new Patient();
                p.setId(rs.getInt("ID"));
                p.setPatientName(rs.getString("NAME"));
                p.setAge(rs.getInt("AGE"));
                p.setGender(rs.getString("GENDER"));
                p.setPhoneNumber(rs.getString("PHONE NUMBER"));
                patientsList.add(p);
            }
            /*
            for(Patient pp : patientsList)
                System.out.println(pp.toString());*/
            //System.out.println("PRELUARE FINALIZATA");
            return patientsList;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }
    private ObservableList<Consultation> getAllRecordsAppointments(Connection con) throws SQLException {
        String sql = "SELECT * FROM `appointments`";
        try(Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Consultation> appointments = FXCollections.observableArrayList();
            while(rs.next()){
                Consultation c = new Consultation();
                c.setId(rs.getInt("ID"));
                c.setDate(rs.getString("DATE"));
                c.setHour(rs.getString("HOUR"));
                c.setDescription(rs.getString("DESCRIPTION"));
                c.setTooth(rs.getString("TOOTH"));
                c.setPatientId(rs.getInt("PATIENT_ID"));
                appointments.add(c);
            }
            return appointments;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }
    private ObservableList<Consultation> getAllRecordsAppointmentsForToday(Connection con) throws SQLException {
        String sql = "SELECT * FROM `appointments`";
        try(Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Consultation> appointments = FXCollections.observableArrayList();
            while(rs.next()){
                Consultation c = new Consultation();
                c.setId(rs.getInt("ID"));
                c.setDate(rs.getString("DATE"));
                c.setHour(rs.getString("HOUR"));
                c.setDescription(rs.getString("DESCRIPTION"));
                c.setTooth(rs.getString("TOOTH"));
                c.setPatientId(rs.getInt("PATIENT_ID"));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDateTime now = LocalDateTime.now();
                String todayDate = dtf.format(now);
                if(c.getDate().equals(todayDate))
                    appointments.add(c);
            }
            return appointments;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }

    @FXML void AddAppointmentButton(ActionEvent actionEvent) throws SQLException {
        ConnectionClass connection1 = new ConnectionClass();
        Connection con = connection1.getConnection();
        ObservableList<Patient> patients = getAllRecordsPatients(con);
        boolean existPatient = false;
        int idP = 1;
        for(Patient patient : patients) {
            if(nameAdd.getText().toUpperCase().equals(patient.getPatientName().toUpperCase())){
                idP = patient.getId();
                existPatient = true;
            }
        }
        if(!existPatient){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Result");
            alert.setHeaderText("Missing patient!");
            alert.setContentText("Go and add the patient!");
            alert.showAndWait();
        }
        else{
            ObservableList<Consultation> consultations = getAllRecordsAppointments(con);
            //daca pacientul are deja programare in ziua aia
            boolean already = false;
            for(Consultation consultation: consultations){
                if(consultation.getPatientId() == idP && dateAdd.getText().equals(consultation.getDate())){
                    already = true;
                    popUpAlreadyScheduled();
                }
            }
            if(!already){
                String dateToInsert = dateAdd.getText();
                String hourToInsert = hourAdd.getText();
                String toothToInsert = toothAdd.getText();
                String descriptionToInsert = descriptionAdd.getText();
                if(dateToInsert.matches("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([1-9][0-9][0-9][0-9])$")){
                    if(hourToInsert.matches("(^[01]?[0-9]|2[0-3]):[0-5][0-9]$")){
                        boolean existConsult = false;
                        for(Consultation consultation: consultations){
                            if(consultation.getDate().equals(dateToInsert) && consultation.getHour().equals(hourToInsert)){
                                existConsult = true;
                                popUpNotAvailable();
                            }
                        }
                        if(!existConsult){
                            int id=1;
                            try {
                                ResultSet rs = null;
                                Statement st = con.createStatement();
                                String query = "SELECT ID FROM APPOINTMENTS ORDER BY ID DESC LIMIT 1";
                                rs = st.executeQuery(query);
                                if (rs.next()) {
                                    id = rs.getInt("ID");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            id++;
                            Consultation consultationToAdd = new Consultation(id, dateToInsert, hourToInsert, descriptionToInsert, toothToInsert, idP);

                            ConnectionClass connectionClass = new ConnectionClass();
                            Connection connection = connectionClass.getConnection();
                            String sqlApp = "INSERT INTO APPOINTMENTS (`ID`, `DATE`, `HOUR`, `DESCRIPTION`, `TOOTH`, `PATIENT_ID`) VALUES ('" + id + "', '" + dateToInsert + "', '" + hourToInsert + "', '" + descriptionToInsert + "', '" + toothToInsert + "', '" + idP + "')";
                            System.out.println(sqlApp);
                            Statement statementP = connection.createStatement();
                            statementP.executeUpdate(sqlApp);
                            popUpResultAdded();
                            clear();

                            updateAppointmentsTable();
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            LocalDateTime now = LocalDateTime.now();
                            String todayDate = dtf.format(now);
                            if(dateToInsert.equals(todayDate)){
                                updateTodayAppointmentsTable();
                            }

                        }
                    }
                    else{
                        popUpWrongHourFormat();
                    }
                }
                else{
                    popUpWrongDateFormat();
                }
            }

        }
    }
    @FXML void UpdateAppointmentButton(ActionEvent actionEvent) throws SQLException {
        ConnectionClass connection1 = new ConnectionClass();
        Connection con = connection1.getConnection();
        ObservableList<Patient> patients = getAllRecordsPatients(con);
        Patient patientToData = null;
        boolean existPatient = false;
        int idPatient = 1;
        boolean ph = false;
        boolean nameP = false;
        if(namePhoneSearch.getText().isEmpty()){
            popUpNoNamePhoneSearch();
        }
        else{
            String getNameOrPhone = namePhoneSearch.getText();
            if(getNameOrPhone.length()==10 && getNameOrPhone.matches("^(0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])$")) {
                ph = true;
                for(Patient patient: patients){
                    if(patient.getPhoneNumber().equals(getNameOrPhone)){
                        idPatient = patient.getId();
                        existPatient = true;
                        patientToData = patient;
                    }
                }
            }
            else{
                nameP = true;
                for(Patient patient: patients){
                    if(patient.getPatientName().equals(getNameOrPhone)){
                        idPatient = patient.getId();
                        existPatient = true;
                        patientToData = patient;
                    }
                }
            }
        }
        if(!existPatient){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Result");
            alert.setHeaderText("Missing patient!");
            alert.setContentText("Go and add the patient!");
            alert.showAndWait();
        }
        else{
            ObservableList<Consultation> consultations = getAllRecordsAppointments(con);
            //daca pacientul are deja programare in ziua aia
            boolean already = false;
            int consultId = 1;
            Consultation consultToData = null;
            for(Consultation consultation: consultations){
                if(consultation.getPatientId() == idPatient && dateAddT.getText().equals(consultation.getDate())){
                    already = true;
                    consultId = consultation.getId();
                    consultToData = consultation;
                }
            }
            //popUp to see the patient data
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient");
            alert.setHeaderText("The patient data");
            String data = "Id: " + patientToData.getId() +
                    "\nName: " + patientToData.getPatientName() +
                    "\nPhone number: " + patientToData.getPhoneNumber() +
                    "\nConsultationId: " + consultToData.getId() +
                    "\nDate: " + consultToData.getDate() +
                    "\nHour: " + consultToData.getHour() +
                    "\nDescription: " + consultToData.getDescription() +
                    "\nTooth: " + consultToData.getTooth();
            alert.setContentText(data);
            alert.showAndWait();
            if(already){
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                String sql;

                if(hourAddT.getText()!=null){
                    String hourToInsert = hourAddT.getText();
                    if(hourToInsert.equals(consultToData.getHour())){
                        popUpSameHour();
                    }
                    else{
                        if(hourToInsert.matches("(^[01]?[0-9]|2[0-3]):[0-5][0-9]$")){
                            sql = "UPDATE `appointments` SET `HOUR`='"+ hourToInsert +"'WHERE ID = '"+ consultId +"'";
                            System.out.println(sql);
                            Statement statementP = connection.createStatement();
                            statementP.executeUpdate(sql);
                        }
                        else{
                            popUpWrongHourFormat();
                        }
                    }
                }
                if(toothAddT.getText()!=null){
                    String toothToInsert = toothAddT.getText();
                    sql = "UPDATE `appointments` SET `TOOTH`='"+ toothToInsert +"'WHERE ID = '"+ consultId +"'";
                    System.out.println(sql);
                    Statement statementP = connection.createStatement();
                    statementP.executeUpdate(sql);
                }
                if(descriptionAddT.getText()!=null){
                    String descriptionToInsert = descriptionAddT.getText();
                    sql = "UPDATE `appointments` SET `DESCRIPTION`='"+ descriptionToInsert +"'WHERE ID = '"+ consultId +"'";
                    System.out.println(sql);
                    Statement statementP = connection.createStatement();
                    statementP.executeUpdate(sql);
                }

                clear();
                updateAppointmentsTable();
                updateTodayAppointmentsTable();
            }
            else{
                popUpPacientNotSheduled();
            }
        }
    }
    @FXML void seeDataAppointment(ActionEvent event) throws SQLException {
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        ObservableList<Consultation> consultations = getAllRecordsAppointments(con);
        ObservableList<Patient> patients = getAllRecordsPatients(con);
        if(consultId.getText().isEmpty()){
            popUpIdNotInserted();
        }
        else{
            int idC = Integer.parseInt(consultId.getText());
            for(Consultation consultation:consultations){
                if(idC==consultation.getId()){
                    int idP = consultation.getPatientId();
                    for(Patient p: patients){
                        if(idP == p.getId()){
                            String data = "Id: " + p.getId() +
                                    "\nName: " + p.getPatientName() +
                                    "\nPhone number: " + p.getPhoneNumber() +
                                    "\nConsultationId: " + consultation.getId() +
                                    "\nDate: " + consultation.getDate() +
                                    "\nHour: " + consultation.getHour() +
                                    "\nDescription: " + consultation.getDescription() +
                                    "\nTooth: " + consultation.getTooth();
                            textAreaDetails.setText(data);
                        }
                    }
                }
            }
        }
    }

    @FXML void deleteAppointment(ActionEvent event) throws SQLException {
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        if(consultId.getText().isEmpty()){
            popUpIdNotInserted();
        }
        else{
            String sqlConsult = "DELETE FROM `appointments` WHERE ID = '" + consultId.getText() + "'";
            System.out.println(sqlConsult);
            Statement statementC = con.createStatement();
            statementC.executeUpdate(sqlConsult);
            updateAppointmentsTable();
            updateTodayAppointmentsTable();
            consultId.clear();
            textAreaDetails.clear();
        }
    }

    private void updateAppointmentsTable() {
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        try {
            ObservableList<Consultation> consultations = getAllRecordsAppointments(con);
            id.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("id"));
            date.setCellValueFactory(new PropertyValueFactory<Consultation, String>("date"));
            hour.setCellValueFactory(new PropertyValueFactory<Consultation, String>("hour"));
            description.setCellValueFactory(new PropertyValueFactory<Consultation, String>("description"));
            tooth.setCellValueFactory(new PropertyValueFactory<Consultation, String>("tooth"));
            patientId.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("patientId"));
            appointmentsTable.setItems(consultations);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateTodayAppointmentsTable() {
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        try {
            ObservableList<Consultation> consultations = getAllRecordsAppointmentsForToday(con);
            idT.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("id"));
            dateT.setCellValueFactory(new PropertyValueFactory<Consultation, String>("date"));
            hourT.setCellValueFactory(new PropertyValueFactory<Consultation, String>("hour"));
            descriptionT.setCellValueFactory(new PropertyValueFactory<Consultation, String>("description"));
            toothT.setCellValueFactory(new PropertyValueFactory<Consultation, String>("tooth"));
            patientIdT.setCellValueFactory(new PropertyValueFactory<Consultation, Integer>("patientId"));
            appointmentsTableT.setItems(consultations);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void popUpResultAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText("Appointment successfully added!");
        alert.setContentText("Have a nice day!");
        alert.showAndWait();
    }
    protected void popUpWrongDateFormat(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong date format! (dd/mm/yyyy)");
        alert.showAndWait();
    }
    protected void popUpWrongHourFormat(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong hour format! (hh:mm)");
        alert.showAndWait();
    }
    protected void popUpNotAvailable() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("The hour is not available!");
        alert.showAndWait();
    }
    protected void popUpAlreadyScheduled() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Shedule");
        alert.setHeaderText("Patient already scheduled on this date!");
        alert.showAndWait();
    }
    private void popUpNoNamePhoneSearch() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText("No name or phone number!");
        alert.showAndWait();
    }
    private void popUpPacientNotSheduled() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Shedule");
        alert.setHeaderText("Patient not scheduled on this date!");
        alert.showAndWait();
    }
    private void popUpWrongPhoneNumberFormat() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Wrong format! (xxxxxxxxxx - 10 digits)");
        alert.showAndWait();
    }
    private void popUpSameHour() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Same hour!");
        alert.showAndWait();
    }
    private void popUpIdNotInserted() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText("Missing id!");
        alert.showAndWait();
    }
    protected void clear() {
        nameAdd.clear();
        descriptionAdd.clear();
        toothAdd.clear();
        dateAdd.clear();
        hourAdd.clear();

        namePhoneSearch.clear();
        dateAddT.clear();
        hourAddT.clear();
        descriptionAddT.clear();
        toothAddT.clear();
    }
}