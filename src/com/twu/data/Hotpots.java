package com.twu.data;

import com.twu.pojo.Hotpot;

import java.util.ArrayList;
import java.util.List;

public class Hotpots {
    private Hotpots(){};
    private static List<Hotpot> list=new ArrayList<>();
    //获取当前热点
    public static List<Hotpot> getHotpots(){
        return list;
    }
    public static int addHotpot(String name,boolean isSupperHotpot){
        if(list.stream().filter(h->h.getName().equals(name)).count()==0){
            list.add(new Hotpot(name,isSupperHotpot));
            return 0;
        }else{
            //已经存在同名热点，添加失败
            return -1;
        }
    }
}
