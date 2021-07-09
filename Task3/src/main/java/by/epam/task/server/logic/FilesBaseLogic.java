package by.epam.task.server.logic;

import by.epam.task.server.entity.File;
import by.epam.task.server.entity.FilesBase;
import by.epam.task.server.entity.Subject;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.*;

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

        Element id = new Element("id");
        id.appendChild("id");
        Element firstName = new Element("firstName");
        firstName.appendChild(studentFile.getStudent().getFirstName());
        Element secondName = new Element("secondName");
        secondName.appendChild(studentFile.getStudent().getSecondName());
        Element age = new Element("age");
        age.appendChild(studentFile.getStudent().getAge() + "");
        Element groupNumber = new Element("groupNumber");
        groupNumber.appendChild(studentFile.getStudent().getGroupNumber() + "");

        file.appendChild(id);
        file.appendChild(firstName);
        file.appendChild(secondName);
        file.appendChild(age);
        file.appendChild(groupNumber);

        if (studentFile.getProgress().containsKey(Subject.MATH)) {
            Element math = new Element("math");
            math.appendChild(String.valueOf(studentFile.getProgress().get(Subject.MATH)));
            file.appendChild(math);
        }

        if (studentFile.getProgress().containsKey(Subject.ENGLISH)) {
            Element english = new Element("english");
            english.appendChild(String.valueOf(studentFile.getProgress().get(Subject.ENGLISH)));
            file.appendChild(english);
        }

        if (studentFile.getProgress().containsKey(Subject.GEOGRAPHY)) {
            Element geography = new Element("geography");
            geography.appendChild(String.valueOf(studentFile.getProgress().get(Subject.GEOGRAPHY)));
            file.appendChild(geography);
        }

        if (studentFile.getProgress().containsKey(Subject.PHYSICS)) {
            Element physics = new Element("physics");
            physics.appendChild(String.valueOf(studentFile.getProgress().get(Subject.PHYSICS)));
            file.appendChild(physics);
        }

        if (studentFile.getProgress().containsKey(Subject.LITERATURE)) {
            Element literature = new Element("literature");
            literature.appendChild(String.valueOf(studentFile.getProgress().get(Subject.LITERATURE)));
            file.appendChild(literature);
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

    public void readXml() {

    }

}
