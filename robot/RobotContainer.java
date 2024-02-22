// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Climb;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.GoLaunch;
import frc.robot.commands.ReverseWrist;
import frc.robot.commands.TakeIn;
import frc.robot.commands.Autonomi.OneNote_noMove;
import frc.robot.commands.Autonomi.TwoNote_2;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.WristIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static SendableChooser<Command> autoChooser;

  private final Drivetrain m_Drivetrain = new Drivetrain();
private Drive drivecommand = new Drive(m_Drivetrain);

  private final Launcher m_Launcher = new Launcher();
private GoLaunch launchcommand = new GoLaunch(m_Launcher);

  private final Intake m_Intake = new Intake();
private TakeIn intakecommand = new TakeIn(m_Intake);

  private final WristIntake m_WristIntake = new WristIntake();
private ReverseWrist wristintakecommand = new ReverseWrist(m_WristIntake);

private final Climber m_Climber = new Climber();
private Climb climbcommand = new Climb(m_Climber);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final static CommandJoystick m_LjoystickController =
      new CommandJoystick(OperatorConstants.kljoystickport);
  private final static JoystickButton Ltrigger = new JoystickButton(m_LjoystickController.getHID(), 1);

  private final static CommandJoystick m_RjoystickController =
    new CommandJoystick(OperatorConstants.krjoystickport);
  private final static JoystickButton Rtrigger = new JoystickButton(m_RjoystickController.getHID(), 1);

  private final static XboxController m_XboxController =
    new XboxController(OperatorConstants.kDriverControllerPort);

  private final Command m_oneNote_noMove = new OneNote_noMove(m_Launcher, m_Intake);
  private final Command m_twoNote_2 = new TwoNote_2(m_Launcher, m_Intake, m_Drivetrain, m_WristIntake);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //Autonomous names will have what they do in respective order, the side of the speaker they start on, and either blue or red
    autoChooser = new SendableChooser<Command>();
    autoChooser.setDefaultOption("One Note no Move", m_oneNote_noMove);
    autoChooser.addOption("Two Note_2 (Center)", m_twoNote_2);
    // Configure the trigger bindings
    configureBindings();
    m_Intake.setDefaultCommand(intakecommand);
    m_Launcher.setDefaultCommand(launchcommand);
    m_Drivetrain.setDefaultCommand(drivecommand);
    m_WristIntake.setDefaultCommand(wristintakecommand);
    m_Climber.setDefaultCommand(climbcommand);
  } 

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
  }
  public static double getLeftXboxJoystickY() {
    return m_XboxController.getLeftY();
  }
  public static boolean getLeftXboxBumper() {
    return m_XboxController.getLeftBumper();
  }
  public static boolean getRightXboxBumper() {
    return m_XboxController.getRightBumper();
  }
  public static boolean getXboxBButton() {
    return m_XboxController.getBButton();
  }
  public static boolean getXboxAButton() {
    return m_XboxController.getAButton();
  }
  public static boolean getXboxYButton() {
    return m_XboxController.getYButton();
  }
  public static boolean getXboxXButton() {
    return m_XboxController.getXButton();
  }
  public static boolean getltrigger() {
    return Ltrigger.getAsBoolean();
  }
  public static boolean getrtrigger() {
    return Rtrigger.getAsBoolean();
  }
  public static double getljoystick() {
    return m_LjoystickController.getY();
  }
  public static double getrjoystick() {
    return m_RjoystickController.getY();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
       return autoChooser.getSelected();
  }
}