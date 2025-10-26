package CODE_TCP.Object;

import TCP.Laptop;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class osPP53CA {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109",2209),5000);
        
        var in = new ObjectInputStream(socket.getInputStream());
        var out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject("B22DCCN477;osPP53CA");
        out.flush();

        Laptop laptop = (Laptop) in.readObject();

        List<String> list = new ArrayList<>();
        for(String s: laptop.getName().split(" ")){
            list.add(s);
        }

        var tmp = list.get(0);
        list.set(0, list.getLast());
        list.set(list.size()-1, tmp);
        
        laptop.setName(String.join(" ",list));
        laptop.setQuantity(swap(laptop.getQuantity()));

        out.writeObject(laptop);
        out.flush();

        socket.close();
    }

    public static int swap(int x){
        String s = String.valueOf(x);
        int num=0;
        for(int i=s.length()-1; i>=0; i--){
            num = num*10 + (s.charAt(i)-'0');
        }
        return num;
    }
}
