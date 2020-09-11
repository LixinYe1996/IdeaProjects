package java.shptools;

import org.hsqldb.lib.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataConnection {
//    private static String url = "jdbc:oracle:" + "thin:@192.168.0.201:1521:orcl";// 127.0.0.1是本机地址
//    private static String user = "doc";
//    private static String password = "doc";
    private static String url = "jdbc:oracle:thin:@202.114.207.232:1521/orcl.xdata";// 127.0.0.1是本机地址
    private static String user = "c##cug4gqp";
    private static String password = "fOme3#5H3E";
//    private static String url = "jdbc:oracle:thin:@202.114.207.233:1521/pdb1.xdata";// 127.0.0.1是本机地址
//    private static String user = "bgdba";
//    private static String password = "Bigdata2019";
//    private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//    private static String user = "c##test";
//    private static String password = "123456";
    private static Connection connection;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getDataConnection(){
        return connection;
    }
     /**获取数据库中所有表名称
     * @return
     * @throws SQLException
     */
    public static List<String> getTables() throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet tables = databaseMetaData.getTables(connection.getCatalog(), "DOC", null, new String[] { "TABLE" });
        ArrayList<String> tablesList = new ArrayList<String>();
        while (tables.next()) {
            tablesList.add(tables.getString("TABLE_NAME"));
        }
        return tablesList;
    }
    public static HashMap<String,String> getcolumnname() throws SQLException {
        HashMap<String,String> columnname=new HashMap<String,String>();
        Statement stmt = connection.createStatement() ;
        ResultSet rs = stmt.executeQuery("SELECT FTABLENAME,FENAME,FCNAME FROM DICT_FIELDINFO") ;
        while(rs.next()){
            String table_name=rs.getString(1);
            String column_name=rs.getString(2);
            String comment=rs.getString(3);
            String names=table_name+"&"+comment;
            System.out.println(names+"+"+column_name);
            columnname.put(names,column_name);
        }
        return columnname;
    }

    public HashMap<String, String> gettableStructual(String tbn) throws SQLException {
        HashMap<String,String> datastructual=new HashMap<String,String>();
        Statement stmt = connection.createStatement() ;
        ResultSet rs = stmt.executeQuery("SELECT FENAME,FCNAME FROM DICT_FIELDINFO where FTABLENAME='"+tbn+"'") ;
        while(rs.next()){
            String column_name=rs.getString(1);
            String comments=rs.getString(2);
            System.out.println(column_name+"->"+comments);
            datastructual.put(column_name,comments);
        }
        return datastructual;

    }

    public HashMap<String, String> getclassname() throws SQLException {
        HashMap<String,String> columnname=new HashMap<String,String>();
        Statement stmt = connection.createStatement() ;
        ResultSet rs = stmt.executeQuery("SELECT TENAME,TCNAME FROM DICT_TABINFO") ;
        while(rs.next()){
            String tableename=rs.getString(1);
            String tablecname=rs.getString(2);
          //  String comment=rs.getString(3);
          //  String names=table_name+"&"+comment;
//            System.out.println(names+"+"+column_name);
            System.out.println(tableename+"->"+tablecname);
            columnname.put(tablecname,tableename);
        }
        return columnname;
    }
    public HashMap<String, String> querysql(String sql ) throws SQLException {
        HashMap<String,String> hm=new HashMap<String,String>();
        Statement stmt = connection.createStatement() ;
        ResultSet rs = stmt.executeQuery(sql) ;
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount=rsmd.getColumnCount();
        List<String> colNameList=new ArrayList<String>();
        for(int i=0;i<colCount;i++){
            colNameList.add(rsmd.getColumnName(i+1));
        }
        while(rs.next()){
            for(int i=0;i<colCount;i++){
                Map map=new HashMap<String, StringUtil>();
                String key=colNameList.get(i);
                String value=rs.getString(colNameList.get(i));
                hm.put(key, value);
            }
        }
        return hm;}

    }

