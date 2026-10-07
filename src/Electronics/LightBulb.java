package Electronics;


public class LightBulb extends HomeDevice implements Switchable, Controllable, EnergyEfficient {
    private boolean isOnState;
    private int brightness;
    private String color;

    public LightBulb(String name, String manufacturer, int year, String color) {
        super(name, manufacturer, year);
        this.color = color;
        this.brightness = 0;
    }
        @Override
        public void turnOn () {
            isOnState = true;
            if (brightness == 0) brightness = 50;
        }

        @Override
        public void turnOff () {
            isOnState = false;
        }

        @Override
        public boolean isOn () {
            return isOnState;
        }

        @Override
        public void setPower ( int power){
            if (power < 0 || power > 100) {
                System.out.println("Error:brightness is required 0-100%");
                return;
            }
            this.brightness = power;
        }

        @Override
        public int getPower () {
            return brightness;
        }


        @Override
        public String getStatus () {
            if (isOnState) {
                return String.format("On,%d,%s,brightness,color" );
            }
            return "Off";
        }

        @Override
        public double calculateEnergyConsumption () {
            if (!isOnState) return 0.0;
            return brightness * 0.1;
        }

        @Override
        public String getEnergyClass () {
            return brightness <= 50 ? "A" : "B";
        }

        @Override
        public String getDeviceInfo () {
            return String.format("LightBulb '%s' от %s (%d г.), color: %s",
                    name, manufacturer, year, color);
        }

        public String getColor () {
            return color;
        }
        public void setColor (String color){
            this.color = color;
        }

    }





