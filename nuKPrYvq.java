import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class nuKPrYvq {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109",2207),5000);

        var in = new DataInputStream(socket.getInputStream());
        var out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF("B22DCCN477;nuKPrYvq");
        out.flush();

        int a = in.readInt();
        int b = in.readInt();

        out.writeInt(a+b);
        out.flush();

        out.writeInt(a*b);
        out.flush();

        socket.close();
    }
}
