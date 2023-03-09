package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection  {
    private Connection state;
    TcpConnection(String ip, int port) {
         this.state = new Disconnected(this);
    }
    public String getCurrentState() {
        return state.getNameState();
    }

    public void connect() {
        state.connect();
    }

    public void disconnect() {
        state.disconnect();
    }
    public void setState(Connection state) {
        this.state = state;
    }

    public void write(String text) {
        System.out.println("Error! You cannot send a message, because the connection is not established");
    }
}
// END
