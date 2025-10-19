import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class mv4lleRB {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2206), 5000);

        DataOutputStream out =new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));

        writer.write("B22DCCN477;mv4lleRB\n");
        writer.flush();

        byte[] buff = new byte[1024];
        int cnt = in.read(buff);
        String res = new String(buff,0,cnt, StandardCharsets.UTF_8);

        System.out.println(res);

        long sum=0;
        for(String s: res.split(",")){
            long x = Long.parseLong(s);
            if(snt(x)==1) sum += x;
        }

        writer.write(sum + "\n");
        writer.flush();

        socket.close();
    }

    public static int snt(Long n){
        if(n<2) return 0;
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i==0) return 0;
        }
        return 1;
    }
}
