package com.example.cdzhangruize.mrswang;

import com.example.cdzhangruize.mrswang.pojo.ArtistPojo;
import com.example.cdzhangruize.mrswang.pojo.ResultPojo;
import com.example.cdzhangruize.mrswang.pojo.SongListPojo;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class MusicTools {
    public static String generateListVocalString(InputStream search) {
        InputStreamReader reader = new InputStreamReader(search);
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        ResultPojo resultPojo = gson.fromJson(reader, ResultPojo.class);
        SongListPojo songListPojo = gson.fromJson(gson.toJson(resultPojo.result), SongListPojo.class);
        if (songListPojo == null || songListPojo.songs == null) {
            return null;
        }
        if (songListPojo.songCount > 0) {
            int readCount = songListPojo.songCount > 5 ? 5 : songListPojo.songCount;
            sb.append("找到了").append(songListPojo.songCount).append("首相关歌曲，");
            for (int i = 0; i < readCount; i++) {
                sb.append("第").append(i + 1).append("首，");
                if (songListPojo.songs.get(i).artists != null) {
                    ArrayList<ArtistPojo> artists = songListPojo.songs.get(i).artists;
                    for (int j = 0; j < artists.size(); j++) {
                        if (artists.get(j) != null && artists.get(j).name != null) {
                            sb.append(artists.get(j).name);
                            if (j != artists.size() - 1) {
                                sb.append("和");
                            }
                        }
                    }
                }
                sb.append("创作的");
                sb.append(songListPojo.songs.get(i).name);
            }
            sb.append("你想听哪一首？");
        } else {
            sb.append("没有找到结果");
        }
        return sb.toString();
    }
}
