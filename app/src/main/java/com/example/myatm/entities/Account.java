package com.example.myatm.entities;

public class Account {
    private Integer balance;
    private Integer amount;
    private Integer moneyInHand;

    public Account() {
        this.balance = 3000;
        this.amount = 1000;
        this.moneyInHand = 0;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMoneyInHand() {
        return moneyInHand;
    }

    public void setMoneyInHand(Integer moneyInHand) {
        this.moneyInHand = moneyInHand;
    }

    public void changeAmount() {
        switch (getAmount()) {
            case 1000:
                setAmount(200);
                break;
            case 200:
                setAmount(500);
                break;
            case 500:
                setAmount(1000);
                break;
        }
    }
}
