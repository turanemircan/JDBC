import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 01-Adım : Veri Tabanını sürücüsü sınıfını kaydetmekle başlayabiliriz.

        Class.forName("org.postgresql.Driver");

        // 02-Adım : Bağlantıyı oluşturma : DB URL
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt", "techpro", "password");

        // 03-Adım : Statement olusturma
        Statement statement = con.createStatement();

        System.out.println("Success");

        // 04-Adım : sorguyu oluşturma/çalıştırma

        /*
        execute tüm sorguları calıstırmak icin kullanılır
        sorgunun sonucunda bize eger ResultSet alınıyorsa TRUE,aksi halde FALSE doner.
        1-DDL(data definition language)-->False
        2-DQL(data query language)-->True
        */

        // ÖRNEK 1:"workers" adında bir tablo oluşturup "worker_id,worker_name,salary" sütunlarını ekleyiniz.

        // CREATE TABLE workers(worker_id INT,worker_name VARCHAR(50),salary REAL);

        boolean sql1 = statement.execute("CREATE TABLE if not exists workers(worker_id INT,worker_name VARCHAR(50),salary REAL)");
        System.out.println("sql1 = " + sql1);

        // ÖRNEK 2: "workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.
        // ALTER TABLE workers ADD COLUMN city VARCHAR(20)

        // boolean sql2 = statement.execute("ALTER TABLE workers ADD COLUMN city VARCHAR(20)");
        // System.out.println("sql2 = " + sql2);

        // Örnek 3: "workers" tablosunu SCHEMA'dan siliniz.
        // DROP TABLE workers;
        statement.execute("DROP TABLE workers");

        // 05-Adım : Kaynakları kapatma
        statement.close();
        con.close();
    }
}
