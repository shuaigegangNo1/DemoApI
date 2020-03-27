package com.sgg.rest.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {
//	@ExceptionHandler({RuntimeException.class})
	@ExceptionHandler({BussinessException.class})
	@ResponseBody
	public Map<String,String> exceptionHandler(HttpServletRequest h,Exception e){
		HashMap<String,String> map = new HashMap<String,String>();
		System.out.println(">>>>e"+e.getMessage());
		System.out.println(">>>>h"+h.getRequestURI());
		System.out.println(">>>>method"+h.getMethod());
		String message = null;
      if(e instanceof ArithmeticException){// �������Ͳ�ƥ���쳣
    	  ArithmeticException ex = (ArithmeticException) e;
    	  message = String.format("����������Ϊ0");
      }
      else if(e instanceof TypeMismatchException){// �������Ͳ�ƥ���쳣
            TypeMismatchException ex = (TypeMismatchException) e;
            message = String.format("�������Ͳ�ƥ�䣬����Ӧ��Ϊ: %s", ex.getRequiredType());
           // log.info("�쳣�ӿ�{}  �쳣��Ϣ{}",request.getRequestURI(), message);
//            return ResultGenerator.genHandleResult(Constant.FAIL, message);
        }else if(e instanceof MissingServletRequestParameterException){// ȱ�ٲ����쳣

            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;

            message = String.format("ȱ�ٲ���: %s", ex.getParameterName());
//
//            log.info("�쳣�ӿ�{}  �쳣��Ϣ{}", request.getRequestURI(), message);
//
//            return ResultGenerator.genHandleResult(Constant.FAIL, message);

        }else if(e instanceof HttpRequestMethodNotSupportedException){// ���������쳣

            HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) e;

            message = String.format("�ӿ���������Ӧ��Ϊ: %s", ex.getSupportedHttpMethods());

     //       log.info("�ӿ� ��{}�� �������ʹ���!", request.getMethod());
//
           // return ResultGenerator.genHandleResult(Constant.FAIL, message);

        }else if(e instanceof BussinessException){
    		map.put("responseCode", ((BussinessException) e).getCode());
    		map.put("responseMsg", ((BussinessException) e).getMsg());
    		return map;
        }

//       log.info("�쳣�ӿ�{}  �쳣��Ϣ{}", request.getRequestURI(), e.toString());
        System.out.println("msg"+message);
        System.out.println("�쳣�ӿ�{}  �쳣��Ϣ{}"+ h.getRequestURI()+"exception:"+e.toString());
		map.put("responseCode", "500");
		map.put("responseMsg", "�������");
		return map;
	}
}
