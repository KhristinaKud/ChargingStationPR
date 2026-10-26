package ua.ev_charging_station.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.ev_charging_station.R
import ua.ev_charging_station.data.Calculator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvChargingApp() {
    //  Статичні режими станції
    var selectedMode by remember { mutableStateOf("DC 50 кВт") }
    var selectedPower by remember { mutableStateOf(50.0) }
    var modeComment by remember { mutableStateOf("Оптимальний вибір для міського ритму") }

    //  Вхідні параметри
    var capacityInput by remember { mutableStateOf("60") }
    var currentChargeInput by remember { mutableStateOf("20") }

    //  Результати
    var resultTime by remember { mutableStateOf(0.0) }
    var resultCost by remember { mutableStateOf(0.0) }
    var isCalculated by remember { mutableStateOf(false) }

    val calculator = remember { Calculator() }

    //  палітра кольорів
    val mainBgColor = Color(0xFFA2DCC0)
    val cardBgColor = Color(0xFF6EA489)
    val inputBgColor = Color(0xFF234734)
    val purpleAccent = Color(0xFFBA89E3)
    val purpleCard = Color(0xFF4A1E6D)
    val textLight = Color(0xFFF1F8F4)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBgColor)
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ev),
                contentDescription = "Зарядна станція Octa",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            // Заголовок
            Text(
                text = " Зарядна Станція EV",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = inputBgColor
            )

            //  Вибір режиму потужності станції
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = cardBgColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Обрано порт: $selectedMode",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = textLight
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Button(
                            onClick = {
                                selectedMode = "AC 7 кВт"
                                selectedPower = 7.0
                                modeComment = "Зарядка для збереження ресурсу АКБ"
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedMode == "AC 7 кВт") purpleAccent else inputBgColor
                            ),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            Text("AC 7 кВт", fontSize = 12.sp, color = textLight)
                        }

                        Button(
                            onClick = {
                                selectedMode = "DC 50 кВт"
                                selectedPower = 50.0
                                modeComment = "Оптимальний вибір для міста"
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedMode == "DC 50 кВт") purpleAccent else inputBgColor
                            ),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            Text("DC 50 кВт", fontSize = 12.sp, color = textLight)
                        }

                        Button(
                            onClick = {
                                selectedMode = "DC 120 кВт"
                                selectedPower = 120.0
                                modeComment = "Максимальна швидкість зарядки на заправці"
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedMode == "DC 120 кВт") purpleAccent else inputBgColor
                            ),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            Text("120 кВт", fontSize = 12.sp, color = textLight)
                        }
                    }

                    Text(
                        text = " $modeComment",
                        fontSize = 13.sp,
                        color = textLight
                    )
                }
            }

            // Форма введення даних
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val textFieldColors = TextFieldDefaults.colors(
                    focusedContainerColor = inputBgColor,
                    unfocusedContainerColor = inputBgColor,
                    focusedIndicatorColor = purpleAccent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = purpleAccent,
                    unfocusedLabelColor = mainBgColor,
                    focusedTextColor = textLight,
                    unfocusedTextColor = textLight
                )

                TextField(
                    value = capacityInput,
                    onValueChange = { capacityInput = it },
                    label = { Text("Ємність батареї (кВт·год)") },
                    shape = RoundedCornerShape(10.dp),
                    colors = textFieldColors,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                TextField(
                    value = currentChargeInput,
                    onValueChange = { currentChargeInput = it },
                    label = { Text("Поточний заряд (%)") },
                    shape = RoundedCornerShape(10.dp),
                    colors = textFieldColors,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Button(
                    onClick = {
                        val (time, cost) = calculator.calculateChargingData(
                            capacityInput,
                            currentChargeInput,
                            selectedPower.toString()
                        )
                        resultTime = time
                        resultCost = cost
                        isCalculated = true
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = purpleAccent,
                        contentColor = textLight
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                ) {
                    Text(
                        text = "Розррахувати",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            //  Результати
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = purpleCard),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Прогноз результатів",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = purpleAccent
                    )

                    HorizontalDivider(color = Color(0x33BA89E3), thickness = 1.dp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Час до 100%:", color = textLight, fontSize = 13.sp)
                        Text(
                            text = if (isCalculated) "${String.format("%.2f", resultTime)} год" else "--",
                            fontWeight = FontWeight.Bold,
                            color = textLight,
                            fontSize = 14.sp
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Орієнтовна вартість:", color = textLight, fontSize = 13.sp)
                        Text(
                            text = if (isCalculated) "${String.format("%.2f", resultCost)} грн" else "--",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFD166),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}