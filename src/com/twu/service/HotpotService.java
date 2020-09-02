package com.twu.service;

import com.twu.data.Hotpots;
import com.twu.pojo.Hotpot;
import com.twu.pojo.User;

import java.util.*;
import java.util.stream.Collectors;

//热点相关的service方法类
public class HotpotService {

    //存储热点的list
    private List<Hotpot> hotpotList= Hotpots.getHotpots();
    private UserService userService=new UserService();
    //获取当前排行时执行的
    public void printList() {
        //获取购买且未被替换的热点list
        List<Hotpot> boughtList=hotpotList.stream().filter(h -> h.getRank()!=0&&h.getBoughtPrice()>0&&!h.isReplaced()).collect(Collectors.toList());
        //获取非购买且未被替换的list
        List<Hotpot> generalList =hotpotList.stream().filter(h->h.getBoughtPrice()==0&&!h.isReplaced()).sorted((h1,h2)->h2.getVotes()-h1.getVotes()).collect(Collectors.toList());
        //需要显示的热点数量，为上面二者之和
        long displayCount=hotpotList.stream().filter(h->h.isReplaced()==false).count();
        Iterator iterator=generalList.iterator();
        System.out.println("排序\t名称\t热度");
        //循环打印
        for(int i=0;i<displayCount;i++){
            int temp = i;
            //获取当前pos的购买的热度，看是否存在
            List<Hotpot> matchedBoughtHotpots=boughtList.stream().filter(h->h.getRank()-1== temp&&!h.isReplaced()).collect(Collectors.toList());
            Hotpot hotpot=null;
            //已经被购买，打印购买的热点信息
            if(matchedBoughtHotpots.size()>0){
                hotpot=matchedBoughtHotpots.get(0);
            }
            //没有被购买，从genneralHotpots中顺序取出一个
            if(matchedBoughtHotpots.size()==0&&iterator.hasNext()){
                hotpot= (Hotpot) iterator.next();
            }
            //打印热点的排名和热度
            System.out.println((i+1)+"\t"+hotpot.getName()+"\t"+hotpot.getVotes());
        }
    }

    //给热搜投票
    public int vote(User currentUser, String hotpotName, int votesNum) {
        //判断输入的热点名称是否存在
        boolean isNameValid=false;
        for(Hotpot hotpot:hotpotList)
        {
            if(hotpot.getName().equals(hotpotName)){
                //热点有效
                isNameValid=true;
                //判断是否是超级热点，是则票数乘以2
                boolean isSuperHot=hotpot.isSuperHotpot();
                if(isSuperHot){
                    hotpot.setVotes(hotpot.getVotes()+votesNum*2);
                }else {
                    hotpot.setVotes(hotpot.getVotes()+votesNum);
                }
            }
        }
        //当前用户票数减少
        if(isNameValid){
            currentUser.setRemainVotes(currentUser.getRemainVotes()-votesNum);
            return currentUser.getRemainVotes();
        }else{
            return -1;
        }

    }

    //增加热点，第二个参数为true时添加超级热点
    public int addHotpot(String name,boolean isSuperHotpot) {
        return Hotpots.addHotpot(name,isSuperHotpot);
    }
    //购买热搜
    public int buyHotpot(String hotpotName, int pos, double price) {
        //boolean isNameValid=false;
        Hotpot toBuyHotpot=null;
        for(Hotpot hotpot:hotpotList)
        {
            if(hotpot.getName().equals(hotpotName)){
                toBuyHotpot=hotpot;
            }
        }
        if(toBuyHotpot==null){
            //名称无效，返回
            return -1;
        }
        boolean isPosBought=false;
        //判断当前位置是否已经被购买
        for(Hotpot hotpot:hotpotList){
            if(hotpot.isReplaced()==false&&hotpot.getRank()==pos){
                //已经被购买，
                isPosBought=true;
                if(price>hotpot.getBoughtPrice()){
                    //金额比较大，可以购买成功
                    //设置购买的hp的属性
                    toBuyHotpot.setRank(pos);
                    toBuyHotpot.setBoughtPrice(price);
                    toBuyHotpot.setReplaced(false);
                    //设置被顶替的hp的属性
                    hotpot.setReplaced(true);
                }else{
                    return -1;
                }
            }
        }
        //当前位置没有被购买
        if(!isPosBought){
            //设置购买的hp的属性
            toBuyHotpot.setRank(pos);
            toBuyHotpot.setBoughtPrice(price);
            toBuyHotpot.setReplaced(false);
        }
        //返回0则购买成功
        return 0;
    }
}
