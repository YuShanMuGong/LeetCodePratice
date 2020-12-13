package org.example.pay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 问题：用户在支付宝拥有多种支付方式（余额、红包、余额宝等，每种支付工具分布在不同系统），每种支付方式通过调用远程服务获取可用性。在外部资源环境不变情况下，请设计程序以最短响应时间获得尽可能多的可用支付方式列表。
 *
 * 整体实现思路：
 * 思考解决两个问题：
 * 1. 抽象化 支付渠道的校验，提高代码可维护性；
 * 2. 以最短响应时间获得尽可能多的可用支付方式列表，
 *
 * 针对问题1：抽象一个 PayChannel ，具体的PayChannel对象 需要 实现 支付渠道检验服务方法；例如如果是花呗，可能就去调花呗的RPC接口等；
 * 针对问题2：使用线程池，批量提交支付渠道校验逻辑；这样就能并行执行了；最长的阻塞时间等于最慢的那个Check RPC调用
 *
 */
public class FindPayChannelService {

    /**
     * 支付渠道 抽象接口
     */
    private interface PayChannel{

        /**
         * 获取渠道名称
         * @return
         */
        String getChannelName();

        /**
         * 校验当前 传入的用户和金额，是否可以可以使用该渠道支付
         * @param userId
         * @param amount
         * @return
         */
        boolean checkPayStatus(String userId , BigDecimal amount);
    }

    /**
     * 目前拥有的支付渠道；可以考虑Spring注入等方式实现
     */
    private List<PayChannel> payChannels = new ArrayList<>();

    /**
     * 任务执行的线程池
     */
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,10, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1024));

    public FindPayChannelService(){
        init();
    }

    private void init(){
        // 花呗
        payChannels.add(new PayChannel() {
            @Override
            public String getChannelName() {
                return "花呗";
            }

            @Override
            public boolean checkPayStatus(String userId, BigDecimal amount) {
                // RPC 调用花呗服务，check是否可以使用该支付方式
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });

        // 红包
        payChannels.add(new PayChannel() {
            @Override
            public String getChannelName() {
                return "红包";
            }

            @Override
            public boolean checkPayStatus(String userId, BigDecimal amount) {
                // RPC 调用红包服务，check是否可以使用该支付方式
                return true;
            }
        });

    }



    private static class FindPayChannelResult{
        final PayChannel channel;
        final boolean canPay;

        private FindPayChannelResult(PayChannel channel, boolean canPay) {
            this.channel = channel;
            this.canPay = canPay;
        }
    }


    /**
     * 在指定的时间里，寻找支付方式；
     * @param userId 用户ID
     * @param amount 本次交易金额
     * @return
     */
    public List<String> findPayChannel(String userId , BigDecimal amount){
        List<Future<FindPayChannelResult>> canPayFutures = new ArrayList<>();
        for (PayChannel channel : payChannels){
            canPayFutures.add(executor.submit(() -> {
                try {
                    boolean payStatus = channel.checkPayStatus(userId,amount);
                    return new FindPayChannelResult(channel,payStatus);
                }catch (Exception e){
                    return new FindPayChannelResult(channel,false);
                }
            }));
        }
        List<String> canPayChannelNames = new ArrayList<>();
        for (Future<FindPayChannelResult> future : canPayFutures){
            try {
                FindPayChannelResult result = future.get();
                if(result.canPay){
                    canPayChannelNames.add(result.channel.getChannelName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return canPayChannelNames;
    }

    public static void main(String[] args) {
        FindPayChannelService findPayChannelService = new FindPayChannelService();
        List<String> channelList = findPayChannelService.findPayChannel("1000",new BigDecimal(100));
        System.out.println(Arrays.toString(channelList.toArray()));
    }

}
