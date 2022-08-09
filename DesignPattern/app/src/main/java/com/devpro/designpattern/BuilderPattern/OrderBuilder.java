package com.devpro.designpattern.BuilderPattern;

import com.devpro.designpattern.BuilderPattern.type.BreadType;
import com.devpro.designpattern.BuilderPattern.type.OrderType;
import com.devpro.designpattern.BuilderPattern.type.SauceType;
import com.devpro.designpattern.BuilderPattern.type.VegetableType;

public interface OrderBuilder {
    OrderBuilder orderType(OrderType orderType);

    OrderBuilder orderBread(BreadType breadType);

    OrderBuilder orderSauce(SauceType sauceType);

    OrderBuilder orderVegetable(VegetableType vegetableType);

    Order build();

}
