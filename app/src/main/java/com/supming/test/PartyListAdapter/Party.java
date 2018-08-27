package com.supming.test.PartyListAdapter;



public class Party {

    private String partytitle;
    private int partyjour;
    private int partymois;
    private int partyannee;
    private byte[] partyimage;

   public Party(String partytitle, int partyjour , int partymois , int partyannee,byte[] partyimage) {

       this.partytitle = partytitle;
       this.partyjour = partyjour ;
       this.partymois = partymois ;
       this.partyannee = partyannee ;
       this.partyimage = partyimage ;
    }
    public Party(String partytitle, int partyjour , int partymois , int partyannee) {

        this.partytitle = partytitle;
        this.partyjour = partyjour ;
        this.partymois = partymois ;
        this.partyannee = partyannee ;
    }

    public Party(){}

    public int getpartymois() {return partymois;}
    public void setpartymois(int partymois){this.partymois=partymois;}

    public int getpartyjour() {return partyjour;}
    public void setpartyjour(int partyjour){this.partyjour=partyjour;}

    public int getpartyannee() {return partyannee;}
    public void setpartyannee(int partyannee){this.partyannee=partyannee;}

    public String getpartytitle() {return partytitle;}
    public void setpartytitle(String partytitle){this.partytitle=partytitle;}

    public byte[] getpartyimage() {
        return partyimage;
    }
    public void setpartyimage(byte[] partyimage){this.partyimage=partyimage;}

}
