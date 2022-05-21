package com.ironhack.BankingSystem.Model.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaHash {

        private static final Charset UTF_8 = StandardCharsets.UTF_8;
        private static final String OUTPUT_FORMAT = "%-20s:%s";

        public static byte[] digest(byte[] input, String algorithm) {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException(e);
            }
            byte[] result = md.digest(input);
            return result;
        }

        private static byte[] checksum(String filePath, String algorithm) {

            MessageDigest md;
            try {
                md = MessageDigest.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException(e);
            }

            try (InputStream is = new FileInputStream(filePath);
                 DigestInputStream dis = new DigestInputStream(is, md)) {
                while (dis.read() != -1) ; //empty loop to clear the data
                md = dis.getMessageDigest();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            return md.digest();

        }

        public static String bytesToHex(byte[] bytes) {
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }


    }

