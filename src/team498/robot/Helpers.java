package team498.robot;

public class Helpers {
    
    /**
    * Restrict a value using a min and max constraint.
    * @param value The value to evaluate and restrict.
    * @param min The inclusive minimum possible value.
    * @param max The inclusive maximum possible value.
    * @return New value restricted within the specified min and max.
    */
    public static double range(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }
    
    /**
    * Restrict a value within the range of -1 to 1
    * @param value The value to evaluate and restrict.
    * @return New value restricted within the range of -1 to 1.
    */
    public static double range(double value) {
        return range(value, -1, 1);
    }
    
    /**
    * Check how close the value is to 0 compared to the allowed tolerance
    * @param value The value to evaluate and restrict.
    * @return New value restricted within the range.
    */
    public static double normalize(double value, double tolerance) {
        if (Math.abs(value) <= tolerance) {
            return 0;
        }
        return value;        
    }
    
    /**
    * Calculate a bot rotate value based on current and target angle.  
    * @param currentAngle The current angle of the bot.
    * @param targetAngle The desired angle of the bot.
    * @param tolerance The amount of tolerance allowed in the difference between the target and current angle.
    * @param gain The proportional gain amount applied to the angle calculation.
    * @return The rotate value to apply to the bot drivetrain.
    */
    public static double rotateToTarget(double currentAngle, double targetAngle, double tolerance, double gain) { 
        double difference = normalize(currentAngle - targetAngle, tolerance);         
        return -range((difference * gain), -1, 1);
    }
	
}
