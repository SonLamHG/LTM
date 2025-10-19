import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ghbpfcO2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2206), 5000);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));

        writer.write("B22DCCN477;ghbpfcO2\n");
        writer.flush();

        byte[] buff = new byte[1024];
        int cnt = in.read(buff);
        String res = new String(buff,0,cnt,StandardCharsets.UTF_8);

        long sum=0;
        for(String s: res.split("\\|")){
            sum += Long.parseLong(s);
        }

        writer.write(sum + "\n");
        writer.flush();

        socket.close();
    }
}
