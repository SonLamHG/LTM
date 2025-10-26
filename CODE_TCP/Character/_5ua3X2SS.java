package CODE_TCP.Character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _5ua3X2SS {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("203.162.10.109", 2208));

        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write("B22DCCN477;5ua3X2SS\n");
        out.flush();

        String res = in.readLine();
        System.out.println(res);

        List<String> list = new ArrayList<>(); 
        Set<Character> st = new HashSet<>();
        for(char c: res.toCharArray()){
            if(Character.isAlphabetic(c)){
                if(!st.contains(c)){
                    list.add(c+"");
                    st.add(c);
                }
            }
        }

        String ans = String.join("", list);
        out.write(ans);
        out.flush();

        socket.close();
    }
}
