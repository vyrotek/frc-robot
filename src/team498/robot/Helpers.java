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
	
}
