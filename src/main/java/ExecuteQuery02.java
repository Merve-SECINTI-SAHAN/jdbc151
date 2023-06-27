import java.sql.*;

public class ExecuteQuery02 {
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
            System.out.println(resultSet.getString("country_name"));
        }

        System.out.println("\n=======2. Örnek: ==============\n");

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        String sql2 = "SELECT country_id, country_name FROM countries WHERE region_id > 2";
        ResultSet resultSet2 = statement.executeQuery(sql2);

        while (resultSet2.next()){//ResultSet son satıra gelip false verdikten sonra kapanır. Kapalı ResultSet üzerinde işlem yapılırsa exception atar.
            System.out.println(resultSet2.getString(1)+"-- "+resultSet2.getString(2));
        }


    }
}

