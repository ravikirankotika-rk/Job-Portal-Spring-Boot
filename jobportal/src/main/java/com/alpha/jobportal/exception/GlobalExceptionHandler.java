package com.alpha.jobportal.exception;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.jobportal.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseStructure<String> handlingCandidateNotFound() {
        ResponseStructure<String> rs = new ResponseStructure<String>();
        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Candidate Not Found");
        rs.setData(null);
        return rs;
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseStructure<String> handlingCompanyNotFound() {
        ResponseStructure<String> rs = new ResponseStructure<String>();
        rs.setStatuscode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Company Not Found");
        rs.setData(null);
        return rs;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlemethodArgumentvalidException(MethodArgumentNotValidException ex) {
    	
    	List<FieldError> errors = ex.getFieldErrors();
    	Map<String, String> resp = new HashMap<String, String>();
    	for (FieldError fieldError : errors) {
    		resp.put(fieldError.getField(), fieldError.getDefaultMessage());
    		
		}
		return resp;
    	
    }
}