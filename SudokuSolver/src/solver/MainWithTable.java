/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;


import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainWithTable {
 
    
    public static void createAndShowGUI() {
 
  JFrame frame=new JFrame("Sudoku Solver");

    frame.setSize(400,300);
    JPanel panel=new JPanel();
    JPanel panel2=new JPanel();
    panel.setLayout(new GridLayout(9,9,1,1));
    
    JTextField tbox[][]=new JTextField[9][9];
    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    
    for(int i=0;i<9;i++){
        for(int j=0;j<9;j++){
            tbox[i][j]=new JTextField("0");
            panel.add(tbox[i][j]);
            tbox[i][j].setVisible(true);
        }
    }
     
    
    JButton Solve=new JButton("Solve");
    Solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //Code for sudoku solving here
                try{
                int A[][]=new int[9][9];
                for(int i=0;i<9;i++){
                    for(int j=0;j<9;j++)
                    {
                        A[i][j]=Integer.valueOf(tbox[i][j].getText());
                    }
                }
                Solution s=new Solution();
                 boolean x=s.rSolve(A, 0, 0);
                 if(x==false)
                        System.out.println("yoyo");
                for(int i=0;i<9;i++){
                    for(int j=0;j<9;j++)
                        tbox[i][j].setText(String.valueOf(A[i][j]));
                }
             
               }
            catch(Exception e){
                System.out.println("Sth wtn wrong in button solve action");
                
        }
        
          }
               
               
                
            
    });
    JButton Clear=new JButton("Clear");
    Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for(int i=0;i<9;i++){
                    for(int j=0;j<9;j++)
                        tbox[i][j].setText("0");
                }
                
            }
    });
    JButton Exit=new JButton("Exit");
    Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
                
            }
    });
    panel2.setLayout(new GridLayout(1,3));
    panel2.add(Solve);
    panel2.add(Clear);
    panel2.add(Exit);
    frame.setLayout(new BorderLayout());
    frame.add(panel, BorderLayout.NORTH);
    frame.add(panel2, BorderLayout.SOUTH);
    
  
  
   
  frame.pack();
 
  frame.setVisible(true);
 
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    }
 
    public static void main(String[] args) {

 
 
    createAndShowGUI(); 
 

    }
 
}
class Solution extends Throwable{
     public boolean check(int row,int col, int n,int grid[][]){
         
        for(int i=0;i<9;i++){
            
            if(grid[i][col]==n&&i!=row)
                return false;
            if(grid[row][i]==n&&i!=col)
                return false;
        }
        int x=(row)/3;
        int y=(col)/3;

        for(int i=3*x;i<3*x+3;i++){
            for(int j=y*3;j<y*3+3;j++)
                if(grid[i][j]==n&&!(i==row&&j==col)){
                    return false;
                }
                   
        }
        return true;
         
         

    }
  public boolean rSolve(int grid[][],int r,int c){
       
        if(r==8&&c==9)
            return true;
        if(c==9)
        {
            r++;
            c=0;
        }
        if(grid[r][c]==0){
                
        for(int i=1;i<10;i++){
            if(check(r,c,i,grid)){
                grid[r][c]=i;
                if(rSolve(grid,r,c+1)){
                    
                    return true;
                }
                grid[r][c]=0;
                tbox[i][j].setText(String.valueOf(grid[i][j]));
            }
        }
        }
        if(grid[r][c]!=0){
            if(check(r,c,grid[r][c],grid))
                return rSolve(grid,r,c+1);
            else
                return false;
        }
        
        return false;
        
        
    }
}