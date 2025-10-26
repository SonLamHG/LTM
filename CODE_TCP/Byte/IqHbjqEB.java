package CODE_TCP.Byte;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IqHbjqEB {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2206));

        var in = socket.getInputStream();
        var out = socket.getOutputStream();

        out.write("B22DCCN477;IqHbjqEB".getBytes());
        out.flush();

        byte[] buff = new byte[1024];
        int cnt = in.read(buff);
        String res = new String(buff,0,cnt,StandardCharsets.UTF_8);
        int n = Integer.parseInt(res);

        List<String> list = new ArrayList<>();
        list.add(String.valueOf(n));
        while(n!=1){
            if(n%2==0){
                n/=2;
            }else{
                n=3*n+1;
            }
            list.add(String.valueOf(n));
        }

        String ans = String.join(" ", list) + "; " + String.valueOf(list.size());
        out.write(ans.getBytes());
        out.flush();
    }
}
