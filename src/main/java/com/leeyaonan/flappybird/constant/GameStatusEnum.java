package com.leeyaonan.flappybird.constant;

/**
 * 游戏状态枚举
 */
public enum GameStatusEnum {

    /**
     * 就绪
     */
    GAME_READY(0),
    /**
     * 游戏开始，进行中
     */
    GAME_START(1),
    /**
     * 游戏暂停
     */
    GAME_PAUSE(2),
    /**
     * 游戏结束
     */
    GAME_OVER(3);

    int status;

    GameStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
