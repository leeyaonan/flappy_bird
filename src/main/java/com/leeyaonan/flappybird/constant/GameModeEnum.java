package com.leeyaonan.flappybird.constant;

public enum GameModeEnum {
    /**
     * 普通模式：标准游戏规则
     */
    NORMAL(0),
    /**
     * 金手指模式：无视障碍物
     */
    GOLDEN_FINGER(1);

    int mode;

    GameModeEnum(int mode) {
        this.mode = mode;
    }
}
