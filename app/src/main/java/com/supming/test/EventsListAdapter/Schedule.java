package com.supming.test.EventsListAdapter;



public class Schedule {

    private String Smonday;
    private String Stuesday;
    private String Swednesday;
    private String Sthursday;
    private String Sfriday;
    private String Ssaturday;

   public Schedule(String Smonday , String Stuesday, String Swednesday , String Sthursday , String Sfriday, String Ssaturday) {
       this.Smonday = Smonday;
       this.Stuesday = Stuesday;
       this.Swednesday = Swednesday ;
       this.Sthursday = Sthursday ;
       this.Sfriday = Sfriday ;
       this.Ssaturday = Ssaturday ;
    }

    public Schedule(){}

    public String getSmonday() {return Smonday;}
    public void setSmonday(String Smonday){this.Smonday=Smonday;}

    public String getStuesday() {return Stuesday;}
    public void setStuesday(String Stuesday){this.Stuesday=Stuesday;}

    public String getSwednesday() {return Swednesday;}
    public void setSwednesday(String Swednesday){this.Swednesday=Swednesday;}

    public String getSthursday() {return Sthursday;}
    public void setSthursday(String Sthursday){this.Sthursday=Sthursday;}

    public String getSfriday() {return Sfriday;}
    public void setSfriday(String Sfriday){this.Sfriday=Sfriday;}

    public String getSsaturday() {return Ssaturday;}
    public void setSsaturday(String Ssaturday){this.Ssaturday=Ssaturday;}
}
