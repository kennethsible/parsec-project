/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsecsei;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author kensible
 */
public class APIConnection {
    
    private HashMap<String, char[]> login;
    private HashMap<String, String> courses;
    private HashMap<String, String[]> users;
    
    public APIConnection() {
        File login_db = new File("login_database.txt");
        login = new HashMap<>();
        try {
            Scanner reader = new Scanner(login_db);
            while(reader.hasNext()) {
                String[] userLogin = reader.nextLine().split(" ");
                login.put(userLogin[0], userLogin[1].toCharArray());
            }
        } catch (FileNotFoundException e) {
            System.err.println("[System] Login Database Failure");
        }
        
        File user_db = new File("user_database.txt");
        users = new HashMap<>();
        try {
            Scanner reader = new Scanner(user_db);
            while(reader.hasNext()) {
                String[] userInfo = reader.nextLine().split(";");
                users.put(userInfo[0], userInfo);
            }
        } catch (FileNotFoundException e) {
            System.err.println("[System] User Database Failure");
        }
        
        File course_db = new File("course_database.txt");
        courses = new HashMap<>();
        try {
            Scanner reader = new Scanner(course_db);
            while(reader.hasNext()) {
                String[] courseInfo = reader.nextLine().split(";");
                courses.put(courseInfo[0], courseInfo[1] + ";" + courseInfo[2]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("[System] Course Database Failure");
        }
    }
    
    public HashMap<String, char[]> retrieveLogin() {
        return login;
    }
    
    public String[] retrieveUser(String username) {
        return users.get(username);
    }
    
    public HashMap<String, String> retrieveCourses(String[] CRNList) {
        HashMap<String, String> courseList = new HashMap<>();
        for (String CRN : CRNList) {
            String[] courseInfo = courses.get(CRN).split(";");
            courseList.put(courseInfo[0], courseInfo[1]);
        }
        return courseList;
    }
    
}
