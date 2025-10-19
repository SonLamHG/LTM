import TCP.Product;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class fDZz3ywJ {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2209), 5000);

        var in = new ObjectInputStream(socket.getInputStream());
        var out = new ObjectOutputStream(socket.getOutputStream());

        out.writeObject("B22DCCN477;fDZz3ywJ");
        out.flush();

        Product product = (Product) in.readObject();
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getDiscount());

        String s = String.valueOf((int) product.getPrice());
        int sum=0;
        for(char c: s.toCharArray()) sum += c-'0';

        product.setDiscount(sum);

        out.writeObject(product);
        out.flush();

        socket.close();
    }
}
