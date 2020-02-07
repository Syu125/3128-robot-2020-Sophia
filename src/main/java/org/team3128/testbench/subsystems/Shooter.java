package org.team3128.testbench.subsystems;

import org.team3128.common.utility.Log;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;

import org.team3128.athos.subsystems.NEODrive;
import org.team3128.common.generics.Threaded;
import org.team3128.testbench.subsystems.Constants;
import org.team3128.common.hardware.motor.LazyCANSparkMax;

public class Shooter extends Threaded {

    
    public static final Shooter shooter = new Shooter();
    private LazyCANSparkMax motor_LEFT;
    private LazyCANSparkMax motor_RIGHT;

    private double output, error, pastError;
    private double targetVelocity = 0.0;

    private Shooter(){
        config();
    }
    public static Shooter getShooter(){
        return shooter;
    }
    private void config(){
        motor_LEFT = new LazyCANSparkMax(Constants.SHOOTER_MOTOR_ID, MotorType.kBrushless);
        motor_RIGHT = new LazyCANSparkMax(Constants.SHOOTER_MOTOR_1_ID, MotorType.kBrushless);
        Log.info("[Shooter]", "Motors config");
    }
    public double getRPM() {
        return motor_LEFT.getEncoder().getVelocity();
    }

    public void setTarget(double velocity) {
        targetVelocity = velocity;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        error =  targetVelocity - getRPM();
        output = error * Constants.K_SHOOTER_P;
        
        Log.info("[shooter]","error: " + error + "output: " + output);
    }

}