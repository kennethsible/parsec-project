/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsecsei;

/**
 *
 * @author HP
 */

public class Email {
    
    public static void main(String [] args){
     // You can put students email addresss here. but I just putted just in case
        //String[] to={"leen6225@gmail.com", "projecttesting951@gmail.com","hp0006@mix.wvu.edu"};
        String[] faculty={"leen6225@gmail.com"};
        String[] student={"leen6225@gmail.com"};
        String facultyReminder= "Faculties, \nPlease Complete and open the survey forms";
        String studentReminder= "Students, \nThe survey is now opened. \nPlease Complete SEI";
        String facultySummary= "Summary of SEI Evaluation :";//+summary results
        //use if and else statement
        
        //call open 
        Reminder(faculty,facultyReminder);
        Reminder(student,studentReminder);
        Reminder(faculty,facultySummary);
        
        
        
        
       //if survey is open, send to students
       //else if student who took survey is 0, send reminder to professors
       //if survey is closed and the stutdent who took survey is not zero send summary to the professor :)
     
    /*
      if(Send.sendMail
      ("projecttesting951@gmail.com", 
      "vmfhwprxm", 
      ("Reminder: \n"+"SEI is now opened. \nPlease Complete your SEI"), 
      to))
      {System.out.println("email successfully sent");}
      else
      {System.out.println("ERROR OCCURED");}*/
 
    }
    
    public static void Reminder(String[] arrayList, String EmailContents){
       if(Send.sendMail
      ("projecttesting951@gmail.com", 
      "vmfhwprxm", 
      ("Reminder: \n"+EmailContents), 
      arrayList))
      {System.out.println("email successfully sent");}
      else
      {System.out.println("ERROR OCCURED");}
    
    }
   
  
}