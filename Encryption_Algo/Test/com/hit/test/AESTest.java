package com.hit.test;
import com.hit.algorithm.AES;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AESTest {
        private AES aes;

        @Before
        public void setup() {
            this.aes = new AES();
            assertNotNull(aes);
        }

        @Test //MATCHED KEYS
        public void matched_keys() {
            String message = "message";

            String encrypted = this.aes.encrypt(message);
            String decrypted = this.aes.decrypt(encrypted);

            assertEquals(message, decrypted);
        }

        @Test //UNMATCHED SECRET_KEY
        public void unmatched_SECRET_KEY() {
            AES other_aes = new AES();
            assertNotNull(other_aes);
            aes.setSECRET_KEY("other key");

            String message = "message";
            String encrypted = this.aes.encrypt(message);
            String decrypted = other_aes.decrypt(encrypted);

            assertNotEquals(message, decrypted);
        }

        @Test //UNMATCHED SALT
        public void unmatched_SALT() {
            AES other_aes = new AES();
            assertNotNull(other_aes);
            aes.setSALT("other key");

            String message = "message";
            String encrypted = this.aes.encrypt(message);
            String decrypted = other_aes.decrypt(encrypted);

            assertNotEquals(message, decrypted);
        }
    }