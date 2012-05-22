package tempwork;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.integration.commandline.CommandLineUtils;
import org.apache.log4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenerateChangelog  {
  private static final Logger log = Logger.getLogger(GenerateChangelog.class);

  public static final void main(String argv[])
      throws SQLException, LiquibaseException, IOException, ParserConfigurationException {

    Connection c = DriverManager.getConnection("jdbc:mysql://localhost/cre-orig", "cre", "cre");
    Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
    CommandLineUtils.doGenerateChangeLog("src/main/resources/original-changelog.xml", database, "cre-orig", null, "author", null, null);


  }

}
