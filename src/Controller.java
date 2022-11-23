import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {

    @FXML
    private ToggleGroup answers;

    @FXML
    private Text question_text;

    @FXML
    private RadioButton radio_btn_1;

    @FXML
    private RadioButton radio_btn_2;

    @FXML
    private RadioButton radio_btn_3;

    @FXML
    private RadioButton radio_btn_4;

    @FXML
    private Button answerBtn;

    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox checkBox2;
    @FXML
    private CheckBox checkBox3;
    @FXML
    private CheckBox checkBox4;

    private Questions[] questionsJava = new Questions[] {
            new Questions("В каком из вариантов представлен корректный формат вывода информации на экран?", new String[] {"Console.Write()", "console.log()", "print()", "System.out.println()"}),
            new Questions("Какой тип данных отвечает за целые числа?", new String[] {"String", "Float", "Boolean", "Integer"}),
            new Questions("Где правильно присвоено новое значение к многомерному массиву?", new String[] {"a(0)(0) = 1;", "a[0 0] = 1;", "a{0}{0} = 1;", "a[0][0] = 1;"}),
            new Questions("Какой метод позволяет запустить программу на Java?", new String[] {"Основного метода нет", "С класса, что был написан первым и с методов что есть внутри него", "Любой, его можно задавать в настройках проекта", "С метода main в любом из классов"}),
            new Questions("Каждый файл должен называется...", new String[] {"по имени первой библиотеки в нём", "по имени названия пакета", "как вам захочется", "по имени класса в нём"}),
            new Questions("Сколько параметров может принимать функция?", new String[] {"-2", "-10", "20", "неограниченное количество"})
    };

    private Questions[] questionsСPP = new Questions[] {
            new Questions("Сколько аргументов можно передать в функцию?", new String[] {"До 10","Не более 50","До 5","Неограниченное количество"}),
            new Questions("Как подключить стандартную библиотеку iostream?", new String[] {"#include “iostream.h”","#include iostream","#include <iostream.h>","#include <iostream>"}),
            new Questions("Как указать комментарий?", new String[] {"# здесь комментарий","/* здесь комментарий","/* здесь комментарий /*","// здесь комментарий"}),
            new Questions("Сколько параметров можно передать в деструктор?", new String[] {"Не более 10","Не более 15","Максимум 1","Нельзя передавать параметры в деструктор"}),
            new Questions("Где правильно указана переменная?", new String[] {"var str = \"Hi\";","float x = 32,14;","char sym = 'a';","int num = 1;"})
    };

    private int nowQuestion = 0, correctAnswers;
    private String nowCorrectAnswer;

    @FXML
    public void initialize() {
        Test(questionsJava);
    }

    private boolean checkBox;

    public void Test(Questions[] questions){
        correctAnswers = 0;
        nowQuestion = 0;

        radio_btn_1.setVisible(true);
        radio_btn_2.setVisible(true);
        radio_btn_3.setVisible(true);
        radio_btn_4.setVisible(true);
        answerBtn.setVisible(true);

        nowCorrectAnswer = questions[nowQuestion].correctAnswer();

        question_text.setText(questions[nowQuestion].getQuestion());
        String[] answersString = questions[nowQuestion].getAnswers();

        List<String> intList1 = Arrays.asList(answersString);

        Collections.shuffle(intList1);

        radio_btn_1.setText(intList1.get(0));
        radio_btn_2.setText(intList1.get(1));
        radio_btn_3.setText(intList1.get(2));
        radio_btn_4.setText(intList1.get(3));


        answerBtn.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();

            if(selectedRadioButton != null || checkBox) {
                checkBox = false;

                if(nowQuestion + 1 == questions.length){
                    int correct = 0; int selected = 0;
                    String[] answers = questions[nowQuestion].getAnswers();

                    if(checkBox1.isSelected()){
                        selected++;
                        if(checkBox1.getText().equals(questions[nowQuestion].correctAnswerCheckBox2()) || checkBox1.getText().equals(questions[nowQuestion].correctAnswerCheckBox1()))
                            correct++;
                    }

                    if(checkBox2.isSelected()){
                        selected++;
                        if(checkBox2.getText().equals(questions[nowQuestion].correctAnswerCheckBox2()) || checkBox2.getText().equals(questions[nowQuestion].correctAnswerCheckBox1()))
                            correct++;
                    }

                    if(checkBox3.isSelected()){
                        selected++;
                        if(checkBox3.getText().equals(questions[nowQuestion].correctAnswerCheckBox2()) || checkBox3.getText().equals(questions[nowQuestion].correctAnswerCheckBox1()))
                            correct++;
                    }

                    if(checkBox4.isSelected()){
                        selected++;
                        if(checkBox4.getText().equals(questions[nowQuestion].correctAnswerCheckBox2()) || checkBox4.getText().equals(questions[nowQuestion].correctAnswerCheckBox1()))
                            correct++;
                    }

                    if(correct == 2 && correct == selected){ //чтобы если были выбраны правильные ответы + неправильные, не засчитывало балл
                        correctAnswers++; System.out.println("Верный ответ");
                    }

                }else{
                    String toogleGroupValue = selectedRadioButton.getText(); //проверка для радиокнопак
                    if(toogleGroupValue.equals(nowCorrectAnswer)) {
                        System.out.println("Верный ответ");
                        correctAnswers++;
                    } else {
                        System.out.println("Неверный ответ");
                    }

                }
                // Это был последний вопрос
                if(nowQuestion + 1 == questions.length) {
                    radio_btn_1.setVisible(false);
                    radio_btn_2.setVisible(false);
                    radio_btn_3.setVisible(false);
                    radio_btn_4.setVisible(false);

                    answerBtn.setVisible(false);

                    checkBox1.setVisible(false);
                    checkBox2.setVisible(false);
                    checkBox3.setVisible(false);
                    checkBox4.setVisible(false);

                    correctAnswers++;
                    question_text.setText("Вы получили " + correctAnswers + " баллов, ответив на " + questions.length + " вопросов!");
                } else if(nowQuestion + 2 == questions.length){ //checkboxes
                    checkBox = true;
                    nowQuestion++;
                    loadCheckBox(questions);
                    selectedRadioButton.setSelected(false);
                }
                else {
                    nowQuestion++;
                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();

                    question_text.setText(questions[nowQuestion].getQuestion());
                    String[] answers = new String[4];
                    answers[0] = questions[nowQuestion].getAnswers()[0];
                    answers[1] = questions[nowQuestion].getAnswers()[1];
                    answers[2] = questions[nowQuestion].getAnswers()[2];
                    answers[3] = questions[nowQuestion].getAnswers()[3];


                    List<String> intList = Arrays.asList(answers);

                    Collections.shuffle(intList);

                    radio_btn_1.setText(intList.get(0));
                    radio_btn_2.setText(intList.get(1));
                    radio_btn_3.setText(intList.get(2));
                    radio_btn_4.setText(intList.get(3));

                    selectedRadioButton.setSelected(false);
                }
            }
        });

    }

    public void loadCheckBox(Questions[] questions){
        radio_btn_1.setVisible(false); // отключаются все радиокнопки
        radio_btn_2.setVisible(false);
        radio_btn_3.setVisible(false);
        radio_btn_4.setVisible(false);

        checkBox1.setVisible(true); //чекбоксы
        checkBox2.setVisible(true);
        checkBox3.setVisible(true);
        checkBox4.setVisible(true);

        checkBox1.setSelected(false); //снять галочки
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
        checkBox4.setSelected(false);

        question_text.setText(questions[nowQuestion].getQuestion());
        String[] answers = new String[4];
        answers[0] = questions[nowQuestion].getAnswers()[0]; //для того чтобы был создан новый массив, а не ссылка
        answers[1] = questions[nowQuestion].getAnswers()[1];
        answers[2] = questions[nowQuestion].getAnswers()[2];
        answers[3] = questions[nowQuestion].getAnswers()[3];

        List<String> intList = Arrays.asList(answers);

        Collections.shuffle(intList);

        checkBox1.setText(intList.get(0)); //текст в чекбоксы
        checkBox2.setText(intList.get(1));
        checkBox3.setText(intList.get(2));
        checkBox4.setText(intList.get(3));
    }

    public void onClickCSharp()
    {
        Test(questionsСPP);
    }

    public void onClickJava()
    {
        Test(questionsJava);
    }
}
