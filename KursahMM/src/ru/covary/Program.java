package ru.covary;

public class Program {
    double timeStart;
    double timeWork;

    Program(){
        timeStart = 0.5 + (Math.random() * 0.833);
        timeWork = 1 + (int) (Math.random() * 5);
    }
    public double getTimeWork(){
        return  timeWork;
    }
    public double getTimeStart(){
        return timeStart;
    }
}
