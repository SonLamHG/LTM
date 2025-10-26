package CODE_RMI;

import RMI.ObjectService;
import RMI.ProductX;
import java.rmi.Naming;

public class E9ymV68i {
    public static void main(String[] args) {
        try {
            // 1. Kết nối RMI Server
            String url = "rmi://203.162.10.109/RMIObjectService"; // IP hoặc localhost
            ObjectService service = (ObjectService) Naming.lookup(url);

            String studentCode = "B22DCCN477";
            String qAlias = "E9ymV68i";

            // 2. Nhận đối tượng ProductX từ server
            ProductX product = (ProductX) service.requestObject(studentCode, qAlias);
            System.out.println("📦 Product nhận từ server: " + product);

            // 3. Tính tổng các chữ số trong discountCode
            int sum = 0;
            for (char c : product.discountCode.toCharArray()) {
                if (Character.isDigit(c)) sum += (c - '0');
            }
            product.discount = sum; // cập nhật discount
            System.out.println("💰 Discount sau khi tính: " + product.discount);

            // 4. Gửi đối tượng đã cập nhật lại server
            service.submitObject(studentCode, qAlias, product);
            System.out.println("✅ Đã gửi ProductX đã cập nhật về server.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
