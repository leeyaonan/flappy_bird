package com.leeyaonan.flappybird;

import com.leeyaonan.flappybird.constant.GameModeEnum;
import com.leeyaonan.flappybird.entity.Game;

/**
 * 游戏启动器
 */
public class Application {

    public static void main(String[] args) {
        // todo:从配置文件中读取游戏模式开启金手指
        Game.getInstance(GameModeEnum.NORMAL);
    }
}
