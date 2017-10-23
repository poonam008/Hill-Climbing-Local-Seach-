/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillclimbing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author ardroid
 */

// Poonam       (1611MC07)

public class HillClimbing {

    public static int displaced(int input[][] , int goal[][])
    
    {
        int tilesDisplaced = 0;
        for(int i=0;i<input.length;i++)
            {
                for(int j=0;j<input[i].length;j++)
                    {
                        if(input[i][j]!=goal[i][j] && input[i][j]!=0)
                            tilesDisplaced++;
                    }
            }
    
    return tilesDisplaced;
    }
    
    public static void printMatrix(int arr[][])
    {    
      for(int i=0;i<3;i++)
      {
          for(int j=0;j<3;j++)
          {
              if(arr[i][j]==0)
                  System.out.print("B"+" ");
              else
                System.out.print("T"+arr[i][j]+" ");
          }
      
      System.out.println("");
      }
      
      System.out.println("---------------------------------------------------------");
    
    }
    
    public static String str(int arr[][])
    {    
        String string="";
      for(int i=0;i<3;i++)
      {
          for(int j=0;j<3;j++)
          {
            string=string+arr[i][j];
          }
      
     
      }
     return string;
    }
    
    public static int[][] copy(int arr[][])
    {    
       int temp[][] = new int[3][3]; 
      for(int i=0;i<3;i++)
      {
          for(int j=0;j<3;j++)
          {
             temp[i][j]=arr[i][j];
          }
      
      }
      
      return temp;
    }
    
    public static int hillClimb(int input[][],int goal[][], int i, int j)
    
    {
        //printMatrix(input);
        
        int minimum = 10;
        int duplicates[]= new int[4];
        int initial = displaced(input,goal);
        
        if(initial == 0)
            return 4;
        
        int temp[][] = new int[3][3];
        int dis;
        if(j+1<3)
            { temp = copy(input);
            temp[i][j]=temp[i][j+1]; 
            temp[i][j+1]=0; 
            dis = displaced(temp,goal); 
            if(dis<=minimum){duplicates[0]=1;}
            if(dis<minimum)
            {duplicates[1]=0;duplicates[2]=0;duplicates[3]=0;minimum=dis;}
            }
        
        if(j-1>=0)
            {temp = copy(input); 
            temp[i][j]=temp[i][j-1]; 
            temp[i][j-1]=0; 
            dis = displaced(temp,goal); 
            if(dis<=minimum){duplicates[1]=1;}
            if(dis<minimum)
            {duplicates[0]=0;duplicates[2]=0;duplicates[3]=0;minimum=dis;}
            }
        
        if(i+1<3)
            {temp = copy(input); 
            temp[i][j]=temp[i+1][j]; 
            temp[i+1][j]=0; 
            dis = displaced(temp,goal); 
            if(dis<=minimum){duplicates[2]=1;}
            if(dis<minimum)
            {duplicates[1]=0;duplicates[0]=0;duplicates[3]=0;minimum=dis;}
            }

        if(i-1>=0)
            {temp = copy(input); 
            temp[i][j]=temp[i-1][j]; 
            temp[i-1][j]=0; 
            dis = displaced(temp,goal); 
            if(dis<=minimum){duplicates[3]=1;}
            if(dis<minimum)
            {duplicates[1]=0;duplicates[2]=0;duplicates[0]=0;minimum=dis;}
            }
       
           int ind = 0;
         //  System.out.println("min: "+minimum +" initial: "+initial);
           if(minimum <= initial)
                {
                    do{
                    ind  = (int)(Math.random() * 4);
                    }while(duplicates[ind]==0);
                
           
           switch(ind)
           {
               case 0 : input[i][j]=input[i][j+1];input[i][j+1]=0; return 0;
               case 1 : input[i][j]=input[i][j-1];input[i][j-1]=0;return 1;
               case 2 : input[i][j]=input[i+1][j];input[i+1][j]=0;return 2;
               case 3 : input[i][j]=input[i-1][j];input[i-1][j]=0;return 3;
           
           }
          }
          
               
       
    return -1;
    
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {

         FileInputStream in = null;
      FileOutputStream out = null;
      int i=0,j=0;
      int blanki=0,blankj = 0;
      int input[][] = new int[3][3];
      int goal[][] = new int[3][3];
      
      boolean storeInput=false,storeGoal = false,inputDone=false;

      try {
         in = new FileInputStream("input.txt");
         
         int c;
         while ((c = in.read()) != -1) {
             if(c=='T' || c=='B')
             {
                 if(inputDone)
                     storeGoal = true;
                 else
                     storeInput=true;
                 if(c=='T')
                    continue;
             }
             
             if(storeInput==true)
             {  if(c=='B')
                    {input[i][j]=0;blanki=i;blankj=j;}
                else
                    input[i][j]=c-48;
                if(j==2)
                    {j=0;i++;}
                else
                    j++;
                storeInput=false;
             }
             
             if(storeGoal==true)
             {  if(c=='B')
                    goal[i][j]=0;
                else
                    goal[i][j]=c-48;
                if(j==2)
                    {j=0;i++;}
                else
                    j++;
                storeGoal=false;
             }
             
             if(i==3)
                {inputDone=true;storeInput=false;i=0;j=0;}
             
         }
      }finally {
         if (in != null) {
            in.close();
         }
         
      }
      
      
      ArrayList<int[][]> list = new ArrayList<int[][]>();
      HashSet<String> set = new HashSet<String>();
     
      list.add(copy(input));
      set.add(str(input));
      while(true)
      {
          int x = hillClimb(input,goal,blanki,blankj);
          if(x!=-1 && x!=4)
          {
          list.add(copy(input));
          if(!set.add(str(input)))
              x=-1;
          }
          switch(x)
          {
              case -1: System.out.println("Goal state can't be reached");
                       System.out.println("Start State: ");printMatrix(list.get(0));
                       System.out.println("Goal State: ");printMatrix(goal);
                       break;
              
              case 0: blankj++;break;
              case 1: blankj--;break;
              case 2: blanki++;break;
              case 3: blanki--;break;
              
              case 4: 
                  System.out.println("Goal state reached");
                  System.out.println("Start State: ");printMatrix(list.get(0));
                  System.out.println("Goal State: ");printMatrix(goal);
                  System.out.println("Path followed: ");
                  for(i =0;i<list.size();i++)
                      printMatrix(list.get(i));
                  System.out.println("Cost = "+list.size());
                  System.out.println("Total number of states explored: "+set.size());
                  break; 
          }
          
          if(x==-1 || x==4)
              break;
         
      }
      
        
    }
    
}
