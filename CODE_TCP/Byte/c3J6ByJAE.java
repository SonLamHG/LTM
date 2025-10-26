package CODE_TCP.Byte;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class c3J6ByJAE {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109",2206), 5000);

        var in = socket.getInputStream();
        var out = socket.getOutputStream();

        out.write("B22DCCN477;3J6ByJAE\n".getBytes());
        out.flush();

        byte[] buff = new byte[1024];
        int cnt = in.read(buff);
        String res = new String(buff,0,cnt,StandardCharsets.UTF_8);

        String[] nums = res.split(",");
        float tb=0;
        for(String s: nums) tb += Integer.parseInt(s);
        tb /= nums.length;

        int a=0, b=0;
        float mn = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                int x = Integer.parseInt(nums[i]);
                int y = Integer.parseInt(nums[j]);
                if(Math.abs(x+y-2*tb)<mn){
                    mn = Math.abs(x+y-2*tb);
                    a=x;
                    b=y;
                }
            }
        }

        out.write((String.valueOf(Math.min(a, b)) + "," + String.valueOf(Math.max(a, b))).getBytes());
        out.flush();

        socket.close();
    }
}
