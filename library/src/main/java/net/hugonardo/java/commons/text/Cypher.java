package net.hugonardo.java.commons.text;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cypher {

    private static final String MD5 = "MD5";

    private static final String SHA1 = "SHA-1";

    /**
     * Criptografa uma String através do algoritmo MD5.
     *
     * @param input Uma String de entrada para criptografar
     * @return O resultado da criptografia
     */
    public String toMd5(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            return encrypt(MessageDigest.getInstance(MD5), input);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return input;
        }
    }

    public String toSha1(String input) {
        try {
            return encrypt(MessageDigest.getInstance(SHA1), input);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return input;
        }
    }

    private String encrypt(MessageDigest algorithm, String input) throws UnsupportedEncodingException {
        return new String(hexCodes(algorithm.digest(input.getBytes("UTF-8"))));
    }

    private char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;
        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.getChars(hexString.length() - 2,
                    hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }
}