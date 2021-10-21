package org.alkemy.somosmas.util;

import org.alkemy.somosmas.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
    private SecurityUtils(){}

    public static UserDetailsImpl getLoggedInUser() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
