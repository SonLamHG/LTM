package CODE_RMI;

import java.rmi.Naming;
import java.util.LinkedHashMap;
import java.util.Map;

public class RMIClient {
    public static void main(String[] args) {
        String studentCode = "B22DCCN477"; // Thay mã sinh viên của bạn
        String qCode = "572epyPt";         // Mã câu hỏi
        String serviceName = "rmi://localhost/RMICharacterService";

        try {
            // (a) Kết nối đến RMI Server và gọi requestCharacter
            CharacterService service = (CharacterService) Naming.lookup(serviceName);
            String inputString = service.requestCharacter(studentCode, qCode);
            System.out.println("Nhận được chuỗi từ server: " + inputString);

            // (b) Đếm tần suất ký tự trong chuỗi
            Map<Character, Integer> freq = new LinkedHashMap<>();
            for (char c : inputString.toCharArray()) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }

            // Tạo chuỗi kết quả
            StringBuilder resultBuilder = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                if (!first) resultBuilder.append(", ");
                resultBuilder.append("\"").append(entry.getKey()).append("\": ").append(entry.getValue());
                first = false;
            }
            resultBuilder.append("}");

            String resultString = resultBuilder.toString();
            System.out.println("Kết quả tần suất ký tự: " + resultString);

            // (c) Gửi kết quả trở lại server
            service.submitCharacter(studentCode, qCode, resultString);
            System.out.println("Đã gửi kết quả về server.");

            // (d) Kết thúc
            System.out.println("Hoàn thành chương trình client.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}