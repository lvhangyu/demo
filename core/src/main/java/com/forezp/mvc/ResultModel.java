package com.forezp.mvc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
public class ResultModel<T> {
    private Integer code;
    private String msg;
    private T data;

}
