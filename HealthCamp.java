package Assignment1;

import java.util.*;

public class HealthCamp {
    int p_count; //no of patients in the health camp
    int a_count; // counter for the patients that have been admitted
    HashMap<Integer,Patient>  paccounts;
    HashMap<String,Institute> iaccounts;
    public HealthCamp(int n){
        p_count = n;
        a_count =0;
        paccounts = new HashMap<Integer, Patient>();
        iaccounts = new HashMap<String,Institute>();
    }
    void remove_p(){
        System.out.println("Account ID removed of admitted patients");
    ArrayList<Patient> rp = new ArrayList<>();
        for(Patient p : paccounts.values()){
            if (p.getAdmission_status().contentEquals("Admitted")){
                System.out.println(p.getId());
                rp.add(p);
            }
        }
        if (rp.size()==0){
            System.out.println("No accounts to be removed are present");
        }
        for (int i=0;i<rp.size();i++){
            paccounts.remove(rp.get(i).getId());
        }
    }
    void remove_i(){
        System.out.println("Accounts removed of Institutes whose admission is closed");
        ArrayList<Institute> ri = new ArrayList<>();
        for(Institute i : iaccounts.values()){
            if (i.getStatus().contentEquals("CLOSED")){
                System.out.println(i.getName());
                ri.add(i);
            }
        }
        if (ri.size()==0){
            System.out.println("No Institutes to be be removed are present.");
        }
        for (int i=0;i<ri.size();i++){
        iaccounts.remove(ri.get(i).getName());}
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        HealthCamp camp = new HealthCamp(n);
        for(int i=0;i<n;i++){
            String name = in.next();
            float temp = in.nextFloat();
            int o2 = in.nextInt();
            int a = in.nextInt();
            Patient newp = new Patient(name,temp,o2,a);
            camp.paccounts.put(newp.getId(), newp);
        }
        while (camp.a_count != camp.p_count){
            int q = in.nextInt();
            if (q==1){
                System.out.println("Enter the name of the Institute:");
                String iname = in.next();
                System.out.println("Temperature criteria:");
                float tt = in.nextFloat();
                System.out.println("Lowest Oxygen Levels criteria:");
                int oo = in.nextInt();
                System.out.println("No. of beds available:");
                int bb = in.nextInt();
                Institute newi = new Institute(iname,tt,oo,bb);
                camp.iaccounts.put(iname,newi);
                camp.a_count = camp.a_count + newi.Selection(camp.paccounts);

            }
            else if (q==2){
                camp.remove_p();
            }
            else if (q==3){
                camp.remove_i();
            }
            else if (q==4){
                System.out.println(camp.p_count - camp.a_count + " patients");
            }
            else if (q==5){
                int ans5 =0;
                for(Institute i : camp.iaccounts.values()){
                    if (i.getStatus().contentEquals("OPEN")){
                        ans5++;
                    }
                }
                System.out.println(ans5 + "institutes are admitting patients currently");
            }
            else if (q==6){
                String input = in.next();
                if (camp.iaccounts.containsKey(input)){
                Institute printed =  camp.iaccounts.get(input);
                System.out.println(printed.getName());
                System.out.println("Temperature should be<= " + printed.getMax_temp());
                System.out.println("Oxygen levels should be >= "+ printed.getMin_o2level());
                System.out.println("Number of Available beds – "+ printed.getNo_beds());
                System.out.println("Admission Status – " + printed.getStatus());}
                else {
                    System.out.println("This institute doesn't exist.");
                }

            }
            else if (q == 7){
                int idi = in.nextInt();
                if (camp.paccounts.containsKey(idi)){
                Patient pp = camp.paccounts.get(idi);
                System.out.println(pp.getName());
                System.out.println("Temperature is " + pp.getTemp());
                System.out.println("Oxygen levels is " + pp.getO2level());
                System.out.println("Admission Status –" + pp.getAdmission_status());
                System.out.println("Admitting Institute -" + pp.getInstitute());}
                else {
                    System.out.println("Patient with ID doesn't exist.");
                }
            }
            else if (q==8){
                for(Patient p : camp.paccounts.values()){
                    System.out.println(p.getId()+ " "+ p.getName());
                }
            }
            else if (q==9){
                String input = in.next();
                if (camp.iaccounts.containsKey(input)){
                Institute in1 =  camp.iaccounts.get(input);
                if (in1.getAdmitted_p().size()==0){
                    System.out.println("No patients are admitted here.");
                }
                else{
                for(Patient p : in1.getAdmitted_p().values()){
                    System.out.println(p.getName()+ ","+ "recovery time is "+ p.getRecovery() +"days");
                }}}
                else {
                    System.out.println("This institute doesn't exist.");
                }
            }




        }

    }

}

