import java.awt.*;

import javax.swing.*;

public class PanelRecord extends JPanel{ 
    private JScrollPane spInfo;
    private JTextArea taInfo;
   
    private JPanel pMain = new JPanel(new BorderLayout());

    public PanelRecord() {
        cComponent();
        cLayOut();
    }

    private void cComponent() {
        String info = User.getInstance().info();

        taInfo = new JTextArea();
        //add
        String title = String.format("   %-13s%-15s%-7s%-5s%-6s%-7s%-12s%-11s%-11s%-25s\n", "Pickup Code", "Place", "Price",
				"Cup", "Bowl", "Spoon", "Chopsticks", "Utensils1", "Utensils2", "Time");
        
        taInfo.setText(title + " ---------------------------------------------------------------------------------------------------------------\n" + info);
        //Justify Align
        taInfo.setFont(new Font("monospaced", Font.PLAIN, 12));
        spInfo = new JScrollPane(taInfo);
        spInfo.setPreferredSize(new Dimension(1000,600));
    }

    public void cLayOut() {
        pMain.removeAll();
        
        pMain.add(taInfo);
        
        pMain.revalidate();
        pMain.repaint();
        
        
        add(pMain);

        setVisible(true);
    }
}
