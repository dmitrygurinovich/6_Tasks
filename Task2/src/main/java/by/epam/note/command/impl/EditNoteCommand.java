package by.epam.note.command.impl;

import by.epam.note.command.Command;
import by.epam.note.service.NoteBaseService;
import by.epam.note.service.ServiceProvider;

public class EditNoteCommand implements Command {
    private final NoteBaseService noteBaseService = ServiceProvider.getInstance().getNoteBaseService();

    @Override
    public void execute() {
        noteBaseService.editNote();
    }
}
