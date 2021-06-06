package org.rajoub.data_access_layer;
import org.junit.jupiter.api.*;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;

@Testcontainers
class TourAndLogDAOTest extends DAO_Base{

    @Test
    void testEmptyDBConnectionTourDAO_null(){
        Assertions.assertEquals(tourDAO.GetTours().size(), 0);
    }

    @Test
    void testEmptyDBConnectionLogDAO_null(){
        Assertions.assertEquals(logDAO.GetLogs().size(), 0);
    }

    @Test
    void GetLogsTourDAO_inserted() throws SQLException {
        tourDAO.insertNewTour("tour" ,"from" ,"to");
        Assertions.assertEquals(tourDAO.GetTours().size() ,  1);
    }


    @Test
    void GetLogsLogDAO_inserted() throws SQLException {
        logDAO.insertNewLog("01.01.2020" ,253f ,253f, "tour" , "Good");
        Assertions.assertEquals(logDAO.GetLogs().size() ,  1);
    }

    @Test
    void insertToursValue_inserted() throws SQLException {
        tourDAO.insertNewTour("tour" ,"from" ,"to");
        Assertions.assertEquals(tourDAO.GetTours().get(0).getTourName() ,  "tour");
        Assertions.assertEquals(tourDAO.GetTours().get(0).getFrom() ,  "from");
        Assertions.assertEquals(tourDAO.GetTours().get(0).getTo() ,  "to");
    }
    @Test
    void insertLogsValue_inserted() throws SQLException {
        logDAO.insertNewLog("01.01.2020" ,253f ,253f , "tour" , "Good");
        Assertions.assertEquals(logDAO.GetLogs().get(0).getTourName() ,  "tour");
        Assertions.assertEquals(logDAO.GetLogs().get(0).getDate(),  "01.01.2020");
        Assertions.assertEquals(logDAO.GetLogs().get(0).getDuration() ,  253f);
        Assertions.assertEquals(logDAO.GetLogs().get(0).getDest() ,  253f);
        Assertions.assertEquals(logDAO.GetLogs().get(0).getRatings(),  "Good");
    }
    @Test
    void editToursValue_edited() throws SQLException {
        tourDAO.insertNewTour("tour" ,"from" ,"to");
        tourDAO.EditTour("Tour" ,"From" ,"To" , "tour");
        Assertions.assertEquals(tourDAO.GetTours().get(0).getTourName() ,  "Tour");
        Assertions.assertEquals(tourDAO.GetTours().get(0).getFrom() ,  "From");
        Assertions.assertEquals(tourDAO.GetTours().get(0).getTo() ,  "To");
    }

    @Test
    void editLogsValue_edited() throws SQLException {
        logDAO.insertNewLog("01.01.2020" ,253f ,253f , "tour" , "Good");
        logDAO.EditTLog("02.01.2020" ,253f ,253f , "Tour" , 1);
        Assertions.assertEquals(logDAO.GetLogs().get(0).getTourName() ,  "Tour");
        Assertions.assertEquals(logDAO.GetLogs().get(0).getDate(),  "02.01.2020");
        Assertions.assertEquals(logDAO.GetLogs().get(0).getDuration() ,  253f);
        Assertions.assertEquals(logDAO.GetLogs().get(0).getDest() ,  253f);
        Assertions.assertEquals(logDAO.GetLogs().get(0).getRatings(),  "Good");
    }
    @Test
    void CopyToursValue_copied() throws SQLException {
        tourDAO.insertNewTour("tour" ,"from" ,"to");
        tourDAO.copyTour("tour" ,"from" ,"to");
        Assertions.assertEquals(tourDAO.GetTours().get(1).getTourName() ,  "copy_tour");
        Assertions.assertEquals(tourDAO.GetTours().get(1).getFrom() ,  "from");
        Assertions.assertEquals(tourDAO.GetTours().get(1).getTo() ,  "to");
    }
    @Test
    void CopyLogsValue_copied() throws SQLException {
        logDAO.insertNewLog("01.01.2020" ,253f ,253f , "tour" , "Good");
        logDAO.copyLog("01.01.2020" ,253f ,253f , "tour" , "Good");
        Assertions.assertEquals(logDAO.GetLogs().get(1).getTourName() ,  "tour");
        Assertions.assertEquals(logDAO.GetLogs().get(1).getDate(),  "01.01.2020");
        Assertions.assertEquals(logDAO.GetLogs().get(1).getDuration() ,  253f);
        Assertions.assertEquals(logDAO.GetLogs().get(1).getDest() ,  253f);
        Assertions.assertEquals(logDAO.GetLogs().get(1).getRatings(),  "Good");
    }
    @Test
    void DeleteToursValue_Deleted() throws SQLException {
        tourDAO.insertNewTour("tour" ,"from" ,"to");
        tourDAO.deleteTour("tour");
        Assertions.assertEquals(tourDAO.GetTours().size(), 0);
    }
    @Test
    void DeleteLogsValue_Deleted() throws SQLException {
        logDAO.insertNewLog("01.01.2020" ,253f ,253f , "tour" , "Good");
        logDAO.deleteLog(1);
        Assertions.assertEquals(logDAO.GetLogs().size(), 0);
    }
}
