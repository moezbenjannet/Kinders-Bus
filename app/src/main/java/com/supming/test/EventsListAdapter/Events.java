package com.supming.test.EventsListAdapter;



public class Events {

    private int eventid;
    private String eventtitle;
    private int eventjour;
    private int eventmois;
    private int eventannee;
    private int eventminute;
    private int eventheure;
    private String eventplace;
    private byte[] eventsimage;

   public Events(int eventid , String eventtitle,int eventjour ,int eventmois ,int eventannee, int eventminute,int eventheure, String eventplace , byte[] eventsimage) {
       this.eventid = eventid;
       this.eventtitle = eventtitle;
       this.eventjour = eventjour ;
       this.eventmois = eventmois ;
       this.eventannee = eventannee ;
       this.eventminute = eventminute ;
       this.eventplace = eventplace;
       this.eventsimage = eventsimage ;
       this.eventheure = eventheure ;
    }
    public Events(int eventid,String eventtitle,int eventjour ,int eventmois ,int eventannee, int eventminute,int eventheure, String eventplace) {
        this.eventid = eventid;
        this.eventtitle = eventtitle;
        this.eventjour = eventjour ;
        this.eventmois = eventmois ;
        this.eventannee = eventannee ;
        this.eventminute = eventminute ;
        this.eventplace = eventplace;
        this.eventheure = eventheure ;
    }

    public Events(){}

    public int geteventid() {return eventid;}
    public void seteventid(int eventid){this.eventid=eventid;}

    public int geteventmois() {return eventmois;}
    public void seteventmois(int eventmois){this.eventmois=eventmois;}

    public int geteventjour() {return eventjour;}
    public void seteventjour(int eventjour){this.eventjour=eventjour;}

    public int geteventannee() {return eventannee;}
    public void seteventannee(int eventannee){this.eventannee=eventannee;}

    public String geteventtitle() {return eventtitle;}
    public void seteventtitle(String eventtitle){this.eventtitle=eventtitle;}

    public int geteventminute() {
        return eventminute;
    }
    public void seteventminute(int eventminute){this.eventminute=eventminute;}

    public int geteventheure() {
        return eventheure;
    }
    public void seteventheure(int eventheure){this.eventheure=eventheure;}


    public String geteventplace() {
        return eventplace;
    }
    public void seteventplace(String eventplace){this.eventplace=eventplace;}

    public byte[] geteventsimage() {
        return eventsimage;
    }
    public void seteventsimage(byte[] eventsimage){this.eventsimage=eventsimage;}

}
