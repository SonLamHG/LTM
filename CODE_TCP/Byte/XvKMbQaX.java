package CODE_TCP.Byte;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class XvKMbQaX {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2206), 5000);

        var in = socket.getInputStream();
        var out = socket.getOutputStream();
        
        out.write("B22DCCN477;XvKMbQaX".getBytes());
        out.flush();

        byte[] buff = new byte[1024];
        int cnt = in.read(buff);
        String res = new String(buff,0,cnt,StandardCharsets.UTF_8);

        int sum=0;
        for(String s: res.split(",")){
            int x = Integer.parseInt(s);
            if(snt(x)==1){
                sum+=x;
            }
        }

        out.write(String.valueOf(sum).getBytes());
        out.flush();

        System.out.println(sum);

        socket.close();
    }

    public static int snt(int n){
        if(n<2) return 0;
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i==0){
                return 0;
            }
        }
        return 1;
    }
}
