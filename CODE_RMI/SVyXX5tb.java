package CODE_RMI;

import java.rmi.Naming;
import RMI.ByteService; // 🔥 import đúng package như server

public class SVyXX5tb {
    public static void main(String[] args) {
        try {
            String url = "rmi://203.162.10.109/RMIByteService";
            ByteService service = (ByteService) Naming.lookup(url);

            String studentCode = "B22DCCN477";
            String qCode = "SVyXX5tb";

            byte[] data = service.requestData(studentCode, qCode);
            System.out.println("Nhận dữ liệu: " + new String(data, "ASCII"));

            String key = "PTIT";
            byte[] keyBytes = key.getBytes("ASCII");

            byte[] encoded = new byte[data.length];
            for (int i = 0; i < data.length; i++) {
                encoded[i] = (byte) (data[i] ^ keyBytes[i % keyBytes.length]);
            }

            service.submitData(studentCode, qCode, encoded);
            System.out.println("✅ Đã gửi dữ liệu XOR thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
