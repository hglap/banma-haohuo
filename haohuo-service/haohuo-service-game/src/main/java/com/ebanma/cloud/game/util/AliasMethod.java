package com.ebanma.cloud.game.util;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author banma-
 * @version $ Id: AliasMethod, v 0.1 2023/06/07 9:33 banma- Exp $
 */
public class AliasMethod {

    private final Random random;     /* 随机方法 */
    private final int[] alias;     /* 存放填未命中返回的一方 */
    private final double[] probability;     /*存放 新生成的概率*/

    public AliasMethod(List<Double> probabilities) {
        this(probabilities, new Random());
    }
    public AliasMethod(List<Double> probabilities, Random random) {
        /**
         * 校验入参
         */
        if (probabilities == null || random == null) throw new NullPointerException();
        if (probabilities.size() == 0)
            throw new IllegalArgumentException("概率向量必须为非空。");
        /**
         * 概率校验及修正
         * 如果概率和不等于1;进行修正
         */
        double sum = probabilities.stream().mapToDouble(Double::doubleValue).sum();
        if( new BigDecimal(sum).compareTo( new BigDecimal(1) )!=0){
            for (int i = 0; i < probabilities.size(); i++) {
                probabilities.set(i, probabilities.get(i) * (1D / sum));
            }
        }
        /* 初始化数组容量*/
        probability = new double[probabilities.size()];
        alias = new int[probabilities.size()];
        this.random = random;
        /*计算概率平均值*/
        final double average = 1.0 / probabilities.size();
        probabilities = new ArrayList<Double>(probabilities);
        /*初始化两个队列 用于接受 大于，小于平均概率的选项*/
        Deque<Integer> small = new ArrayDeque<>();
        Deque<Integer> large = new ArrayDeque<>();
        /*遍历概率项，进行划分*/
        for (int i = 0; i < probabilities.size(); ++i) {
            if (probabilities.get(i) >= average) large.add(i);
            else small.add(i);
        }
        /**
         * 将小于 平均概率得选项乘总选项个数   得到概率 x
         * 将大于 平均值的概率选项 进行相关减除  得到  y
         * 并且y的选项 下标记录到alias集合中
         * 继续判断 y 是否还大于 平均概率 如果大于  继续添加到 队列中进行 上述操作 否则 添加到 小于平均概率的对象中 进行上述操作
         * 逻辑分析
         * 将这两个选项 综合 拼成一个 为 100%的概率
         * 注意： 一个单元中有且之能有两个选项去拼成100%的概率  即 x+y =100%
         */
        while (!small.isEmpty() && !large.isEmpty()) {
            //得到 两个选项的下标
            int less = small.removeLast();
            int more = large.removeLast();
            /*将小于平均概率的数组下标对应的概率 进行增加概率*/
            probability[less] = probabilities.get(less) * probabilities.size();
            /*记录大于平均概率的选项的下标*/
            alias[less] = more;
            /*将大于 平均值的概率选项 进行减除*/
            probabilities.set(more, (probabilities.get(more) + probabilities.get(less)) - average);
            /*继续判断 y 是否还大于 平均概率 如果大于  继续添加到 队列中进行 上述操作 */
            if (probabilities.get(more) >= 1.0 / probabilities.size()) large.add(more);
                /*否则 添加到 小于平均概率的对象中 进行上述操作*/
            else small.add(more);
        }
        /* 补齐空概率的选项下标为 188%*/
        while (!small.isEmpty()) probability[small.removeLast()] = 1.0;
        while (!large.isEmpty()) probability[large.removeLast()] = 1.0;
    }

    public int next() {
        /*
         从 选项中随机得到一个选项
         */
        int column = random.nextInt(probability.length);
        /*判断随机生成的数值是在哪个范围之上*/
        boolean coinToss = random.nextDouble() < probability[column];
        /*
        返回对应的结果
         */
        return coinToss ? column : alias[column];
    }

    public static void main(String[] args) {
        TreeMap<String, Double> map = new TreeMap<String, Double>();
        map.put("1金币", 0.2);
        map.put("2金币", 0.19);
        map.put("3金币", 0.1);
//        map.put("4金币", 0.01);
        map.put("未中奖", 0.5);
        List<Double> list = new ArrayList<Double>(map.values());
        double sum = list.stream().mapToDouble(Double::doubleValue).sum();
        List<String> gifts = new ArrayList<String>(map.keySet());
        AliasMethod method = new AliasMethod(list);
        Map<String, AtomicInteger> resultMap = new HashMap<String, AtomicInteger>();
        int len = 100000;
        for (int i = 0; i < len; i++) {
            int index = method.next();
            String key = gifts.get(index);
            if (!resultMap.containsKey(key)) {
                resultMap.put(key, new AtomicInteger());
            }
            resultMap.get(key).incrementAndGet();
        }
        int count = 0;
        for (String key : resultMap.keySet()) {
            count += resultMap.get(key).get();
            double value =  (sum*resultMap.get(key).get()/len/map.get(key)-1)*100;
            System.out.printf(key + "==" + resultMap.get(key)+"   %.2f \n" ,value);
        }
        System.out.println(count);
    }

}



