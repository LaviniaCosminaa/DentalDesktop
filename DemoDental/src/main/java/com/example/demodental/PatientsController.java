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
import java.util.ResourceBundle;
import static java.sql.DriverManager.getConnection;

public class PatientsController implements Initializable {
    @FXML private TableView<Patient> patientsTable;
    @FXML private TableView<MedicalRecord> medrecordsTable;
    @FXML private TableColumn<Patient, Integer> id;
    @FXML private TableColumn<Patient, String> patientName;
    @FXML private TableColumn<Patient, Integer> age;
    @FXML private TableColumn<Patient, String> gender;
    @FXML private TableColumn<Patient, String> phoneNumber;
    @FXML private TableColumn<MedicalRecord, Integer> idMR;
    @FXML private TableColumn<MedicalRecord, String> allergies;
    @FXML private TableColumn<MedicalRecord, String> treatment;
    @FXML private TableColumn<MedicalRecord, String> ltdate;
    @FXML private TableColumn<MedicalRecord, String> otherSurgery;
    @FXML private TableColumn<MedicalRecord, String> vices;
    @FXML private TextField nameAdd;
    @FXML private TextField ageAdd;
    @FXML private TextField genderAdd;
    @FXML private TextField phoneAdd;
    @FXML private TextField allergiesAdd;
    @FXML private TextField lastTDateAdd;
    @FXML private TextField treatmentAdd;
    @FXML private TextField vicesAdd;
    @FXML private TextField surgeryAdd;
    @FXML private TextField dateAdd;
    @FXML private TextField hourAdd;
    @FXML private TextField descriptionAdd;
    @FXML private TextField toothAdd;
    @FXML private TextField nameToSearch;
    @FXML private TextField phoneToSearch;
    @FXML private Label messageResultName;
    @FXML private Label messageResultPhone;
    @FXML private Label detailsByName;
    @FXML private Label detailsByPhone;

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
    //add in tableView
    ConnectionClass connection = new ConnectionClass();
    Connection con = connection.getConnection();
    try {
        ObservableList<Patient> patients = getAllRecordsPatients(con);
        id.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
        patientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
        age.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
        gender.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Patient, String>("phoneNumber"));
        patientsTable.setItems(patients);
        ObservableList<MedicalRecord> medrecords = getAllRecordsMedRec(con);
        idMR.setCellValueFactory(new PropertyValueFactory<MedicalRecord, Integer>("id"));
        allergies.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("allergies"));
        treatment.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("treatment"));
        ltdate.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("lastTreatmentDate"));
        otherSurgery.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("otherSurgery"));
        vices.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("vices"));
        medrecordsTable.setItems(medrecords);
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
}
    //getRecords from table patients
    public static ObservableList<Patient> getAllRecordsPatients(Connection con) throws SQLException {
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
    return patientsList;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }
    public static ObservableList<MedicalRecord> getAllRecordsMedRec(Connection con) throws SQLException {
        String sql = "SELECT * FROM `medrecords`";
        try(Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<MedicalRecord> medRecList = FXCollections.observableArrayList();
            while(rs.next()){
                MedicalRecord m = new MedicalRecord();
                m.setId(rs.getInt("ID"));
                m.setAllergies(rs.getString("ALLERGIES"));
                m.setTreatment(rs.getString("TREATMENT"));
                m.setLastTreatmentDate(rs.getString("LAST TREATMENT DATE"));
                m.setOtherSurgery(rs.getString("OTHER SURGERY"));
                m.setVices(rs.getString("VICES"));
                medRecList.add(m);
            }
            return medRecList;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }
    //getRecords from table appointments
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
    //put in table the data from getAllRecords
    private void updatePatientTable() {
        //add in tableView
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        try {
            ObservableList<Patient> patients = getAllRecordsPatients(con);
            id.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
            patientName.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientName"));
            age.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
            gender.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<Patient, String>("phoneNumber"));
            patientsTable.setItems(patients);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateMedRecTable() {
        //add in tableView
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        try {
            ObservableList<MedicalRecord> medRecList = getAllRecordsMedRec(con);
            idMR.setCellValueFactory(new PropertyValueFactory<MedicalRecord, Integer>("id"));
            allergies.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("allergies"));
            treatment.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("treatment"));
            ltdate.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("lastTreatmentDate"));
            otherSurgery.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("otherSurgery"));
            vices.setCellValueFactory(new PropertyValueFactory<MedicalRecord, String>("vices"));
            medrecordsTable.setItems(medRecList);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML void AddPatientButton(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (nameAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing name!");
            alert.showAndWait();
        }
        else if (ageAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing age!");
            alert.showAndWait();
        }
        else if (genderAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing gender!");
            alert.showAndWait();
        }
        else if (phoneAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing phone number!");
            alert.showAndWait();
        }
        else if (allergiesAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing allergies!");
            alert.showAndWait();
        }
        else if (treatmentAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing treatment!");
            alert.showAndWait();
        }
        else if (lastTDateAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing last treatment date!");
            alert.showAndWait();
        }
        else if (vicesAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing vices!");
            alert.showAndWait();
        }
        else if (surgeryAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing surgery!");
            alert.showAndWait();
        }
        else if (dateAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing date!");
            alert.showAndWait();
        }
        else if (hourAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing hour!");
            alert.showAndWait();
        }
        else if (descriptionAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing description!");
            alert.showAndWait();
        }
        else if (toothAdd.getText().isEmpty()) {
            alert.setHeaderText("Missing tooth!");
            alert.showAndWait();
        }
        else add();
    }
    private void add() throws SQLException  {
        //verificare daca pacientul exista deja (nume)
        ConnectionClass connection1 = new ConnectionClass();
        Connection con = connection1.getConnection();
        ObservableList<Patient> patients = getAllRecordsPatients(con);
        ObservableList<Consultation> appointments = getAllRecordsAppointments(con);
        //verify the name
        boolean existName = false;
        for(Patient patient : patients) {
            String toAddName = nameAdd.getText().toUpperCase();
            String nameInPatients = patient.getPatientName().toUpperCase();
            if(toAddName.equals(nameInPatients)){
                existName = true;
                break;
            }
        }
        //verify the age
        boolean correctAge = true;
        int ageToEnter = Integer.parseInt(ageAdd.getText());
        if(ageToEnter < 0 || ageToEnter > 150) {
            correctAge = false;
        }
        //verify the gender
        boolean validGender = false;
        String genderToEnter = genderAdd.getText();
        if(genderToEnter.equals("M") || genderToEnter.equals("F")){
            validGender = true;
        }
        //verify the phone number
        boolean validPhoneNumber = true;
        String phoneToEnter = phoneAdd.getText();
        if(phoneToEnter.length() != 10){
            validPhoneNumber = false;
        }
        //verify last treatment date string to be dd/mm/yyyy
        boolean ltd = false;
        String ltdToEnter = lastTDateAdd.getText();
        if(ltdToEnter.matches("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([1-9][0-9][0-9][0-9])$")){
            ltd = true;
        }
        //verify the date and time
        //TODO date and hour format
        boolean existAppointmentDate = false;
        boolean existAppointmentHour = false;
        for(Consultation consultation : appointments) {
            String toAddDate = dateAdd.getText();
            String toAddHour = hourAdd.getText();
            String dateInAppointment = consultation.getDate();
            String hourInAppointment = consultation.getHour();
            if(toAddDate.equals(dateInAppointment)){
                existAppointmentDate = true;
                if(toAddHour.equals(hourInAppointment)){
                    existAppointmentHour = true;
                    break;
                }
                break;
            }
        }
        //if else
        if(existName == true){
            popUpResultExist();
            clear();
        }
        else if(correctAge == false){
            popUpWrongAge();
        }
        else if(validGender == false){
            popUpGender();
        }
        else if(validPhoneNumber == false){
            popUpWrongPhoneNumber();
        }
        else if(ltd == false){
            popUpWrongDateFormat();
        }
        else if(!dateAdd.getText().matches("^(0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/]([1-9][0-9][0-9][0-9])$")){
            popUpWrongDateFormat();
        }
        else if(!hourAdd.getText().matches("(^[01]?[0-9]|2[0-3]):[0-5][0-9]$")){
            popUpWrongHourFormat();
        }
        else if(existAppointmentDate == true && existAppointmentHour == true){
            popUpResultExistAppointment();
        }
        //else if(!toothAdd.getText().matches("^[1-4][.][1-8]$")){
        //    popUpWrongTooth();
        //}
        else{
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        String sqlPatient = "INSERT INTO PATIENTS (`NAME`, `AGE`, `GENDER`, `PHONE NUMBER`) VALUES ('" + nameAdd.getText() + "', '" + ageAdd.getText() + "', '" + genderAdd.getText() + "', '" + phoneAdd.getText() + "')";
        System.out.println(sqlPatient);
        Statement statementP = connection.createStatement();
        statementP.executeUpdate(sqlPatient);

        String sqlMedRec = "INSERT INTO MEDRECORDS (`ALLERGIES`, `TREATMENT`, `LAST TREATMENT DATE`, `OTHER SURGERY`, `VICES`) VALUES ('" + allergiesAdd.getText() + "', '" + treatmentAdd.getText() + "', '" + lastTDateAdd.getText() + "', '" + surgeryAdd.getText() + "', '" + vicesAdd.getText() + "')";
        System.out.println(sqlMedRec);
        Statement statementM = connection.createStatement();
        statementM.executeUpdate(sqlMedRec);
        
        int lastID = 1;
        try {
            ResultSet rs = null;
            Statement st = con.createStatement();
            String query = "SELECT ID FROM PATIENTS ORDER BY ID DESC LIMIT 1";
            rs = st.executeQuery(query);
            if (rs.next()) {
                lastID = rs.getInt("ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sqlConsult = "INSERT INTO APPOINTMENTS (`DATE`, `HOUR`, `DESCRIPTION`, `TOOTH`, `PATIENT_ID`) VALUES ('" + dateAdd.getText() + "', '" + hourAdd.getText() + "', '" + descriptionAdd.getText() + "', '" + toothAdd.getText() + "', '" + lastID + "')";
        System.out.println(sqlConsult);
        Statement statementC = connection.createStatement();
        statementC.executeUpdate(sqlConsult);

        popUpResultAdded();
        clear();
        updatePatientTable();
        updateMedRecTable();
        }
    }
    //search tab
    @FXML void SearchByName(ActionEvent actionEvent) throws SQLException {
        String name = nameToSearch.getText().toUpperCase();
        ConnectionClass connection1 = new ConnectionClass();
        Connection con = connection1.getConnection();
        ObservableList<Patient> patients = getAllRecordsPatients(con);
        ObservableList<MedicalRecord> medrecords = getAllRecordsMedRec(con);
        for(Patient patient : patients) {
            if(patient.getPatientName().toUpperCase().equals(name)){
                messageResultName.setText("Patient found!");
                String details = "Id: " + patient.getId() +
                        "\nName: " + patient.getPatientName() +
                        "\nAge: " + patient.getAge() +
                        "\nGender: " + patient.getGender() +
                        "\nPhone number: " + patient.getPhoneNumber();
                for(MedicalRecord medrec: medrecords){
                    System.out.println(medrec.toString());
                    if( patient.getId() == medrec.getId()){
                        details+= "\nAllergies: " + medrec.getAllergies() +
                                "\nTreatment: " + medrec.getTreatment() +
                                "\nLast treatment date: " + medrec.getLastTreatmentDate() +
                                "\nOther surgery: " + medrec.getOtherSurgery() +
                                "\nVices: " + medrec.getVices();
                        break;
                    }
                }
                detailsByName.setText(details);
                break;
            }
            else{
                messageResultName.setText("Patient not found!");
            }
        }
    }
    @FXML void SearchByPhone(ActionEvent actionEvent) throws SQLException {
        String phone = phoneToSearch.getText().toUpperCase();
        ConnectionClass connection1 = new ConnectionClass();
        Connection con = connection1.getConnection();
        ObservableList<Patient> patients = getAllRecordsPatients(con);
        ObservableList<MedicalRecord> medrecords = getAllRecordsMedRec(con);
        for(Patient patient : patients) {
            if(patient.getPhoneNumber().toUpperCase().equals(phone)){
                messageResultPhone.setText("Patient found!");
                String details = "Id: " + patient.getId() +
                        "\nName: " + patient.getPatientName() +
                        "\nAge: " + patient.getAge() +
                        "\nGender: " + patient.getGender() +
                        "\nPhone number: " + patient.getPhoneNumber();
                for(MedicalRecord medrec: medrecords){
                    System.out.println(medrec.toString());
                    if( patient.getId() == medrec.getId()){
                        details+= "\nAllergies: " + medrec.getAllergies() +
                                "\nTreatment: " + medrec.getTreatment() +
                                "\nLast treatment date: " + medrec.getLastTreatmentDate() +
                                "\nOther surgery: " + medrec.getOtherSurgery() +
                                "\nVices: " + medrec.getVices();
                        break;
                    }
                }
                detailsByPhone.setText(details);
                break;
            }
            else{
                messageResultPhone.setText("Patient not found!");
            }
        }
    }
    @FXML void clearName(ActionEvent actionEvent){
        nameToSearch.clear();
        messageResultName.setText("");
        detailsByName.setText("");
    }
    @FXML void clearPhone(ActionEvent actionEvent){
        phoneToSearch.clear();
        messageResultPhone.setText("");
        detailsByPhone.setText("");
    }

    protected void popUpResultAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setHeaderText("Patient successfully added!");
        alert.setContentText("Have a nice day!");
        alert.showAndWait();
    }
    protected void popUpResultExist() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Patient already exists!");
        alert.showAndWait();
    }
    protected void popUpWrongAge(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("The age seems wrong!");
        alert.showAndWait();
    }
    protected void popUpGender(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("The gender must be a capital letter: M or F!");
        alert.showAndWait();
    }
    protected void popUpWrongPhoneNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Phone number must be 10 digits!");
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
    protected void popUpResultExistAppointment(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("The hour on this date is occupied!");
        alert.setContentText("Choose another hour or date!");
        alert.showAndWait();
    }
    protected void clear() {
        nameAdd.clear();
        ageAdd.clear();
        genderAdd.clear();
        phoneAdd.clear();
        allergiesAdd.clear();
        lastTDateAdd.clear();
        treatmentAdd.clear();
        vicesAdd.clear();
        surgeryAdd.clear();
        dateAdd.clear();
        hourAdd.clear();
        descriptionAdd.clear();
        toothAdd.clear();
    }
}