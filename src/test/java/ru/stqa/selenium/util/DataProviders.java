package ru.stqa.selenium.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {
    @DataProvider
    //здесь берем иноформацию из файла
    public static Iterator<Object[]> dataProviderFirst() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/dataFirst.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }


    @DataProvider
    //здесь указываем разные наборы для проверки входных параметров
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"irina_moroshek@mail.ru", "iae062322"});
        data.add(new Object[]{"irina_moroshek@mail.ru", "data22"});
        //data.add(new Object[]{"irina_moroshek@mail.ru", "iae062322"});

        return data.iterator();
    }


    @DataProvider
    //здесь можно добавить массив, передать значения в цикл, автоматическая генерация значений
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 4; ++i) {
            data.add(new Object[]{this.generateRandomName(), this.generateRandomPassword()});
        }

        return data.iterator();
    }


    private Object generateRandomName() {

        return "demo" + (new Random()).nextInt() + "@gmail.com";
    }

    private Object generateRandomPassword() {

        return "pass" + (new Random()).nextInt();
    }

    @DataProvider
    //здесь берем иноформацию из файла
    public static Iterator<Object[]> dataProviderCreateList() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/createList.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }


    @DataProvider
    //Sel-17
    public static Iterator<Object[]> dataProviderLoginIncorrect() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"", "iae062322", "Missing email"});
        data.add(new Object[]{"irina_moroshekmail.ru", "1234", "There isn't an account for this username"});
        data.add(new Object[]{"i_moroshek@mail.ru", "iu90", "There isn't an account for this email"});

        return data.iterator();
    }

    //Sel-17 информация из файла
    @DataProvider
    public static Iterator<Object[]> dataProviderLoginIncorrectFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginIncorrect.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    //здесь можно добавить массив, передать значения в цикл, автоматическая генерация значений
    public Iterator<Object[]> dataProviderRandomPassword() {
        List<Object[]> data = new ArrayList();

        for (int i = 0; i < 4; ++i) {
            data.add(new Object[]{this.generateRandomName2(), this.generateRandomPassword2()});
        }

        return data.iterator();
    }


    private Object generateRandomName2() {

        return "demo" + (new Random()).nextInt() + "@gmail.com";
    }

    private Object generateRandomPassword2() {

        return "pass" + (new Random()).nextInt();
    }
}


    //генерация названия листа


