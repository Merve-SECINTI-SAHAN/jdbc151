import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {


        Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","421653");
        Statement statement= connection.createStatement();

        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1=  "Select country_name from countries where region_id = 1";
        boolean r1= statement.execute(sql1);
        System.out.println("r1= " + r1); //true ==> dql ile data çağırıyoruz

        //Datayı çağırıp okumak için executeQuery methodunu kullanmalıyız.
        ResultSet resultSet = statement.executeQuery(sql1);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }

    }
}
