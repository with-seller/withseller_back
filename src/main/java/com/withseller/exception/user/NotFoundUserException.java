package com.withseller.exception.user;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("회원정보를 찾을 수 없습니다.");
    }
}
