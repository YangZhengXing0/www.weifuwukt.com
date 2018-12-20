package Adapter.player;

import Adapter.player.AdvancedMediaPlayer;

/**
 * @author 杨郑兴
 * @Date 2018/12/13 19:42
 * @官网 www.weifuwukt.com
 */
public class Mp4Player implements AdvancedMediaPlayer {
    public void playVlc(String fileName) {

    }

    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: "+ fileName);
    }
}
