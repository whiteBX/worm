package org.white.worm.strategy;

/**
 * <p></p >
 *
 * @author white
 * @version $Id: SpiltStrategyFactory.java, v 0.1 2019年09月06日 11:58:00 white Exp$
 */
public class SplitStrategyFactory {

    public static SplitStrategy STRATEGY = new DefaultSplitStrategy();

    /**
     * 预留方法注册自己扩充的分库分表方案
     * @param splitStrategy
     */
    public static void registSplitStrategy(SplitStrategy splitStrategy) {
        SplitStrategyFactory.STRATEGY = splitStrategy;
    }
}
