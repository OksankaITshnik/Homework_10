package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        List<User> userList = readFromFile("C:\\Users\\vital\\IdeaProjects\\mventesting\\src\\main\\java\\org\\example\\nameage.txt");
        writeToJsonFile(userList, "C:\\Users\\vital\\IdeaProjects\\mventesting\\src\\main\\java\\org\\example\\user.json");
    }

    private static List<User> readFromFile(String fileName) {
        List<User> userList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String[] headers = br.readLine().split("\\s+");

            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\s+");
                User user = new User();
                for (int i = 0; i < headers.length; i++) {
                    switch (headers[i]) {
                        case "name":
                            user.setName(values[i]);
                            break;
                        case "age":
                            user.setAge(Integer.parseInt(values[i]));
                            break;
                        // Add other cases for additional fields if needed
                    }
                }
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userList;
    }

    private static void writeToJsonFile(List<User> userList, String jsonFileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (FileWriter fileWriter = new FileWriter(jsonFileName)) {
            objectMapper.writeValue(fileWriter, userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

