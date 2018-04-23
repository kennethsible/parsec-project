package parsecsei;

public class User {
    
    public static enum Position { STUDENT, FACULTY, ADMIN };
    
    private Position pos;
    private String name;
    private String[] CRNList;
    
    public User(Position pos, String name, String[] CRNList) {
        this.pos = pos;
        this.name = name;
        this.CRNList = CRNList;
    }
    
    public Position getPosition() {
        return pos;
    }
    
    public String getName() {
        return name;
    }
    
    public String[] getCRNList() {
        return CRNList;
    }
    
}
