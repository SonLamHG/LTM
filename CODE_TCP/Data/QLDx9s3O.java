package CODE_TCP.Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class QLDx9s3O {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2207));

        var in = new DataInputStream(socket.getInputStream());
        var out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF("B22DCCN477;QLDx9s3O");
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
