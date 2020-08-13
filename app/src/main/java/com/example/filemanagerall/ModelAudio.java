package com.example.filemanagerall;

public class ModelAudio {

    long SongId;
    String SongTitle;
    String SongArtist;

    public ModelAudio(long thisId, String thisTitle, String thisArtist) {
        this.SongId = thisId;
        this.SongTitle = thisTitle;
        this.SongArtist = thisArtist;

    }

    public long getSongId() {
        return SongId;
    }

    public void setSongId(long songId) {
        SongId = songId;
    }

    public String getSongTitle() {
        return SongTitle;
    }

    public void setSongTitle(String songTitle) {
        SongTitle = songTitle;
    }

    public String getSongArtist() {
        return SongArtist;
    }

    public void setSongArtist(String songArtist) {
        SongArtist = songArtist;
    }

}
