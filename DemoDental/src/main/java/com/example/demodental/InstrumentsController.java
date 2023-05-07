package com.example.demodental;

import Connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InstrumentsController implements Initializable {
    @FXML private TableView<Instrument> stockTable;
    @FXML private TableColumn<Instrument, Integer> idS;
    @FXML private TableColumn<Instrument, String> instrument;
    @FXML private TableColumn<Instrument, String> details;
    @FXML private TableColumn<Instrument, Integer> pieces;
    @FXML private TableColumn<Instrument, Integer> price;
    @FXML private TableColumn<Instrument, String> stockDate;
    @FXML private TextField addInstrument;
    @FXML private TextField addDetails;
    @FXML private TextField addPieces;
    @FXML private TextField addPrice;
    @FXML private TextField addLastStockDate;
    @FXML private TextField updateInstrument;
    @FXML private TextField updateDetails;
    @FXML private TextField updatePiecesNo;
    @FXML private TextField updatePrice;
    @FXML private TextField todayDate;

    @FXML private TableView<Sterilization> sterilizationTable;
    @FXML private TableColumn<Sterilization, Integer> id;
    @FXML private TableColumn<Sterilization, String> date;
    @FXML private TableColumn<Sterilization, String> begin;
    @FXML private TableColumn<Sterilization, String> end;
    @FXML private TableColumn<Sterilization, String> device;
    @FXML private TableColumn<Sterilization, String> content;
    @FXML private TableColumn<Sterilization, String> doctorName;
    @FXML private TextField dateAdd;
    @FXML private TextField beginAdd;
    @FXML private TextField endAdd;
    @FXML private TextField deviceAdd;
    @FXML private TextArea contentAdd;
    @FXML private TextField doctorNameAdd;



    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        ObservableList<Instrument> stockList = null;
        ObservableList<Sterilization> sterilizationList = null;
        try {
            stockList = getDataforStock();
            updateStockList(stockList);
            sterilizationList = getDataforSterilization();
            updateSterilizationList(sterilizationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Instrument> getDataforStock() throws SQLException {
        //getDataFromDataBase
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        String sql = "SELECT * FROM `stock`";
        try(Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Instrument> instrumentsList = FXCollections.observableArrayList();
            while(rs.next()){
                Instrument p = new Instrument();
                p.setId(rs.getInt("ID"));
                p.setName(rs.getString("INSTRUMENT"));
                p.setDetails(rs.getString("DETAILS"));
                p.setAmount(rs.getInt("PIECES"));
                p.setPrice(rs.getInt("PRICE/PIECE"));
                p.setStockDate(rs.getString("LAST STOCK DATE"));
                instrumentsList.add(p);
            }
            return instrumentsList;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }
    private ObservableList<Sterilization> getDataforSterilization() throws SQLException {
        //getDataFromDataBase
        ConnectionClass connection = new ConnectionClass();
        Connection con = connection.getConnection();
        String sql = "SELECT * FROM `sterilizations`";
        try(Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Sterilization> sterilizations = FXCollections.observableArrayList();
            while(rs.next()){
                Sterilization p = new Sterilization();
                p.setId(rs.getInt("ID"));
                p.setDate(rs.getString("DATE"));
                p.setBegin(rs.getString("BEGIN"));
                p.setEnd(rs.getString("END"));
                p.setDevice(rs.getString("DEVICE"));
                p.setContent(rs.getString("CONTENT"));
                p.setName(rs.getString("NAME"));
                sterilizations.add(p);
            }
            return sterilizations;
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }
    private void updateStockList(ObservableList<Instrument> stockList) {
        idS.setCellValueFactory(new PropertyValueFactory<Instrument, Integer>("id"));
        instrument.setCellValueFactory(new PropertyValueFactory<Instrument, String>("name"));
        details.setCellValueFactory(new PropertyValueFactory<Instrument, String>("details"));
        pieces.setCellValueFactory(new PropertyValueFactory<Instrument, Integer>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<Instrument, Integer>("price"));
        stockDate.setCellValueFactory(new PropertyValueFactory<Instrument, String>("stockDate"));
        stockTable.setItems(stockList);
        //System.out.println("UPDATE FINALIZAT");
    }
    private void updateSterilizationList(ObservableList<Sterilization> sterilizationList) {
        id.setCellValueFactory(new PropertyValueFactory<Sterilization, Integer>("id"));
        date.setCellValueFactory(new PropertyValueFactory<Sterilization, String>("date"));
        begin.setCellValueFactory(new PropertyValueFactory<Sterilization, String>("begin"));
        end.setCellValueFactory(new PropertyValueFactory<Sterilization, String>("end"));
        device.setCellValueFactory(new PropertyValueFactory<Sterilization, String>("device"));
        content.setCellValueFactory(new PropertyValueFactory<Sterilization, String>("content"));
        doctorName.setCellValueFactory(new PropertyValueFactory<Sterilization, String>("name"));
        sterilizationTable.setItems(sterilizationList);
    }


    @FXML void AddInstrumentButton(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (addInstrument.getText().isEmpty()) {
            alert.setHeaderText("Missing instrument!");
            alert.showAndWait();
        }
        else if (addDetails.getText().isEmpty()) {
            alert.setHeaderText("Missing details!");
            alert.showAndWait();
        }
        else if (addPieces.getText().isEmpty()) {
            alert.setHeaderText("Missing pieces!");
            alert.showAndWait();
        }
        else if (addPrice.getText().isEmpty()) {
            alert.setHeaderText("Missing price!");
            alert.showAndWait();
        }
        else if (addLastStockDate.getText().isEmpty()) {
            alert.setHeaderText("Missing date!");
            alert.showAndWait();
        }
        else add();

    }
    private void add() throws SQLException {
        ObservableList<Instrument> instruments = getDataforStock();
        //verify the name
        boolean existName = false;
        for(Instrument instrument : instruments) {
            String toAddInstrument = addInstrument.getText().toUpperCase();
            String nameInInstruments = instrument.getName().toUpperCase();
            if(toAddInstrument.equals(nameInInstruments)){
                existName = true;
                break;
            }
        }
        //verify the pieces
        boolean piecesNo = true;
        int toAddPieces = Integer.parseInt(addPieces.getText());
        if(toAddPieces < 0 || toAddPieces > 1000) {
            piecesNo = false;
        }
        //verify the price
        boolean pricee = true;
        int toAddPrice = Integer.parseInt(addPrice.getText());
        if(toAddPrice < 0 || toAddPrice > 5000){
            pricee = false;
        }

        //verify last stock date string to be dd/mm/yyyy
        boolean ltd = false;
        String ltdToEnter = addLastStockDate.getText();
        if(ltdToEnter.matches("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([1-9][0-9][0-9][0-9])$")){
            ltd = true;
        }

        //if else
        if(existName == true){
            popUpResultExist();
            clear();
        }
        else if(piecesNo == false){
            popUpOutOfIntervalPieces();
        }
        else if(pricee == false){
            popUpOutOfIntervalPrice();
        }
        else if(ltd == false){
            popUpWrongDate();
        }
        else{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String sqlInstrument = "INSERT INTO `stock`(`INSTRUMENT`, `DETAILS`, `PIECES`, `PRICE/PIECE`, `LAST STOCK DATE`) VALUES ('"+ addInstrument.getText() +"','"+ addDetails.getText() +"','"+ addPieces.getText() +"','"+ addPrice.getText() +"','"+ addLastStockDate.getText() +"')";
            System.out.println(sqlInstrument);
            Statement statementP = connection.createStatement();
            statementP.executeUpdate(sqlInstrument);

            instruments = getDataforStock();
            clear();
            updateStockList(instruments);
        }
    }
    @FXML void UpdateInstrumentButton(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (updateInstrument.getText().isEmpty()) {
            alert.setHeaderText("Missing instrument!");
            alert.showAndWait();
        }
        else if (updateDetails.getText().isEmpty()) {
            alert.setHeaderText("Missing details!");
            alert.showAndWait();
        }
        else if (updatePiecesNo.getText().isEmpty()) {
            alert.setHeaderText("Missing pieces!");
            alert.showAndWait();
        }
        else if (updatePrice.getText().isEmpty()) {
            alert.setHeaderText("Missing price!");
            alert.showAndWait();
        }
        else if (todayDate.getText().isEmpty()) {
            alert.setHeaderText("Missing date!");
            alert.showAndWait();
        }
        else update();
    }
    private void update() throws SQLException {
        ObservableList<Instrument> instruments = getDataforStock();
        //verify the name
        int idI = 1;
        boolean existName = false;
        for(Instrument instrument : instruments) {
            String toAddInstrument = updateInstrument.getText().toUpperCase();
            String nameInInstruments = instrument.getName().toUpperCase();
            if(toAddInstrument.equals(nameInInstruments)){
                existName = true;
                idI = instrument.getId();
                break;
            }
        }
        //verify the pieces
        boolean piecesNo = true;
        int toAddPieces = Integer.parseInt(updatePiecesNo.getText());
        if(toAddPieces < 0 || toAddPieces > 1000) {
            piecesNo = false;
        }
        //verify the price
        boolean pricee = true;
        int toAddPrice = Integer.parseInt(updatePrice.getText());
        if(toAddPrice < 0 || toAddPrice > 5000){
            pricee = false;
        }

        //verify last stock date string to be dd/mm/yyyy
        boolean ltd = false;
        String ltdToEnter = todayDate.getText();
        if(ltdToEnter.matches("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([1-9][0-9][0-9][0-9])$")){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String todayDate = dtf.format(now);
            if(ltdToEnter.equals(todayDate)){
                ltd = true;
            }
        }

        //if else
        if(existName == true){
            if(piecesNo == false){
                popUpOutOfIntervalPieces();
            }
            else if(pricee == false){
                popUpOutOfIntervalPrice();
            }
            else if(ltd == false){
                popUpWrongDate();
            }
            else{
                ConnectionClass connectionClass = new ConnectionClass();
                Connection con = connectionClass.getConnection();
                int piecess = Integer.parseInt(updatePiecesNo.getText());

                int piece = 1;
                try {
                    ResultSet rs = null;
                    Statement st = con.createStatement();
                    String query = "SELECT PIECES FROM STOCK WHERE ID = '" + idI + "'";
                    rs = st.executeQuery(query);
                    if (rs.next()) {
                        piece = rs.getInt("PIECES");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                piecess+=piece;
                System.out.println("\n"+pieces);
                String sqlInstrument = "UPDATE STOCK SET `DETAILS`='"+ updateDetails.getText() +"', `PIECES`='"+ piecess +"', `PRICE/PIECE`='"+ updatePrice.getText() +"', `LAST STOCK DATE`='"+ todayDate.getText() +"' WHERE ID = '"+ idI+"' ";
                //UPDATE STOCK SET `DETAILS`='***',`PIECES`='600',`PRICE/PIECE`='1',`LAST STOCK DATE`='02/01/2022' WHERE ID = '1'
                System.out.println(sqlInstrument);
                Statement statementP = con.createStatement();
                statementP.executeUpdate(sqlInstrument);
                instruments = getDataforStock();
                clear();
                updateStockList(instruments);
            }
        }
        else{
            popUpResultNotExist();
        }

    }


    @FXML void AddSterilizationButton(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        if (date.getText().isEmpty()) {
            alert.setHeaderText("Missing date!");
            alert.showAndWait();
        }
        else if (begin.getText().isEmpty()) {
            alert.setHeaderText("Missing begin hour!");
            alert.showAndWait();
        }
        else if (end.getText().isEmpty()) {
            alert.setHeaderText("Missing end hour!");
            alert.showAndWait();
        }
        else if (device.getText().isEmpty()) {
            alert.setHeaderText("Missing device!");
            alert.showAndWait();
        }
        else if (content.getText().isEmpty()) {
            alert.setHeaderText("Missing content!");
            alert.showAndWait();
        }
        else if (doctorName.getText().isEmpty()) {
            alert.setHeaderText("Missing doctor name!");
            alert.showAndWait();
        }
        else addS();
    }
    private void addS() throws SQLException {
        ObservableList<Sterilization> sterilizations = getDataforSterilization();
        //verify the date string to be dd/mm/yyyy
        boolean d = false;
        String dToEnter = dateAdd.getText();
        String dev = deviceAdd.getText().toUpperCase();
        if(dToEnter.matches("^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([1-9][0-9][0-9][0-9])$")){
            d = true;
        }
        if(!d){
            popUpWrongDate();
        }
        else if(!beginAdd.getText().matches("(^[01]?[0-9]|2[0-3]):[0-5][0-9]$")){
            popUpWrongHourFormat();
        }
        else if(!endAdd.getText().matches("(^[01]?[0-9]|2[0-3]):[0-5][0-9]$")){
            popUpWrongHourFormat();
        }
        else if(contentAdd.getText().isEmpty()){
            popUpMissingContent();
        }
        else if(doctorNameAdd.getText().isEmpty()){
            popUpMissingDoctorName();
        }
        else if((!dev.equals("AUTOCLAV")) && (!dev.equals("PUPINEL"))){
            popUpWrongDevice();
        }
        else{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            String sqlSterilization = "INSERT INTO `sterilizations`(`DATE`, `BEGIN`, `END`, `DEVICE`, `CONTENT`, `NAME`) VALUES ('"+ dateAdd.getText() +"','"+ beginAdd.getText() +"','"+ endAdd.getText() +"','"+ deviceAdd.getText() +"','"+ contentAdd.getText() +"','"+ doctorNameAdd.getText() +"')";
            System.out.println(sqlSterilization);
            Statement statementP = connection.createStatement();
            statementP.executeUpdate(sqlSterilization);
            sterilizations = getDataforSterilization();
            clearSter();
            updateSterilizationList(sterilizations);
        }
    }


    protected void popUpResultExist() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Instrument already exists!");
        alert.showAndWait();
    }
    protected void popUpResultNotExist() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Instrument does not exists!");
        alert.showAndWait();
    }
    protected void popUpOutOfIntervalPieces(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("The pieces number is out of range!");
        alert.showAndWait();
    }
    protected void popUpOutOfIntervalPrice(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("The price is out of range!");
        alert.showAndWait();
    }
    protected void popUpWrongDate(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong date format -(dd/mm/yyyy) or wrong date!");
        alert.showAndWait();
    }
    protected void popUpWrongHourFormat(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong hour format! (hh:mm)");
        alert.showAndWait();
    }
    protected void popUpWrongDevice(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wrong device!(autoclav/pupinel)");
        alert.showAndWait();
    }
    private void popUpMissingContent() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing Content!");
        alert.showAndWait();
    }
    private void popUpMissingDoctorName() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Missing doctor name!");
        alert.showAndWait();
    }
    protected void clear() {
        addInstrument.clear();
        addDetails.clear();
        addPieces.clear();
        addPrice.clear();
        addLastStockDate.clear();
        updateInstrument.clear();
        updateDetails.clear();
        updatePiecesNo.clear();
        updatePrice.clear();
        todayDate.clear();
    }
    protected void clearSter() {
        dateAdd.clear();
        beginAdd.clear();
        endAdd.clear();
        deviceAdd.clear();
        doctorNameAdd.clear();
        contentAdd.clear();
    }
}
