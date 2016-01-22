
package org.usfirst.frc.team3933.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;



import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	 private RobotDrive drive;
	 private Joystick j1;
	    private RobotTimer timer;
	    private Talon talon1;
	    private Talon talon2;
	    private Talon talon3;
	    private Talon talon4;
	    private AnalogInput ultrasonic;
	    private Compressor compresora;
	    private Solenoid solenoide;
	    private DoubleSolenoid Double;
	    private DoubleSolenoid Double1;
	   
	    
	    private int state;
	    private long counter;
}
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	   drive = new RobotDrive(0,1);
    	     timer = new RobotTimer();
    	     ultrasonic = new AnalogInput(0);
    	     talon1 = new Talon(4); 
    	     talon2 = new Talon(5);
    	     talon3 = new Talon(6);
    	     talon4 = new Talon(7);
    	     solenoide = new Solenoid(0);
    	     compresora = new Compressor(0);
    	     Double = new DoubleSolenoid(0,1);
    	     Double1 = new DoubleSolenoid(2,3);

    	     j1= new Joystick(0);
    	     
    	     counter = 0;
    	     state = 0;
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	
		 timer = new RobotTimer();
	        state = 0;
	        counter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	  timer.Update();
    	     counter+=timer.getMillisecondsPerFrame();
    	     
    	     if(state==0){
    	      talon3.set(-1);
    	      talon4.set(-1);
    	      if(counter>=1900){
    	       talon3.set(0);
    	       talon4.set(0);
    	       counter=0;
    	       state++;
    	      }
    	     }
    	     if(state==1){
    	      drive.drive(.5, 0);
    	      if(counter>=2400){
    	       drive.drive(0, 0);
    	       counter=0;
    	       state++;
    	      }
    	     }
    	     /*if(state==1){
    	      talon3.set(1);
    	      talon4.set(1);
    	      if(counter>=2000){
    	       talon3.set(0);
    	       talon4.set(0);
    	       counter=0;
    	       state++;
    	      }
    	     }
    	     if(state==2){
    	      drive.drive(.5, 0);
    	      if(counter>=100){
    	       drive.drive(0, 0);
    	       counter=0;
    	       state++;
    	      }
    	     }
    	     
    	    
    	    }
    /**
     * This function is called periodically during operator control
     */
    	     public void teleopInit()
    	     {
    	     }
    	     
    	     public void teleopPeriodic() {
    	      boolean fwd = j1.getRawButton(1); // boton A
    	      boolean bwd = j1.getRawButton(4); // boton Y
    	      
    	      boolean fwd1 = j1.getRawButton(2); // boton B
    	      boolean bwd1 = j1.getRawButton(3); // boton X
    	      
    	      boolean fwd2 = j1.getRawButton(6); // RB
    	      boolean bwd2 = j1.getRawButton(5); // LB
    	      
    	      /*if(fwd == bwd) {
    	       talon1.set(0);
    	       talon2.set(0);
    	      } else if(fwd) {
    	       talon1.set(1);
    	       talon2.set(1);
    	      } else {
    	       talon1.set(-1);
    	       talon2.set(-1);
    	      }
    	      if(fwd1 == bwd1) {
      talon3.set(0);
      talon4.set(0);
     } else if(fwd1) {
      talon3.set(1);
      talon4.set(1);
     } else {
      talon3.set(-1);
      talon4.set(-1);
     }
     
     if(fwd2){
      talon1.set(-.7);
      talon2.set(-.7);
     }
     
     if(bwd2){
      talon3.set(.7);
      talon4.set(.7);
     }
     
    /**
     * This function is called periodically during test mode
     */
    	      if(bwd){ //cilindro de simple efecto
    	          Double.set(DoubleSolenoid.Value.kForward);
    	          Double1.set(DoubleSolenoid.Value.kForward);
    	         }
    	         if(fwd){
    	          Double.set(DoubleSolenoid.Value.kReverse);
    	          Double1.set(DoubleSolenoid.Value.kReverse);
    	         }
    	         drive.arcadeDrive(-Util.deadCentre(0.125, j1.getRawAxis(1)*.8, 1), -Util.deadCentre(0.125, j1.getRawAxis(0)*.8, 1));
    	         SmartDashboard.putNumber("Ultrasonic", ultrasonic.getAverageVoltage()*211.2188);
    	         
    	        }
    	        
    	        /**
    	         * 
    	         * This function is called periodically during test mode
    	         */
    	        public void testPeriodic() {
    	        
    	        }
    	        
    	    }
    public void testPeriodic() {
    
    }
    
}
