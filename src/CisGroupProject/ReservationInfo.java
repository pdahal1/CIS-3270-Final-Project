package CisGroupProject;

public class ReservationInfo{

 String  flyingTo;
 public String  flyingFrom;
 public String  date;

    public ReservationInfo(){
    }

    public ReservationInfo(String flyingTo, String flyingFrom, String date){
       this.flyingTo = flyingTo;
          this.flyingFrom = flyingFrom;
          this.date = date;

    }
    
    public String getFlyingFrom() {
        return flyingFrom;
    }
    public void setFlyingFrom(String flyingFrom) {
        this.flyingFrom = flyingFrom;
    }
    
    public String getflyingTo() {
        return flyingTo;
    }
    public void setflyingTo(String flyingTo) {
     this.flyingTo = flyingTo;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

}
