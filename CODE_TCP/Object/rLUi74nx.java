package CODE_TCP.Object;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import TCP.Address;

public class rLUi74nx {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109",2209),5000);
        
        var in = new ObjectInputStream(socket.getInputStream());
        var out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject("B22DCCN477;rLUi74nx");
        out.flush();

        Address address = (Address) in.readObject();

        StringBuilder sb = new StringBuilder();
        for(String s: address.getAddressLine().split(" ")){
            sb.append(Character.toUpperCase(s.charAt(0)));
            for(int i=1; i<s.length(); i++){
                if(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))){
                    sb.append(Character.toLowerCase(s.charAt(i)));
                }
            }
            sb.append(" ");
        }
        address.setAddressLine(sb.toString());
        sb.setLength(0);

        for(char c: address.getPostalCode().toCharArray()){
            if(Character.isDigit(c) || c=='-'){
                sb.append(c);
            }
        }
        address.setPostalCode(sb.toString());

        out.writeObject(address);
        out.flush();

        socket.close();
    }
}
