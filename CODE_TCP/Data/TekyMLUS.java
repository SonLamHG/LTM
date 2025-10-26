package CODE_TCP.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TekyMLUS {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109",2207));

        var in = new DataInputStream(socket.getInputStream());
        var out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF("B22DCCN477;TekyMLUS");
        out.flush();

        String res = in.readUTF();

        int[] nums = new int[1000];
        int n=0;
        for(String s: res.split(",")){
            nums[n++] = Integer.parseInt(s);
        }
        
        int delta = 0, cnt=0;
        for(int i=0; i+1<n; i++){
            delta += Math.abs(nums[i]-nums[i+1]);
            if(i-1>=0 && i+1<n){
                if(nums[i]>nums[i-1] == nums[i]>nums[i+1]) cnt++;
            }
        }

        out.writeInt(cnt);
        out.writeInt(delta);
        out.flush();

        socket.close();
    }
}
