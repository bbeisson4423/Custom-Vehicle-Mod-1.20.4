package net.beison555.cvm.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class InputCheck {
    /**
     * 引数のNullチェックを行う
     */
    public static boolean isNullOrBlank(Object obj){
        boolean result = false;

        if(Objects.isNull(obj)){
            // Null
            result = true;
        }else{
            // String型の場合、空文字チェックも行う
            if(obj instanceof String){
                if(obj == ""){
                    // 空文字
                    result = true;
                }
            }
        }

        return result;
    }
}
