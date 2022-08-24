package jdbcexample;

import jdbcexample.manager.UserAndEvantManager;
import jdbcexample.model.Event;
import jdbcexample.model.EventType;
import jdbcexample.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main implements Commands{
    private static Scanner scanner = new Scanner(System.in);
    private static final UserAndEvantManager userAndEvantManager = new UserAndEvantManager();
    public static void main(String[] args) throws SQLException {
        boolean run = true;
        while (run){
            Commands.printCommand();
            int command;
            try {
                command = Integer.parseInt(scanner.next());
            }catch (NumberFormatException e){
                command = -1;
            }
            switch (command){
                case EXIT:
                    run = false;
                    break;
                case ADD_EVENT:
                    addEvent();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case SHOW_EVENTS:
                    printEvent();
                    break;
                case SHOW_USERS:
                    printUser();
                    break;
                default:
                    System.out.println("Invalid value");
                    break;
            }
        }
    }

    private static void printUser() throws SQLException {
        List<User> allUsers = userAndEvantManager.getAllUsers();
        for (User allUser : allUsers) {
            System.out.println(allUser);
        }
    }

    private static void printEvent() throws SQLException {
        List<Event> allEvents = userAndEvantManager.getAllEvents();
        for (Event allEvent : allEvents) {
            System.out.println(allEvent);
        }
    }

    private static void addUser() throws SQLException {
        System.out.println("Input user name");
        String userName = scanner.next();
        System.out.println("Input user surname");
        String userSurname = scanner.next();
        System.out.println("input user email");
        String userEmail = scanner.next();
        System.out.println("Input event id");
        int eventId = scanner.nextInt();

        User user = new User(userName, userSurname, userEmail, eventId);
        userAndEvantManager.addUser(user);
    }

    private static void addEvent() throws SQLException {
        System.out.println("Input event name");
        String eventName = scanner.next();
        System.out.println("Input event place");
        String eventPlace = scanner.next();
        System.out.println("Input isOnline - true or false");
        boolean isOnline = scanner.nextBoolean();
        System.out.println("Input event price");
        double eventPrice = scanner.nextDouble();
        EventType eventType = EventType.valueOf(eventType());

        Event event = new Event(eventName, eventPlace, isOnline, eventPrice, eventType);
        userAndEvantManager.addEvent(event);

    }

    private static String eventType(){
        System.out.println("Chose event type 1 or 2");
        String eventTy = null;
        try {
            String event = scanner.next();
            eventTy = String.valueOf(event.charAt(0));
            if(Integer.parseInt(eventTy) == 1){
                eventTy = String.valueOf(EventType.HAPPYBIRTHDAY);
            } else if (Integer.parseInt(eventTy) == 2) {
                eventTy = String.valueOf(EventType.NEWYEAR);
            }else {
                System.out.println("Input only 1 or 2");
            }
        }catch (NumberFormatException e){
            System.out.println("Input only number");
        }
        return eventTy;

    }
}
