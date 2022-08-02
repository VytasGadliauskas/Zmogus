package lt.bit.zmones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresasRepo {
    private static List<Adresas> adresai = null;

    public static List<Adresas> getAdresai(int zmogaus_id) throws SQLException {
        Adresas adresas = null;
        adresai = new ArrayList<>();
        Connection con = Db.getCon();
        if (con != null) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Adresai WHERE `zmogaus_id`=?")) {
                preparedStatement.setInt(1, zmogaus_id);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    adresas = new Adresas(rs.getInt("id"), rs.getInt("zmogaus_id"),
                            rs.getString("adresas"), rs.getString("miestas"),
                            rs.getString("pasto_kodas"), rs.getString("valstybe"));
                    adresai.add(adresas);
                }
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return adresai;
    }

    public static void addAdresas(Adresas adresas) {
        if (adresas != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO Adresai(`zmogaus_id`,`adresas`,`miestas`,`pasto_kodas`,`valstybe`) VALUES (?,?,?,?,?);")) {
                preparedStatement.setInt(1, adresas.getZmogaus_id());
                preparedStatement.setString(2, adresas.getAdresas());
                preparedStatement.setString(3, adresas.getMiestas());
                preparedStatement.setString(4, adresas.getPastoKodas());
                preparedStatement.setString(5, adresas.getValstybe());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteAdresas(Adresas adresas) {
        if (adresas != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Adresai WHERE id=?;")) {
                preparedStatement.setInt(1, adresas.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void updateAdresas(Adresas adresas) {
        if (adresas != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement(
                    "UPDATE Adresai SET `zmogaus_id`=?, `adresas`=?, `miestas`=?, `pasto_kodas`=? , `valstybe`=? WHERE `id`= ?;")) {
                preparedStatement.setInt(6, adresas.getId());
                preparedStatement.setInt(1, adresas.getZmogaus_id());
                preparedStatement.setString(2, adresas.getAdresas());
                preparedStatement.setString(3, adresas.getMiestas());
                preparedStatement.setString(4, adresas.getPastoKodas());
                preparedStatement.setString(5, adresas.getValstybe());
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                System.out.println("AdresasRepo.updateAdresas "+e.getMessage());
            }
        }
    }

    public static Adresas getById(int id) {
        Adresas adresas = null;
        Connection con = Db.getCon();
        if (con != null) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Adresai WHERE id=?")) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                adresas = new Adresas(rs.getInt("id"), rs.getInt("zmogaus_id"),
                        rs.getString("adresas"), rs.getString("miestas"),
                        rs.getString("pasto_kodas"), rs.getString("valstybe"));
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return adresas;
    }

    public static List<Zmogus> getZmonesByAdresas(String valstybe, String miestas, String adresas, String pastokodas) {
        List<Zmogus> zmones = new ArrayList<>();
        Connection con = Db.getCon();
        if (con != null) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT zmogaus_id FROM Adresai " +
                    "WHERE `valstybe` LIKE ? and `miestas` LIKE ? and `adresas` LIKE ? and `pasto_kodas` LIKE ?")) {
                preparedStatement.setString(1, valstybe.trim().replace('*','%'));
                preparedStatement.setString(2, miestas.trim().replace('*','%'));
                preparedStatement.setString(3, adresas.trim().replace('*','%'));
                preparedStatement.setString(4, pastokodas.trim().replace('*','%'));
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
