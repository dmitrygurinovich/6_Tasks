package by.epam.task.server.logic;

import by.epam.task.server.entity.File;
import by.epam.task.server.entity.FilesBase;
import by.epam.task.server.entity.Subject;
import nu.xom.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class FilesBaseLogic {
    private static final String filesBasePath = "Task3/src/main/resources/files.xml";

    public FilesBaseLogic() {

    }

    public static void format(OutputStream stream, Document doc) throws Exception {
        Serializer serializer = new Serializer(stream, "ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

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

    public Document getXmlDocument(FilesBase base) {
        Element files = new Element("files");
        for (File file : base.getFiles()) {
            files.appendChild(getXmlElement(file));
        }
        return new Document(files);
    }

    public void writeFilesToXml(FilesBase base) throws Exception {
        format(new BufferedOutputStream(new FileOutputStream(filesBasePath)), getXmlDocument(base));
    }

    public ArrayList<File> readFilesFromXml() throws ParsingException, IOException {
        Document document = new Builder()
                .build(filesBasePath);

        ArrayList<File> filesList = new ArrayList<>();

        Elements elements = document.getRootElement().getChildElements();

        for (int i = 0; i < elements.size(); i++) {
            filesList.add(new File(elements.get(i)));
        }

        return filesList;
    }
}
