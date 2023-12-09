package example.note;

import org.junit.Test;

import static org.junit.Assert.*;

public class NoteLogicTest {
    @Test
    public void TestAddAndListNote() {
        NoteLogic noteLogic = new NoteLogic();
        noteLogic.handleMessage("/add abc");
        String result = noteLogic.handleMessage("/notes");
        assertEquals("Your notes:\n 1. abc", result);
    }

    @Test
    public void TestEditNote() {
        NoteLogic noteLogic = new NoteLogic();
        noteLogic.handleMessage("/add abc");
        noteLogic.handleMessage("/edit def");
        String result = noteLogic.handleMessage("/notes");
        assertEquals("Your notes:\n 1. def", result);
    }

    @Test
    public void TestDeleteNote() {
        NoteLogic noteLogic = new NoteLogic();
        noteLogic.handleMessage("/add abc");
        noteLogic.handleMessage("/add def");
        assertEquals("Note deleted!", noteLogic.handleMessage("/del 1"));
        String result = noteLogic.handleMessage("/notes");
        assertEquals("Your notes:\n 1. def", result);
    }
}