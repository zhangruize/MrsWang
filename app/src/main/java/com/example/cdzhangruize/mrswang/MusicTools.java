package com.example.cdzhangruize.mrswang;

import com.example.cdzhangruize.mrswang.pojo.ResultPojo;
import com.example.cdzhangruize.mrswang.pojo.SongListPojo;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

public class MusicTools {
    public static String generateListVocalString(InputStream search) {
        InputStreamReader reader = new InputStreamReader(search);
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        ResultPojo resultPojo = gson.fromJson(reader, ResultPojo.class);
        SongListPojo songListPojo = gson.fromJson(gson.toJson(resultPojo.result), SongListPojo.class);
        if (songListPojo.songCount > 0) {
            int readCount = songListPojo.songCount > 5 ? 5 : songListPojo.songCount;
            sb.append("找到了" + songListPojo.songCount + "首相关歌曲，");
            for (int i = 0; i < 5; i++) {
                sb.append("第" + (i + 1) + "首，" + songListPojo.songs.get(i).name);
            }
            sb.append("你想听哪一首？");
        } else {
            sb.append("没有找到结果");
        }
        return sb.toString();
    }
}
