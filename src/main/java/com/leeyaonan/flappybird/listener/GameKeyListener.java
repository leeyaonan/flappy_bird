package com.leeyaonan.flappybird.listener;

import com.leeyaonan.flappybird.entity.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    /**
     * 按下按键，根据游戏当前的状态调用不同的方法
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        Game.getInstance().keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Game.getInstance().keyReleased(e.getKeyCode());
    }
}
