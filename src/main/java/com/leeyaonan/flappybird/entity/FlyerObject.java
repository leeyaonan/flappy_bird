package com.leeyaonan.flappybird.entity;

import java.awt.*;

/**
 * 飞行物抽象类，所有飞翔的对象都需要继承该类
 */
public abstract class FlyerObject {

    /**
     * 振翅
     */
    public abstract void flap();

    /**
     * 坠落
     */
    public abstract void fall();

    /**
     * 释放按键
     */
    public abstract void keyReleased();

    public void draw(Graphics bufG) {

    }
}
