import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {

        // 02-ADIM:bağlantıyı oluşturma: db url,kullanıcı adı,password
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpro", "password");


        // 03-ADIM:Statement oluşturma:SQL ifadelerini veritabanına
        // iletir ve çalıştırır
        Statement st = connection.createStatement();

        // kayıtların tamamnını görelim
        System.out.println("----------------Update'den önce----------------");
        ResultSet rs = st.executeQuery("SELECT * FROM developers");

        while (rs.next()) {
            System.out.println("isim : " + rs.getString("name") + " maaş : " + rs.getDouble("salary"));
        }

        //  ÖRNEK 1: developers tablosunda maaşı ortalama maaştan az olanların maaşını ortalama maaş ile güncelleyiniz

        String sql01 = "UPDATE developers SET salary=(SELECT AVG(salary) FROM developers)" +
                "WHERE salary <(SELECT AVG(salary) FROM developers) ";

        int updated = st.executeUpdate(sql01);

        System.out.println("Güncellenen kayıt sayısı: " + updated);

        // kayıtların tamamnını görelim
        System.out.println("----------------Update'den Sonra----------------");
        ResultSet rs02 = st.executeQuery("SELECT * FROM developers");

        while (rs02.next()) {
            System.out.println("isim : " + rs02.getString("name") + " maaş : " + rs02.getDouble("salary"));
        }
    }
}
