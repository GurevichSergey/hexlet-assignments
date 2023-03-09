package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection implements Connection {
    private Connection state;
    TcpConnection(String ip, int port) {
         this.state = new Disconnected();
    }

    @Override
    public String getCurrentState() {
        return state.getCurrentState();
    }

    @Override
    public void connect() {
        this.state = new Connected();
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void disconnect() {
        this.state = new Disconnected();
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String text) {
        System.out.println("Error! You cannot send a message, because the connection is not established");
    }
}
// END
