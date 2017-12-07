package com.jk.util;

import java.util.UUID;

public class UUIDUtil {

	
    /**  
10.     * UUID去-
11.     * @return  
12.     */  
   public static String getUUID() {  
        return UUID.randomUUID().toString().replace("-", "");  
    } 

}
