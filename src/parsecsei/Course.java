package parsecsei;

public class Course {
    
    private String CRN;
    private String name;
    private String instructor;
    private DateTime dt;
    
    public Course(String CRN, String name, String instructor, DateTime dt) {
        this.CRN = CRN;
        this.name = name;
        this.instructor = instructor;
        this.dt = dt;
    }
    
    public String getCRN() {
        return CRN;
    }
    
    public String getName() {
        return name;
    }
    
    public String getInstructor() {
        return instructor;
    }
    
    public DateTime getDateTime() {
        return dt;
    }
    
}
