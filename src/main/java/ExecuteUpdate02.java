import java.sql.*;

public class ExecuteUpdate02 {
    public static void main(String[] args) throws SQLException {

        // 02-ADIM:bağlantıyı oluşturma: db url,kullanıcı adı,password
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpro", "password");


        // 03-ADIM:Statement oluşturma:SQL ifadelerini veritabanına
        // iletir ve çalıştırır
        Statement st = connection.createStatement();

        // ÖRNEK 2: developers tablosuna yeni bir developer ekleyiniz.
        String sql1 = "INSERT INTO developers(name,salary,prog_lang) " +
                "VALUES('Jack Sparrow',5000,'Java')";

        // int inserted = st.executeUpdate(sql1);
        // System.out.println("Eklenen kayıt sayısı: " + inserted);

        // Tüm kayıtları görelim.
        ResultSet rs = st.executeQuery("SELECT * FROM developers");

        while (rs.next()) {

            System.out.println("isim : " + rs.getString("name") + " maaş : " + rs.getDouble("salary"));

        }

        // ÖRNEK 3: developers tablosundan id'si 9 olanı siliniz.
        int deleted = st.executeUpdate("DELETE FROM developers WHERE id=9");
        System.out.println("Silinen kayıt sayısı : " + deleted);

        // ÖRNEK 4: developers tablosundan id'si 14 den büyük olanları siliniz.
        int deleted2 = st.executeUpdate("DELETE FROM developers WHERE id>14");
        System.out.println("Silinen kayıt sayısı 2 : " + deleted2);

        // Tüm kayıtları görelim.
        ResultSet rs02 = st.executeQuery("SELECT * FROM developers");

        while (rs02.next()) {

            System.out.println("id : " + rs02.getInt("id") +
                    " isim : " + rs02.getString("name") + " maaş : " + rs02.getDouble("salary"));
        }
    }
}
