import java.awt.Color;
import java.io.*;
import static java.lang.Math.abs;
import java.util.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        this.getContentPane().setBackground(getBackground());
        jf1.getContentPane().setBackground(getBackground());
        jf1.setVisible(false);
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
        b3.setVisible(false);
        
        bti1.setVisible(false);
        bti2.setVisible(false);
        ti.setVisible(false);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
    }
    
    
   
    
    ArrayList <player> bat_arr = new ArrayList<player>();
    ArrayList <player> bol_arr = new ArrayList<player>();
    ArrayList <player> all_arr = new ArrayList<player>();
    ArrayList <Team> team = new ArrayList<Team>();
    
    Set <player>FinalBatsmen= new HashSet<player>();
    Set <player>FinalBowler= new HashSet<player>();
    
    int no_of_batsmen = 20;
    int no_of_bowler = 20;
    
    double TotalFund = 0;
    double BowlFund;
    double BatFund;
    
    int TotalItr = 0;
    int Iter1 = 0;
    int Iter2 = 0;
    
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
        
        public void bol1(int k, int noOfItems,ArrayList<player> dataItems, int wkt) {


            this.k = k;
            this.noOfItems = noOfItems;
            cz = new ArrayList<>();
            oldCz = new ArrayList<>();
            row = new ArrayList<>();
            groups = new ArrayList<>();
            
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

            Iter1 += iter;
            
            
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

            Iter1 += iter;
            
            
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

            Iter2 += iter;
            
            
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
            
            Iter2 += iter;
            
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

        jf1 = new javax.swing.JFrame();
        jcb1 = new javax.swing.JComboBox<>();
        br1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt1 = new javax.swing.JTextArea();
        t1 = new javax.swing.JTextField();
        tf = new javax.swing.JLabel();
        jcb2 = new javax.swing.JComboBox<>();
        br2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt2 = new javax.swing.JTextArea();
        b2 = new javax.swing.JButton();
        fr = new javax.swing.JLabel();
        fr2 = new javax.swing.JLabel();
        b3 = new javax.swing.JButton();
        bti1 = new javax.swing.JLabel();
        ti = new javax.swing.JLabel();
        bti2 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        l1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        b1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jf1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jf1.setBackground(new java.awt.Color(204, 255, 204));
        jf1.setMinimumSize(new java.awt.Dimension(1040, 655));
        jf1.setPreferredSize(new java.awt.Dimension(1040, 700));
        jf1.setSize(new java.awt.Dimension(1040, 700));

        jcb1.setBackground(new java.awt.Color(204, 0, 51));
        jcb1.setFont(new java.awt.Font("Mshtakan", 0, 24)); // NOI18N
        jcb1.setForeground(new java.awt.Color(0, 153, 51));
        jcb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Team", "Delhi Daredevils", "Gujrat Lions", "Kings XI Punjab", "Kolkata Knight Riders", "Mumbai Indians", "Rising Pune Supergiants", "Royal Challengers Bangalore", "Sunrisers Hyderabad" }));
        jcb1.setOpaque(false);
        jcb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb1ActionPerformed(evt);
            }
        });

        br1.setBackground(new java.awt.Color(204, 0, 204));
        br1.setFont(new java.awt.Font("Mshtakan", 1, 24)); // NOI18N
        br1.setForeground(new java.awt.Color(0, 153, 153));
        br1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        br1.setText("BATSMEN RECOMENDED");
        br1.setFocusCycleRoot(true);

        jt1.setEditable(false);
        jt1.setBackground(new java.awt.Color(255, 255, 153));
        jt1.setColumns(20);
        jt1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jt1.setForeground(new java.awt.Color(255, 0, 0));
        jt1.setRows(5);
        jt1.setCaretColor(new java.awt.Color(0, 0, 102));
        jt1.setDisabledTextColor(new java.awt.Color(0, 0, 102));
        jt1.setOpaque(false);
        jScrollPane1.setViewportView(jt1);

        t1.setBackground(new java.awt.Color(255, 153, 153));
        t1.setFont(new java.awt.Font("Mshtakan", 0, 24)); // NOI18N
        t1.setForeground(new java.awt.Color(102, 102, 0));
        t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ActionPerformed(evt);
            }
        });

        tf.setBackground(new java.awt.Color(0, 0, 102));
        tf.setFont(new java.awt.Font("Lucida Bright", 1, 24)); // NOI18N
        tf.setForeground(new java.awt.Color(204, 0, 204));
        tf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tf.setText("Total Fund (in Cr)");

        jcb2.setBackground(new java.awt.Color(204, 0, 51));
        jcb2.setFont(new java.awt.Font("Mshtakan", 0, 24)); // NOI18N
        jcb2.setForeground(new java.awt.Color(0, 153, 51));
        jcb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type Of Pitch", "Damp", "Hard", "Dusty" }));
        jcb2.setOpaque(false);
        jcb2.setPreferredSize(new java.awt.Dimension(277, 31));
        jcb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb2ActionPerformed(evt);
            }
        });

        br2.setBackground(new java.awt.Color(240, 0, 240));
        br2.setFont(new java.awt.Font("Mshtakan", 1, 24)); // NOI18N
        br2.setForeground(new java.awt.Color(0, 153, 153));
        br2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        br2.setText("BOWLERS RECOMENDED");

        jt2.setEditable(false);
        jt2.setBackground(new java.awt.Color(255, 255, 153));
        jt2.setColumns(20);
        jt2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jt2.setForeground(new java.awt.Color(255, 0, 0));
        jt2.setRows(5);
        jt2.setCaretColor(new java.awt.Color(0, 0, 102));
        jt2.setDisabledTextColor(new java.awt.Color(0, 0, 102));
        jt2.setOpaque(false);
        jScrollPane2.setViewportView(jt2);

        b2.setBackground(new java.awt.Color(51, 0, 51));
        b2.setFont(new java.awt.Font("Mshtakan", 1, 24)); // NOI18N
        b2.setForeground(new java.awt.Color(204, 255, 204));
        b2.setText("SHOW RESULTS");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        fr.setBackground(new java.awt.Color(0, 0, 102));
        fr.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        fr.setForeground(new java.awt.Color(51, 0, 51));
        fr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fr.setText("FUNDS REMAINING");

        fr2.setBackground(new java.awt.Color(0, 0, 102));
        fr2.setFont(new java.awt.Font("Mshtakan", 1, 24)); // NOI18N
        fr2.setForeground(new java.awt.Color(102, 0, 102));
        fr2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        b3.setBackground(new java.awt.Color(51, 0, 51));
        b3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        b3.setForeground(new java.awt.Color(204, 255, 204));
        b3.setText("EXIT");
        b3.setActionCommand("EXIT");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        bti1.setBackground(new java.awt.Color(0, 0, 102));
        bti1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bti1.setForeground(new java.awt.Color(255, 51, 51));
        bti1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bti1.setText("BATSMEN CLUSTERING ITERATIONS");

        ti.setBackground(new java.awt.Color(0, 0, 102));
        ti.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ti.setForeground(new java.awt.Color(255, 51, 51));
        ti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ti.setText("TOTAL ITERATIONS");

        bti2.setBackground(new java.awt.Color(0, 0, 102));
        bti2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bti2.setForeground(new java.awt.Color(255, 51, 51));
        bti2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bti2.setText("BOWLERS CLUSTERING ITERATIONS");

        l3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        l3.setForeground(new java.awt.Color(0, 51, 102));
        l3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        l2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        l2.setForeground(new java.awt.Color(0, 51, 102));
        l2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        l1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        l1.setForeground(new java.awt.Color(0, 51, 102));
        l1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setBackground(new java.awt.Color(0, 0, 153));
        jLabel2.setFont(new java.awt.Font("Mshtakan", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BEAT- Best and Efficent Auction of Teams");

        javax.swing.GroupLayout jf1Layout = new javax.swing.GroupLayout(jf1.getContentPane());
        jf1.getContentPane().setLayout(jf1Layout);
        jf1Layout.setHorizontalGroup(
            jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jf1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jf1Layout.createSequentialGroup()
                        .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(l1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bti1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcb1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(br1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jf1Layout.createSequentialGroup()
                                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(t1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(fr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fr2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(br2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcb2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jf1Layout.createSequentialGroup()
                                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(l2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ti, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bti2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jf1Layout.setVerticalGroup(
            jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jf1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tf)
                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jf1Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcb1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcb2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jf1Layout.createSequentialGroup()
                                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jf1Layout.createSequentialGroup()
                                        .addComponent(br1)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jf1Layout.createSequentialGroup()
                                        .addComponent(br2)
                                        .addGap(18, 18, 18)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jf1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fr2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bti2)
                    .addComponent(ti)
                    .addComponent(bti1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jf1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setForeground(new java.awt.Color(51, 51, 255));

        b1.setBackground(new java.awt.Color(51, 0, 51));
        b1.setFont(new java.awt.Font("Mshtakan", 1, 36)); // NOI18N
        b1.setForeground(new java.awt.Color(204, 255, 204));
        b1.setText("START");
        b1.setAlignmentX(0.5F);
        b1.setPreferredSize(new java.awt.Dimension(200, 55));
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 153));
        jLabel1.setFont(new java.awt.Font("Mshtakan", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BEAT- Best and Efficent Auction of Teams");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dd.png"))); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gl.png"))); // NOI18N

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kkr.png"))); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kxip.png"))); // NOI18N

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcb.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rps.png"))); // NOI18N

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/srh.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mi.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(158, 158, 158)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(17, 17, 17))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(23, 23, 23))
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
        
        this.setVisible(false);
        jf1.setVisible(true);
        
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
        TotalFund = 0;
        if(!t1.getText().equals(""))
            TotalFund = Integer.parseInt(t1.getText()) * 100;
        
        BatFund =  (TotalFund/10)*6;
        BowlFund = (TotalFund/10)*4;
        //System.out.println(BowlFund);
        if(tt!=0)
        {
            gsize = team.get(tt-1).size;
        }
        int pitch = jcb2.getSelectedIndex();
        
        
        int wkt = 10;
        int hitters = 8;
        
        if(tt == 0 || pitch == 0 || TotalFund==0)
        {
            JOptionPane.showMessageDialog(null, "PLEASE FILL ALL THE FIELDS","INPUT ERROR",0);
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
            
            l1.setText("" + Iter2);
            l3.setText("" + Iter1);
            
            
            TotalItr = (bol_arr.size()*Iter1) + (bat_arr.size()*Iter2);
            System.out.println(bol_arr.size());
            l2.setText("" + (TotalItr*3));
            
            l1.setVisible(true);
            l2.setVisible(true);
            l3.setVisible(true);
            bti1.setVisible(true);
            bti2.setVisible(true);
            ti.setVisible(true);
            
            
            b2.setVisible(false);
            b3.setVisible(true);
            
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

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
       // TODO add your handling code here:
       
   this.setVisible(true);
   jf1.setVisible(false);
   jt1.setText("");
   jt2.setText("");
   l1.setText("");
   l2.setText("");
   l3.setText("");
   b1.setVisible(true);
   t1.setText("");
   jcb1.setSelectedIndex(0);
   jcb2.setSelectedIndex(0);
   
   no_of_batsmen = 20;
   no_of_bowler = 20;
   TotalFund = 0;
   TotalItr = 0;
   Iter1 = 0;
   Iter2 = 0;
   
   System.exit(0);
    
    }//GEN-LAST:event_b3ActionPerformed

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
    private javax.swing.JButton b3;
    private javax.swing.JLabel br1;
    private javax.swing.JLabel br2;
    private javax.swing.JLabel bti1;
    private javax.swing.JLabel bti2;
    private javax.swing.JLabel fr;
    private javax.swing.JLabel fr2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcb1;
    private javax.swing.JComboBox<String> jcb2;
    private javax.swing.JFrame jf1;
    private javax.swing.JTextArea jt1;
    private javax.swing.JTextArea jt2;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JTextField t1;
    private javax.swing.JLabel tf;
    private javax.swing.JLabel ti;
    // End of variables declaration//GEN-END:variables
}
