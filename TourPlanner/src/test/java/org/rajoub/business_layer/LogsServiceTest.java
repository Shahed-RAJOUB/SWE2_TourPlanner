package org.rajoub.business_layer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rajoub.data_access_layer.LogDAO;
import org.rajoub.model.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class LogsServiceTest {
    @Mock
    private LogDAO logDAO;
    private LogsService logsService;

    @BeforeEach
    void setup() {
        logsService = new LogsService(logDAO);
    }

    @Test
    void GetLogsTest_getLogsList(){
        List<Log> logs = new ArrayList<>();
        Log log_1 = new Log(1);
        Log log_2 = new Log(2);
        Log log_3 = new Log(3);

        logs.add(log_1);
        logs.add(log_2);
        logs.add(log_3);
        Mockito.when(logDAO.GetLogs()).thenReturn(logs);

        Assertions.assertEquals(logsService.GetLogs() , logs);
    }
    @Test
    void DeleteLogTest_deleted() throws SQLException {
        Log expected = new Log(1);
        logsService.deleteLog(expected.getId());
        Mockito.verify(logDAO).deleteLog(expected.getId());
    }
    @Test
    void CopyLogTest_Copied() throws SQLException {
        Log expected = new Log(1,"01.01.2010" , 20 , 895 , 160 ,"tour" , "Good");
        logsService.copyLog(expected.getDate() , expected.getDuration() , expected.getDest() , expected.getTourName() , expected.getRatings());
        Mockito.verify(logDAO).copyLog(expected.getDate() , expected.getDuration() , expected.getDest() , expected.getTourName() , expected.getRatings());
    }

    @Test
    void InsertLogTest_inserted() throws SQLException {
        Log expected = new Log(1,"01.01.2010" , 20 , 895 , 160 ,"tour" , "Good");
        logsService.insertLog(expected.getDate() , expected.getDuration() , expected.getDest() , expected.getTourName() , expected.getRatings());
        Mockito.verify(logDAO).insertNewLog(expected.getDate() , expected.getDuration() , expected.getDest() , expected.getTourName() , expected.getRatings());
    }
    @Test
    void EditLogTest_edited() throws SQLException {
        Log expected = new Log(1,"01.01.2010" , 20 , 895 , 160 ,"tour" , "Good");
        logsService.EditTLog(expected.getDate() , expected.getDuration() , expected.getDest() , expected.getTourName() , expected.getId());
        Mockito.verify(logDAO).EditTLog(expected.getDate() , expected.getDuration() , expected.getDest() , expected.getTourName() , expected.getId());
    }

}
