package CODE_UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class A195YFeO {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2208));

        String msg = ";B22DCCN477;A195YFeO";
        byte[] buff = msg.getBytes();
        socket.send(new DatagramPacket(buff, buff.length));

        buff = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buff, buff.length);
        socket.receive(packet);
        String res = new String(packet.getData());
        System.out.println(res);

        var s = res.split(";");
        String requestId = s[0];
        var num = s[1].split(",");
        long b1 = cal(num[0].trim());
        long b2 = cal(num[1].trim());
        System.out.println(b1);
        System.out.println(b2);

        msg = requestId + ";" + String.valueOf(b1+b2);
        buff = msg.getBytes();
        socket.send(new DatagramPacket(buff, buff.length));

        socket.close();
    }

    public static long cal(String s){
        System.out.println(s);
        long sum=0;
        for(int i=0; i<s.length(); i++){
            sum += (s.charAt(i)-'0') * Math.pow(2, s.length()-i-1);
        }
        return sum;
    }
}
