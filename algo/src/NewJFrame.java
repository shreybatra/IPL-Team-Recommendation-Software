import java.io.*;
import static java.lang.Math.abs;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shreybatra
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        
        b2.setVisible(false);
        jcb1.setVisible(false);
        jcb2.setVisible(false);
        t1.setVisible(false);
        tf.setVisible(false);
        jt1.setVisible(false);
        jt2.setVisible(false);
        br1.setVisible(false);
        br2.setVisible(false);
        fr.setVisible(false);
        fr2.setVisible(false);
    }
    
    
    
    
    ArrayList <player> bat_arr = new ArrayList<player>();
    ArrayList <player> bol_arr = new ArrayList<player>();
    ArrayList <player> all_arr = new ArrayList<player>();
    ArrayList <Team> team = new ArrayList<Team>();
    
    ArrayList <player>FinalBatsmen= new ArrayList<player>();
    Set <player>FinalBowler= new HashSet<player>();
    
    int no_of_batsmen = 20;
    int no_of_bowler = 20;
    
    double TotalFund = 0;
    double BowlFund;
    double BatFund;
    
    public class Team
    {
        String name;
        int size;
        String ground;
    }
    
    
    public class player {
        String name;
        int bat_matches,bat_innings,bat_not_out,bat_runs,bat_high_score,bat_avg,bat_balls_faced,bat_strike_rate,bat_hundreds,bat_fifties,bat_ducks,bat_fours,bat_sixes;
        int bol_matches,bol_innings,bol_overs,bol_maidens,bol_runs,bol_wickets,bol_best,bol_avg,bol_economy,bol_bstrike,bol_four_wickets,bol_five_wickets;
        double price;
    }
    
    
    
    class BowlEcoComparator implements Comparator{  
        public int compare(Object o1,Object o2){  
            player s1=(player)o1;  
            player s2=(player)o2;  

            if(s1.bol_economy==s2.bol_economy)  
            return 0;  
            else if(s1.bol_economy>s2.bol_economy)  
            return 1;  
            else  
            return -1;  
        }  
    } 
    
    class BowlWktComparator implements Comparator{  
        public int compare(Object o1,Object o2){  
            player s1=(player)o1;  
            player s2=(player)o2;  

            if((s1.bol_wickets/s1.bol_innings)==(s2.bol_wickets/s2.bol_innings))
            return 0;  
            else if((s1.bol_wickets/s1.bol_innings)<(s2.bol_wickets/s2.bol_innings))  
            return 1;  
            else  
            return -1;  
        }  
    }
    
    class BatFourComparator implements Comparator{  
        public int compare(Object o1,Object o2){  
            player s1=(player)o1;  
            player s2=(player)o2;  

            if((s1.bat_fours)==(s2.bat_fours))
            return 0;  
            else if((s1.bat_fours)<(s2.bat_fours))  
            return 1;  
            else  
            return -1;  
        }  
    }
    
    class BatAVGComparator implements Comparator{  
        public int compare(Object o1,Object o2){  
            player s1=(player)o1;  
            player s2=(player)o2;  

            if((s1.bat_avg)==(s2.bat_avg))
            return 0;  
            else if((s1.bat_avg)<(s2.bat_avg))  
            return 1;  
            else  
            return -1;  
        }  
    }
    
    int waste(String str)
    {
        int i=0;
        while(str.charAt(i)!=' ')
        {
            i++;
        }
        
        return (i+1);
        
    }

    public int str2dec(String s) {
        return (Integer.parseInt(s, 10));
    }
    
    
    class KMean {

        int k;
        int noOfItems;
        
        ArrayList<Double> cz;
        ArrayList<Double> oldCz;
        ArrayList<Double> row;
        ArrayList<ArrayList<player>> groups;
        Scanner input;

        public void bol1(int k, int noOfItems,ArrayList<player> dataItems, int wkt) {


            this.k = k;
            this.noOfItems = noOfItems;
            cz = new ArrayList<>();
            oldCz = new ArrayList<>();
            row = new ArrayList<>();
            groups = new ArrayList<>();
            input = new Scanner(System.in);

            for (int i = 0; i < k; i++) {
                groups.add(new ArrayList<>());
            }

            //System.out.println("ABCD");
            
            for (int i = 0; i < k; i++) {
                double d = (double) dataItems.get(i).bol_wickets/dataItems.get(i).bol_innings;
                cz.add(d);
            }
            
            int iter = 1;
            do {
                for (player aItem : dataItems) {
                    for (double c : cz) {
                        row.add(abs(c - (aItem.bol_wickets/aItem.bol_innings)));
                    }
                    groups.get(row.indexOf(Collections.min(row))).add(aItem);
                    row.removeAll(row);
                }
                for (int i = 0; i < k; i++) {
                    if (iter == 1) {
                        oldCz.add(cz.get(i));
                    } else {
                        oldCz.set(i, cz.get(i));
                    }
                    if (!groups.get(i).isEmpty()) {
                        cz.set(i, averageWkt(groups.get(i)));
                    }
                }
                if (!cz.equals(oldCz)) {
                    for (int i = 0; i < groups.size(); i++) {
                        groups.get(i).removeAll(groups.get(i));
                    }
                }
                iter++;
            } while (!cz.equals(oldCz));

            
            
            
            /*
            String abc = new String();
            
            for (int i = 0; i < cz.size(); i++) {
                //System.out.println("New C" + (i + 1) + " " + cz.get(i));
                abc = abc + "New C" + (i+1) + " " + cz.get(i) + "\n";
            }

            for (int i = 0; i < groups.size(); i++) {
                //System.out.println("Group " + (i + 1));
                //System.out.println(groups.get(i).toString());
                abc = abc + "\nGroup " + (i+1) + "\n";
                for(player aaa : groups.get(i)){
                    abc = abc + aaa.name.toString() + ", ";
                }
                //abc = abc + groups.get(i).toString() + "\n";
            }
            abc = abc + "\n";
            //System.out.println("Number of Itrations: " + iter);
            abc = abc + "Number of Iterations: " + iter + "\n";
            jt1.setText(abc);
            */
            
            
            
            // find max wala centeroid 
            
            
            // addd players in final players....!!
            int i=0;
            
            int count = 0;
            while(count<wkt)
            {
                ArrayList <player> ppp = new ArrayList<>();
                double max = 0;
                int pos = -1;
                for(i=0;i<3;i++)
                {
                    if(cz.get(i) > max && cz.get(i)!=-1.0)
                    {
                        
                        pos = i;
                        max = cz.get(i);
                    }
                }
                
                
                
                if(pos!=-1)
                {
                    cz.set(pos, -1.0);
                    ppp.addAll(groups.get(pos));
                }
                else
                {
                    break;
                }
                
                //groups.remove(groups.get(pos));
                
                Collections.sort(ppp, new BowlEcoComparator());
                
                //System.out.println(pos);
                for(i=0;count<wkt && i<ppp.size();i++)
                {
                    //System.out.println("ABCD");
                    int n = FinalBowler.size();
                    if(ppp.get(i).bol_innings > 10 && ppp.get(i).price < BowlFund)
                    {
                        FinalBowler.add(ppp.get(i));
                    }
                    
                    if(FinalBowler.size()>n)
                    {
                        count++;
                        BowlFund = BowlFund - ppp.get(i).price;
                    }
                }
                
                
            }
            
            
            
            
        }
    
        public void bol2(int k, int noOfItems,ArrayList<player> dataItems, int wkt) {


            this.k = k;
            this.noOfItems = noOfItems;
            cz = new ArrayList<>();
            oldCz = new ArrayList<>();
            row = new ArrayList<>();
            groups = new ArrayList<>();
            input = new Scanner(System.in);

            for (int i = 0; i < k; i++) {
                groups.add(new ArrayList<>());
            }

            //System.out.println("ABCD");
            
            for (int i = 0; i < k; i++) {
                double d = (double) dataItems.get(i).bol_economy;
                cz.add(d);
            }
            
            int iter = 1;
            do {
                for (player aItem : dataItems) {
                    for (double c : cz) {
                        row.add(abs(c - (aItem.bol_economy)));
                    }
                    groups.get(row.indexOf(Collections.min(row))).add(aItem);
                    row.removeAll(row);
                }
                for (int i = 0; i < k; i++) {
                    if (iter == 1) {
                        oldCz.add(cz.get(i));
                    } else {
                        oldCz.set(i, cz.get(i));
                    }
                    if (!groups.get(i).isEmpty()) {
                        cz.set(i, averageEco(groups.get(i)));
                    }
                }
                if (!cz.equals(oldCz)) {
                    for (int i = 0; i < groups.size(); i++) {
                        groups.get(i).removeAll(groups.get(i));
                    }
                }
                iter++;
            } while (!cz.equals(oldCz));

            
            
            
            /*
            
            String abc = new String();
            
            for (int i = 0; i < cz.size(); i++) {
                //System.out.println("New C" + (i + 1) + " " + cz.get(i));
                abc = abc + "New C" + (i+1) + " " + cz.get(i) + "\n";
            }

            for (int i = 0; i < groups.size(); i++) {
                //System.out.println("Group " + (i + 1));
                //System.out.println(groups.get(i).toString());
                abc = abc + "\nGroup " + (i+1) + "\n";
                for(player aaa : groups.get(i)){
                    abc = abc + aaa.name.toString() + ", ";
                }
                //abc = abc + groups.get(i).toString() + "\n";
            }
            abc = abc + "\n";
            //System.out.println("Number of Itrations: " + iter);
            abc = abc + "Number of Iterations: " + iter + "\n";
            jt1.setText(abc);
            
            */
            
            
            // find max wala centeroid 
            
            
            // addd players in final players....!!
            int i=0;
            
            int count = 0;
            while(count<wkt)
            {
                ArrayList <player> ppp = new ArrayList<>();
                double min = 1000;
                int pos = -1;
                for(i=0;i<3;i++)
                {
                    if(cz.get(i) < min && cz.get(i)!=-1.0 && cz.get(i)!=0.0)
                    {
                        
                        pos = i;
                        min = cz.get(i);
                    }
                }
                
                
                if(pos!=-1)
                {
                    cz.set(pos, -1.0);
                    ppp.addAll(groups.get(pos));
                }
                else
                {
                    break;
                }
                //groups.remove(groups.get(pos));
                
                Collections.sort(ppp, new BowlWktComparator());
                
                //System.out.println(pos);
                
                
                for(i=0;count<wkt && i<ppp.size();i++)
                {
                    int n = FinalBowler.size();
                    if(ppp.get(i).bol_innings > 10 && ppp.get(i).price < BowlFund)
                    {
                        FinalBowler.add(ppp.get(i));
                        
                    }
                    if(FinalBowler.size()>n)
                    {
                        BowlFund = BowlFund - ppp.get(i).price;
                        count++;
                    }
                        
                }
                
                
                
            }
            
            
            
            
            
        }
        
        public void bat1(int k, int noOfItems,ArrayList<player> dataItems, int hitters) {


            this.k = k;
            this.noOfItems = noOfItems;
            cz = new ArrayList<>();
            oldCz = new ArrayList<>();
            row = new ArrayList<>();
            groups = new ArrayList<>();
            input = new Scanner(System.in);

            for (int i = 0; i < k; i++) {
                groups.add(new ArrayList<>());
            }

            //System.out.println("ABCD");
            
            for (int i = 0; i < k; i++) {
                double d = (double) dataItems.get(i).bat_sixes;
                cz.add(d);
            }
            
            int iter = 1;
            do {
                for (player aItem : dataItems) {
                    for (double c : cz) {
                        row.add(abs(c - (aItem.bat_sixes)));
                    }
                    groups.get(row.indexOf(Collections.min(row))).add(aItem);
                    row.removeAll(row);
                }
                for (int i = 0; i < k; i++) {
                    if (iter == 1) {
                        oldCz.add(cz.get(i));
                    } else {
                        oldCz.set(i, cz.get(i));
                    }
                    if (!groups.get(i).isEmpty()) {
                        cz.set(i, averageSixes(groups.get(i)));
                    }
                }
                if (!cz.equals(oldCz)) {
                    for (int i = 0; i < groups.size(); i++) {
                        groups.get(i).removeAll(groups.get(i));
                    }
                }
                iter++;
            } while (!cz.equals(oldCz));

            
            
            
            /*
            
            String abc = new String();
            
            for (int i = 0; i < cz.size(); i++) {
                //System.out.println("New C" + (i + 1) + " " + cz.get(i));
                abc = abc + "New C" + (i+1) + " " + cz.get(i) + "\n";
            }

            for (int i = 0; i < groups.size(); i++) {
                //System.out.println("Group " + (i + 1));
                //System.out.println(groups.get(i).toString());
                abc = abc + "\nGroup " + (i+1) + "\n";
                for(player aaa : groups.get(i)){
                    abc = abc + aaa.name.toString() + ", ";
                }
                //abc = abc + groups.get(i).toString() + "\n";
            }
            abc = abc + "\n";
            //System.out.println("Number of Itrations: " + iter);
            abc = abc + "Number of Iterations: " + iter + "\n";
            jt1.setText(abc);
            
            */
            
            
            // find max wala centeroid 
            
            
            // addd players in final players....!!
            int i=0;
            
            int count = 0;
            while(count<hitters)
            {
                ArrayList <player> ppp = new ArrayList<>();
                double max = 0;
                int pos = -1;
                for(i=0;i<3;i++)
                {
                    if(cz.get(i) > max && cz.get(i)!=-1.0 && cz.get(i)!=0.0)
                    {
                        
                        pos = i;
                        max = cz.get(i);
                    }
                }
                
                
                if(pos!=-1)
                {
                    cz.set(pos, -1.0);
                    ppp.addAll(groups.get(pos));
                }
                else
                {
                    break;
                }
                //groups.remove(groups.get(pos));
                
                Collections.sort(ppp, new BatFourComparator());
                
                //System.out.println(pos);
                
                
                for(i=0;count<hitters && i<ppp.size();i++)
                {
                    int n = FinalBatsmen.size();
                    if(ppp.get(i).bat_sixes > 10 && ppp.get(i).price < BatFund)
                    {
                        FinalBatsmen.add(ppp.get(i));
                    }
                    if(FinalBatsmen.size()>n)
                    {
                        BatFund = BatFund - ppp.get(i).price;
                        count++;
                    }
                        
                }
                
                
            }
            
            
            
            
            
        }
        
        public void bat2(int k, int noOfItems,ArrayList<player> dataItems, int hitters) {


            this.k = k;
            this.noOfItems = noOfItems;
            cz = new ArrayList<>();
            oldCz = new ArrayList<>();
            row = new ArrayList<>();
            groups = new ArrayList<>();
            input = new Scanner(System.in);

            for (int i = 0; i < k; i++) {
                groups.add(new ArrayList<>());
            }

            //System.out.println("ABCD");
            
            for (int i = 0; i < k; i++) {
                double d = (double) dataItems.get(i).bat_strike_rate;
                cz.add(d);
            }
            
            int iter = 1;
            do {
                for (player aItem : dataItems) {
                    for (double c : cz) {
                        row.add(abs(c - (aItem.bat_strike_rate)));
                    }
                    groups.get(row.indexOf(Collections.min(row))).add(aItem);
                    row.removeAll(row);
                }
                for (int i = 0; i < k; i++) {
                    if (iter == 1) {
                        oldCz.add(cz.get(i));
                    } else {
                        oldCz.set(i, cz.get(i));
                    }
                    if (!groups.get(i).isEmpty()) {
                        cz.set(i, averageSTR(groups.get(i)));
                    }
                }
                if (!cz.equals(oldCz)) {
                    for (int i = 0; i < groups.size(); i++) {
                        groups.get(i).removeAll(groups.get(i));
                    }
                }
                iter++;
            } while (!cz.equals(oldCz));

            
            
            
            /*
            
            String abc = new String();
            
            for (int i = 0; i < cz.size(); i++) {
                //System.out.println("New C" + (i + 1) + " " + cz.get(i));
                abc = abc + "New C" + (i+1) + " " + cz.get(i) + "\n";
            }

            for (int i = 0; i < groups.size(); i++) {
                //System.out.println("Group " + (i + 1));
                //System.out.println(groups.get(i).toString());
                abc = abc + "\nGroup " + (i+1) + "\n";
                for(player aaa : groups.get(i)){
                    abc = abc + aaa.name.toString() + ", ";
                }
                //abc = abc + groups.get(i).toString() + "\n";
            }
            abc = abc + "\n";
            //System.out.println("Number of Itrations: " + iter);
            abc = abc + "Number of Iterations: " + iter + "\n";
            jt1.setText(abc);
            
            */
            
            
            // find max wala centeroid 
            
            
            // addd players in final players....!!
            int i=0;
            
            int count = 0;
            while(count<hitters)
            {
                ArrayList <player> ppp = new ArrayList<>();
                double max = 0;
                int pos = -1;
                for(i=0;i<3;i++)
                {
                    if(cz.get(i) > max && cz.get(i)!=-1.0 && cz.get(i)!=0.0)
                    {
                        
                        pos = i;
                        max = cz.get(i);
                    }
                }
                


                if(pos!=-1)
                {
                    cz.set(pos, -1.0);
                    ppp.addAll(groups.get(pos));
                }
                else
                {
                    break;
                }
//groups.remove(groups.get(pos));
                
                Collections.sort(ppp, new BatAVGComparator());
                
                //System.out.println(pos);
                
                
                for(i=0;count<hitters && i<ppp.size();i++)
                {
                    int n = FinalBatsmen.size();
                    if(ppp.get(i).bat_strike_rate > 100 && ppp.get(i).price < BatFund)
                    {
                        FinalBatsmen.add(ppp.get(i));
                    }
                    if(FinalBatsmen.size()>n)
                    {
                        
                        BatFund = BatFund - ppp.get(i).price;
                        count++;
                    }
                }
                
                
            }
            
            
            
            
            
        }
        
        public double averageWkt(ArrayList<player> list) {
            double sum = 0;
            for (player value : list) {
                sum = sum + (value.bol_wickets/value.bol_innings);
            }
            return sum / list.size();
        }
        
        public double averageEco(ArrayList<player> list) {
            double sum = 0;
            for (player value : list) {
                sum = sum + (value.bol_economy);
            }
            return sum / list.size();
        }
        
        public double averageSixes(ArrayList<player> list) {
            double sum = 0;
            for (player value : list) {
                sum = sum + (value.bat_sixes);
            }
            return sum / list.size();
        }
    
        public double averageSTR(ArrayList<player> list) {
            double sum = 0;
            for (player value : list) {
                sum = sum + (value.bat_strike_rate);
            }
            return sum / list.size();
        }
    
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        jcb1 = new javax.swing.JComboBox<>();
        jcb2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt1 = new javax.swing.JTextArea();
        t1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        br1 = new javax.swing.JLabel();
        br2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf = new javax.swing.JLabel();
        fr2 = new javax.swing.JLabel();
        fr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        b1.setBackground(new java.awt.Color(255, 51, 51));
        b1.setFont(new java.awt.Font("Mshtakan", 1, 36)); // NOI18N
        b1.setText("START");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setFont(new java.awt.Font("Mshtakan", 1, 36)); // NOI18N
        b2.setText("SHOW RESULTS");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        jcb1.setFont(new java.awt.Font("Mshtakan", 0, 24)); // NOI18N
        jcb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Team", "Delhi Daredevils", "Gujrat Lions", "Kings XI Punjab", "Kolkata Knight Riders", "Mumbai Indians", "Rising Pune Supergiants", "Royal Challengers Bangalore", "Sunrisers Hyderabad" }));
        jcb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb1ActionPerformed(evt);
            }
        });

        jcb2.setFont(new java.awt.Font("Mshtakan", 0, 24)); // NOI18N
        jcb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type Of Pitch", "Damp", "Hard", "Dusty" }));
        jcb2.setPreferredSize(new java.awt.Dimension(277, 31));
        jcb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb2ActionPerformed(evt);
            }
        });

        jt1.setColumns(20);
        jt1.setFont(new java.awt.Font("Mshtakan", 0, 18)); // NOI18N
        jt1.setRows(5);
        jt1.setBorder(null);
        jScrollPane1.setViewportView(jt1);

        t1.setFont(new java.awt.Font("Mshtakan", 0, 24)); // NOI18N
        t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ActionPerformed(evt);
            }
        });

        jt2.setColumns(20);
        jt2.setFont(new java.awt.Font("Mshtakan", 0, 18)); // NOI18N
        jt2.setRows(5);
        jt2.setBorder(null);
        jScrollPane2.setViewportView(jt2);

        jLabel1.setFont(new java.awt.Font("Mshtakan", 1, 40)); // NOI18N
        jLabel1.setText("ALOGORITHM AND PROBLEM SOLVING");

        br1.setFont(new java.awt.Font("Mshtakan", 0, 24)); // NOI18N
        br1.setText("BATSMEN RECOMENDED");

        br2.setFont(new java.awt.Font("Mshtakan", 0, 24)); // NOI18N
        br2.setText("BOWLERS RECOMENDED");

        jLabel4.setFont(new java.awt.Font("Mshtakan", 1, 36)); // NOI18N
        jLabel4.setText("PROJECT");

        tf.setFont(new java.awt.Font("Mshtakan", 0, 18)); // NOI18N
        tf.setText("TOTAL FUND AVAILABLE (cr)");

        fr2.setFont(new java.awt.Font("Mshtakan", 0, 18)); // NOI18N
        fr2.setText("jLabel6");

        fr.setFont(new java.awt.Font("Mshtakan", 1, 18)); // NOI18N
        fr.setText("FUND REMAINING");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(461, 461, 461)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jcb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jcb2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(br1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(86, 86, 86))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(b2)
                                                .addGap(47, 47, 47))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(132, 132, 132)
                                        .addComponent(fr)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(br2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89)))))
                .addContainerGap(3, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(519, 519, 519)
                .addComponent(fr2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tf)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addComponent(tf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcb1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(br1)
                    .addComponent(br2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(fr))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fr2)
                .addGap(0, 69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        
        String fname = "final.txt";
        String fname2 = "final2.txt";
        
        try{
            
            File ff = new File(fname);
            FileReader fff = new FileReader(ff);
            BufferedReader buff = new BufferedReader(fff);
            StringBuffer sb = new StringBuffer();
            String line;
            
            
            File ff2 = new File(fname2);
            FileReader fff2 = new FileReader(ff2);
            BufferedReader buff2 = new BufferedReader(fff2);
            StringBuffer sb2 = new StringBuffer();
            String line2;
            
            
            int c = 0;
            
            int i = 0;
            while((line = buff.readLine()) != null)
            {
                c++;
                player p = new player();
                line2 = buff2.readLine();
                p.name = line2;
                /*
                sb.append(p.name);
                sb.append("\n");
                */
                line = buff.readLine(); // batt info
                
                i = waste(line); // 'overall' hatane ke liye
                
                if(line.charAt(i)=='2' && line.charAt(i+1)=='0' && (line.charAt(i+2)=='0' || line.charAt(i+2)=='1'))
                {
                    i = i + 10; //date span hatane ke liye
                }
                
                
                int chance = 1;
                
                while(i<line.length())  // BATSMEN WALA
                {
                    char[] abc = new char[10];
                    int z = 0;
                    while(line.charAt(i)!=' ' && line.charAt(i)!='.' && line.charAt(i)!='*' && line.charAt(i)!='/')
                    {
                        abc[z] = line.charAt(i);
                        z++;
                        i++;
                    }
                    
                    if(line.charAt(i)!=' ')
                    {
                        while(line.charAt(i)!=' ')
                        {
                            i++;
                        }
                        i++;
                    }
                    else
                    {
                        i++;
                    }
                    
                    String word = new String(abc,0,z);
                    
                    
                    //System.out.println(word + " ");
                    int x;

                    if(word.equals("-") || word.equals("Profile") || word.equals(""))
                    {
                        x = 0;
                        continue;
                    }
                    else
                    { 
                        x = str2dec(word);
                    }
                    //System.out.println(x);
                                      
                    switch(chance)
                    {
                        case 1:{
                            p.bat_matches = x;
                            chance++;
                            break;
                        }
                        case 2:{
                            p.bat_innings = x;
                            chance++;
                            break;
                        }
                        case 3:{
                            p.bat_not_out = x;
                            chance++;
                            break;
                        }
                        case 4:{
                            p.bat_runs = x;
                            chance++;
                            break;
                        }
                        case 5:{
                            p.bat_high_score = x;
                            chance++;
                            break;
                        }
                        case 6:{
                            p.bat_avg = x;
                            chance++;
                            break;
                        }
                        case 7:{
                            p.bat_balls_faced = x;
                            chance++;
                            break;
                        }
                        case 8:{
                            p.bat_strike_rate = x;
                            chance++;
                            break;
                        }
                        case 9:{
                            p.bat_hundreds = x;
                            chance++;
                            break;
                        }
                        case 10:{
                            p.bat_fifties = x;
                            chance++;
                            break;
                        }
                        case 11:{
                            p.bat_ducks = x;
                            chance++;
                            break;
                        }
                        case 12:{
                            p.bat_fours = x;
                            chance++;
                            break;
                        }
                        case 13:{
                            p.bat_sixes = x;
                            chance++;
                            break;
                        }
                    }
                
                    
                    
                
                }
                
                
                
                line = buff.readLine(); //bolwing
                line = buff.readLine(); // bowl info
                
                i = waste(line); // 'overall' hatane ke liye
                
                if(line.charAt(i)=='2' && line.charAt(i+1)=='0' && (line.charAt(i+2)=='0' || line.charAt(i+2)=='1'))
                {
                    i = i + 10; //date span hatane ke liye
                }
                
                
                int chance2 = 1;
                
                while(i<line.length())  // BOWLER WALA
                {
                    char[] abc = new char[10];
                    int z = 0;
                    while(line.charAt(i)!=' ' && line.charAt(i)!='.' && line.charAt(i)!='*' && line.charAt(i)!='/')
                    {
                        abc[z] = line.charAt(i);
                        z++;
                        i++;
                    }
                    
                    if(line.charAt(i)!=' ')
                    {
                        while(line.charAt(i)!=' ')
                        {
                            i++;
                        }
                        i++;
                    }
                    else
                    {
                        i++;
                    }
                    
                    String word = new String(abc,0,z);
                    
                    
                    //System.out.println(word + " ");
                    int x;

                    if(word.equals("-") || word.equals("Profile") || word.equals(""))
                    {
                        x = 0;
                        continue;
                    }
                    else
                    { 
                        x = str2dec(word);
                    }
                    //System.out.println(x);
                                      
                    switch(chance2)
                    {
                        case 1:{
                            p.bol_matches = x;
                            chance2++;
                            break;
                        }
                        case 2:{
                            p.bol_innings = x;
                            chance2++;
                            break;
                        }
                        case 3:{
                            p.bol_overs = x;
                            chance2++;
                            break;
                        }
                        case 4:{
                            p.bol_maidens = x;
                            chance2++;
                            break;
                        }
                        case 5:{
                            p.bol_runs = x;
                            chance2++;
                            break;
                        }
                        case 6:{
                            p.bol_wickets = x;
                            chance2++;
                            break;
                        }
                        case 7:{
                            p.bol_best = x;
                            chance2++;
                            break;
                        }
                        case 8:{
                            p.bol_avg = x;
                            chance2++;
                            break;
                        }
                        case 9:{
                            p.bol_economy = x;
                            chance2++;
                            break;
                        }
                        case 10:{
                            p.bol_bstrike = x;
                            chance2++;
                            break;
                        }
                        case 11:{
                            p.bol_four_wickets = x;
                            chance2++;
                            break;
                        }
                        case 12:{
                            p.bol_five_wickets = x;
                            chance2++;
                            break;
                        }
                    }
                
                }
                
                
                if(p.bat_innings > (p.bat_matches/2))
                {
                    //System.out.println(p.name + " " + p.bat_matches + " " + p.bat_innings);
                    p.price = 20 + (p.bat_innings/5);
                    
                    bat_arr.add(p); // ADDING BATSMEN
                }
                
                else if(p.bol_innings > (p.bol_matches/2))
                {
                    //System.out.println(p.name + " " + p.bol_matches + " " + p.bol_innings);
                    //p.price = ((double)p.bol_wickets/p.bol_innings)*20;
                    
                    if(p.bol_innings < 20)
                    {
                        p.price = 20;
                    }
                    else if(p.bol_innings > 30)
                    {
                        p.price = 30;
                    }
                    else
                    {
                        p.price = p.bol_innings;
                    }
                    bol_arr.add(p); // ADDING BOLWER
                }
                
                
            };
            
            
            
            
            fff.close();
            
        } catch(IOException e){
            
            System.out.print("File Not Found");
            return;
            
        }
        
        
        /*
            Iterator itr=bol_arr.iterator();  
            while(itr.hasNext()){  
                player temp = (player) itr.next();
                
                System.out.println(temp.name + " " + temp.price);
               }
            */
        
        try{
            
            File ff = new File("team.txt");
            FileReader fff = new FileReader(ff);
            BufferedReader buff = new BufferedReader(fff);
            StringBuffer sb = new StringBuffer();
            String line;
            
            while((line = buff.readLine()) != null)
            {
                
                Team tt = new Team();
                tt.name = line;
                line = buff.readLine();
                tt.ground = line;
                line = buff.readLine();
                tt.size = str2dec(line);
                team.add(tt);
                //System.out.println(tt.pitch);
            }
            
            
        } catch(IOException e){
            
            System.out.print("File Not Found");
            return;
            
        }
        
        
        b2.setVisible(true);
        jcb1.setVisible(true);
        jcb2.setVisible(true);
        t1.setVisible(true);
        tf.setVisible(true);
        jt1.setVisible(true);
        jt2.setVisible(true);
        br1.setVisible(true);
        br2.setVisible(true);
        fr.setVisible(false);
        fr2.setVisible(false);
        b1.setVisible(false);
        
        
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        
        int tt;
        tt = jcb1.getSelectedIndex();
        int gsize = -1;
        
        TotalFund = Integer.parseInt(t1.getText()) * 100;
        
        BatFund =  (TotalFund/10)*6;
        BowlFund = (TotalFund/10)*4;
        //System.out.println(BowlFund);
        if(tt!=0)
        {
            gsize = team.get(tt-1).size;
        }
        int pitch = jcb2.getSelectedIndex();
        
        
        int wkt = 5;
        int hitters = 4;
        
        if(tt == 0 || pitch == 0 || TotalFund==0)
        {
            // error
        }
        else
        {
            // now algo lagao
            switch(pitch)
            {
                case 1:{
                    wkt = 8;
                    break;
                }
                case 2:{
                    wkt = 12;
                    break;
                }
                case 3:{
                    wkt = 10;
                    break;
                }
            }
            
            KMean k = new KMean();
            
            k.bol1(3,bol_arr.size(),bol_arr,wkt);
            
            k.bol2(3,bol_arr.size(),bol_arr,no_of_bowler-wkt);
            
            
            /*
            Iterator itr=FinalBowler.iterator();  
            while(itr.hasNext()){  
                player temp = (player) itr.next();
                
                System.out.println(temp.name);
               }
            */
            
            
            switch(gsize)
            {
                case 0:{
                    hitters = 12;
                    break;
                }
                case 1:{
                    hitters = 8;
                    break;
                }
                case 2:{
                    hitters = 4;
                    break;
                }
            }
            
            k.bat1(3,bat_arr.size(),bat_arr,hitters);
            k.bat2(3,bat_arr.size(),bat_arr,no_of_batsmen-hitters);
            
            
            /*
            Iterator itr=FinalBatsmen.iterator();  
            while(itr.hasNext()){  
                player temp = (player) itr.next();
                
                System.out.println(temp.name);
               }
            */
            
            
            
            
            // YE SAB DISPLAYING!!! ---!!!
            
            String abc = "";
            Iterator itr=FinalBatsmen.iterator();  
            while(itr.hasNext()){  
                player temp = (player) itr.next();
                
                abc = abc + temp.name + " " + temp.price + "\n";
               }
            jt1.setText(abc);
            
            abc = "";
            
            itr=FinalBowler.iterator();  
            while(itr.hasNext()){  
                player temp = (player) itr.next();
                
                abc = abc + temp.name + " " + temp.price + "\n";
               }
            jt2.setText(abc);
            
            TotalFund = BowlFund + BatFund;
            fr2.setText("" + TotalFund + " Lacs");
            
            fr.setVisible(true);
            fr2.setVisible(true);
            
            b2.setEnabled(false);
            
            /// YAHA TAK   !!!!
            
            
            
            
        }
        
        
    }//GEN-LAST:event_b2ActionPerformed

    private void jcb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb2ActionPerformed

    private void t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ActionPerformed

    private void jcb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
                
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JLabel br1;
    private javax.swing.JLabel br2;
    private javax.swing.JLabel fr;
    private javax.swing.JLabel fr2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcb1;
    private javax.swing.JComboBox<String> jcb2;
    private javax.swing.JTextArea jt1;
    private javax.swing.JTextArea jt2;
    private javax.swing.JTextField t1;
    private javax.swing.JLabel tf;
    // End of variables declaration//GEN-END:variables
}
