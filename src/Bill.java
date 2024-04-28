import java.util.ArrayList;
import java.util.Date;


public class Bill {
    private static int  id=0;
    private Date date;
    private double total;
    ArrayList<Medecine> med;

    Bill()
    {
        id++;
        med=new ArrayList<>();
        total=0;
        date=new Date();

    }
    public void addMed(Medecine m)
    {
        med.add(m);
    }
    public void CalcTotal()
    {
        for(int i=0;i<med.size();i++)
        {
            total=total+med.get(i).getPrice();
        }
    }



    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Bill.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Medecine> getMed() {
        return med;
    }

    public void setMed(ArrayList<Medecine> med) {
        this.med = med;
    }
}
