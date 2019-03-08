package de.slowloris.community.v2.utils.music;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

public class MusicManager {

    private File file;
    private SongPlayer player;


    public void play(File file){
        this.file = file;
        Song song = NBSDecoder.parse(file);
        player = new RadioSongPlayer(song);
        player.setAutoDestroy(true);
        for (Player all : Bukkit.getOnlinePlayers()) {
            player.addPlayer(all);
        }
        player.setPlaying(true);
    }

    public void pause(){
        player.setPlaying(false);
    }

    public void stop(){
        player.destroy();
    }

    public void playerJoin(Player player){
        this.player.addPlayer(player);
    }
}
