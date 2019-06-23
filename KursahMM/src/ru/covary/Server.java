package ru.covary;

import java.util.ArrayList;

public class Server {
    boolean run = false;
    double timeAllWork = 0;
    double downtime = 0;
    int buffer = 0;
    double oneBufferTime = 0;
    double twoBufferTime = 0;
    double threeBufferTime = 0;
    int lostProgram;
    int count = 5;
    double currentTime = 0;
    double finishWorkTime = 0;
    Program pr;
    ArrayList<Program> buf = new ArrayList<>();
    int countProg = 0;
    boolean processedProg = true;
    int wasInBuffer = 0;

    public void running(){
        while(currentTime < count){
            System.out.println("туть_1");
            if(processedProg){
                pr = genProg();
            }
            System.out.println("current time = " + currentTime + " finishWorkTime " + finishWorkTime + " pr.timeStart " + pr.timeStart);
            if(run){
                System.out.println("туть_2");
                if(finishWorkTime <= currentTime + pr.timeStart){
                    if(checkBuffer()){
                        working(getFromBufer());
                        processedProg = false;
                        System.out.println(countProg + " " + currentTime + " " + finishWorkTime);
                    }
                    else {
                        working(pr);
                        processedProg = true;
                        System.out.println(countProg + " " + currentTime + " " + finishWorkTime);
                    }
                }
                else if(finishWorkTime > currentTime + pr.timeStart){
                    addInBuffer(pr.timeStart, pr);
                    currentTime = currentTime + pr.timeStart;
                    processedProg = true;
                }
            }else {
                run = true;
                currentTime = pr.timeStart;
                finishWorkTime = currentTime + pr.timeWork;
                countProg++;
                System.out.println(countProg + " " + currentTime + " " + finishWorkTime);
            }

        }
        System.out.println("");
        System.out.println("CountProg = " + countProg + " wasInBuffer = " + wasInBuffer + " LostProgram " + lostProgram);
    }
    public void working(Program pr){
        currentTime = finishWorkTime;
        finishWorkTime = currentTime + pr.timeWork;
        countProg++;
    }
    public boolean checkBuffer(){
        if(buffer > 0){
            return true;
        } else return false;
    }
    public Program getFromBufer(){
        Program prog = buf.get(buffer - 1);
        buffer--;
        return prog;
    }
    public void addInBuffer(double time, Program pr){
        wasInBuffer++;
        if(buffer == 0){
            buffer++;
            oneBufferTime += time;
            buf.add(pr);
        } else if (buffer == 1){
            buffer++;
            twoBufferTime += time;
            buf.add(pr);
        } else if(buffer == 2){
            buffer++;
            threeBufferTime += time;
            buf.add(pr);
        } else {
            lostProgram++;
        }
    }
    public Program genProg(){
        return new Program();
    }
}
