package by.epam.note.command.impl;

import by.epam.note.command.Command;
import by.epam.note.service.NoteBaseService;
import by.epam.note.service.ServiceProvider;

public class AddNoteCommand implements Command {

    @Override
    public void execute() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        NoteBaseService noteBaseService = serviceProvider.getNoteBaseService();

        noteBaseService.addNote();
    }
}
