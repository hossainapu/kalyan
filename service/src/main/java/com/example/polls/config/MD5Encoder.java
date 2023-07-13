package com.example.polls.config;

import com.example.polls.util.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MD5Encoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return Utils.getMd5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equalsIgnoreCase(Utils.getMd5(charSequence.toString()));
    }
}
