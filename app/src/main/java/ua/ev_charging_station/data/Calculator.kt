package ua.ev_charging_station.data

class Calculator {
    fun calculateChargingData(
        batteryCapacity: String,
        currentCharge: String,
        chargerPower: String
    ): Pair<Double, Double> {
        val capacity = batteryCapacity.toDoubleOrNull() ?: 0.0
        val current = currentCharge.toDoubleOrNull() ?: 0.0
        val power = chargerPower.toDoubleOrNull() ?: 0.0

        if (power <= 0.0 || current >= 100.0) return Pair(0.0, 0.0)

        // Необхідна енергія для 100% заряду
        val neededPercent = (100.0 - current) / 100.0
        val neededEnergyKWh = capacity * neededPercent

        // Час зарядки (годин) з урахуванням ККД
        val timeHours = neededEnergyKWh / (power * 0.9)

        // Вартість за тарифом 4.32 грн/кВт·год
        val costGrn = neededEnergyKWh * 4.32

        return Pair(timeHours, costGrn)
    }
}