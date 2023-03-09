package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private  TcpConnection connection;
    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getNameState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already connection");
    }

    @Override
    public void disconnect() {
        this.connection.setState(new Disconnected(this.connection));
    }

    @Override
    public void write(String text) {
    }
}
// END
