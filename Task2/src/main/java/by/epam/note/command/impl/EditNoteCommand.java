package by.epam.note.command.impl;

import by.epam.library.controller.impl.ServiceController;
import by.epam.note.command.Command;
import by.epam.note.service.NoteBaseService;
import by.epam.note.service.ServiceProvider;

public class EditNoteCommand implements Command {
    @Override
    public void execute() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        NoteBaseService noteBaseService = serviceProvider.getNoteBaseService();

        noteBaseService.editNote();
    }
}
