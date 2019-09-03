package org.white.worm.cache;

import org.white.worm.aspect.Table;
import org.white.worm.entity.TableMeta;
import org.white.worm.util.ReflectUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: OrmHolder.java, v 0.1 2019年09月02日 17:48:00 white Exp$
 */
public class OrmHolder {

    public static Map<Class, TableMeta> HOLDER = new HashMap<>();

    private static ReentrantLock lock = new ReentrantLock();

    public static TableMeta get(Class clazz) {
        if (HOLDER.get(clazz) == null) {
            lock.lock();
            try {
                if (HOLDER.get(clazz) == null) {
                    if (clazz.isAnnotationPresent(Table.class)) {
                        HOLDER.put(clazz, ReflectUtil.parseMeta(clazz));
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return HOLDER.get(clazz);
    }
}
