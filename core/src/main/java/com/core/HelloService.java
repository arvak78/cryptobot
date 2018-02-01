package com.core;
import org.springframework.stereotype.Service;
@Service
public class HelloService {
	public String getMessage(){
		return "Hello World!";
	}
}