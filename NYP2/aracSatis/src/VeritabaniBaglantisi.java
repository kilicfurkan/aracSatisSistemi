import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class VeritabaniBaglantisi {
    private static final String URL = "jdbc:mysql://localhost:3306/aracSatisSistemi?useSSL=false&serverTimezone=Europe/Istanbul";
    private static final String KULLANICI_ADI = "root";
    private static final String SIFRE = "211207";

    public static Connection baglan() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, KULLANICI_ADI, SIFRE);
        }
        catch (ClassNotFoundException e) {
            throw new SQLException("Driver bulunamadı", e);
        }
    }

    public static int aracEkle(Arac arac) {
        String sql = "INSERT INTO araclar (arac_turu, uretim_yili, marka, model, fiyat) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = baglan();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, arac.getAracTuru());
            stmt.setInt(2, arac.getUretimYili());
            stmt.setString(3, arac.getMarka());
            stmt.setString(4, arac.getModel());
            stmt.setDouble(5, arac.getFiyat());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int aracId = -1;
            if (rs.next()) {
                aracId = rs.getInt(1);
            }

            if (arac instanceof Otomobil) {
                Otomobil oto = (Otomobil) arac;
                String ekSql = "INSERT INTO otomobil (id, km, motor_gucu) VALUES (?, ?, ?)";
                try (PreparedStatement ekStmt = conn.prepareStatement(ekSql)) {
                    ekStmt.setInt(1, aracId);
                    ekStmt.setInt(2, oto.getKm());
                    ekStmt.setInt(3, oto.getMotorGucu());
                    ekStmt.executeUpdate();
                }
            }
            if (arac instanceof Ticari) {
                Ticari tic = (Ticari) arac;
                String ekSql = "INSERT INTO ticari (id, km, motor_gucu, yuk_kapasitesi) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ekStmt = conn.prepareStatement(ekSql)) {
                    ekStmt.setInt(1, aracId);
                    ekStmt.setInt(2, tic.getKm());
                    ekStmt.setInt(3, tic.getMotorGucu());
                    ekStmt.setInt(4, tic.getYukKapasitesi());
                    ekStmt.executeUpdate();
                }
            }
            if (arac instanceof Motosiklet) {
                Motosiklet moto = (Motosiklet) arac;
                String ekSql = "INSERT INTO motosiklet (id, km, motor_hacmi) VALUES (?, ?, ?)";
                try (PreparedStatement ekStmt = conn.prepareStatement(ekSql)) {
                    ekStmt.setInt(1, aracId);
                    ekStmt.setInt(2, moto.getKm());
                    ekStmt.setInt(3, moto.getMotorHacmi());
                    ekStmt.executeUpdate();
                }
            }
            if (arac instanceof DenizAraci) {
                DenizAraci den = (DenizAraci) arac;
                String ekSql = "INSERT INTO denizAraci (id, tip) VALUES (?, ?)";
                try (PreparedStatement ekStmt = conn.prepareStatement(ekSql)) {
                    ekStmt.setInt(1, aracId);
                    ekStmt.setString(2, den.getTip());
                    ekStmt.executeUpdate();
                }
            }
            if (arac instanceof HavaAraci) {
                HavaAraci hava = (HavaAraci) arac;
                String ekSql = "INSERT INTO havaAraci (id, tip, motor_Sayisi) VALUES (?, ?, ?)";
                try (PreparedStatement ekStmt = conn.prepareStatement(ekSql)) {
                    ekStmt.setInt(1, aracId);
                    ekStmt.setString(2, hava.getTip());
                    ekStmt.setInt(3, hava.getMotorSayisi());
                    ekStmt.executeUpdate();
                }
            }

            System.out.println("Araç başarıyla eklendi.");
            return aracId;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static ArrayList<Arac> aracAra(Integer id, String marka, String model, Integer uretimYili, Double fiyat, String tur) {
        ArrayList<Arac> araclar = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = baglan();

            if (id != null) {
                ps = conn.prepareStatement("SELECT * FROM araclar WHERE id = ?");
                ps.setInt(1, id);
            } else {
                int kriterSayisi = 0;
                StringBuilder query = new StringBuilder("SELECT * FROM araclar WHERE 1=1");

                if (marka != null && !marka.isEmpty()) {
                    query.append(" AND marka = ?");
                    kriterSayisi++;
                }
                if (model != null && !model.isEmpty()) {
                    query.append(" AND model = ?");
                    kriterSayisi++;
                }
                if (uretimYili != null) {
                    query.append(" AND uretim_yili = ?");
                    kriterSayisi++;
                }
                if (fiyat != null) {
                    query.append(" AND fiyat = ?");
                    kriterSayisi++;
                }
                if (tur != null && !tur.isEmpty()) {
                    query.append(" AND arac_turu = ?");
                    kriterSayisi++;
                }

                if (kriterSayisi < 2) {
                    return araclar;
                }

                ps = conn.prepareStatement(query.toString());

                int paramIndex = 1;
                if (marka != null && !marka.isEmpty()) {
                    ps.setString(paramIndex++, marka);
                }
                if (model != null && !model.isEmpty()) {
                    ps.setString(paramIndex++, model);
                }
                if (uretimYili != null) {
                    ps.setInt(paramIndex++, uretimYili);
                }
                if (fiyat != null) {
                    ps.setDouble(paramIndex++, fiyat);
                }
                if (tur != null && !tur.isEmpty()) {
                    ps.setString(paramIndex++, tur);
                }
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                int aracId = rs.getInt("id");
                int yil = rs.getInt("uretim_yili");
                String m = rs.getString("marka");
                String mo = rs.getString("model");
                double f = rs.getDouble("fiyat");
                String t = rs.getString("arac_turu");

                Arac arac = null;

                if (t.equals("Otomobil")) {
                    int km = 0, motorGucu = 0;
                    ps = conn.prepareStatement("SELECT km, motor_gucu FROM otomobil WHERE id = ?");
                    ps.setInt(1, aracId);
                    ResultSet rsOtomobil = ps.executeQuery();
                    if (rsOtomobil.next()) {
                        km = rsOtomobil.getInt("km");
                        motorGucu = rsOtomobil.getInt("motor_gucu");
                    }
                    arac = new Otomobil(yil, m, mo, f, km, motorGucu);
                    arac.setId(aracId);
                } else if (t.equals("Ticari")) {
                    int km = 0, motorGucu = 0, yukKapasitesi = 0;
                    ps = conn.prepareStatement("SELECT km, motor_gucu, yuk_kapasitesi FROM ticari WHERE id = ?");
                    ps.setInt(1, aracId);
                    ResultSet rsTicari = ps.executeQuery();
                    if (rsTicari.next()) {
                        km = rsTicari.getInt("km");
                        motorGucu = rsTicari.getInt("motor_gucu");
                        yukKapasitesi = rsTicari.getInt("yuk_kapasitesi");
                    }
                    arac = new Ticari(yil, m, mo, f, km, motorGucu, yukKapasitesi);
                    arac.setId(aracId);
                } else if (t.equals("Motosiklet")) {
                    int km = 0, motorHacmi = 0;
                    ps = conn.prepareStatement("SELECT km, motor_hacmi FROM motosiklet WHERE id = ?");
                    ps.setInt(1, aracId);
                    ResultSet rsMotosiklet = ps.executeQuery();
                    if (rsMotosiklet.next()) {
                        km = rsMotosiklet.getInt("km");
                        motorHacmi = rsMotosiklet.getInt("motor_hacmi");
                    }
                    arac = new Motosiklet(yil, m, mo, f, km, motorHacmi);
                    arac.setId(aracId);
                } else if (t.equals("Deniz Aracı")) {
                    String tip = "";
                    ps = conn.prepareStatement("SELECT tip FROM denizaraci WHERE id = ?");
                    ps.setInt(1, aracId);
                    ResultSet rsDenizAraci = ps.executeQuery();
                    if (rsDenizAraci.next()) {
                        tip = rsDenizAraci.getString("tip");
                    }
                    arac = new DenizAraci(yil, m, mo, f, tip);
                    arac.setId(aracId);
                } else if (t.equals("Hava Aracı")) {
                    String tip = "";
                    int motorSayisi = 0;
                    ps = conn.prepareStatement("SELECT tip, motor_sayisi FROM havaaraci WHERE id = ?");
                    ps.setInt(1, aracId);
                    ResultSet rsHavaAraci = ps.executeQuery();
                    if (rsHavaAraci.next()) {
                        tip = rsHavaAraci.getString("tip");
                        motorSayisi = rsHavaAraci.getInt("motor_sayisi");
                    }
                    arac = new HavaAraci(yil, m, mo, f, tip, motorSayisi);
                    arac.setId(aracId);
                }

                if (arac != null) {
                    araclar.add(arac);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return araclar;
    }

    public static Arac aracGetir(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = baglan();

            // İlk olarak araclar tablosundan temel bilgileri çekiyoruz
            ps = conn.prepareStatement("SELECT * FROM araclar WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.next()) {
                return null; // Araç bulunamadıysa null döndür
            }

            int yil = rs.getInt("uretim_yili");
            String marka = rs.getString("marka");
            String model = rs.getString("model");
            double fiyat = rs.getDouble("fiyat");
            String tur = rs.getString("arac_turu");

            Arac arac = null;

            if (tur.equals("Otomobil")) {
                ps = conn.prepareStatement("SELECT km, motor_gucu FROM otomobil WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rsDetay = ps.executeQuery();
                if (rsDetay.next()) {
                    int km = rsDetay.getInt("km");
                    int motorGucu = rsDetay.getInt("motor_gucu");
                    arac = new Otomobil(yil, marka, model, fiyat, km, motorGucu);
                }
            } else if (tur.equals("Ticari")) {
                ps = conn.prepareStatement("SELECT km, motor_gucu, yuk_kapasitesi FROM ticari WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rsDetay = ps.executeQuery();
                if (rsDetay.next()) {
                    int km = rsDetay.getInt("km");
                    int motorGucu = rsDetay.getInt("motor_gucu");
                    int yukKapasitesi = rsDetay.getInt("yuk_kapasitesi");
                    arac = new Ticari(yil, marka, model, fiyat, km, motorGucu, yukKapasitesi);
                }
            } else if (tur.equals("Motosiklet")) {
                ps = conn.prepareStatement("SELECT km, motor_hacmi FROM motosiklet WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rsDetay = ps.executeQuery();
                if (rsDetay.next()) {
                    int km = rsDetay.getInt("km");
                    int motorHacmi = rsDetay.getInt("motor_hacmi");
                    arac = new Motosiklet(yil, marka, model, fiyat, km, motorHacmi);
                }
            } else if (tur.equals("Deniz Aracı")) {
                ps = conn.prepareStatement("SELECT tip FROM denizaraci WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rsDetay = ps.executeQuery();
                if (rsDetay.next()) {
                    String tip = rsDetay.getString("tip");
                    arac = new DenizAraci(yil, marka, model, fiyat, tip);
                }
            } else if (tur.equals("Hava Aracı")) {
                ps = conn.prepareStatement("SELECT tip, motor_sayisi FROM havaaraci WHERE id = ?");
                ps.setInt(1, id);
                ResultSet rsDetay = ps.executeQuery();
                if (rsDetay.next()) {
                    String tip = rsDetay.getString("tip");
                    int motorSayisi = rsDetay.getInt("motor_sayisi");
                    arac = new HavaAraci(yil, marka, model, fiyat, tip, motorSayisi);
                }
            }

            if (arac != null) {
                arac.setId(id);
            }

            return arac;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    public static boolean aracSil(int id) {
        String sql = "DELETE FROM araclar WHERE id = ?";

        try (Connection conn = baglan();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int etkilenenSatir = ps.executeUpdate();

            if (etkilenenSatir > 0) {
                System.out.println("Araç başarıyla silindi.");
                return true;
            } else {
                System.out.println("Araç bulunamadı.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}