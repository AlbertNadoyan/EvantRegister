package jdbcexample.manager;

import jdbcexample.db.DBConnectionProvider;
import jdbcexample.model.Event;
import jdbcexample.model.EventType;
import jdbcexample.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserAndEvantManager {
    private Connection connection;

    public UserAndEvantManager(){
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into user(name, surname, email, event_id) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setInt(4, user.getEvent_id());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if(resultSet.next()){
            int id = resultSet.getInt(1);
            user.setId(id);
        }
    }

    public void addEvent(Event event) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into event(name, place, isOnline, price, event_type) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, event.getName());
        preparedStatement.setString(2, event.getPlace());
        preparedStatement.setBoolean(3, event.isOnline());
        preparedStatement.setDouble(4, event.getPrice());
        preparedStatement.setString(5, event.getEventType().name());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if(resultSet.next()){
            int id = resultSet.getInt(1);
            event.setId(id);
        }
    }

    public List<Event> getAllEvents() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from event");
        List<Event> events = new LinkedList<>();

        while (resultSet.next()){
            Event event = new Event();
            event.setId(resultSet.getInt("id"));
            event.setName(resultSet.getString("name"));
            event.setPlace(resultSet.getString("place"));
            event.setOnline(resultSet.getBoolean("isOnline"));
            event.setPrice(resultSet.getDouble("price"));
            event.setEventType(EventType.valueOf(resultSet.getString("event_type")));
            events.add(event);
        }
        return events;

    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        List<User> users = new LinkedList<>();

        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setEvent_id(resultSet.getInt("event_id"));
            users.add(user);
        }
        return users;

    }
}
