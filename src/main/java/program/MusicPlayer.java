package com.program;

import com.music.Music;

public class MusicPlayer {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        player.playSong();
    }

    public void playSong() {
        try {
            playChords1();
            Thread.sleep(400);
            playChords2();
            Thread.sleep(400);
            playChords3();
            Thread.sleep(400);
            playChords4();
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playChords1() {
        Music.playNote("E4min G4 B4 D5");  // Em7 chord
    }

    private void playChords2() {
        Music.playNote("D4maj F#4 A4 C5"); // D major 7
    }

    private void playChords3() {
        Music.playNote("C4maj E4 G4 B4");  // Cmaj7 chord
    }

    private void playChords4() {
        Music.playNote("A3min C4 E4 G4");  // Am7 chord
    }
}
