package exercise.connections;

public interface Connection {
    // BEGIN
    String getNameState();
    void connect();
    void disconnect();
    void write(String text);
    // END
}
