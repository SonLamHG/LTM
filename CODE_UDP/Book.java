package CODE_UDP;

import UDP.Book;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Khởi tạo socket UDP
            socket = new DatagramSocket();
            socket.setSoTimeout(5000); // Thiết lập timeout 5 giây

            // Địa chỉ server
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2209;

            // a. Gửi chuỗi định dạng ";studentCode;qCode"
            String studentCode = "B22DCCN477";
            String qCode = "SQ0E32fP";
            String message = ";" + studentCode + ";" + qCode;
            byte[] sendData = message.getBytes("UTF-8");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("Sent: " + message);

            // b. Nhận thông điệp từ server (8 byte requestId + đối tượng Book)
            byte[] receiveData = new byte[1024]; // Buffer đủ lớn
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            // Tách 8 byte đầu (requestId)
            byte[] requestIdBytes = Arrays.copyOfRange(receiveData, 0, 8);
            String requestId = new String(requestIdBytes, "UTF-8").trim();
            System.out.println("Received requestId: " + requestId);

            // Tách phần còn lại để deserialize đối tượng Book
            byte[] bookData = Arrays.copyOfRange(receiveData, 8, receivePacket.getLength());
            try (ByteArrayInputStream bais = new ByteArrayInputStream(bookData);
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                Book book = (Book) ois.readObject();
                System.out.println("Received Book: " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getIsbn() + ", " + book.getPublishDate());

                // c. Chuẩn hóa đối tượng Book
                // Chuẩn hóa title: viết hoa chữ cái đầu của mỗi từ
                book.setTitle(capitalizeEachWord(book.getTitle()));

                // Chuẩn hóa author: "HỌ, Tên"
                book.setAuthor(normalizeAuthor(book.getAuthor()));

                // Chuẩn hóa ISBN: "978-3-16-148410-0"
                book.setIsbn(normalizeIsbn(book.getIsbn()));

                // Chuẩn hóa publishDate: từ yyyy-mm-dd sang mm/yyyy
                book.setPublishDate(convertDateFormat(book.getPublishDate()));

                System.out.println("Normalized Book: " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getIsbn() + ", " + book.getPublishDate());

                // d. Gửi lại requestId + đối tượng Book đã chuẩn hóa
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(book);
                oos.flush();

                // Tạo buffer: 8 byte requestId + dữ liệu Book
                byte[] bookBytes = baos.toByteArray();
                ByteBuffer buffer = ByteBuffer.allocate(8 + bookBytes.length);
                buffer.put(requestIdBytes); // 8 byte requestId
                buffer.put(bookBytes); // Dữ liệu Book
                sendData = buffer.array();

                sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);
                System.out.println("Sent normalized Book with requestId: " + requestId);
            }

        } catch (SocketTimeoutException e) {
            System.err.println("Timeout waiting for server response: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                System.out.println("Socket closed");
            }
        }
    }

    // Chuẩn hóa title: viết hoa chữ cái đầu mỗi từ
    private static String capitalizeEachWord(String title) {
        if (title == null || title.isEmpty()) return title;
        String[] words = title.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        return result.toString().trim();
    }

    // Chuẩn hóa author: "HỌ, Tên"
    private static String normalizeAuthor(String author) {
        var words = author.split(" ");
        String ho = words[0].trim();
        String ten = String.join(" ", Arrays.copyOfRange(words, 1, words.length)).trim();

        return ho.toUpperCase() + ", " + capitalizeEachWord(ten); 
    }

    // Chuẩn hóa ISBN: "978-3-16-148410-0"
    private static String normalizeIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) return "978-3-16-148410-0";
        // Loại bỏ ký tự không phải số
        String digits = isbn.replaceAll("[^0-9]", "");
        if (digits.length() >= 13) {
            return String.format("%s-%s-%s-%s-%s",
                    digits.substring(0, 3),  // 978
                    digits.substring(3, 4),  // 3
                    digits.substring(4, 6),  // 16
                    digits.substring(6, 12), // 148410
                    digits.substring(12, 13)); // 0
        }
        return "978-3-16-148410-0"; // Trả về mặc định nếu ISBN không hợp lệ
    }

    // Chuyển đổi publishDate: từ yyyy-mm-dd sang mm/yyyy
    private static String convertDateFormat(String date) {
        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return date; // Trả về nguyên bản nếu định dạng không hợp lệ
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM/yyyy");
            Date parsedDate = inputFormat.parse(date);
            return outputFormat.format(parsedDate);
        } catch (Exception e) {
            return date; // Trả về nguyên bản nếu lỗi
        }
    }
}