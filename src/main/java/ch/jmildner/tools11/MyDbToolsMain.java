
package ch.jmildner.tools11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

public class MyDbToolsMain {

    private final DataSource DS;

    public static void main(String[] args) throws SQLException {
        new MyDbToolsMain().run();
    }

    public MyDbToolsMain() {
        this.DS = new MyPoolingDataSource("H2").getDataSource();
    }

    private void run() throws SQLException {
        MyTools.h1("MyDbToolsMain");

        try (Connection c = DS.getConnection()) {
            drop(c);
            create(c);
            insert(c);
            select(c);
        }
    }

    private void drop(Connection c) throws SQLException {
        try (Statement s = c.createStatement()) {
            s.execute("drop table test if exists");
        }
    }

    private void create(Connection c) throws SQLException {
        try (Statement s = c.createStatement()) {
            s.execute("create table test "
                    + "(id int primary key, name varchar(20))");
        }
    }

    private void select(Connection c) throws SQLException {
        MyDbTools.select(c, "select id, name from test");
    }

    private void insert(Connection c) throws SQLException {
        try (PreparedStatement ps =
                     c.prepareStatement("insert into test values(?,?)")) {
            for (int i = 1; i <= 10; i++) {
                ps.setInt(1, i);
                ps.setString(2, "name-" + i);
                ps.execute();
            }
        }
    }
}
