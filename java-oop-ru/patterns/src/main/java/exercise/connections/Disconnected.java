package exercise.connections;


// BEGIN
public class Disconnected implements Connection {
    public Disconnected() {
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        new Connected();
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String text) {
        System.out.println("Error! You cannot send a message, because the connection is not established");
    }
}
// END
