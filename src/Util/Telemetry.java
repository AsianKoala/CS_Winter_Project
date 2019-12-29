package Util;

import Subsystems.Subsystem;

import java.awt.*;

// im just going to make it have its own thread cause im lazy
public class Telemetry implements Subsystem {
    private long updateRate;
    private String telemetryMessage = "";

    public Telemetry(long telemetryRate) {
        updateRate = telemetryRate;
    }

    public Telemetry() {
        updateRate = 1000;
    }


    /**
     * I honestly couldn't care less about casting, this way i never have to cast when calling methods yay
      * @param str
     */
    public void addLine(String str) { telemetryMessage += str; }

    public void addData(String str, String strr) { telemetryMessage += addSpacer() + str + ": " + strr;}
    public void addData(String str, int data) { telemetryMessage += addSpacer() + str  + ": " + data; }
    public void addData(String str, double data) { telemetryMessage += addSpacer() + str + ": " + data; }
    public void addData(String str, long data) { telemetryMessage += addSpacer() + str + ": " + data; }


    public void clear() { telemetryMessage = ""; }

    public String addSpacer() { return "\n"; }



    private long lastLoopTime = 0;

    @Override
    public void run(Graphics g) {
        if(System.currentTimeMillis() - lastLoopTime < updateRate) { return; }

        addSpacer();
        addSpacer();
        System.out.println(telemetryMessage);
        telemetryMessage = "";
        lastLoopTime = System.currentTimeMillis();
    }

}
