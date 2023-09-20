package src.dao;

import src.domain.User;

import java.sql.*;

public class UserDao {
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        UserDao dao = new UserDao();
        dao.reset();
        User user = new User();
        user.setName("권민혁");
        user.setId("pongyy");
        user.setPassword("toby");
        dao.add(user);
        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getName());
        System.out.println(user2.getId());
        System.out.println(user2.getPassword());
        System.out.println(user2.getName() + " 조회 성공");
    }
    public void add(User user)throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/toby", "root", "pong369369ey~");
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        c.close();
    }
    public User get(String name) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/toby", "root", "pong369369ey~");
        PreparedStatement ps = c.prepareStatement(
                "select * from users where name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
    public void reset() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/toby", "root", "pong369369ey~");
        PreparedStatement ps = c.prepareStatement(
                "truncate users");
        ps.executeUpdate();
        c.close();
    }
}
