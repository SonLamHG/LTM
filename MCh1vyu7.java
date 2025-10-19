import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MCh1vyu7 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2206), 5000);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        writer.write("B22DCCN477;MCh1vyu7");
        writer.flush();

        byte[] buff = new byte[1024];
        int cntbyte = in.read(buff);
        String res = new String(buff, 0, cntbyte, StandardCharsets.UTF_8);

        long x=0,y=0,d=Long.MAX_VALUE,tb=0;

        List<Long> nums = new ArrayList<>();
        for(String s: res.split(",")) {
            long tmp = Long.parseLong(s);
            nums.add(tmp);
            tb += tmp;
        }

        int n=nums.size();
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(Math.abs(n*(nums.get(i) + nums.get(j)) - tb*2) < d){
                    d = Math.abs(n*(nums.get(i) + nums.get(j)) - tb*2);
                    x = nums.get(i);
                    y = nums.get(j);
                }
            }
        }

        if(x>y){
            long tmp=x;
            x=y;
            y=tmp;
        }
        writer.write(x+","+y+"\n");
        writer.flush();

        socket.close();
    }
}
