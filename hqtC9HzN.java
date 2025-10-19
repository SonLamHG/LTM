import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class hqtC9HzN {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109",2207),5000);

        var in = new DataInputStream(socket.getInputStream());
        var out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF("B22DCCN477;hqtC9HzN");
        out.flush();

        int num = in.readInt();
        
        out.writeUTF(Integer.toString(num, 8) + ";" + Integer.toString(num, 16).toUpperCase());
        out.flush();

        socket.close();
    }
}
