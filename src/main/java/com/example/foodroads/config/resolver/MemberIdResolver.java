package com.example.foodroads.config.resolver;

import com.example.foodroads.common.exception.model.InternalServerException;
import com.example.foodroads.config.interceptor.Auth;
import com.example.foodroads.domain.auth.service.JwtTokenProvider;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MemberIdResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MemberId.class) && Long.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer, @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Auth auth = parameter.getMethodAnnotation(Auth.class);

        if (auth == null) {
            throw new InternalServerException(String.format("예상치 못한 에러가 발생하였습니다. 인증이 필요한 컨트롤러(%s-%s)로, @Auth 어노테이션을 붙여주세요.", parameter.getDeclaringClass().getSimpleName(), Objects.requireNonNull(parameter.getMethod()).getName()));
        }

        String authorization = webRequest.getHeader("Authorization");

        Object memberId = jwtTokenProvider.getMemberId(authorization);

        if (memberId == null) {
            throw new InternalServerException(String.format("예상치 못한 에러가 발생하였습니다. 컨트롤러(%s-%s)에서 MEMBER_ID를 가져오지 못했습니다.", parameter.getDeclaringClass().getSimpleName(), Objects.requireNonNull(parameter.getMethod()).getName()));
        }
        return memberId;
    }

}
