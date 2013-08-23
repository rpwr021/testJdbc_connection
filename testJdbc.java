import java.sql.*;

public class testJdbc {

public static void main(String[] args)
throws ClassNotFoundException, SQLException
{
Class.forName("oracle.jdbc.driver.OracleDriver");


String hostname = args[0];
String instance = args[1];
String user = args[2];
String password = args[3];
String intype;

if  (  args[4] == null )
{
        intype = "null" ;
}

        else

{

 intype = args[4] ;

}



if ( intype.equals("RAC") ||  intype.equals("rac") )

{
        System.out.printf("%s \n",intype);
        String url = String.format("jdbc:oracle:thin:@(description= (enable=broken) (load_balance=off) (failover=on) (address= (protocol=tcp) (host=%s) (port=1521)) (connect_data= (service_name=%s)))",hostname, instance);
        System.out.printf("\n RAC Connection URL is --> %s \n",url);
        System.out.printf("\n");
        Connection conn = DriverManager.getConnection(url,user,password);
        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        ResultSet rset =
        stmt.executeQuery("select BANNER from SYS.V_$VERSION");
        while (rset.next())
        {
                System.out.println (rset.getString(1));
        }
        stmt.close();

        System.out.println (" \n Success!, connected successfully with JDBC \n");

}

else

{

        System.out.printf("%s \n",intype);
        String url = String.format("jdbc:oracle:thin:@%s:1521:%s",hostname, instance);
        System.out.printf("\n Connection URL is --> %s \n",url);
        System.out.printf("\n");
        Connection conn = DriverManager.getConnection(url,user,password);
                conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        ResultSet rset =
        stmt.executeQuery("select BANNER from SYS.V_$VERSION");
        while (rset.next())
        {
                System.out.println (rset.getString(1));
        }
        stmt.close();

        System.out.println ("\n Success!, connected successfully with JDBC \n");

}


}

}
   
        
