package com.bytedance.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class PwdEncoder {
  public String md5EncoderTwice (String password) {
    return DigestUtils.md5Hex(DigestUtils.md5Hex(password));
  }

}
