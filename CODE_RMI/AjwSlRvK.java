package CODE_RMI;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import RMI.DataService;

public class AjwSlRvK {
    public static void main(String[] args) {
        try {
            // Thông tin sinh viên và mã câu hỏi
            String studentCode = "B22DCCN477";  // <-- điền MSSV của bạn
            String qCode = "AjwSlRvK";

            // Kết nối đến RMI server
            String url = "rmi://203.162.10.109/RMIDataService"; 
            DataService service = (DataService) Naming.lookup(url);
            System.out.println("✅ Đã kết nối tới server RMIDataService.");

            // a. Nhận dữ liệu từ server
            Object data = service.requestData(studentCode, qCode);
            String inputStr = data.toString();
            System.out.println("📥 Chuỗi nhận từ server: " + inputStr);

            // Chuyển chuỗi thành mảng số nguyên
            List<Integer> list = new ArrayList<>();
            for(char c: inputStr.toCharArray()){
                if(Character.isDigit(c)){
                    list.add(c-'0');
                }
            }

            int[] arr = new int[list.size()];
            for(int i=0; i<list.size(); i++) arr[i] = list.get(i);

            // b. Tìm tổ hợp kế tiếp theo thứ tự từ điển
            nextPermutation(arr);

            // Chuyển lại thành chuỗi kết quả
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
                if (i < arr.length - 1) sb.append(",");
            }
            String result = sb.toString();

            System.out.println("📤 Tổ hợp kế tiếp: " + result);

            // c. Gửi kết quả trở lại server
            service.submitData(studentCode, qCode, result);
            System.out.println("✅ Đã gửi kết quả về server.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm sinh tổ hợp kế tiếp (next permutation)
    private static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
