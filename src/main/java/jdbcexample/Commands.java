package jdbcexample;

public interface Commands {
    final int EXIT = 0;
    final int ADD_EVENT = 1;
    final int ADD_USER = 2;
    final int SHOW_EVENTS = 3;
    final int SHOW_USERS = 4;

    static void printCommand(){
        System.out.println("Input " + EXIT + " for exit");
        System.out.println("Input " + ADD_EVENT + " for add event");
        System.out.println("Input " + ADD_USER + " for add user");
        System.out.println("Input " + SHOW_EVENTS + " for show event");
        System.out.println("Input " + SHOW_USERS + " for show user");
    }
}
