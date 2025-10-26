package CODE_TCP.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class IC4B7yzr {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2207), 5000);

        var in = new DataInputStream(socket.getInputStream());
        var out = new DataOutputStream(socket.getOutputStream());

        out.writeUTF("B22DCCN477;IC4B7yzr");
        out.flush();

        int k = in.readInt();
        String res = in.readUTF();

        String[] nums = res.split(",");
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<nums.length; i+=k){
            for(int j=k-1+i; j>=i; j--){
                if(j<nums.length){
                    sb.append(nums[j]);
                    sb.append(",");
                }    
            }
        }

        if(sb.charAt(sb.length()-1) == ','){
            sb.deleteCharAt(sb.length()-1);
        }
        
        out.writeUTF(sb.toString());
        out.flush();

        System.out.println(res);
        System.out.println(sb.toString());

        socket.close();
    }
}
