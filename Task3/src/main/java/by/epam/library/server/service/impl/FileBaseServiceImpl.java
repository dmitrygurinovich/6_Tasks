package by.epam.library.server.service.impl;

import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.FilesBaseDAO;
import by.epam.library.server.entity.File;
import by.epam.library.server.entity.Subject;
import by.epam.library.server.service.FileBaseService;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.IOException;
import java.io.OutputStream;

public class FileBaseServiceImpl implements FileBaseService {
    private final FilesBaseDAO filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

    @Override
    public void format(OutputStream stream, Document doc) {
        try {
            Serializer serializer = new Serializer(stream, "ISO-8859-1");
            serializer.setIndent(4);
            serializer.setMaxLength(60);
            serializer.write(doc);
            serializer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Element getXmlElement(File studentFile) {
        Element file = new Element("file");

        Element student = new Element("student");

        Element id = new Element("id");
        id.appendChild(Integer.toString(studentFile.getId()));
        file.appendChild(id);

        file.appendChild(student);

        Element firstName = new Element("first-name");
        firstName.appendChild(studentFile.getStudent().getFirstName());
        Element secondName = new Element("second-name");
        secondName.appendChild(studentFile.getStudent().getSecondName());
        Element age = new Element("age");
        age.appendChild(Integer.toString(studentFile.getStudent().getAge()));
        Element groupNumber = new Element("group-number");
        groupNumber.appendChild(Integer.toString(studentFile.getStudent().getGroupNumber()));

        student.appendChild(firstName);
        student.appendChild(secondName);
        student.appendChild(age);
        student.appendChild(groupNumber);

        if (!studentFile.getProgress().isEmpty()) {
            Element progress = new Element("progress");
            file.appendChild(progress);

            if (studentFile.getProgress().containsKey(Subject.MATH)) {
                Element math = new Element("math");
                math.appendChild(String.valueOf(studentFile.getProgress().get(Subject.MATH)));
                progress.appendChild(math);
            }

            if (studentFile.getProgress().containsKey(Subject.ENGLISH)) {
                Element english = new Element("english");
                english.appendChild(String.valueOf(studentFile.getProgress().get(Subject.ENGLISH)));
                progress.appendChild(english);
            }

            if (studentFile.getProgress().containsKey(Subject.GEOGRAPHY)) {
                Element geography = new Element("geography");
                geography.appendChild(String.valueOf(studentFile.getProgress().get(Subject.GEOGRAPHY)));
                progress.appendChild(geography);
            }

            if (studentFile.getProgress().containsKey(Subject.PHYSICS)) {
                Element physics = new Element("physics");
                physics.appendChild(String.valueOf(studentFile.getProgress().get(Subject.PHYSICS)));
                progress.appendChild(physics);
            }

            if (studentFile.getProgress().containsKey(Subject.LITERATURE)) {
                Element literature = new Element("literature");
                literature.appendChild(String.valueOf(studentFile.getProgress().get(Subject.LITERATURE)));
                progress.appendChild(literature);
            }
        }

        return file;
    }

    @Override
    public Document getXmlDocument() {
        Element files = new Element("files");
        for (File file : filesBaseDAO.getFiles()) {
            files.appendChild(getXmlElement(file));
        }
        return new Document(files);
    }

    @Override
    public void addFile(File file) {
        try {
            filesBaseDAO.getFiles().add(file);
            filesBaseDAO.writeFilesToXml();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void deleteFile(int fileId) {
        filesBaseDAO.getFiles().remove(fileId - 1);
        for (int i = 0; i < filesBaseDAO.getFiles().size(); i++) {
            filesBaseDAO.getFiles().get(i).setId(i + 1);
        }
        try {
            filesBaseDAO.writeFilesToXml();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

