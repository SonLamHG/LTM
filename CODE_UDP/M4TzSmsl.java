package CODE_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class M4TzSmsl {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2207));

        String msg = ";B22DCCN477;M4TzSmsl";
        byte[] buff = msg.getBytes();
        socket.send(new DatagramPacket(buff, buff.length));

        buff = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buff, buff.length);
        socket.receive(packet);
        String res = new String(packet.getData(), 0, packet.getLength());
        System.out.println(res);

        var s = res.split(";");
        String requestId = s[0];
        String a = s[1];
        int sum=0;
        for(char c: a.toCharArray()){
            sum += c-'0';
        }

        msg = requestId + ";" + String.valueOf(sum);
        buff = msg.getBytes();
        socket.send(new DatagramPacket(buff, buff.length));

        socket.close();
    }
}
