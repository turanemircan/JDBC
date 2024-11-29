import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

        // 02-Adım : Bağlantıyı oluşturma : DB URL
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt", "techpro", "password");

        // 03-Adım : Statement olusturma
        Statement statement = con.createStatement();

        // 04-Adım
        System.out.println("-------------------------------ÖRNEK 1-------------------------------");
        // ÖRNEK 1: id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.
        String sql01 = "Select country_name from countries where id between 5 and 10";
        boolean query01 = statement.execute(sql01);
        System.out.println("query01 = " + query01); // true

        /*
        Verileri alabillmek için:
        JDBC kullanarak veri çekme işlemi sonrasında
        veri listelemek için ResultSet sınıfı kullanılır.

        SQL sorgusu çalıştırıldıktan sonra veritabanından alınan
        verileri saklar. Verilerin arasında gitmemizi sağlar.
        Adv NOT: Veriler üzerinde dolaşmak için next, first, last, previous,
        absolute gibi metotlara sahiptir. Bunun için ayarlama gereklidir.
        */

        ResultSet rs = statement.executeQuery(sql01);
        while (rs.next()) {
            // System.out.println("Ülke Adı : " + rs.getString("country_name"));
            // System.out.println("Ülke Adı : " + rs.getString(1));
        }

        System.out.println("-------------------------------ÖRNEK 2-------------------------------");

        // ÖRNEK 2: phone_code'u 200 den büyük olan ülkelerin "phone_code" ve "country_name" bilgisini listeleyiniz

        String sql02 = "SELECT phone_code,country_name from countries where phone_code>200";
        ResultSet rs02 = statement.executeQuery(sql02);
        while (rs02.next()) {
            System.out.println("Ülke Adı : " + rs02.getString("country_name") +
                    "Telefon Kodu : " + rs02.getInt("phone_code"));

        }

        System.out.println("-------------------------------ÖRNEK 3-------------------------------");

        // ÖRNEK 3:developers tablosunda "salary" değeri minimum olan developerların tüm bilgilerini gösteriniz

        ResultSet rs03 = statement.executeQuery("select * from developers where salary=(select min(salary) from developers)");

        while (rs03.next()) {
            System.out.println("id : " + rs03.getInt("id") +
                    " --- isim : " + rs03.getString("name") +
                    " --- maas : " + rs03.getDouble("salary") +
                    " --- prog-dili : " + rs03.getString("prog_lang"));
        }

        System.out.println("-------------------------------ÖDEV-------------------------------");

        // ÖDEV:Puanı taban puanlarının ortalamasından yüksek olan öğrencilerin isim ve puanlarını listeleyiniz.
    }
}