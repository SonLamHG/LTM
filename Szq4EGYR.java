import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Szq4EGYR {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109",2208));

        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write("B22DCCN477;Szq4EGYR\n");
        out.flush();

        String res = in.readLine();
        System.out.println(res);

       StringBuilder sb = new StringBuilder();
        for(String s: res.split(" ")){
            for(int i=s.length()-1; i>=0; i--){
                int cnt=0, j=i;
                while(j>=0 && s.charAt(i)==s.charAt(j)){
                    cnt++;
                    j--;
                }
                sb.append(String.valueOf(s.charAt(i)));
                if(cnt>1) sb.append(String.valueOf(cnt));
                i=j+1;
            }
            sb.append(" ");
        }

        out.write(sb.toString() + "\n");
        out.flush();

        System.out.println(sb.toString());

        socket.close();
    }
}
