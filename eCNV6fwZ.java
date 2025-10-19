import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class eCNV6fwZ {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2208), 5000);

        var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        var reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        writer.write("B22DCCN477;eCNV6fwZ\n");
        writer.flush();

        String res = reader.readLine();
        
        List<String> list = new ArrayList<>();
        for(String s: res.split(", ")){
            if(s.endsWith(".edu")){
                list.add(s);
            }
        }

        writer.write(String.join(", ",list));
        writer.flush();

        System.out.println(res);
        System.out.println(String.join(", ",list));

        socket.close();
    }
}
