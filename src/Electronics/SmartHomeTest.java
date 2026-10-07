package Electronics;

public class SmartHomeTest {
    public static void main(String[] args) {
        System.out.println("Smart home:Testing");

        LightBulb bulb1 = new LightBulb("Лампа в гостиной", "Philips", 2023, "тёплый белый");
        LightBulb bulb2 = new LightBulb("Лампа в спальне", "Xiaomi", 2024, "холодный белый");

        Thermostat thermostat1 = new Thermostat("Термостат гостиной", "Nest", 2023, 20, 24);
        Thermostat thermostat2 = new Thermostat("Термостат кухни", "Bosch", 2022, 22, 23);

        SmartTV tv1 = new SmartTV("Телевизор в гостиной", "Samsung", 2024, "Discovery");
        SmartTV tv2 = new SmartTV("Телевизор в спальне", "LG", 2023, "Netflix");
        HomeDevice[] allDevices = {bulb1, bulb2, thermostat1, thermostat2, tv1, tv2};

        System.out.println("Информация обо всех устройствах");
        for (HomeDevice d : allDevices) {
            System.out.println("  • " + d.getDeviceInfo());
        }

        System.out.println("Включение устройств");
        bulb1.turnOn();
        bulb2.turnOn();
        thermostat1.turnOn();
        tv1.turnOn();
        System.out.println("  " + bulb1.getName() + ": " + bulb1.getStatus());
        System.out.println("  " + bulb2.getName() + ": " + bulb2.getStatus());
        System.out.println("  " + thermostat1.getName() + ": " + thermostat1.getStatus());
        System.out.println("  " + tv1.getName() + ": " + tv1.getStatus());

        System.out.println("Изменение настроек");
        bulb1.setPower(80);
        bulb2.setPower(30);
        tv1.setPower(65);
        tv1.setCurrentChannel("TVF1");
        thermostat1.setTargetTemperature(26);
        System.out.println("  Яркость " + bulb1.getName() + " = " + bulb1.getPower() + "%");
        System.out.println("  Яркость " + bulb2.getName() + " = " + bulb2.getPower() + "%");
        System.out.println("  Громкость " + tv1.getName() + " = " + tv1.getPower() + "%");
        System.out.println("  Канал " + tv1.getName() + " = " + tv1.getCurrentChannel());

        System.out.println("Статусы устройств");
        System.out.println("  " + bulb1.getName() + ": " + bulb1.getStatus());
        System.out.println("  " + bulb2.getName() + ": " + bulb2.getStatus());
        System.out.println("  " + thermostat1.getName() + ": " + thermostat1.getStatus());
        System.out.println("  " + tv1.getName() + ": " + tv1.getStatus());

        System.out.println("Расчёт потребления и класс энергоэффективности");
        for (HomeDevice d : allDevices) {
            if (d instanceof EnergyEfficient) {
                EnergyEfficient e = (EnergyEfficient) d;
                System.out.printf("  %-25s → %.2f Вт, класс %s%n",
                        d.getName(), e.calculateEnergyConsumption(), e.getEnergyClass());
            }
        }

        System.out.println("Выключение части устройств");
        bulb2.turnOff();
        tv1.turnOff();
        System.out.println("  " + bulb2.getName() + ": " + bulb2.getStatus());
        System.out.println("  " + tv1.getName() + ": " + tv1.getStatus());
        System.out.printf("  Потребление %s после выключения: %.2f Вт%n",
                bulb2.getName(), bulb2.calculateEnergyConsumption());
        System.out.printf("  Потребление %s после выключения: %.2f Вт%n",
                tv1.getName(), tv1.calculateEnergyConsumption());

        System.out.println("Демонстрация полиморфизма");

        System.out.println("Массив Switchable[]");
        Switchable[] switchables = {bulb1, bulb2, thermostat1, thermostat2, tv1, tv2};
        for (Switchable s : switchables) {
            System.out.printf("  Устройство %s: включено = %b%n",
                    ((HomeDevice) s).getName(), s.isOn());
        }

        System.out.println("Массив Controllable[]");
        Controllable[] controllables = {bulb1, bulb2, thermostat1, thermostat2, tv1, tv2};
        for (Controllable c : controllables) {
            System.out.printf("  %-25s → мощность = %d, статус: %s%n",
                    ((HomeDevice) c).getName(), c.getPower(), c.getStatus());
        }

        System.out.println("Массив EnergyEfficient[]");
        EnergyEfficient[] energyDevices = {bulb1, bulb2, tv1, tv2};
        for (EnergyEfficient e : energyDevices) {
            System.out.printf("  %-25s → %.2f Вт, класс %s%n",
                    ((HomeDevice) e).getName(),
                    e.calculateEnergyConsumption(),
                    e.getEnergyClass());
        }

        System.out.println("ИТОГОВАЯ СТАТИСТИКА");
        double totalConsumption = 0;
        int activeCount = 0;
        for (HomeDevice d : allDevices) {
            if (d instanceof EnergyEfficient) {
                totalConsumption += ((EnergyEfficient) d).calculateEnergyConsumption();
            }
            if (d instanceof Switchable && ((Switchable) d).isOn()) {
                activeCount++;
            }
        }
        System.out.printf("  Активных устройств: %d из %d%n", activeCount, allDevices.length);
        System.out.printf("  Общее потребление: %.2f Вт%n", totalConsumption);
    }
}






