package com.lsh.vivo.provider;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.util.AESUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 密码处理
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/17 23:32
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private static final String SECURITY_DEFAULT_PASSWORD = "userNotFoundPassword";

    @Resource
    private AESUtils aesUtils;

    public CustomPasswordEncoder() {
    }

    @Override
    public String encode(CharSequence password) {
        if (password == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        if (Objects.equals(password, SECURITY_DEFAULT_PASSWORD)) {
            return GlobalConstant.STRING_EMPTY;
        }
        String encodePassword;
        try {
            encodePassword = aesUtils.encode((String) password);
        } catch (Exception ignored) {
            encodePassword = GlobalConstant.STRING_EMPTY;
        }
        return encodePassword;
    }

    @Override
    public boolean matches(CharSequence password, String encodedPassword) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(encodedPassword)) {
            return false;
        }
        String encodePassword;
        try {
            encodePassword = aesUtils.encode((String) password);
        } catch (Exception ignored) {
            encodePassword = GlobalConstant.STRING_EMPTY;
        }
        return Objects.equals(encodePassword, encodedPassword);
    }
}