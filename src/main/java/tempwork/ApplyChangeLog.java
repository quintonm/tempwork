package tempwork;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import org.apache.log4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Quinton McCombs (dt77102)
 * @since 05/2012
 */
public class ApplyChangeLog {
  private static final Logger log = Logger.getLogger(ApplyChangeLog.class);
  public static final void main(String argv[])
      throws SQLException, LiquibaseException, IOException, ParserConfigurationException {

    Connection c = DriverManager.getConnection("jdbc:mysql://localhost/cre-new", "cre", "cre");
    Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));
    Liquibase lb = new Liquibase("src/main/resources/modified-changelog.xml", new FileSystemResourceAccessor(), database);
    lb.dropAll("cre-new");
    lb.update(null);
  }

}
