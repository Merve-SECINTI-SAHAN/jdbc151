import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
     // pom = page object model
    // dependency nereden = mvnrepository den alınır(normalde şirketten alınır çünkü
    // site public tir virüs falan bulaştırabilir.)
    // Çalıştığınız şirket size verir.
    // mavenrepository sitesinden her türlü dependency bulunabilir
    // database çok sıkıntılı tehlikeli bir yer oldugu için şifresi verilir ama çok kısıtlı
    // bir yetki ile verilir şirket tarafından

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1.Adım: Driver'a kaydol
       // Class.forName("org.postgresql.Driver"); //JDBC4 sonrası yapmaya gerek yok

        //2. Adım: Database'e bağlan
        Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","421653");
        //getConnection() static’tir. Cunku class ismi ile cagiriyoruz.

        //postgresql de filin üzerine sağ tıkladık, en altta properties dedik ve
        // connectiona gelip bilgilere baktık ve bilgileri oradan aldık
        // hostname/adress + port + maintenance database ==> adresim  ==> localhost:5432/postgres
        // ilk parametrenin başı standarttır. (jdbc:postgresql://...adresim...)

        /*   DRY= Don't Repeat Yourself
             WET= Write Everything Twice   */


        //3.Adım: Statement oluştur- Statement bizim icin bir ara adimdir.
        Statement statement= connection.createStatement();

       // 4.Adım: Query çalıştır

       // 1.Örnek: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.
        // "CREATE TABLE workers(worker_id VARCHAR(20),worker_name VARCHAR(20),worker_salary INT)";
        //Bunu önce pgAdmin'de yaptık, sonra kodu buraya copy-paste yaptık.

        String sql1= ("CREATE TABLE workers(worker_id VARCHAR(20),worker_name VARCHAR(20),worker_salary INT)");

        boolean r1=  statement.execute(sql1);
        System.out.println("r1= " + r1); //false

        /*
        execute() methodu DDL(create, drop, alter table) ve DQL(select) ile kullanılır
          1) Eğer execute() methodu DDL ile kullanılırsa hep "false" döner. Çünkü DDL ile data çağrılmaz
          2) Eğer execute() methodu DQL ile kullanılırsa data döndüğünde "true" data dönmediğinde "false" verir.
         */



        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.

        String sql2= "ALTER table workers ADD COLUMN worker_address VARCHAR(50)";
        boolean r2= statement.execute(sql2);
        System.out.println("r2= " + r2);


        //3.Örnek: workers table'ını silin.
        String sql3= "DROP table workers";
        boolean r3= statement.execute(sql3);
        System.out.println("r3= " + r3);

        //4. Adım: Bağlantıyı kapat
        connection.close();
        statement.close();

    }

}
