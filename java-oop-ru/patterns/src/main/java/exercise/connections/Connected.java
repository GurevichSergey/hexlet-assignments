package exercise.connections;

// BEGIN
public class Connected implements Connection {
    public Connected() {
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already connection");
    }

    @Override
    public void disconnect() {
        new Disconnected();
    }

    @Override
    public void write(String text) {

    }
}
// END
