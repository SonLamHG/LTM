package CODE_TCP.Character;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class h5YeAfwc {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109",2208),5000);
        
        var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        var in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        out.write("B22DCCN477;h5YeAfwc\n");
        out.flush();

        String res = in.readLine();
        System.out.println(res);

        String[] ss = res.split(" ");
        Arrays.sort(ss, (a,b) -> a.length() - b.length());

        out.write(String.join(", ", ss) + "\n");
        out.flush();

        System.out.println(String.join(" ", ss));
        socket.close();
    }
}
