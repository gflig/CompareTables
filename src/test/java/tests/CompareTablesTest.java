package tests;

import compare.CompareTables;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class CompareTablesTest {
    @Test
    public void compareTables() throws SQLException {
        CompareTables compareTables = new CompareTables();
        compareTables.compareTables();
    }
}
