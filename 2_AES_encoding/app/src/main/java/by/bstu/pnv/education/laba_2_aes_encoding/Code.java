package by.bstu.pnv.education.laba_2_aes_encoding;

import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Dima on 15.02.2018.
 */

public class Code {
    private SecretKeySpec key;
    private byte[] code;

    public Code(SecretKeySpec key, byte[] code){
        this.key = key;
        this.code = code;
    }

    public byte[] getCode() {
        return code;
    }

    public SecretKeySpec getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.format(key + " " + code);
    }
}
