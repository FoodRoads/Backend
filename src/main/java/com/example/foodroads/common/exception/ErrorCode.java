package com.example.foodroads.common.exception;

public enum ErrorCode {

    /**
     * 400 Bad Request (잘못된 요청)
     */
    E400_INVALID("BR000", "잘못된 요청입니다"),
    E400_INVALID_ENCODING_ID( "BR001", "잘못된 ID가 입력되었습니다"),
    E400_INVALID_AUTH_TOKEN("BR002", "만료되거나 유효하지 않은 소셜 인증 토큰입니다"),

    E400_MISSING_PARAMETER( "BR100", "필수 파라미터가 입력되지 않았습니다"),
    E400_MISSING_AUTH_TOKEN_PARAMETER("BR101", "인증 토큰을 입력해주세요"),


    /**
     * 401 UnAuthorized (인증 실패)
     */
    E401_UNAUTHORIZED("UA000", "세션이 만료되었습니다. 다시 로그인 해주세요"),


    /**
     * 403 Forbidden (권한 등의 이유로 허용하지 않는 요청)
     */
    E403_FORBIDDEN("FB000", "허용하지 않는 요청입니다"),


    /**
     * 404 Not Found (존재하지 않는 리소스)
     */
    E404_NOT_EXISTS("NF000", "존재하지 않습니다"),
    E404_NOT_EXISTS_USER("NF001", "탈퇴하거나 존재하지 않는 유저입니다"),


    /**
     * 405 Method Not Allowed
     */
    E405_METHOD_NOT_ALLOWED("MN000", "허용되지 않은 HTTP 메소드입니다"),


    /**
     * 406 Not Acceptable
     */
    E406_NOT_ACCEPTABLE("NA000", "허용되지 않은 Content-Type 입니다"),


    /**
     * 409 Conflict (중복되는 리소스)
     */
    E409_DUPLICATE("CF000", "이미 존재합니다"),
    E409_DUPLICATE_NICKNAME("CF001", "이미 사용중인 닉네임입니다.\n다른 닉네임을 이용해주세요"),
    E409_DUPLICATE_USER("CF002", "이미 회원가입하셨습니다.\n해당 계정으로 로그인 해주세요"),
    E409_ALREADY_CONNECTED_SOCIAL("CF003", "이미 소셜 계정에 연결된 유저입니다"),


    /**
     * 415 Unsupported Media Type
     */
    E415_UNSUPPORTED_MEDIA_TYPE("UM000", "지원 하지 않는 MediaType 입니다/"),


    /**
     * 500 Internal Server Exception (서버 내부 에러)
     */
    E500_INTERNAL_SERVER("IS000", "예상치 못한 에러가 발생하였습니다ㅠ.ㅠ\n잠시 후 다시 시도해주세요!"),
    E500_INTERNAL_SERVER_CONCURRENCY_PROBLEM("IS001", "일시적인 문제가 발생하였습니다.\n잠시 후 다시 시도해주세요!"),


    /**
     * 501 Not Implemented (현재 지원하지 않는 요청)
     */
    E501_NOT_SUPPORTED("II000", "지원하지 않는 요청입니다"),
    /**
     * 502 Bad Gateway (외부 시스템의 Bad Gateway)
     */
    E502_BAD_GATEWAY("BG000", "일시적인 에러가 발생하였습니다ㅠ.ㅠ\n잠시 후 다시 시도해주세요!"),

    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
