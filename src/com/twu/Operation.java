package com.twu;

import com.twu.pojo.Hotpot;
import com.twu.pojo.User;
import com.twu.service.HotpotService;
import com.twu.service.UserService;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

//主入口class
public class Operation {

    //存储当前登录的用户
    private User currentUser=null;

    private UserService userService=new UserService();

    private HotpotService hotpotService=new HotpotService();

    //标准输入
    private static Scanner sc=new Scanner(System.in);

    //主函数
    public void operate(){
        while(true){
            System.out.println("请输入用户名：");
            String username=sc.nextLine();
            System.out.println("请输入密码：");
            String password=sc.nextLine();
            //验证用户名密码
            currentUser=userService.getAuthUser(username,password);
            if(currentUser==null){
                System.out.println("用户认证失败，系统退出。。。");
                break;
            }
            switch (currentUser.getUserType()){
                case 1:processAdmin();break;
                case 2:processOrdinUser();break;
            }
        }
    }

    //普通用户登陆后的操作
    private void processOrdinUser() {
        while(true){
            System.out.println("请输入要进行的操作：\n1、查看热搜排行榜\n2、给热搜事件投票\n3、购买热搜\n4、添加热搜\n5、退出登录");
            String action=sc.nextLine();
            switch (action){
                case "1":hotpotService.printList();break;
                case "2":vote();break;
                case "3":buyHotpot();break;
                case "4":addHotpot(false);break;
            }
            if(action.equals("5")){
                System.out.println("当前用户退出。。。");
                currentUser=null;
                break;
            }
        }
    }

    //管理员用户登陆后的操作
    private void processAdmin() {
        while(true){
            System.out.println("请输入要进行的操作：\n1、查看热搜排行榜\n2、添加热搜\n3、添加超级热搜\n4、退出登录");
            String action=sc.nextLine();
            switch (action){
                case "1":hotpotService.printList();break;
                case "2":addHotpot(false);break;
                case "3":addHotpot(true);break;
            }
            if(action.equals("4")){
                System.out.println("当前用户退出。。。");
                currentUser=null;
                break;
            }
        }
    }

    //添加热搜
    private void addHotpot(boolean isSuperHotpot) {
        System.out.println("请输入热搜名称：");
        String name = sc.nextLine();
        int re=hotpotService.addHotpot(name,isSuperHotpot);
        if(re==0){
            System.out.println("热搜添加成功！");
        }else{
            System.out.println("热搜添加失败！");
        }
    }

    //购买热搜
    private void buyHotpot() {
        String hotpotName="";
        int pos=0;
        double price=0;
        try{
            System.out.println("请输入要购买的热搜名称");
            hotpotName=sc.next();
            System.out.println("请输入要购买的热搜位置");
            pos=sc.nextInt();
            System.out.println("请输入要购买的热搜价格");
            price=sc.nextDouble();
            if(pos<0||price<0){
                throw new Exception("输入数据不合法");
            }
        }catch(Exception e){
            System.out.println("请输入合法正整数");
        }
        int re = hotpotService.buyHotpot(hotpotName, pos, price);
        if(re==0){
            System.out.println("热搜购买成功！");
        }else {
            System.out.println("热搜购买失败！");
        }
    }

    //为热搜投票
    private void vote() {
        while(true){
            System.out.println("请输入热搜名称：(输入exit退出投票)");
            String hotpotName=sc.next();
            if("exit".equals(hotpotName)){
                break;
            }
            System.out.println("请输入要投的票数");
            int votesNum=0;
            try{
                votesNum=sc.nextInt();
            }catch (Exception e){
                System.out.println("请输入整数票数");
                continue;
            }
            if(votesNum<0||votesNum>currentUser.getRemainVotes()){
                System.out.println("票数需为正整数且不得大于当前用户剩余票数，当前用户剩余票数："+currentUser.getRemainVotes());
                continue;
            }

            //投票
            int result=hotpotService.vote(currentUser,hotpotName,votesNum);
            if(result>=0){
                System.out.println("投票成功");
                System.out.printf("当前用户剩余%d票",result);
//                System.out.println("当前用户剩余"+result+"票");
                break;
            }else {
                System.out.println("投票失败");
            }
        }
    }






}
