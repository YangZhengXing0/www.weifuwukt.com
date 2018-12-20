package Adapter.player;

import Adapter.player.AdvancedMediaPlayer;

/**
 * @author 杨郑兴
 * @Date 2018/12/13 19:41
 * @官网 www.weifuwukt.com
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: "+ fileName);
    }

    public void playMp4(String fileName) {

    }
}
