package lt.bit.zmones;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ZmogusRepo {

    private static List<Zmogus> zmones = null;
    private static List<Zmogus> zmonesFiltered = null;

    public static void loadZmonesFromDB(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        try (ResultSet rs = stmt.executeQuery("select * from Zmones");) {
            if (stmt != null) {
                zmones = new ArrayList<>();
                while (rs.next()) {
                    Zmogus zmogus = new Zmogus(rs.getInt("id"), rs.getString("vardas"),
                            rs.getString("pavarde"), rs.getDate("gimimo_data"),
                            rs.getBigDecimal("alga"));
                    zmones.add(zmogus);
                }
                rs.close();
            } else {
                System.out.println("No connection to DB");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static List<Zmogus> getZmones() throws SQLException {
        if (zmones == null) {
            loadZmonesFromDB(Db.connectDb());
            return zmones;
        }
        return zmones;
    }

    public static void addZmogus(Zmogus zmogus){
        if (zmogus != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO Zmones (`vardas`,`pavarde`,`gimimo_data`,`alga`) VALUES (?,?,?,?);")) {
                preparedStatement.setString(1, zmogus.getVardas());
                preparedStatement.setString(2, zmogus.getPavarde());
                if (zmogus.getGimimoData() != null) {
                    preparedStatement.setDate(3, new Date(zmogus.getGimimoData().getTime()));
                } else {
                    preparedStatement.setNull(3, Types.DATE);
                }
                if (zmogus.getAlga() != null) {
                    preparedStatement.setDouble(4, zmogus.getAlga().doubleValue());
                } else {
                    preparedStatement.setNull(4, Types.VARCHAR);
                }
                int row = preparedStatement.executeUpdate();
                if (row != 0) {
                    loadZmonesFromDB(Db.connectDb());
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void deleteZmogus(Zmogus zmogus){
        if (zmogus != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Zmones WHERE id=?;")) {
                preparedStatement.setInt(1, zmogus.getId());
                int row = preparedStatement.executeUpdate();
                if (row != 0) {
                    loadZmonesFromDB(Db.connectDb());
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void updateZmogus(Zmogus zmogus) {
        if (zmogus != null) {
            Connection con = Db.getCon();
            try (PreparedStatement preparedStatement = con.prepareStatement(
                    "UPDATE Zmones SET `vardas`=?, `pavarde`=?, `gimimo_data`=?, `alga`=? WHERE `id`= ?;")) {
                preparedStatement.setInt(5, zmogus.getId());
                preparedStatement.setString(1, zmogus.getVardas());
                preparedStatement.setString(2, zmogus.getPavarde());
                if (zmogus.getGimimoData() != null) {
                    preparedStatement.setDate(3, new Date(zmogus.getGimimoData().getTime()));
                } else {
                    preparedStatement.setNull(3, Types.DATE);
                }
                if (zmogus.getAlga() != null) {
                    preparedStatement.setDouble(4, zmogus.getAlga().doubleValue());
                } else {
                    preparedStatement.setNull(4, Types.VARCHAR);
                }
                int row = preparedStatement.executeUpdate();
                if (row != 0) {
                    loadZmonesFromDB(Db.connectDb());
                }
            } catch (SQLException e){
                System.out.println("ZmogusRepo.updateZmogus "+e.getMessage());
            }
        }
    }

    public static Zmogus getById(int id) {
        Zmogus zmogus = null;
        Connection con = Db.getCon();
        if (con != null) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Zmones WHERE id=?")) {
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                zmogus = new Zmogus(rs.getInt("id"), rs.getString("vardas"),
                        rs.getString("pavarde"), rs.getDate("gimimo_data"),
                        rs.getBigDecimal("alga"));
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return zmogus;
    }


    public static List<Zmogus> filteredZmones(String vardas, String pavarde) {
        Zmogus zmogusRes = null;
        Connection con = Db.getCon();
        if (con != null) {
            try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Zmones WHERE `vardas` LIKE ? and `pavarde` LIKE ?;")) {
                preparedStatement.setString(1, vardas.trim().replace('*','%'));
                preparedStatement.setString(2, pavarde.trim().replace('*','%'));
                ResultSet rs = preparedStatement.executeQuery();
                zmonesFiltered = new ArrayList<>();
                while (rs.next()) {
                    zmogusRes = new Zmogus(rs.getInt("id"), rs.getString("vardas"),
                            rs.getString("pavarde"), rs.getDate("gimimo_data"),
                            rs.getBigDecimal("alga"));
                    zmonesFiltered.add(zmogusRes);
                }
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return zmonesFiltered;
    }

    public static List<Zmogus> getListSorted(String sortBy, String order) throws SQLException {
        List<Zmogus> listSorted =  new ArrayList<>();
        for (Zmogus zmogus: getZmones()) {
            listSorted.add(zmogus);
        }

        if ("asc".equals(order)){
            if (getComparatorAsc(sortBy) != null) {
                Collections.sort(listSorted, getComparatorAsc(sortBy));
            }
            return listSorted;
        } else if ("desc".equals(order)){
            if (getComparatorDesc(sortBy) != null) {
                Collections.sort(listSorted, getComparatorDesc(sortBy));
            }
            return listSorted;
        }
        return listSorted;
    }

    private static Comparator<Zmogus> getComparatorAsc(String sortBy){
        Comparator<Zmogus> comparator = null;
        switch (sortBy){
            case "vardas":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o1.getVardas().compareTo(o2.getVardas());
                    }
                };
                break;
            case "pavarde":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o1.getPavarde().compareTo(o2.getPavarde());
                    }
                };
                break;
            case "gdata":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o1.getGimimoData().compareTo(o2.getGimimoData());
                    }
                };
                break;
            case "alga":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o1.getAlga().compareTo(o2.getAlga());
                    }
                };
                break;
        }
        return  comparator;
    }

    private static Comparator<Zmogus> getComparatorDesc(String sortBy){
        Comparator<Zmogus> comparator = null;
        switch (sortBy){
            case "vardas":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o2.getVardas().compareTo(o1.getVardas());
                    }
                };
                break;
            case "pavarde":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o2.getPavarde().compareTo(o1.getPavarde());
                    }
                };
                break;
            case "gdata":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o2.getGimimoData().compareTo(o1.getGimimoData());
                    }
                };
                break;
            case "alga":
                comparator = new Comparator<Zmogus>() {
                    @Override
                    public int compare(Zmogus o1, Zmogus o2) {
                        return o2.getAlga().compareTo(o1.getAlga());
                    }
                };
                break;
        }
        return  comparator;
    }


}
