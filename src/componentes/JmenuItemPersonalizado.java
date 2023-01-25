/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JMenuItem;

/**
 *
 * @author jgfs_
 */
public class JmenuItemPersonalizado extends JMenuItem {
    
    private String label;
    private Dimension medidas = new Dimension(90, 30);
    
    public JmenuItemPersonalizado(String label) {
        super(label);
        this.label = label;
       
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(0, 0, medidas.width, medidas.height);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString(label, 20, 20);
        super.paintComponent(g);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return medidas;
    }
    
    @Override
    public Dimension getMaximumSize() {
        return medidas;
    }
    
    @Override
    public Dimension getMinimumSize() {
        return medidas;
    }
    
}
