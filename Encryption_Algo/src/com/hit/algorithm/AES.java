package com.hit.algorithm;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

    public class AES implements IEncryptionAlgoFamily<String> {
        private String SECRET_KEY = "SECRET_KEY";
        private String SALT = "SALT";

        public String getSECRET_KEY() {
            return SECRET_KEY;
        }

        public void setSECRET_KEY(String SECRET_KEY) {
            this.SECRET_KEY = SECRET_KEY;
        }

        public String getSALT() {
            return SALT;
        }

        public void setSALT(String SALT) {
            this.SALT = SALT;
        }

        @Override
        public String encrypt(String data) {
            try {
                byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                IvParameterSpec ivspec = new IvParameterSpec(iv);

                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
                SecretKey tmp = factory.generateSecret(spec);
                SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
                return Base64.getEncoder()
                        .encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
            } catch (Exception e) {
                System.out.println("Error while encrypting: " + e.toString());
            }
            return null;
        }

        @Override
        public String decrypt(String data) {
            {
                try {
                    byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    IvParameterSpec ivspec = new IvParameterSpec(iv);

                    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                    KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
                    SecretKey tmp = factory.generateSecret(spec);
                    SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

                    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                    cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
                    return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
                } catch (Exception e) {
                    System.out.println("Error while decrypting: " + e.toString());
                }
                return null;
            }
        }

        public static void main(String[] args) {
            AES aes = new AES();

            String message = "Hello";
            String message2 = "World";

            String encrypt = aes.encrypt(message);

            System.out.println(aes.decrypt(encrypt ));


        }
    }