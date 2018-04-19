/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsecsei;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    private HashMap<Boolean, String> qpool;
    private ArrayList<String> questions;
    
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
        
        File qp_db = new File("qp_database.txt");
        qpool = new HashMap<>();
        try {
            Scanner reader = new Scanner(qp_db);
            while(reader.hasNext()) {
                String[] qpInfo = reader.nextLine().split(";");
                qpool.put(qpInfo[0].equals("enabled"), qpInfo[1]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("[System] Question Pool Database Failure");
        }
        
        File select_db = new File("select_database.txt");
        questions = new ArrayList<>();
        try {
            Scanner reader = new Scanner(select_db);
            while(reader.hasNext()) {
                questions.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("[System] Selection Database Failure");
        }
        for (String question : questions)
                System.out.println(question);
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
    
    public HashMap<Boolean, String> retrieveQPool() {
        return qpool;
    }
    
    public ArrayList<String> retrieveQuestions() {
        return questions;
    }
    
    
    
}
