# worm - 简易的支持分库分表的orm框架

如何开始:运行test包中TestOrm类

默认按指定列取余分库分表，需要自行实现分库策略可以通过SplitStrategyFactory#registSplitStrategy方法来指定自己的策略。
默认连接池为10。需要修改自行修改代码扩充。
