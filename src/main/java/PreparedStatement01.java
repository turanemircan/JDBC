import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {

        // 02-ADIM:bağlantıyı oluşturma: db url,kullanıcı adı,password
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpro", "password");


        // 03-ADIM:Statement oluşturma:SQL ifadelerini veritabanına
        // iletir ve çalıştırır
        Statement st = connection.createStatement();

        // ÖRNEK 1: bolumler tablosunda Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
//        String sql01 = "UPDATE bolumler SET taban_puani=475 WHERE bolum ILIKE 'Matematik'";
//        int updated = st.executeUpdate(sql01);
//        System.out.println("Updated : " + updated);

        /*
        PreparedStatement; önceden derlenmiş tekrar tekrar kullanılabilen
        parametreli sorgular oluşturmamızı ve çalıştırmamızı sağlar.

        PreparedStatement Statement ı extend eder(statementın gelişmiş halidir.)
        */

        // Prepared Statement kullanarak bolumler tablosunda
        // Matematik bölümünün taban_puanı'nı 499 olarak güncelleyiniz.

        // parametreli sorguyu hazırlayalım,
        String sql2 = "UPDATE bolumler SET taban_puani=? WHERE bolum ILIKE ?";
        PreparedStatement prst = connection.prepareStatement(sql2); // önceden derlenmiş dinamik bir sorgu oluşturur

        prst.setInt(1, 499);
        prst.setString(2, "Matematik");

        // UPDATE bolumler SET taban_puani=499 WHERE bolum ILIKE 'Matematik'
        prst.executeUpdate();


        // Prepared Statement kullanarak bolumler tablosunda
        // Edebiyat bölümünün taban_puanı'nı 450 olarak güncelleyiniz.
        prst.setString(2, "edebiyat");
        prst.setInt(1, 450);
        //UPDATE bolumler SET taban_puani=450 WHERE bolum ILIKE 'edebiyat'
        prst.executeUpdate();//derlenmiş olan sorguyu çalıştırır.

        // Örnek 3:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475, kampus='Merkez') bölümünü ekleyiniz.
        String sql3 = "INSERT INTO bolumler VALUES(?,?,?,?)";
        PreparedStatement prst2 = connection.prepareStatement(sql3);
        prst2.setInt(1, 5006);
        prst2.setString(2, "Yazılım Müh.");
        prst2.setInt(3, 475);
        prst2.setString(4, "Merkez");
        prst2.executeUpdate();


        st.close();
        prst.close();
        prst2.close();
        connection.close();
    }
}
