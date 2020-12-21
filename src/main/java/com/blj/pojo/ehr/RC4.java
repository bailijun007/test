package com.blj.pojo.ehr;

public class RC4 {

    public static String encoding = "utf-8";

    public static String encrypt(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return bytesToHex(encrypt_byte(data, key));
    }

    public static String decrypt(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        return new String(RC4Base(hexToBytes(data), key));
    }

    public static byte[] encrypt_byte(String data, String key) {
        try {
            if (data == null || key == null) {
                return null;
            }
            byte b_data[] = data.getBytes(encoding);
            return RC4Base(b_data, key);
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] initKey(String aKey) {
        try {
            byte[] b_key = aKey.getBytes(encoding);
            byte state[] = new byte[256];

            for (int i = 0; i < 256; i++) {
                state[i] = (byte) i;
            }
            int index1 = 0;
            int index2 = 0;
            if (b_key == null || b_key.length == 0) {
                return null;
            }
            for (int i = 0; i < 256; i++) {
                index2 = ((b_key[index1] & 0xff) + (state[i] & 0xff) + index2) & 0xff;
                byte tmp = state[i];
                state[i] = state[index2];
                state[index2] = tmp;
                index1 = (index1 + 1) % b_key.length;
            }
            return state;
        } catch (Exception exp) {
            return null;
        }
    }

    private static byte uniteBytes(byte src0, byte src1) {
        char _b0 = (char) Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
        _b0 = (char) (_b0 << 4);
        char _b1 = (char) Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    private static byte[] RC4Base(byte[] input, String mKkey) {
        int x = 0;
        int y = 0;
        byte key[] = initKey(mKkey);
        int xorIndex;
        byte[] result = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 0xff;
            y = ((key[x] & 0xff) + y) & 0xff;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;
            result[i] = (byte) (input[i] ^ key[xorIndex]);
        }
        return result;
    }

    private static final char[] bcdLookup = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final String bytesToHex(byte[] bcd) {
        StringBuffer s = new StringBuffer(bcd.length * 2);
        for (int i = 0; i < bcd.length; i++) {
            s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
            s.append(bcdLookup[bcd[i] & 0x0f]);
        }

        return s.toString();
    }

    public static final byte[] hexToBytes(String s) {
        byte[] bytes;
        bytes = new byte[s.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
                    16);
        }
        return bytes;
    }
}