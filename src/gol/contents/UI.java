package gol.contents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    Map map;
    AutoThread autoThread;
    JPanel control_panel;
    JButton next_button;
    JButton autostop_button;
    JButton autostart_button;
    JPanel jPanel;

    class AutoThread extends Thread{

        boolean running;

        @Override
        public void run() {
            super.run();
            while (running){
                map.nextStep();
                jPanel.repaint();
                try {
                    this.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }


        }

        public void setRunning(boolean running){
            this.running = running;
        }

    }



    public static void main(String args[]){
        Map map = Map.createMap();
        map.cellInitialize(25);
        UI ui = new UI(map);


    }
    public UI(Map map){
        super();
        this.map = map;
        this.setBounds(0,0,1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //----------------------------------------控制窗口部分-----------------------------------------
        control_panel = new JPanel();
        control_panel.setBounds(800,0,200,800);

        next_button = new JButton("下一步");

        autostop_button = new JButton("停止");

        autostart_button = new JButton("自动");

        control_panel.setLayout(null);

        next_button.setBounds(0,0,200,400);
        autostart_button.setBounds(0,400,200,200);
        autostop_button.setBounds(0,600,200,200);


        control_panel.add(next_button);
        control_panel.add(autostart_button);
        control_panel.add(autostop_button);

        this.add(control_panel);
        //----------------------------------------控制窗口部分结束-------------------------------------



        //----------------------------------------图像控制部分-----------------------------------------
        jPanel = new Drawer(map);
        jPanel.setBounds(0,0,800,800);
        this.add(jPanel);
        //----------------------------------------图像控制部分结束--------------------------------------



        class NextAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                map.nextStep();
                jPanel.repaint();


            }
        }
        next_button.addActionListener(new NextAction());


        class AutoStartAction implements ActionListener{

            @Override
           public void actionPerformed(ActionEvent e) {

                autoThread = new AutoThread();
                autoThread.setRunning(true);
                autoThread.start();


           }

        }
        autostart_button.addActionListener(new AutoStartAction());

        class AutoStopAction implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                autoThread.setRunning(false);


            }
        }
        autostop_button.addActionListener(new AutoStopAction());

        this.setVisible(true);




    }

    class Drawer extends JPanel{
        //传入map对象的构造方法，将map中对应位置的存在传入

        Map map;

        public Drawer(Map map){
            super();
            this.map = map;

        }


        public void paint(Graphics graphics){
            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D)graphics;
            for (int y = 0;y <= this.map.getHeight() - 1;y++){
                for (int x = 0;x <= this.map.getWidth() - 1;x++){
                    if (this.map.getLocation(x,y)){
                        graphics2D.fillRect(8*x,8*y,8,8);
                    }else {
                        graphics2D.drawRect(8*x,8*y,8,8);
                    }


                }
            }


        }

    }//显示块





}
