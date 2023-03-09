package exercise.connections;


import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection connection;
    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getNameState(){
        return "disconnected";
    }

    @Override
    public void connect() {
       this.connection.setState(new Connected(this.connection));
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
