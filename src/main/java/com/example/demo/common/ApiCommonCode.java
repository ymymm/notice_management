package com.example.demo.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiCommonCode {

	// UserException
	PASSWORD_ERROR(1001, "password error", "비밀번호를 확인해주세요."),
	NOT_EXIST_USER(1002, "not exist user", "존재하지 않는 유저입니다."),

	PARAM_ERROR(1003, "param error","파라미터 값을 확인해주세요."),

	// TokenException
	INVALID_TOKEN(2001, "invalid token", "유효하지 않은 토큰입니다."),

	// FileException
	NOT_EXIST_FILE(3001, "not exist file", "존재하지 않는 파일입니다."),
	FILE_UPLOAD_ERROR(3002, "file upload error", "파일 업로드 중 오류가 발생하였습니다."),

	//Notice Exception
	NOT_EXIST_NOTICE(4001, "not exist notice", "존재하지 않는 게시글입니다."),
	BEFORE_DATE(4002, "before date", "시작일자 전인 게시글입니다."),
	AFTER_DATE(4003, "after date", "종료일자가 지난 게시글입니다.");

	private final int code;
	private final String message;
	private final String description;
}