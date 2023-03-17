package com.osh.jntest.global.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseFormat {

    private Map<String, Object> meta = new HashMap<>();
    private Object data;

    public ResponseFormat() {
        meta.put(Constants.Keys.CODE.getKey(), Constants.Code.SUCCESS.getCode());
        meta.put(Constants.Keys.MESSAGE.getKey(), Constants.Code.SUCCESS.getMessage());

        this.data = new EmptyResultVo();
    }

    public ResponseFormat(Object data) {
        meta.put(Constants.Keys.CODE.getKey(), Constants.Code.SUCCESS.getCode());
        meta.put(Constants.Keys.MESSAGE.getKey(), Constants.Code.SUCCESS.getMessage());

        this.data = data;
    }

    public ResponseFormat(int code, String message) {
        meta.put(Constants.Keys.CODE.getKey(), code);
        meta.put(Constants.Keys.MESSAGE.getKey(), message);
        this.data = new EmptyResultVo();
    }

    public void addDetail(String target, String message) {
        this.meta.computeIfAbsent(Constants.Keys.DETAIL.getKey(), k -> new ArrayList<Detail>());
        List<Detail> details = (List<Detail>) this.meta.get(Constants.Keys.DETAIL.getKey());
        details.add(new Detail(target, message));
    }

    public void setCode(int code) {
        meta.put(Constants.Keys.CODE.getKey(), code);
    }

    @JsonIgnore
    public int getCode() {
        return (int) meta.get(Constants.Keys.CODE.getKey());
    }

    public void setMessage(String message) {
        meta.put(Constants.Keys.MESSAGE.getKey(), message);
    }

    @JsonIgnore
    public String getMessage() {
        return (String) meta.get(Constants.Keys.MESSAGE.getKey());
    }

    @Getter
    @Setter
    public class Detail {

        private String target;
        private String message;

        Detail(String target, String message) {
            this.target = target;
            this.message = message;
        }
    }


    @Getter
    @NoArgsConstructor
    @ToString
    public static class Feign<T> implements Serializable {

        private T data;

        public Feign( T data) {

            this.data = data;
        }

        public static <T> Feign<T> of(T data) {
            return new Feign<>(data);
        }

    }

}
