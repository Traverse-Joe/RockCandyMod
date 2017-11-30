package kiba.rockcandy.items.special_candy;

import kiba.rockcandy.items.BaseFoodItem;

public class BaseSpecialCandy extends BaseFoodItem {
    public BaseSpecialCandy(String name, int amount, float saturation, boolean isWolfFood) {
        super(name, amount, saturation, isWolfFood);
        this.setAlwaysEdible();

    }

}
