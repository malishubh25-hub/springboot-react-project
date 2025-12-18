package com.baji.global.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Common response wrapper for APIs
 */
@Data
@NoArgsConstructor   // ✅ Generates default no-arg constructor
@AllArgsConstructor  // ✅ Generates constructor with all fields
@JsonInclude(Include.NON_NULL)
public class CommonResponseBean {

    private String message;
    private boolean success;
    private Object response;

    public CommonResponseBean() {}

    public CommonResponseBean(String message, boolean success, Object response) {
        this.message = message;
        this.success = success;
        this.response = response;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public Object getResponse() { return response; }
    public void setResponse(Object response) { this.response = response; }

    // Success response
    public static CommonResponseBean success(Object response) {
        CommonResponseBean bean = new CommonResponseBean();
        bean.setMessage("Operation successful");
        bean.setSuccess(true);
        bean.setResponse(response);
        return bean;
    }

    // Error response
    public static CommonResponseBean error(String message) {
        CommonResponseBean bean = new CommonResponseBean();
        bean.setMessage(message);
        bean.setSuccess(false);
        return bean;
    }
}

