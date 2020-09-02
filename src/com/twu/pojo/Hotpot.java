package com.twu.pojo;

public class Hotpot {
    //热搜名称
    private String name;
    //是否为超级热搜
    private boolean isSuperHotpot;
    //买热搜的排序，默认为0
    private int rank;
    //买热搜的钱数，默认为0
    private double boughtPrice;
    //热搜的票数，默认为0
    private int votes;
    //是否被买热搜替换掉，默认为false
    private boolean isReplaced;
    public Hotpot(String name, boolean isSuperHotpot) {
        this.name = name;
        this.isSuperHotpot = isSuperHotpot;
        this.boughtPrice=0;
        this.votes=0;
        this.rank=0;
    }


    public boolean isReplaced() {
        return isReplaced;
    }

    public void setReplaced(boolean replaced) {
        isReplaced = replaced;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuperHotpot() {
        return isSuperHotpot;
    }

    public void setSuperHotpot(boolean superHotpot) {
        isSuperHotpot = superHotpot;
    }

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
