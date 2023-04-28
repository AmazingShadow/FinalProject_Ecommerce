package com.example.demo.test;

import java.text.Normalizer;

public class Main {
    public static void main(String[] args) {
//        String fullName = "Nguyễn Thái Hoàng";
//        String[] nameParts = fullName.trim().split("\\s+"); // xóa khoảng trắng và tách các phần tên riêng
//        String lastName = nameParts[nameParts.length - 1]; // lấy phần tử cuối cùng trong mảng (tức là họ)
//        String result = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase(); // chuyển ký tự đầu tiên thành in hoa, các ký tự còn lại thành in thường

        String str = "Hoàng";
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD); // loại bỏ các dấu trên chữ cái
        String result = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); // loại bỏ các ký tự không mong muốn (ví dụ: khoảng trắng)

        System.out.println(result); // In ra Hoang


    }
}
