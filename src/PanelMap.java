import javax.swing.*;
import java.awt.*;

public class PanelMap extends PanelBase{ 
	
    protected String getFirstBtName() {
        return "Top";
    }

    protected String getSecondBtName() {
        return "Foot";
    }

    protected String getFirstIconName() {
    	return "place2.png";
    }
    
    protected String getSecondIconName() {
    	return "place1.png";
    }
    
    protected int getIconLength() {
    	return 676;
    }
    
    protected void updateLayout() {
        switch (control) {
            case 0: 
            	pMain.add(imageLabel_First);
            	break;
            case 1:
            	pMain.add(imageLabel_Second);
                break;
        }
       // pMain.add(new JTextArea(Integer.toString(control)));
    }
    

}