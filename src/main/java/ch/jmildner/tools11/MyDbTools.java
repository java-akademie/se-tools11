
package ch.jmildner.tools11;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public final class MyDbTools {

    private MyDbTools() {
    }

    public static String getUrl(final String DATABASE) {
        switch (DATABASE) {
            case "ORACLE":
                return "jdbc:oracle:thin:jees/jees@192.168.0.87:1521:xe";
            case "POSTGRES":
                return "jdbc:postgresql://localhost:5432/jees?user=jees&password=jees";
            case "MYSQL":
                return "jdbc:mysql://localhost:3306/jees?user=jees&password=jees";
            case "MYSQL-DOCKER-LOCAL":
                return "jdbc:mysql://localhost:3316/mysql?user=root&password=geheim";
            case "MYSQL-DOCKER-HOSTTECH":
                return "jdbc:mysql://32582.hostserv.eu:3316/mysql?user=root&password=geheim";
            case "H2":
            default:
                return "jdbc:h2:tcp://localhost:9092/~/test;USER=sa;PASSWORD=sa";
        }
    }

    public static void select(final Connection conn, final String sql) throws SQLException {
        select(conn, sql, KzDbDaten.METADATA_AND_DATA);
    }

    public static void select(final Connection conn, final String sql, final KzDbDaten kz) throws SQLException {
        try (final Statement s = conn.createStatement()) {
            s.execute(sql);

            try (final ResultSet rs = s.getResultSet()) {
                ResultSetMetaData md = rs.getMetaData();

                switch (kz) {
                    case METADATA:
                        showMetadata(md);
                        break;
                    case METADATA_AND_DATA:
                        showMetadata(md);
                        showData(rs, md);
                        break;
                    case DATA:
                    default:
                        showData(rs, md);
                        break;
                }
            }
        }
    }

    private static void showMetadata(final ResultSetMetaData md) throws SQLException {
        System.out.println();
        System.out.println("+----------+");
        System.out.println("! Metadata !");
        System.out.println("+----------+");

        for (int i = 1; i <= md.getColumnCount(); i++) {
            System.out.printf("%-21S ", md.getColumnLabel(i));
            System.out.printf("Type=%5d ", md.getColumnType(i));
            System.out.printf("TypeName=%-12s ", md.getColumnTypeName(i));
            System.out.printf("Precision=%5d ", md.getPrecision(i));
            System.out.printf("Scale=%5d ", md.getScale(i));
            System.out.printf("TableName=%-10s ", md.getTableName(i));
            System.out.printf("ColumnClassName=%-10s ", md.getColumnClassName(i));
            System.out.println();
        }
        System.out.println();
    }

    private static void showData(final ResultSet rs, final ResultSetMetaData md) throws SQLException {
        System.out.println();
        System.out.println("+------+");
        System.out.println("! Data !");
        System.out.println("+------+");

        int anz = md.getColumnCount();

        for (int i = 1; i <= anz; i++) {
            System.out.printf("%-28S", md.getColumnLabel(i));
        }

        System.out.println();

        boolean dataWritten = false;

        while (rs.next()) {
            dataWritten = true;
            for (int i = 1; i <= anz; i++) {
                Object o = rs.getObject(i);
                // System.out.println(o.getClass());
                System.out.printf("%-28s", o);
            }
            System.out.println();
        }
        if (!dataWritten) {
            System.out.println("no data available");
        }

        System.out.println();
    }

}
