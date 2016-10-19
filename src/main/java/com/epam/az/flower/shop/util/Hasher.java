package com.epam.az.flower.shop.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Hasher {
    public String hash(String value) {
        String md5Hex = DigestUtils.md5Hex(value);
        return md5Hex;
    }
}
