package com.wellcom.jppt.system.common.datasources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import lombok.Data;

@Data
public class Test {
    @Autowired
    GetDatainfo info1;

    public void test() {
        System.out.println(info1.toString());
    }
}

class test2 {
    
	public static void main(String[] args) {
        System.out.println("hello world");
        GetDatainfo info = new GetDatainfo();
        System.out.println(info);
        test2 t2 = new test2();
        //t2.test();
    }


}