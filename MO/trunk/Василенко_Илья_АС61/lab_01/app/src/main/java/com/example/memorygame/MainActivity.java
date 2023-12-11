package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Массив с идентификаторами ресурсов изображений для карточек
    int[] images = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4,
            R.drawable.image_5, R.drawable.image_6, R.drawable.image_7, R.drawable.image_8};

    // Массив с случайными числами от 0 до 7 для определения положения карточек на игровом поле
    int[] cards = new int[16];

    // Переменные для хранения индексов открытых карточек
    int firstCard = -1;
    int secondCard = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем массив с числами от 0 до 7 и продублируем его, чтобы получить массив с 16 элементами
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7};
        System.arraycopy(numbers, 0, cards, 0, numbers.length);
        System.arraycopy(numbers, 0, cards, numbers.length, numbers.length);
        // Перемешиваем массив карточек, меняя местами случайные элементы
        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    // Метод для переворачивания карточки по нажатию на нее
    public void flipCard(View view) {
        // Получаем идентификатор объекта
        int id = view.getId();
        // Получаем индекс объекта в GridLayout по его идентификатору
        int index = -1;
        // Используем оператор if-else вместо switch для сравнения идентификаторов
        if (id == R.id.card_0) {
            index = 0;
        } else if (id == R.id.card_1) {
            index = 1;
        } else if (id == R.id.card_2) {
            index = 2;
        } else if (id == R.id.card_3) {
            index = 3;
        } else if (id == R.id.card_4) {
            index = 4;
        } else if (id == R.id.card_5) {
            index = 5;
        } else if (id == R.id.card_6) {
            index = 6;
        } else if (id == R.id.card_7) {
            index = 7;
        } else if (id == R.id.card_8) {
            index = 8;
        } else if (id == R.id.card_9) {
            index = 9;
        } else if (id == R.id.card_10) {
            index = 10;
        } else if (id == R.id.card_11) {
            index = 11;
        } else if (id == R.id.card_12) {
            index = 12;
        } else if (id == R.id.card_13) {
            index = 13;
        } else if (id == R.id.card_14) {
            index = 14;
        } else if (id == R.id.card_15) {
            index = 15;
        }

        // Проверяем, что индекс корректный и карточка еще не открыта
        if (index != -1 && firstCard != index && secondCard != index) {
            // Устанавливаем фоновое изображение объекта по значению в массиве карточек
            view.setBackgroundResource(images[cards[index]]);
            // Проверяем, открыта ли уже первая карточка
            if (firstCard == -1) {
                // Если нет, то запоминаем индекс текущей карточки как первую
                firstCard = index;
            } else {
                // Если да, то запоминаем индекс текущей карточки как вторую
                secondCard = index;
                // Сравниваем значения в массиве карточек для первой и второй карточки
                if (cards[firstCard] == cards[secondCard]) {
                    // Если они совпадают, то удаляем обе карточки с игрового поля
                    // Для этого находим объекты по индексам в GridLayout и устанавливаем им невидимость
                    GridLayout gridLayout = findViewById(R.id.grid_layout);
                    // Создаем объект Handler и запускаем задержанный код с помощью метода postDelayed
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // В этом коде устанавливаем невидимость для обеих карточек
                            gridLayout.getChildAt(firstCard).setVisibility(View.INVISIBLE);
                            gridLayout.getChildAt(secondCard).setVisibility(View.INVISIBLE);
                            // Сбрасываем значения переменных для первой и второй карточки
                            firstCard = -1;
                            secondCard = -1;
                        }
                    }, 500); // Задержка в миллисекундах, например 2000 для двух секунд
                } else {
                    // Если они не совпадают, то переворачиваем обе карточки обратно
                    // Для этого создаем объект Handler и запускаем задержанный код с помощью метода postDelayed
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // В этом коде находим объекты по индексам в GridLayout и устанавливаем им фоновое изображение по умолчанию
                            GridLayout gridLayout = findViewById(R.id.grid_layout);
                            gridLayout.getChildAt(firstCard).setBackgroundResource(R.drawable.card_back);
                            gridLayout.getChildAt(secondCard).setBackgroundResource(R.drawable.card_back);
                            // Сбрасываем значения переменных для первой и второй карточки
                            firstCard = -1;
                            secondCard = -1;
                        }
                    }, 500); // Задержка в миллисекундах, например 1000 для одной секунды
                }
            }
        }
    }

    // Метод для перезапуска игры по нажатию на кнопку
    public void restartGame(View view) {
        // Перемешиваем массив карточек, генерируя новые случайные числа
        Random random = new Random();
        for (int i = 0; i < cards.length; i++) {
            cards[i] = random.nextInt(8);
        }
        // Сбрасываем значения переменных для первой и второй карточки
        firstCard = -1;
        secondCard = -1;
        // Восстанавливаем видимость и фоновое изображение всех карточек на игровом поле
        GridLayout gridLayout = findViewById(R.id.grid_layout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setVisibility(View.VISIBLE);
            imageView.setBackgroundResource(R.drawable.card_back);
        }
    }
}
