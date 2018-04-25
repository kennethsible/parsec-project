package parsecsei;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIConnect {
    
    private HashMap<String, char[]> login; // <Username, Password>
    private HashMap<String, Course> courses; // <CRN, Course>
    private HashMap<String, User> users; // <Username, User>
    private ArrayList<Question> qpool;
    private ArrayList<Question> questions;
    
    public APIConnect() {
        File login_db = new File("login_database.txt");
        login = new HashMap<>();
        try {
            Scanner reader = new Scanner(login_db);
            while(reader.hasNext()) {
                String[] userLogin = reader.nextLine().split(" ");
                login.put(userLogin[0], userLogin[1].toCharArray());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("[System] Login Database Failure");
        }
        
        File user_db = new File("user_database.txt");
        users = new HashMap<>();
        try {
            Scanner reader = new Scanner(user_db);
            while(reader.hasNext()) {
                String[] userInfo = reader.nextLine().split(";");
                User.Position pos;
                switch (userInfo[1]) {
                    case "student":
                        pos = User.Position.STUDENT;
                        break;
                    case "faculty":
                        pos = User.Position.FACULTY;
                        break;
                    default:
                        pos = User.Position.ADMIN;
                        break;
                }
                users.put(userInfo[0], new User(pos, userInfo[2], userInfo[3].split(",")));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("[System] User Database Failure");
        }
        
        File course_db = new File("course_database.txt");
        courses = new HashMap<>();
        try {
            Scanner reader = new Scanner(course_db);
            while(reader.hasNext()) {
                String[] courseInfo = reader.nextLine().split(";");
                boolean status = courseInfo[3].equals("open");
                courses.put(courseInfo[0], new Course(courseInfo[0], courseInfo[1], courseInfo[2],
                    new DateTime(status)));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("[System] Course Database Failure");
        }
        
        File qp_db = new File("qp_database.txt");
        qpool = new ArrayList<>();
        try {
            Scanner reader = new Scanner(qp_db);
            while(reader.hasNext()) {
                String[] qpInfo = reader.nextLine().split(";");
                qpool.add(new Question(qpInfo[0].equals("enabled"), qpInfo[1]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("[System] Question Pool Database Failure");
        }
        
        File select_db = new File("sq_database.txt");
        questions = new ArrayList<>();
        try {
            Scanner reader = new Scanner(select_db);
            while(reader.hasNext()) {
                String[] qpInfo = reader.nextLine().split(";");
                questions.add(new Question(qpInfo[0].equals("enabled"), qpInfo[1]));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("[System] Selection Database Failure");
        }
    }
    
    public HashMap<String, char[]> retrieveLogin() {
        return login;
    }
    
    public User retrieveUser(String username) {
        return users.get(username);
    }
    
    public ArrayList<Course> retrieveCourses(String[] CRNList) {
        ArrayList<Course> courseList = new ArrayList<>();
        for (String CRN : CRNList)
            courseList.add(courses.get(CRN));
        return courseList;
    }
    
    public ArrayList<Question> retrieveQPool() {
        return qpool;
    }
    
    public ArrayList<Question> retrieveQuestions() {
        return questions;
    }
    
    public void addQuestion(boolean status, String question) {
        FileWriter fw = null;
        try {
            qpool.add(new Question(status, question));
            File qp_db = new File("qp_database.txt");
            fw = new FileWriter(qp_db, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String content = status ? "enabled" : "disabled";
            bw.write(content + question);
            bw.close();
        } catch (IOException ex) {
            System.err.println("[System] File Opening Failed");
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                System.err.println("[System] File Closure Failed");
            }
        }
    }
    
}
