package com.jk.util;

import java.util.UUID;

public class UUIDUtil {

	
    /**  
10.     * �Զ����32λ��UUid����Ӧ��ݿ������id���в����á�  
11.     * @return  
12.     */  
   public static String getUUID() {  
        return UUID.randomUUID().toString().replace("-", "");  
    } 

}
