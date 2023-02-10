package com.example.foodroads.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final LoginCheckHandler loginCheckHandler;
    private final LoginOptionalCheckHandler loginOptionalCheckHandler;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }

        if (auth.allowNonLogin()) {
            Long userIdNullable = loginOptionalCheckHandler.getMemberIdOptional(request);
            return true;
        }

        Long userId = loginCheckHandler.getMemberId(request);
        return true;
    }

}
