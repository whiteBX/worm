package org.white.worm.util;

import org.springframework.util.CollectionUtils;
import org.white.worm.exception.BizException;

import java.util.Collection;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: AssertUtil.java, v 0.1 2019年09月02日 17:15:00 white Exp$
 */
public class AssertUtil {

    public static void isTrue(boolean expression, int code, String message) {
        if (!expression) {
            throw new BizException(code, message);
        }
    }

    public static void notNull(Object obj, int code, String message) {
        if (obj == null) {
            throw new BizException(code, message);
        }
    }

    public static void notEmpty(Collection<?> collection, int code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizException(code, message);
        }
    }
}
