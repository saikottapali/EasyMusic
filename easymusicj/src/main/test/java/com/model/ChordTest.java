import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChordTest {

    @Test
    void testValidChord() {
        Chord chord = new Chord("Cmaj");
        assertEquals("Cmaj", chord.getName());
    }

    @Test
    void testInvalidChord() {
        assertThrows(IllegalArgumentException.class, () -> new Chord("InvalidChord"));
    }
}

