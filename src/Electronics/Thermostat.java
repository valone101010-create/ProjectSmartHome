package Electronics;

 class Thermostat extends HomeDevice implements Switchable,Controllable {
    private boolean isOn;
    private int temperature;
    private int targetTemperature;


     public Thermostat(String name, String manufacturer, int year,
                       int temperature, int targetTemperature) {
         super(name, manufacturer, year);
         this.temperature = temperature;
         this.targetTemperature = targetTemperature;
         this.isOn = false;
     }

     @Override
     public void turnOn() { isOn = true; }

     @Override
     public void turnOff() { isOn = false; }

     @Override
     public boolean isOn() { return isOn; }

     @Override
     public void setPower(int power) {
         if (power < 0 || power > 100) {
             System.out.println("Ошибка: значение должно быть 0-100");
             return;
         }
         this.targetTemperature = temperature + power / 5;
     }

     @Override
     public int getPower() {
         int diff = Math.abs(targetTemperature - temperature);
         return Math.min(diff, 100);
     }

     @Override
     public String getStatus() {
         if (isOn) {
             return String.format("Работает, текущая %d°C, целевая %d°C",
                     temperature, targetTemperature);
         }
         return "Выключен";
     }
     @Override
     public String getDeviceInfo() {
         return String.format("Термостат '%s' от %s (%d г.)",
                 name, manufacturer, year);
     }

     public int getTemperature() { return temperature; }
     public void setTemperature(int temperature) { this.temperature = temperature; }

     public int getTargetTemperature() { return targetTemperature; }
     public void setTargetTemperature(int targetTemperature) {
         this.targetTemperature = targetTemperature;
     }
 }
