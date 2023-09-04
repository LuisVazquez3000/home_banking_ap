package com.mindhub.homebanking.utils;

public class randomNumber {

    public static int getRandomNumber(Integer min, Integer max){
        return (int) (Math.random()*(max-min) + min);
    }

}
