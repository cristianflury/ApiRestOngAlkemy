package org.alkemy.somosmas.util;

import java.util.Base64;

public final class EncodeBase64Utils {

    private EncodeBase64Utils(){
    	
    }

    public static String encode(String secretKey){
        return Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


}



