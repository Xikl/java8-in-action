package com.ximo.java8.refactor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 朱文赵
 * @date 2018/12/14 15:44
 */
public class SqlJoiner {
    private final  String InnerJoin = "innerJoin";

    private final  String LefJoin = "leftJoin";

    public List<Map<String,Object>> join(List<Map<String,Object>> leftList , List<Map<String,Object>> rightList, List<JoinKey> joinKeyList, String joinType){

        ConcurrentHashMap<String,MatchRows> matchMap = new ConcurrentHashMap<>();

        for(Map<String,Object> result: leftList){
            StringBuffer rowKey = new StringBuffer();
            for(JoinKey key : joinKeyList){
                rowKey.append(result.get(key.leftKey)).append("||");
            }
            if(!matchMap.containsKey(rowKey.toString())){
                MatchRows row = new MatchRows();
                matchMap.put(rowKey.toString(),row);
            }
            matchMap.get(rowKey.toString()).addLeft(result);
        }

        for(Map<String,Object> result: rightList){
            StringBuffer rowKey = new StringBuffer();
            for(JoinKey key : joinKeyList){
                rowKey.append(result.get(key.rightKey)).append("||");
            }
            if(matchMap.containsKey(rowKey.toString())){
                matchMap.get(rowKey.toString()).addRight(result);
            }
        }

        List<Map<String,Object>> finalResult = new ArrayList<>();

        for(MatchRows matchRows : matchMap.values()){
            List<Map<String,Object>> result = joinRows(matchRows,joinType);
            finalResult.addAll(result);
        }

        return finalResult;
        //TODO:思考如何提高此代码的效率？？平时我们看到Hive Join的map数  reduce数体现到哪个地方
    }

    private List<Map<String,Object>> joinRows(MatchRows matchRows, String joinType){
        List<Map<String,Object>> result = new ArrayList<>();

        //这里为什么可以使用双层循环？ 因为已经过滤一次，能关联上的对象已经很少
        for(Map<String,Object> leftRow  :matchRows.leftList){

            //TODO:innerJoin 跟leftJoin的区别就在这里。当rightRow.isEmpty() 并且为leftJoin时 才需要处理
            for(Map<String,Object> rightRow  :matchRows.rightList){
                Map<String,Object> finalRow = new HashMap<>();
                //TODO：测试假设关联对象key不冲突。 将右边的值append到左边，形成新的结果
                finalRow.putAll(leftRow);
                finalRow.putAll(rightRow);
                result.add(finalRow);
            }
        }
        return result;
    }


    static class MatchRows{
        List<Map<String,Object>> leftList = new ArrayList<>();

        List<Map<String,Object>> rightList = new ArrayList<>();

        public void addLeft(Map<String,Object> obj){
            this.leftList.add(obj);
        }
        public  void addRight(Map<String,Object> obj){
            this.rightList.add(obj);
        }
    }

    class JoinKey{
        public  JoinKey(String leftKey,String rightKey){
            this.leftKey = leftKey;
            this.rightKey = rightKey;
        }
        public String leftKey;
        public String rightKey;
    }

    public static void main(String[] args) {

        List<Map<String,Object>> leftList = new ArrayList<>();
        List<Map<String,Object>> rightList = new ArrayList<>();
        for(int i=0; i<=100000;i++){
            Map<String,Object> left = new HashMap<>();
            left.put("ID",i);
            left.put("CODE",i+"---");
            leftList.add(left);
            leftList.add(left);

            if(i%1000 == 0){
                Map<String,Object> right = new HashMap<>();
                right.put("ID",i);
                right.put("NAME",i+"***");
                rightList.add(right);
            }

        }
        SqlJoiner helper = new SqlJoiner();
        JoinKey joinKey = helper.new JoinKey("ID","ID");
        List<JoinKey> keyList = new ArrayList<>();
        keyList.add(joinKey);
        List<Map<String, Object>> finalResult = helper.join(leftList, rightList, keyList, "innerJoin");
        for (Map<String, Object> objectMap : finalResult) {
            System.out.println(objectMap);
        }

    }
}