package lt.bit.zmones.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KontaktasRepo {
    private static List<Kontaktas> kontaktai = null;

    public static List<Kontaktas> getKontaktai(int zmogaus_id) throws SQLException {
        Kontaktas kontaktas = null;
        kontaktai = new ArrayList<>();
        Connection con = Db.getCon();
        if (con != null) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Kontaktai WHERE `zmogaus_id`=?")) {
                preparedStatement.setInt(1, zmogaus_id);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    kontaktas = new Kontaktas(rs.getInt("id"), rs.getInt("zmogaus_id"),
                            rs.getString("tipas"), rs.getString("reiksme"));
                    kontaktai.add(kontaktas);
                }
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return kontaktai;
    }

    public static void addKontaktas(Kontaktas kontaktas){
        if (kontaktas != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO Kontaktai(`zmogaus_id`,`tipas`,`reiksme`) VALUES (?,?,?);")) {
                preparedStatement.setInt(1, kontaktas.getZmogaus_id());
                preparedStatement.setString(2, kontaktas.getTipas());
                preparedStatement.setString(3, kontaktas.getReiksme());
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteKontaktas(Kontaktas kontaktas){
        if (kontaktas != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Kontaktai WHERE id=?;")) {
                preparedStatement.setInt(1, kontaktas.getId());
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void updateKontaktas(Kontaktas kontaktas) {
        if (kontaktas != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement(
                    "UPDATE Kontaktai SET `zmogaus_id`=?, `tipas`=?, `reiksme`=? WHERE `id`= ?;")) {
                preparedStatement.setInt(4, kontaktas.getId());
                preparedStatement.setInt(1, kontaktas.getZmogaus_id());
                preparedStatement.setString(2, kontaktas.getTipas());
                preparedStatement.setString(3, kontaktas.getReiksme());
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                System.out.println("KontaktasRepo.updateZmogus "+e.getMessage());
            }
        }
    }

    public static Kontaktas getById(int id) {
        Kontaktas kontaktas = null;
        Connection con = Db.getCon();
        if (con != null) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Kontaktai WHERE id=?")) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                kontaktas = new Kontaktas(rs.getInt("id"), rs.getInt("zmogaus_id"),
                        rs.getString("tipas"), rs.getString("reiksme"));
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return kontaktas;
    }

    public static List<Zmogus> getZmonesByKontaktas(String tipas, String reiksme) {
        List<Zmogus> zmones = new ArrayList<>();
        Connection con = Db.getCon();
        if (con != null) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT zmogaus_id FROM Kontaktai WHERE `tipas` LIKE ? and `reiksme` LIKE ?")) {
                preparedStatement.setString(1, tipas.trim().replace('*','%'));
                preparedStatement.setString(2, reiksme.trim().replace('*','%'));
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    Zmogus zmogus = ZmogusRepo.getById(rs.getInt("zmogaus_id"));
                    zmones.add(zmogus);
                }
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return zmones;
    }
}