class Patient {
    private String name;
    private int age;
    private int o2level;
    private float temp;
    private int id;
    static int  id_allocator=0;
    private String admission_status;
    private String institute;
    private int recovery;

    public int getRecovery() {
        return recovery;
    }

    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getAdmission_status() {
        return admission_status;
    }

    public void setAdmission_status(String admission_status) {
        this.admission_status = admission_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getO2level() {
        return o2level;
    }

    public void setO2level(int o2level) {
        this.o2level = o2level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    Patient(String nm,float t, int o2, int a){
        name = nm;
        temp = t;
        o2level = o2;
        age = a;
        id= id_allocator+1;
        id_allocator++;
        admission_status = "Not Admitted";
        institute = "N/A";
        recovery =0;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }
}

class Institute {
    private String name;
    private float max_temp;
    private int min_o2level;
    private int no_beds;
    private int available;
    private String status;
    private HashMap<Integer,Patient> admitted_p = new HashMap<Integer, Patient>();
    Institute(String n,float f, int o, int b){
        name = n;
        max_temp = f;
        min_o2level = o;
        no_beds = b;
        available=b;
        status = "OPEN";
    }

    public HashMap<Integer, Patient> getAdmitted_p() {
        return admitted_p;
    }

    public void setAdmitted_p(HashMap<Integer, Patient> admitted_p) {
        this.admitted_p = admitted_p;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getNo_beds() {
        return no_beds;
    }

    public void setNo_beds(int no_beds) {
        this.no_beds = no_beds;
    }

    public int getMin_o2level() {
        return min_o2level;
    }

    public void setMin_o2level(int min_o2level) {
        this.min_o2level = min_o2level;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int Selection(HashMap<Integer,Patient> current_p){
        if (no_beds==0){
            status = "CLOSED";
        }
        int no_patients = current_p.size();
        //int loop_help = Math.min(no_patients,no_beds);
        for(Patient p : current_p.values()){
            if (available>0){
           if( p.getAdmission_status().contentEquals("Not Admitted") && p.getO2level() >= min_o2level){
               p.setAdmission_status("Admitted");
               p.setInstitute(name) ;
               admitted_p.put(p.getId(),p);
               available--;
           }}
            else{
                break;
            }
        }
        if(available>0){
            for(Patient p : current_p.values()){
                if (available>0){
                if (p.getAdmission_status().contentEquals("Not Admitted") && p.getTemp()<=max_temp ){
                    p.setAdmission_status("Admitted");
                    p.setInstitute(name) ;
                    admitted_p.put(p.getId(),p);
                    available--;
                }
            }
                else{
                    break;
                }
            }

        }
        Scanner in = new Scanner(System.in);
        System.out.println(name);
        System.out.println("Temperature should be<= " + max_temp);
        System.out.println("Oxygen levels should be >= "+ min_o2level);
        System.out.println("Number of Available beds – "+ no_beds);
        System.out.println("Admission Status – " + status );
        if (admitted_p.size()>0){
        for(Patient p : admitted_p.values()) {
            System.out.println("Enter the recovery days for admitted patient ID " + p.getId());
            int rec_days = in.nextInt();
            p.setRecovery(rec_days);
        }}
        if (available==0){
            status = "CLOSED";
        }

    return admitted_p.size();
    }


}