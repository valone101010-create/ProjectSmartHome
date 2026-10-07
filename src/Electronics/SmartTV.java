package Electronics;


class SmartTV extends HomeDevice implements Switchable, Controllable, EnergyEfficient {
    private boolean isOn;
    private int volume; // 0-100
    private String currentChannel;

    public SmartTV(String name, String manufacturer, int year, String currentChannel) {
        super(name, manufacturer, year);
        this.currentChannel = currentChannel;
        this.volume = 0;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        if (volume == 0) volume = 20;
    }

    @Override
    public void turnOff() {
        isOn = false;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void setPower(int power) {
        if (power < 0 || power > 100) {
            System.out.println("Ошибка: громкость должна быть 0-100%");
            return;
        }
        this.volume = power;
    }

    @Override
    public int getPower() {
        return volume;
    }

    @Override
    public String getStatus() {
        if (isOn) {
            return String.format("Включен, канал %s, громкость %d%%",
                    currentChannel, volume);
        }
        return "Выключен";
    }

    @Override
    public double calculateEnergyConsumption() {
        if (!isOn) return 0.0;
        return volume * 0.5 + 10;

    }

    @Override
    public String getEnergyClass() {
        if (volume <= 30) return "A";
        if (volume <= 70) return "B";
        return "C";
    }

    @Override
    public String getDeviceInfo() {
        return String.format("Умный телевизор '%s' от %s (%d г.)",
                name, manufacturer, year);
    }

    public String getCurrentChannel() {
        return currentChannel;
    }

    public void setCurrentChannel(String currentChannel) {
        this.currentChannel = currentChannel;
    }
}
