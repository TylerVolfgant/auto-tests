package ru.stqa.prf.bookaddress.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.prf.bookaddress.model.ContactData;
import ru.stqa.prf.bookaddress.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        }else{
            System.out.println("Unrecognized format " + format);
        }

    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        Random rand = new Random();
        for (int i = 0; i < count ; i++) {
            contacts.add(new ContactData()
                    .withFirstname(String.format("firstname %s", i))
                    .withLastname(String.format("lastname %s", i))
                    .withHomePhone(String.format(rand.nextInt(1000)+ "%s", i))
                    .withWorkPhone(String.format(rand.nextInt(1000)+ "%s", i))
                    .withMobilePhone(String.format(rand.nextInt(1000) + "%s", i)));
        }
        return contacts;
    }

    private void saveAsJson(List<ContactData> contacts, File file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void saveAsXml(List<ContactData> contacts, File file) {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
