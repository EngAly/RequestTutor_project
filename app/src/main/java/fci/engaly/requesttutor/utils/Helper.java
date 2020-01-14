package fci.engaly.requesttutor.utils;

import java.security.MessageDigest;

public class Helper {

    public static String getHash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(text.trim().getBytes());
            final byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception e) {
            return "";
        }
    }
}
