package org.lining.hssf;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * description:
 * date 2017/12/12
 *
 * @author lining1
 * @version 1.0.0
 */
public class ButtonDemo implements ActionListener {

    JLabel jLabel;

    ButtonDemo() {
        JFrame jFrame = new JFrame("抽奖系统");
        jFrame.setLayout(new  FlowLayout(FlowLayout.LEFT,20,40));
        //jFrame.setLayout(new GridLayout(3, 3));
        Border border = new LineBorder(Color.BLUE, 1);
        Font font = new Font("", Font.BOLD, 20);
        jFrame.setSize(800,800);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jLabel = new JLabel();

        JButton jButton1 = new JButton("一等奖");
        JButton jButton2 = new JButton("二等奖");
        JButton jButton3 = new JButton("三等奖");

        jButton1.setBorder(border);
        jButton2.setBorder(border);
        jButton3.setBorder(border);


        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Up")) {
                    jLabel.setText("一等奖:");
                } else {
                    String result = HSSFReadData.selectOne();
                    jLabel.setText("一等奖中奖信息: " + result);
                }
            }
        });


        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Up")) {
                    jLabel.setText("二等奖:");
                } else {
                    String result = HSSFReadData.selectTwo();
                    jLabel.setText("二等奖中奖信息: " + result);
                }
            }
        });


        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Up")) {
                    jLabel.setText("三等奖:");
                } else {
                    String result = HSSFReadData.selectThree();
                    jLabel.setText("三等奖中奖信息: " + result);
                }
            }
        });


        jFrame.add(jButton1);
        jFrame.add(jButton2);
        jFrame.add(jButton3);
        jFrame.add(jLabel);
        jFrame.setVisible(true);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Up")) {
            jLabel.setText("默认:");
        } else {
            String result = HSSFReadData.selectOne();
            jLabel.setText(result);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ButtonDemo();
            }
        });
    }
}
