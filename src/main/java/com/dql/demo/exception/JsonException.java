package com.dql.demo.exception;

import com.dql.demo.exception.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
public class JsonException extends BaseException {

    public JsonException(Status status) {
        super(status);

    }
    public JsonException(Integer code, String message) {
        super(code,message);
    }

}
