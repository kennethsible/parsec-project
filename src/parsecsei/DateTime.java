package parsecsei;

class DateTime {
    
    private boolean status;
    
    public DateTime(boolean status) {
        this.status = status;
    }
    
    public boolean isOpen() {
        return status;
    }
    
    public void openSurvey() {
        this.status = true;
    }
    
    public void closeSurvey() {
        this.status = false;
    }
    
}
