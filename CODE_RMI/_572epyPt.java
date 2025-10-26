package CODE_RMI;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import RMI.CharacterService;

public class _572epyPt {
    public static void main(String[] args) throws Exception {
        String url = "rmi://203.162.10.109/RMICharacterService";
        var service = (CharacterService) Naming.lookup(url);

        String res = service.requestCharacter("B22DCCN477", "572epyPt");
        System.out.println(res);

        Map<Character, Integer> map = new LinkedHashMap<>();
        for(char c: res.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        List<String> list = new ArrayList<>();
        for(var x : map.entrySet()){
            String s = "\"" + x.getKey()+"" + "\"" + ": " + String.valueOf(x.getValue());
            list.add(s);
        }

        String ans = "{" + String.join(", ", list) + "}";

        service.submitCharacter("B22DCCN477", "572epyPt", ans);
    }
}
