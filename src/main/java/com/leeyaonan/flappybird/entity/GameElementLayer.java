package com.leeyaonan.flappybird.entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏元素层
 */
public class GameElementLayer {

    private final List<BarrierObject> barrierObjectList;

    public GameElementLayer() {
        barrierObjectList = new ArrayList<>();
    }

    public void draw(Graphics bufG, FlyerObject flyer) {

    }
}
