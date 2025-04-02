package com.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import com.model.Measure;
import com.model.Note;

class SheetMusicTest {

    @Test
    void testMeasureCreation() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("C5", "q"));
        notes.add(new Note("D5", "h"));

        Measure measure = new Measure(notes, 120, "4/4");

        assertNotNull(measure);
        assertEquals(120, measure.getTempo());
        assertEquals("4/4", measure.getTimeSignature());
        assertEquals(2, measure.getNotes().size());
    }
}
